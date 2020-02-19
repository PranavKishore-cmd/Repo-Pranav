package com.payment.xborder.model.credentials;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;

/**
 * Create password model Request class
 * 
 * @author Yashwanth
 *
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResetPassword {

	@NotBlank
	private String userEmailId;

	@NotBlank
	private String passwordResetToken;

	@NotBlank
	private String newPassword;
	
	@NotBlank
	private String confirmPassword;

	/**
	 * @return the userEmailId
	 */
	public final String getUserEmailId() {
		return userEmailId;
	}

	/**
	 * @param userEmailId the userEmailId to set
	 */
	public final void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}

	/**
	 * @return the passwordResetToken
	 */
	public final String getPasswordResetToken() {
		return passwordResetToken;
	}

	/**
	 * @param passwordResetToken the passwordResetToken to set
	 */
	public final void setPasswordResetToken(String passwordResetToken) {
		this.passwordResetToken = passwordResetToken;
	}

	/**
	 * @return the newPassword
	 */
	public final String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword the newPassword to set
	 */
	public final void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	/**
	 * @return the newPassword
	 */
	public final String getConfirmPassword() {
		return confirmPassword;
	}
	/**
	 * @param newPassword the newPassword to set
	 */
	public final void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
