package com.payment.xborder.ws.onboard;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.payment.xborder.enums.UserStatus;
import com.payment.xborder.exception.BusinessException;
import com.payment.xborder.exception.ValidationException;
import com.payment.xborder.model.onboard.PlatformCODashboardCount;
import com.payment.xborder.model.onboard.User;
import com.payment.xborder.model.onboard.ws.AuthKeyResponse;
import com.payment.xborder.model.onboard.ws.BaseResponse;
import com.payment.xborder.model.onboard.ws.LoginRequest;
import com.payment.xborder.model.onboard.ws.LoginResponse;
import com.payment.xborder.model.onboard.ws.LogoutRequest;
import com.payment.xborder.model.onboard.ws.PasswordResetRequest;
import com.payment.xborder.model.onboard.ws.PasswordResetResponse;
import com.payment.xborder.model.onboard.ws.TwoFactAuthRequest;
import com.payment.xborder.model.onboard.ws.TwoFactAuthResponse;
import com.payment.xborder.model.onboard.ws.UserDetails;
import com.payment.xborder.model.onboard.ws.UserRequest;
import com.payment.xborder.model.onboard.ws.UserUpdateRequest;
import com.payment.xborder.service.onboard.RegistrationService;
import com.payment.xborder.service.onboard.UserService;
import com.payment.xborder.util.converter.onboard.UserConverter;
import com.payment.xborder.ws.AbstractRestController;

@RestController
@RequestMapping("users")
public class UserController extends AbstractRestController {

	@Autowired
	UserService userService;
	
	@Autowired
	RegistrationService registrationService;

	@PostMapping("/register")
	public ResponseEntity<BaseResponse> registerUser(@Valid @NotNull @RequestBody UserRequest userRequest) {
		try {
			userService.createUser(UserConverter.wsToModel(userRequest));
			return baseResponse("User saved successfully" + ".", HttpStatus.CREATED);
		} catch (BusinessException be) {
			return baseResponse(be.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("")
	public ResponseEntity<BaseResponse> addUser(@Valid @NotNull @RequestBody UserRequest userRequest) {
		try {
			userService.addUser(UserConverter.wsToModelAddUser(userRequest));
			return baseResponse("User added successfully" + ".", HttpStatus.CREATED);
		} catch (ValidationException | BusinessException e) {
			HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
					: HttpStatus.valueOf(505);
			return baseResponse(e.getMessage(), httpStatus);
		}

	}

	@GetMapping("/user")
	public ResponseEntity<User> getUser(@RequestParam(value = "email") String email) {
		return ResponseEntity.ok().body(userService.getUser(UserConverter.modelToWs(email)));
	}

	@PutMapping("/user")
	public ResponseEntity<BaseResponse> updateUser(@Valid @NotNull @RequestBody UserUpdateRequest userRequest) {
      try {
         userService.updateUser(UserConverter.wsToModelUpdateUser(userRequest));
         return baseResponse("User details updated " + "successfully.", HttpStatus.OK);
      } catch (BusinessException be) {
         return baseResponse(be.getMessage(), HttpStatus.BAD_REQUEST);
      }
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> userLogin(@Valid @NotNull @RequestBody LoginRequest loginRequest) {
		LoginResponse loginResponse = userService.userLogin(loginRequest);
		if (loginResponse.getSessionId().trim().isEmpty()) {
			return new ResponseEntity<>(loginResponse, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(loginResponse, HttpStatus.CREATED);
	}

	@PutMapping("/logout")
	public ResponseEntity<String> userLogout(@Valid @NotNull @RequestBody LogoutRequest logoutRequest) {
		userService.userLogOut(logoutRequest.getEmail());
		return new ResponseEntity<>("User logged out successfully", HttpStatus.ACCEPTED);
	}

	@PostMapping("/validateSession")
	public ResponseEntity<String> validateSession(@Valid @NotNull @RequestBody LogoutRequest logoutRequest) {
		boolean isValid = userService.validateSession(logoutRequest.getEmail());
		if (isValid) {
			return new ResponseEntity<>("Session valid", HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>("Invalid session", HttpStatus.ACCEPTED);
	}

	@PostMapping("/enable2fa")
	public ResponseEntity<TwoFactAuthResponse> enableTwoFactorAuthentication(
			@Valid @NotNull @RequestBody TwoFactAuthRequest twoFactAuthRequest) {
		User user = userService.getUser(UserConverter.modelToWs(twoFactAuthRequest.getEmail()));
		if (!user.getGAuthState()) {
			TwoFactAuthResponse tfaAuthResponse = userService.enableTwoFactorAuthentication(twoFactAuthRequest);
			return new ResponseEntity<>(tfaAuthResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/disable2fa")
	public ResponseEntity<TwoFactAuthResponse> disableTwoFactorAuthentication(
			@Valid @NotNull @RequestBody TwoFactAuthRequest twoFactAuthRequest) {
		User user = userService.getUser(UserConverter.modelToWs(twoFactAuthRequest.getEmail()));
		if (user.getGAuthState()) {
			TwoFactAuthResponse tfaAuthResponse = userService.disableTwoFactorAuthentication(twoFactAuthRequest);
			return new ResponseEntity<>(tfaAuthResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/changePassword")
	public ResponseEntity<PasswordResetResponse> changePassword(
			@Valid @NotNull @RequestBody PasswordResetRequest passwordResetRequest) {
		PasswordResetResponse passwordResetResponse = userService
				.resetPassword(UserConverter.wsToModelNewPassword(passwordResetRequest));
		if (passwordResetResponse.isResetStatus()) {
			return new ResponseEntity<>(passwordResetResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(passwordResetResponse, HttpStatus.NOT_ACCEPTABLE);
		}

	}

	@PostMapping("/check2fa")
	public ResponseEntity<TwoFactAuthResponse> checkTwoFactorAuthentication(
			@Valid @NotNull @RequestBody TwoFactAuthRequest twoFactAuthRequest) {
		User user = userService.getUser(UserConverter.modelToWs(twoFactAuthRequest.getEmail()));
		TwoFactAuthResponse tfaAuthResponse = userService.checkTwoFactorAuthentication(twoFactAuthRequest, user);
		return new ResponseEntity<>(tfaAuthResponse, HttpStatus.OK);
	}

	@GetMapping("/getAuthKeyAndQRCode/{email}")
	public ResponseEntity<AuthKeyResponse> getUserAuthKeyAndQRCode(@Valid @NotNull @PathVariable String email) {

		AuthKeyResponse authKeyResponse = userService.getUserAuthKeyAndQRCode(email);

		return new ResponseEntity<>(authKeyResponse, HttpStatus.OK);
	}

	@GetMapping("/findUsers/{searchKey}")
	public ResponseEntity<List<UserDetails>> findUsers(@PathVariable String searchKey) {
		return ResponseEntity.ok().body(userService.findUsers(searchKey));

	}

	@GetMapping("/findUsersByRole/{roleName}")
	public ResponseEntity<List<UserDetails>> findUsersByRole(@PathVariable String roleName) {
		return ResponseEntity.ok().body(userService.findUsersByRole(roleName));

	}

	@GetMapping("/findUsersByStatus/{regStatus}")
	public ResponseEntity<List<UserDetails>> findUsersByStatus(@PathVariable String regStatus) {
		return ResponseEntity.ok().body(userService.findUsersByStatus(regStatus));

	}

	@GetMapping("/getAllUsers")
	public ResponseEntity<List<UserDetails>> getAllUsers() {
		return ResponseEntity.ok().body(userService.getAllUsers());
	}

   @GetMapping("/getUsersByCompanyId/{companyRefID}")
   public ResponseEntity<List<UserDetails>> getUsersByCompanyId(@PathVariable String companyRefID) {
      return ResponseEntity.ok().body(userService.getUsersByCompanyRefID(companyRefID));
   }

   @PostMapping("/changeUserStatus/{userEmail}/{userStatus}")
   public ResponseEntity<BaseResponse> changeUserStatus(@PathVariable String userEmail, @PathVariable UserStatus userStatus) {
      try {
         userService.changeUserStatus(userEmail,
                                      userStatus);
         return baseResponse("User Status changed" + ".",
                             HttpStatus.CREATED);
      } catch (ValidationException | BusinessException e) {
         HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
                                                                    : HttpStatus.valueOf(505);
         return baseResponse(e.getMessage(), httpStatus);
      }

   }
   
   @GetMapping("/getPlatformCODashboardCount/{email}")
   public ResponseEntity<PlatformCODashboardCount> getPlatformCODashboardCount(@Valid @NotNull @PathVariable String email) {
	   return ResponseEntity.ok().body(registrationService.getPlatformCODashboardCount(email));
   }

}
