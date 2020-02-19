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
public class ResetPasswordLink {

	@NotBlank
	private String userEmailId;

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

}
