package com.payment.xborder.model.onboard.ws;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.payment.xborder.enums.RegisterUserType;
import com.payment.xborder.enums.UserRole;
import com.payment.xborder.enums.UserStatus;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LoginRequest {

	@NotNull
	private RegisterUserType registerUserType;

	@NotBlank
	private String email;

	@NotBlank
	private String password;

	public LoginRequest() {
	}

	public LoginRequest(@NotNull RegisterUserType registerUserType, @NotBlank String email, @NotBlank String password) {
		this.registerUserType = registerUserType;
		this.email = email;
		this.password = password;
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

}
