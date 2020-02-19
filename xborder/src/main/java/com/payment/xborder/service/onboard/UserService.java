package com.payment.xborder.service.onboard;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.thymeleaf.context.Context;

import com.google.zxing.WriterException;
import com.payment.xborder.configuration.ApplicationProperties;
import com.payment.xborder.dao.onboard.LoginDao;
import com.payment.xborder.dao.onboard.UserDao;
import com.payment.xborder.enums.UserStatus;
import com.payment.xborder.exception.BusinessException;
import com.payment.xborder.exception.ErrorEnumeration;
import com.payment.xborder.exception.ValidationException;
import com.payment.xborder.model.onboard.User;
import com.payment.xborder.model.onboard.UserSession;
import com.payment.xborder.model.onboard.ws.AuthKeyResponse;
import com.payment.xborder.model.onboard.ws.LoginRequest;
import com.payment.xborder.model.onboard.ws.LoginResponse;
import com.payment.xborder.model.onboard.ws.PasswordResetRequest;
import com.payment.xborder.model.onboard.ws.PasswordResetResponse;
import com.payment.xborder.model.onboard.ws.TwoFactAuthRequest;
import com.payment.xborder.model.onboard.ws.TwoFactAuthResponse;
import com.payment.xborder.model.onboard.ws.UserDetails;
import com.payment.xborder.service.gauth.TwoFactorAuthentication;
import com.payment.xborder.service.notification.EmailService;
import com.payment.xborder.service.notification.MailContentBuilderService;
import com.payment.xborder.service.notification.NotificationEnumeration;
import com.payment.xborder.service.roles.RoleService;

@Service
public class UserService {

	@Autowired
	UserDao userDao;

	@Autowired
	LoginDao loginDao;

	@Autowired
	RoleService roleService;

	@Autowired
	EmailService emailService;

	@Autowired
	MailContentBuilderService mailContentBuilderService;
	
	@Autowired
	ApplicationProperties applicationProperties;

	public void createUser(User user) {
		User userDtls = userDao.getUserDetails(user.getEmail());
		if (userDtls == null) {
			// Check if the role assigned is present in system
			if (roleService.getRoleByRoleId(user.getRoleId()) == null) {
				throw new BusinessException("Invalid Role ID passed");
			}
			userDao.createUser(user);
		} else {
			throw new BusinessException("Email id already exists");
		}
	}

	public void addUser(User user) {
		User userDtls = userDao.getUserDetails(user.getEmail());
		if (userDtls == null) {
			
			if (roleService.getRoleByRoleId(user.getRoleId()) == null) {
				throw new ValidationException(ErrorEnumeration.INVALID_ROLE);
			}
			
			user.setPasswordResetToken(UUID.randomUUID().toString());
			user.setTokenExpiryTime(LocalDateTime.now().plusHours(24));
			userDao.createUser(user);
			
			Context context = new Context();
			context.setVariable("firstName", user.getFirstName());
			context.setVariable("lastName", user.getLastName());
			context.setVariable("userId", user.getEmail());
			context.setVariable("passwordResetToken", user.getPasswordResetToken());
			// context.setVariable("companyName",
			// companyService.readActiveCompany(user.getCompanyRef()).getCompanyName());
			context.setVariable("url",applicationProperties.getFrontendUrl());
			emailService.sendEmailAsync(user.getEmail(), NotificationEnumeration.ADD_USER, context);
		} else {
			throw new ValidationException(ErrorEnumeration.EMAIL_ID_ALREADY_EXISTS);
		}
	}
	
	public User getUser(String userEmail) {
		User userDtls = userDao.getUserDetails(userEmail);
		if (userDtls != null) {
			userDtls.setPassword("");
			userDtls.setAuthKey("");
		}
		return userDtls;
	}

	public boolean getUserAuth(boolean state) {
		return state;
	}

	public User updateUser(User user) {
		return userDao.updateUserDetails(user);
	}

	public LoginResponse userLogin(@Valid @NotNull LoginRequest loginRequest) {
		LoginResponse loginResponse = new LoginResponse();
		User userDetails = userDao.getUserDetails(loginRequest.getEmail());
		if (userDetails == null) {
			loginResponse.setMessage("User not found.");
			loginResponse.setSessionId("");
			return loginResponse;
			// throw new BusinessException("No user found");
		}

		if (userDetails.getEmail().equals(loginRequest.getEmail())
				&& userDetails.getPassword().equals(loginRequest.getPassword())) {
			String requestSessionID = RequestContextHolder.currentRequestAttributes().getSessionId();
			UserSession sessionDetails = loginDao.getSession(requestSessionID);
			if (sessionDetails == null) {
				UserSession userSession = new UserSession();
				userSession.setEmail(loginRequest.getEmail());
				userSession.setSessionId(requestSessionID);
				userSession.setLastAccessTime(Calendar.getInstance().getTimeInMillis());
				loginDao.createSession(userSession);

				loginResponse.setMessage("Login Successful");
				loginResponse.setSessionId(userSession.getSessionId());
				loginResponse.setgAuthEnabled(userDetails.getGAuthState());
			} else {
				// Remove existing session and add new
				UserSession userSession = new UserSession();
				userSession.setEmail(loginRequest.getEmail());
				userSession.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());
				userSession.setLastAccessTime(Calendar.getInstance().getTimeInMillis());
				loginDao.updateSession(userSession);

				loginResponse.setMessage("Login Successful");
				loginResponse.setSessionId(userSession.getSessionId());
				loginResponse.setgAuthEnabled(userDetails.getGAuthState());
			}
		} else {
			loginResponse.setMessage("Login Failed. Check credentials");
			loginResponse.setSessionId("");
		}
		return loginResponse;
	}

	public void userLogOut(String emailId) {
		String requestSessionID = RequestContextHolder.currentRequestAttributes().getSessionId();
		UserSession sessionDeatils = loginDao.getSession(requestSessionID);
		if (sessionDeatils == null || !sessionDeatils.getEmail().equals(emailId)) {
			throw new BusinessException("User is not not logged in.");
		}
		System.out.println("Session removed for User:" + emailId + ", Session rmoved:" + requestSessionID);
		loginDao.removeSession(sessionDeatils.getSessionId());
	}

	public boolean validateSession(String email) {
		String requestSessionID = RequestContextHolder.currentRequestAttributes().getSessionId();
		UserSession sessionDeatils = loginDao.getSession(requestSessionID);
		if (sessionDeatils == null) {
			throw new BusinessException("Session invalid for " + email);
		}
		if (Calendar.getInstance().getTimeInMillis() - sessionDeatils.getLastAccessTime() > 600000) {
			// loginDao.removeSession(requestSessionID);
			throw new BusinessException("Session has expired. Login again");
		}
		if (!sessionDeatils.getEmail().equals(email)) {
			throw new BusinessException("No valid session for " + email);
		}
		return true;
	}

	public TwoFactAuthResponse enableTwoFactorAuthentication(@Valid @NotNull TwoFactAuthRequest twoFactAuthRequest) {
		User userDetails = userDao.getUserDetails(twoFactAuthRequest.getEmail());
		if (ObjectUtils.isEmpty(userDetails)) {
			throw new BusinessException("No user found");
		}

		if (twoFactAuthRequest.getPassword() == null || twoFactAuthRequest.getPassword().isEmpty()) {
			throw new BusinessException("Password cannot be empty");
		}

		if (userDetails.getAuthKey() == null || userDetails.getAuthKey().isEmpty()) {
			throw new BusinessException("Auth Key not set");
		}

		if (userDetails.getGAuthState()) {
			throw new BusinessException("Two Factor Authentication is already " + "enabled for user");
		}

		TwoFactorAuthentication tfa = new TwoFactorAuthentication();
		boolean passwordValid = userDetails.getPassword().contentEquals(twoFactAuthRequest.getPassword());

		if (!passwordValid) {
			throw new BusinessException("Unauthorised access: Invalid password");
		}

		boolean totpValid = tfa.performAuthentication(twoFactAuthRequest.getAuthenticationCode(),
				userDetails.getAuthKey());

		if (!totpValid) {
			throw new BusinessException("Authorization Code didn't match");

		}

		if (passwordValid && totpValid) {
			// Enable in DB
			userDetails.setGAuthState(true);
			userDao.updateUserGAuthState(userDetails);
		}

		return new TwoFactAuthResponse(userDetails.getEmail(), passwordValid, totpValid);
	}

	public TwoFactAuthResponse disableTwoFactorAuthentication(@Valid @NotNull TwoFactAuthRequest twoFactAuthRequest) {
		User userDetails = userDao.getUserDetails(twoFactAuthRequest.getEmail());
		if (userDetails == null) {
			throw new BusinessException("No user found");
		}

		if (userDetails.getAuthKey() == null || userDetails.getAuthKey().isEmpty()) {
			throw new BusinessException("Auth Key not set");
		}

		if (!userDetails.getGAuthState()) {
			throw new BusinessException("Two Factor Authentication is already " + "disabled for user");
		}

		TwoFactorAuthentication tfa = new TwoFactorAuthentication();

		boolean totpValid = tfa.performAuthentication(twoFactAuthRequest.getAuthenticationCode(),
				userDetails.getAuthKey());

		if (totpValid == false) {
			throw new BusinessException("Authorization Code didn't match");
		}

		// Enable in DB
		userDetails.setGAuthState(false);
		userDao.updateUserGAuthState(userDetails);

		return new TwoFactAuthResponse(userDetails.getEmail(), totpValid);
	}

	public TwoFactAuthResponse checkTwoFactorAuthentication(TwoFactAuthRequest twoFactAuthRequest, User user) {
		User userDetails = userDao.getUserDetails(twoFactAuthRequest.getEmail());
		if (userDetails == null) {
			throw new BusinessException("No user found");
		}

		if (!userDetails.getGAuthState()) {
			throw new BusinessException(
					"Unsupported operation: Two Factor " + "Authentication is not enabled for " + "user.");
		}

		TwoFactorAuthentication tfa = new TwoFactorAuthentication();
		boolean isTotpValid = tfa.performAuthentication(twoFactAuthRequest.getAuthenticationCode(),
				userDetails.getAuthKey());

		if (twoFactAuthRequest.getPassword() != null && !twoFactAuthRequest.getPassword().isEmpty()) {
			boolean isPasswordValid = user.getPassword().contentEquals(twoFactAuthRequest.getPassword());
			return new TwoFactAuthResponse(userDetails.getEmail(), isPasswordValid, isTotpValid);
		} else {
			return new TwoFactAuthResponse(userDetails.getEmail(), isTotpValid);
		}

	}

	public AuthKeyResponse getUserAuthKeyAndQRCode(String userEmailId) {
		User userDetails = userDao.getUserDetails(userEmailId);
		if (userDetails == null) {
			throw new BusinessException("No user found");
		}
		/*
		 * if (!userDetails.getGAuthState()) { throw new
		 * BusinessException("Unsupported operation: Two Factor " +
		 * "Authentication is not enabled for " + "user."); }
		 */

		AuthKeyResponse authKeyResponse = null;
		try {
			TwoFactorAuthentication tfa = new TwoFactorAuthentication();
			authKeyResponse = tfa.qrCodeGeneration(userDetails.getEmail(), userDetails.getAuthKey());
			System.out.println(authKeyResponse);
		} catch (URISyntaxException | WriterException | IOException e) {
			e.printStackTrace();
		}
		return authKeyResponse;
	}

	public PasswordResetResponse resetPassword(PasswordResetRequest passwordResetRequest) {

		User userDetails = userDao.getUserDetails(passwordResetRequest.getEmail());
		if (userDetails == null) {
			throw new BusinessException("No user found");
		}

		PasswordResetResponse passwordResetResponse = new PasswordResetResponse();

		try {
			if (!userDetails.getPassword().contentEquals(passwordResetRequest.getPassword())) {
				passwordResetResponse.setMessage("Incorrect Password");
				passwordResetResponse.setResetStatus(false);
				passwordResetResponse.setEmail(userDetails.getEmail());
			} else if (userDetails.getPassword().contentEquals(passwordResetRequest.getPassword())
					&& (userDetails.getPassword().contentEquals(passwordResetRequest.getNewPassword()))) {
				passwordResetResponse.setMessage("New Password Cannot be Current Password");
				passwordResetResponse.setResetStatus(false);
				passwordResetResponse.setEmail(userDetails.getEmail());
			} else if ((userDetails.getPassword().contentEquals(passwordResetRequest.getPassword()))
					&& (!passwordResetRequest.getNewPassword()
							.contentEquals(passwordResetRequest.getReEnteredNewPassword()))) {
				passwordResetResponse.setMessage("Password Re-entry Mismatch");
				passwordResetResponse.setResetStatus(false);
				passwordResetResponse.setEmail(userDetails.getEmail());
			}

			else if ((userDetails.getPassword().contentEquals(passwordResetRequest.getPassword()))
					&& (passwordResetRequest.getNewPassword()
							.contentEquals(passwordResetRequest.getReEnteredNewPassword()))) {
				userDao.changePassword(userDetails, passwordResetRequest.getNewPassword());
				passwordResetResponse.setMessage("Password Reset Successful");
				passwordResetResponse.setResetStatus(true);
				passwordResetResponse.setEmail(userDetails.getEmail());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return passwordResetResponse;
	}

	public List<UserDetails> getAllUsers() {
		List<User> userList = userDao.findAll();
		if (!userList.isEmpty()) {
			List<UserDetails> userDetailsList = userList.stream().map(p -> new UserDetails(p))
					.collect(Collectors.toList());
			return userDetailsList;
		}
		return Collections.EMPTY_LIST;
	}

   public List<UserDetails> getUsersByCompanyRefID(String companyRefID) {
      List<User> userList = userDao.findUsersByKeyValue("companyRefId", companyRefID);
      if (!userList.isEmpty()) {
         List<UserDetails> userDetailsList = userList.stream().map(p -> new UserDetails(p))
                                                     .collect(Collectors.toList());
         return userDetailsList;
      }
      return Collections.EMPTY_LIST;
   }

	public List<UserDetails> findUsers(String searchKey) {
		if (searchKey == null || searchKey.trim().isEmpty()) {
			return getAllUsers();
		}

		List<User> userList = userDao.findUsers(searchKey.trim());
		if (!userList.isEmpty()) {
			List<UserDetails> userDetailsList = userList.stream().map(p -> new UserDetails(p))
					.collect(Collectors.toList());
			return userDetailsList;
		}
		return Collections.EMPTY_LIST;

	}

	public List<UserDetails> findUsersByRole(String roleName) {
		if (roleName == null || roleName.trim().isEmpty()) {
			return Collections.EMPTY_LIST;
		}

		List<User> userList = userDao.findUsersByKeyValue("roleId", roleName.trim());
		if (!userList.isEmpty()) {
			List<UserDetails> userDetailsList = userList.stream().map(p -> new UserDetails(p))
					.collect(Collectors.toList());
			return userDetailsList;
		}
		return Collections.EMPTY_LIST;

	}

	public List<UserDetails> findUsersByStatus(String regStatus) {
		if (regStatus == null || regStatus.trim().isEmpty()) {
			return Collections.EMPTY_LIST;
		}

		List<User> userList = userDao.findUsersByKeyValue("userStatus", regStatus.trim());
		if (!userList.isEmpty()) {
			List<UserDetails> userDetailsList = userList.stream().map(p -> new UserDetails(p))
					.collect(Collectors.toList());
			return userDetailsList;
		}
		return Collections.EMPTY_LIST;
	}

   public void changeUserStatus(String emailID, UserStatus userStatus)
   {
      if(Arrays.stream(UserStatus.values()).anyMatch(status -> status.equals(userStatus))){
         User userDtls = userDao.getUserDetails(emailID);
         if (userDtls == null) {
            throw new BusinessException("No user details found");
         } else {
            if(userDtls.getUserStatus().equals(UserStatus.INACTIVE))
                throw new BusinessException("Invalid action! User is already "
                                            + "disabled.");
            if(!userDtls.getUserStatus().equals(userStatus)){
               userDtls.setUserStatus(userStatus);
               userDao.updateUserStatus(userDtls);
            }
         }
      }
      else{
         throw new BusinessException("Invalid status");
      }
   }
}
