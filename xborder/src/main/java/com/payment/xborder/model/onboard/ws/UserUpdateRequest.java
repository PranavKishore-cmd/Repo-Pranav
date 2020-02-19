package com.payment.xborder.model.onboard.ws;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserUpdateRequest {

	@NotBlank
	private String firstName;

	private String middleName;

	@NotBlank
	private String lastName;

	@NotBlank
	private String email;

	private String countryCode;
	private String telephoneNumber;
	private String roleId;

	
	
	/**
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @param email
	 * @param countryCode
	 * @param telephoneNumber
	 * @param roleId
	 */
	public UserUpdateRequest(@NotBlank String firstName, String middleName, @NotBlank String lastName,
			@NotBlank String email, String countryCode, String telephoneNumber, String roleId) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		this.countryCode = countryCode;
		this.telephoneNumber = telephoneNumber;
		this.roleId = roleId;
	}

	public UserUpdateRequest() {
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * @return the telephoneNumber
	 */
	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	/**
	 * @param telephoneNumber the telephoneNumber to set
	 */
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "UserUpdateRequest{" + "firstName='" + firstName + '\'' + ", middleName='" + middleName + '\''
				+ ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + ", countryCode='" + countryCode + '\'' 
				+ ", telephoneNumber='" + telephoneNumber + '\'' + ", roleId='" + roleId + '\'' +'}';
	}
}
