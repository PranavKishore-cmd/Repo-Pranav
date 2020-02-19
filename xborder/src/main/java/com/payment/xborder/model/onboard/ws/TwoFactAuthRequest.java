package com.payment.xborder.model.onboard.ws;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.payment.xborder.enums.RegisterUserType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TwoFactAuthRequest
{

	@NotBlank
	private String email;

	private String password;

	@NotBlank
	private String authenticationCode;

	public TwoFactAuthRequest() {
	}

	public TwoFactAuthRequest(
			@NotBlank String email,
			String password,
			@NotBlank String authenticationCode
	)
	{
		this.email = email;
		this.password = password;
		this.authenticationCode = authenticationCode;
	}

	public TwoFactAuthRequest(
			@NotBlank String email,
			@NotBlank String authenticationCode
	)
	{
		this.email = email;
		this.authenticationCode = authenticationCode;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getAuthenticationCode()
	{
		return authenticationCode;
	}

	public void setAuthenticationCode(String authenticationCode)
	{
		this.authenticationCode = authenticationCode;
	}
}
