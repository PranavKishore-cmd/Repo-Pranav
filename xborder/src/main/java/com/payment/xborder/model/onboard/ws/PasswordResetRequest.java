package com.payment.xborder.model.onboard.ws;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PasswordResetRequest
{

	@NotBlank
	private String email;

	@NotBlank
	private String password;

	@NotBlank
	private String newPassword;
	
	@NotBlank
	private String reEnterNewPassword;
	

	public PasswordResetRequest() {
	}

	public PasswordResetRequest(
			
			@NotBlank String email,
			@NotBlank String password,
			@NotBlank String newPassword,
			@NotBlank String reEnterNewPassword
	)
	{
		this.email = email;
		this.password = password;
		this.newPassword = newPassword;
		this.reEnterNewPassword = reEnterNewPassword;
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
	
	public String getNewPassword()
	{
		return newPassword;
	}
	
	public void setNewPassword(String newPassword)
	{
		this.newPassword = newPassword;
	}
	
	public String getReEnteredNewPassword()
	{
		return reEnterNewPassword;
	}
	
	public void setReEnteredNewPassword(String reEnterNewPassword)
	{
		this.reEnterNewPassword = reEnterNewPassword;
	}

}
