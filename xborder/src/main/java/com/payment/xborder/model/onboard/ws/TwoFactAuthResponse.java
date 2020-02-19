package com.payment.xborder.model.onboard.ws;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TwoFactAuthResponse
{

	@NotBlank
	private String email;

	private boolean passwordValid;

	@NotBlank
	private boolean authentionCodeValid;

	public TwoFactAuthResponse() {
	}

	public TwoFactAuthResponse(
			@NotBlank String email,
			@NotBlank boolean authentionCodeValid
	)
	{
		this.email = email;
		this.authentionCodeValid = authentionCodeValid;
	}
	
	public TwoFactAuthResponse(
			@NotBlank String email,
			boolean passwordValid,
			@NotBlank boolean authentionCodeValid
	)
	{
		this.email = email;
		this.passwordValid = passwordValid;
		this.authentionCodeValid = authentionCodeValid;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public boolean isPasswordValid()
	{
		return passwordValid;
	}

	public void setPasswordValid(boolean passwordValid)
	{
		this.passwordValid = passwordValid;
	}

	public boolean isAuthentionCodeValid()
	{
		return authentionCodeValid;
	}

	public void setAuthentionCodeValid(boolean authentionCodeValid)
	{
		this.authentionCodeValid = authentionCodeValid;
	}
}
