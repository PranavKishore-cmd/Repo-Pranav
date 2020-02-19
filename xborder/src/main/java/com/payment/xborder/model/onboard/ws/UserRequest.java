package com.payment.xborder.model.onboard.ws;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.payment.xborder.enums.RegisterUserType;
import com.payment.xborder.enums.UserStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserRequest {

	@NotBlank
	private String firstName;

	private String middleName;

	@NotBlank
	private String lastName;

	@NotNull
	private RegisterUserType registerUserType;

	@NotBlank
	private String email;

	@NotBlank
	private String password;

	
	private String countryCode;

	@NotBlank
	private String telephoneNumber;

	@NotNull
	private UserStatus userStatus;

	private String companyRefId;

	private String roleId;

	public UserRequest() {
	}

	public UserRequest(
			@NotBlank String firstName,
			String middleName,
			@NotBlank String lastName,
			@NotNull RegisterUserType registerUserType,
			@NotBlank String email,
			@NotBlank String password,
			@NotBlank String countryCode,
			@NotBlank String telephoneNumber,
			@NotNull UserStatus userStatus,
			String companyRefId,
			String role
	)
	{
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.registerUserType = registerUserType;
		this.email = email;
		this.password = password;
		this.countryCode = countryCode;
		this.telephoneNumber = telephoneNumber;
		this.userStatus = userStatus;
		this.companyRefId = companyRefId;
		this.roleId = role;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getMiddleName()
	{
		return middleName;
	}

	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public RegisterUserType getRegisterUserType()
	{
		return registerUserType;
	}

	public void setRegisterUserType(RegisterUserType registerUserType)
	{
		this.registerUserType = registerUserType;
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

	public String getCountryCode()
	{
		return countryCode;
	}

	public void setCountryCode(String countryCode)
	{
		this.countryCode = countryCode;
	}

	public String getTelephoneNumber()
	{
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber)
	{
		this.telephoneNumber = telephoneNumber;
	}

	public UserStatus getUserStatus()
	{
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus)
	{
		this.userStatus = userStatus;
	}

	public String getCompanyRefId()
	{
		return companyRefId;
	}

	public void setCompanyRefId(String companyRefId)
	{
		this.companyRefId = companyRefId;
	}

	public String getRole()
	{
		return roleId;
	}

	public void setRole(String roleId)
	{
		this.roleId = roleId;
	}

	@Override
	public String toString()
	{
		return "UserRequest{" +
				 "firstName='" + firstName + '\'' +
				 ", middleName='" + middleName + '\'' +
				 ", lastName='" + lastName + '\'' +
				 ", registerUserType=" + registerUserType +
				 ", email='" + email + '\'' +
				 ", password='" + password + '\'' +
				 ", countryCode='" + countryCode + '\'' +
				 ", telephoneNumber='" + telephoneNumber + '\'' +
				 ", userStatus=" + userStatus +
				 ", companyRefId='" + companyRefId + '\'' +
				 ", role=" + roleId +
				 '}';
	}
}
