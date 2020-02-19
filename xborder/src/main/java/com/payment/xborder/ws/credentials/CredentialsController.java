package com.payment.xborder.ws.credentials;

import com.payment.xborder.exception.BusinessException;
import com.payment.xborder.exception.ValidationException;
import com.payment.xborder.model.credentials.ResetPassword;
import com.payment.xborder.model.credentials.ResetPasswordLink;
import com.payment.xborder.model.onboard.ws.BaseResponse;
import com.payment.xborder.service.credentials.CredentialService;
import com.payment.xborder.ws.AbstractRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("credentials")
public class CredentialsController extends AbstractRestController {

	private final CredentialService credentialService;

	public CredentialsController(CredentialService credentialService) {
		this.credentialService = credentialService;
	}

	@PutMapping("/newPassword")
	public ResponseEntity<BaseResponse> createPassword(@Valid @NotNull @RequestBody ResetPassword createPassword) {
		try {
			credentialService.createPassword(createPassword.getUserEmailId(), createPassword.getPasswordResetToken(),
                                          createPassword.getNewPassword(), createPassword.getConfirmPassword());
			return baseResponse("Password created succesfully", HttpStatus.OK);
		} catch (ValidationException | BusinessException e) {
			HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
					: HttpStatus.valueOf(505);
			return baseResponse(e.getMessage(), httpStatus);
		}

	}
	@GetMapping("/requestPasswordReset")
	public ResponseEntity<BaseResponse> requestPasswordReset(@Valid @NotNull ResetPasswordLink resetPasswordLink) {
		try {
			credentialService.sendPasswordResetLink(resetPasswordLink.getUserEmailId());
			return baseResponse("Password reset link sent to emal id successfully", HttpStatus.OK);
		} catch (ValidationException | BusinessException e) {
			HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
					: HttpStatus.valueOf(400);
			return baseResponse(e.getMessage(), httpStatus);
		}

	}
	
	@PutMapping("/resetPassword")
	public ResponseEntity<BaseResponse> resetPassword(@Valid @NotNull @RequestBody ResetPassword resetPassword) {
		try {
			credentialService.resetPassword(resetPassword.getUserEmailId(), resetPassword.getPasswordResetToken(),
					resetPassword.getNewPassword(), resetPassword.getConfirmPassword());
			return baseResponse("Password reset succesfull", HttpStatus.OK);
		} catch (ValidationException | BusinessException e) {
			HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
					: HttpStatus.valueOf(505);
			return baseResponse(e.getMessage(), httpStatus);
		}

	}
}
