package com.payment.xborder.model.onboard;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.payment.xborder.enums.RegisterUserType;
import com.payment.xborder.enums.UserStatus;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Document(collection = "USER")
public class User {

	@Id
	private String userId;

	@NotBlank
	@TextIndexed
	private String firstName;

	private String middleName;

	@NotBlank
	@TextIndexed
	private String lastName;

	@NotNull
	private RegisterUserType registerUserType;

	@NotBlank
	@TextIndexed
	private String email;

	@NotBlank
	private String password;

	private String countryCode;

	private String telephoneNumber;

	@NotNull
	private UserStatus userStatus;

	@NotNull
	private LocalDate requestDate;

	private String passwordResetToken;
	private LocalDateTime tokenExpiryTime;

	private String companyRefId;

	private String roleId;

	private String gAuthKey;

	private boolean gAuthEnabled;

	public User() {
	}

	public User(@NotBlank String firstName, String middleName, @NotBlank String lastName,
			@NotNull RegisterUserType registerUserType, @NotBlank String email, @NotBlank String password,
			@NotBlank String countryCode, @NotBlank String telephoneNumber, @NotNull UserStatus userStatus,
			@NotNull LocalDate requestDate, String companyRefId, String roleId) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.registerUserType = registerUserType;
		this.email = email;
		this.password = password;
		this.countryCode = countryCode;
		this.telephoneNumber = telephoneNumber;
		this.userStatus = userStatus;
		this.requestDate = requestDate;
		this.companyRefId = companyRefId;
		this.roleId = roleId;
	}

	/**
	 * @return the tokenExpiryTime
	 */
	public LocalDateTime getTokenExpiryTime() {
		return tokenExpiryTime;
	}

	/**
	 * @param tokenExpiryTime the tokenExpiryTime to set
	 */
	public void setTokenExpiryTime(LocalDateTime tokenExpiryTime) {
		this.tokenExpiryTime = tokenExpiryTime;
	}

	public String getPasswordResetToken() {
		return passwordResetToken;
	}

	public void setPasswordResetToken(String passwordResetToken) {
		this.passwordResetToken = passwordResetToken;
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

	public RegisterUserType getRegisterUserType() {
		return registerUserType;
	}

	public void setRegisterUserType(RegisterUserType registerUserType) {
		this.registerUserType = registerUserType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public LocalDate getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(LocalDate requestDate) {
		this.requestDate = requestDate;
	}

	public String getCompanyRefId() {
		return companyRefId;
	}

	public void setCompanyRefId(String companyRefId) {
		this.companyRefId = companyRefId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public void setAuthKey(String key) {
		this.gAuthKey = key;
	}

	public String getAuthKey() {
		return gAuthKey;
	}

	public void setGAuthState(boolean state) {
		this.gAuthEnabled = state;
	}

	public boolean getGAuthState() {
		return gAuthEnabled;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "User{" + "userId='" + userId + '\'' + ", firstName='" + firstName + '\'' + ", middleName='" + middleName
				+ '\'' + ", lastName='" + lastName + '\'' + ", registerUserType=" + registerUserType + ", email='"
				+ email + '\'' + ", password='" + password + '\'' + ", countryCode='" + countryCode + '\''
				+ ", telephoneNumber='" + telephoneNumber + '\'' + ", userStatus=" + userStatus + ", requestDate="
				+ requestDate + ", companyRefId='" + companyRefId + '\'' + ", role ID =" + roleId + '}';
	}

}
