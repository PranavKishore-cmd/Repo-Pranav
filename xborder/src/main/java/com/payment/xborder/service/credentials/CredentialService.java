package com.payment.xborder.service.credentials;

import java.time.LocalDateTime;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.payment.xborder.configuration.ApplicationProperties;
import com.payment.xborder.dao.onboard.UserDao;
import com.payment.xborder.enums.UserStatus;
import com.payment.xborder.exception.BusinessException;
import com.payment.xborder.exception.ErrorEnumeration;
import com.payment.xborder.exception.ValidationException;
import com.payment.xborder.model.onboard.User;
import com.payment.xborder.service.notification.EmailService;
import com.payment.xborder.service.notification.NotificationEnumeration;
import java.util.Base64;


/**
 * Credential Service class to handle reset, create password.
 * 
 * @author Pradeep
 *
 */
@Service
public class CredentialService {
	
	@Autowired
	ApplicationProperties applicationProperties;

	private final UserDao userDao;
	private final EmailService eMailService;

	public CredentialService(UserDao userDao, EmailService eMailService) {
		this.eMailService = eMailService;
		this.userDao = userDao;
	}

	public void createPassword(String userEmail, String resetToken, String newPassword, String confirmPassword) {
      if (StringUtils.isNotBlank(userEmail) && StringUtils.isNotBlank(resetToken)
          && StringUtils.isNotBlank(newPassword) && StringUtils.isNotBlank(confirmPassword)) {
         User user = userDao.getUserDetails(userEmail);
         validateResetPassword(user, resetToken, newPassword, confirmPassword);
			user.setPasswordResetToken(null);
			user.setTokenExpiryTime(null);
			user.setUserStatus(UserStatus.ACTIVE);
			user.setPassword(newPassword);
			userDao.updateUser(user);
		}
	}
	
	
	public void sendPasswordResetLink(String userEmail) {
		
		if (StringUtils.isNotBlank(userEmail)) {
			User user = userDao.getUserDetails(userEmail);
			if (user != null && user.getUserStatus() == UserStatus.ACTIVE) {
				user.setPasswordResetToken(UUID.randomUUID().toString());
				user.setTokenExpiryTime(LocalDateTime.now().plusHours(1));
				userDao.updateUser(user);
				Context context = new Context();
				context.setVariable("firstName", user.getFirstName());
				context.setVariable("lastName", user.getLastName());
				context.setVariable("userId", user.getEmail());
				context.setVariable("passwordResetToken", user.getPasswordResetToken());
				// context.setVariable("companyName",
				// companyService.readActiveCompany(user.getCompanyRef()).getCompanyName());
				context.setVariable("url",applicationProperties.getFrontendUrl());
				eMailService.sendEmailAsync(userEmail, NotificationEnumeration.RESET_PASSWORD, context);
			}
			else {
				throw new ValidationException(ErrorEnumeration.USER_INACTIVE);
			}
		}else {
			throw new ValidationException(ErrorEnumeration.USER_DOES_NOT_EXIST);
		}
	}
	
	public void resetPassword(String userEmail, String resetToken, String newPassword, String confirmPassword) {
		if (StringUtils.isNotBlank(userEmail) && StringUtils.isNotBlank(resetToken)
				&& StringUtils.isNotBlank(newPassword) && StringUtils.isNotBlank(confirmPassword)) {
			User user = userDao.getUserDetails(userEmail);
			validateResetPassword(user, resetToken, newPassword, confirmPassword);
			user.setPasswordResetToken(null);
			user.setTokenExpiryTime(null);
			user.setPassword(newPassword);
			userDao.updateUser(user);
		}
	}
	
	private void validateResetPassword(User user, String resetToken, String newPassword, String confirmPassword) {
		if (user == null) {
			throw new ValidationException(ErrorEnumeration.USER_DOES_NOT_EXIST.getMessage());
		}
		if (!resetToken.equals(user.getPasswordResetToken())) {
			throw new ValidationException(ErrorEnumeration.INVALID_RESET_TOKEN);
		}
		if (LocalDateTime.now().isAfter(user.getTokenExpiryTime())) {
			user.setPasswordResetToken(null);
			user.setTokenExpiryTime(null);
			throw new ValidationException(ErrorEnumeration.RESET_TOKEN_EXPIRED);
		}
		if (!newPassword.contentEquals(confirmPassword)) {
			throw new ValidationException(ErrorEnumeration.PASSWORD_MISMATCH);
		}
	}

	private void validateCreatePassword(User user, String resetToken) {
		if (user == null) {
			throw new ValidationException(ErrorEnumeration.USER_DOES_NOT_EXIST.getMessage());
		}
		if (UserStatus.ACTIVE.equals(user.getUserStatus())) {
			throw new ValidationException(ErrorEnumeration.USER_ALREADY_ACTIVE.getMessage());
		}
		if (!resetToken.equals(user.getPasswordResetToken())) {
			throw new ValidationException(ErrorEnumeration.INVALID_RESET_TOKEN);
		}
		if (LocalDateTime.now().isAfter(user.getTokenExpiryTime())) {
			user.setPasswordResetToken(null);
			user.setTokenExpiryTime(null);
			user.setUserStatus(UserStatus.PENDING_ACTIVATION);
			throw new ValidationException(ErrorEnumeration.RESET_TOKEN_EXPIRED);
		}
	}

}
