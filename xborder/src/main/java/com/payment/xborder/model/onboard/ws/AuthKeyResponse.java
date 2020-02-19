package com.payment.xborder.model.onboard.ws;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AuthKeyResponse
{

	@NotBlank
	private String email;

	@NotBlank
	private String qrCode;

	private String qrCodeType="base64";

	private String authKeyString;

	public AuthKeyResponse() {
	}

	public AuthKeyResponse(
			@NotBlank String email,
			@NotBlank String qrCode,
			String qrCodeType,
			String authKeyString
	)
	{
		this.email = email;
		this.qrCode = qrCode;
		this.qrCodeType = qrCodeType;
		this.authKeyString = authKeyString;
	}

	public AuthKeyResponse(
			@NotBlank String email,
			@NotBlank String qrCode,
			String authKeyString
	)
	{
		this.email = email;
		this.qrCode = qrCode;
		this.authKeyString = authKeyString;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getQrCode()
	{
		return qrCode;
	}

	public void setQrCode(String qrCode)
	{
		this.qrCode = qrCode;
	}

	public String getQrCodeType()
	{
		return qrCodeType;
	}

	public void setQrCodeType(String qrCodeType)
	{
		this.qrCodeType = qrCodeType;
	}

	public String getAuthKeyString()
	{
		return authKeyString;
	}

	public void setAuthKeyString(String authKeyString)
	{
		this.authKeyString = authKeyString;
	}

	@Override
	public String toString()
	{
		return "AuthKeyResponse{" +
				 "email='" + email + '\'' +
				 ", qrCode='" + qrCode + '\'' +
				 ", qrCodeType='" + qrCodeType + '\'' +
				 ", authKeyString='" + authKeyString + '\'' +
				 '}';
	}
}
