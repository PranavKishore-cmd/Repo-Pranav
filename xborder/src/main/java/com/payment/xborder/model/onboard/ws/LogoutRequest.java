package com.payment.xborder.model.onboard.ws;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LogoutRequest {

	@NotBlank
	private String email;

	public LogoutRequest() {
	}

	public LogoutRequest(@NotBlank String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
