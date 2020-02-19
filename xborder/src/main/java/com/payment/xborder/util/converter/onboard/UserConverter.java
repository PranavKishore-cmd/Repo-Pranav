package com.payment.xborder.util.converter.onboard;

import com.amazonaws.services.datapipeline.model.ValidationError;
import com.payment.xborder.enums.UserStatus;
import com.payment.xborder.errornotifications.ErrorNotification;
import com.payment.xborder.exception.ValidationException;
import com.payment.xborder.model.onboard.User;
import com.payment.xborder.model.onboard.ws.PasswordResetRequest;
import com.payment.xborder.model.onboard.ws.UserRequest;
import com.payment.xborder.model.onboard.ws.UserUpdateRequest;
import com.payment.xborder.service.gauth.TwoFactorAuthentication;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;

public class UserConverter {

	public static User wsToModel(UserRequest userRequest) {
		User user = new User();
		final GoogleAuthenticator gAuth = new GoogleAuthenticator();
		final GoogleAuthenticatorKey googleAuthkey = gAuth.createCredentials();
		ErrorNotification note = validate(userRequest);
		if (!note.hasError()) {
			user.setFirstName(userRequest.getFirstName());
			user.setLastName(userRequest.getLastName());
			user.setMiddleName(userRequest.getMiddleName());
			user.setRegisterUserType(userRequest.getRegisterUserType());
			user.setEmail(userRequest.getEmail());
			user.setPassword(userRequest.getPassword());
			user.setCountryCode(userRequest.getCountryCode());
			user.setTelephoneNumber(userRequest.getTelephoneNumber());
			user.setUserStatus(UserStatus.PENDING_ACTIVATION);
			user.setRoleId(userRequest.getRole());
			user.setUserStatus(userRequest.getUserStatus());
			user.setAuthKey(googleAuthkey.getKey());
			user.setGAuthState(false);
			user.setRequestDate(LocalDate.now());

			return user;
		} else {
			throw new IllegalArgumentException(note.errorMessage());
		}

	}

	public static User wsToModelAddUser(UserRequest userRequest) {
		User user = new User();
		final GoogleAuthenticator gAuth = new GoogleAuthenticator();
		final GoogleAuthenticatorKey googleAuthkey = gAuth.createCredentials();
		ErrorNotification note = validate(userRequest);
		if (!note.hasError()) {
			user.setFirstName(userRequest.getFirstName());
			user.setLastName(userRequest.getLastName());
			user.setMiddleName(userRequest.getMiddleName());
			user.setRegisterUserType(userRequest.getRegisterUserType());
			user.setEmail(userRequest.getEmail());
			user.setPassword(userRequest.getPassword());
			user.setTelephoneNumber(userRequest.getTelephoneNumber());
			user.setCompanyRefId(userRequest.getCompanyRefId());
			user.setUserStatus(UserStatus.PENDING_ACTIVATION);
			user.setRoleId(userRequest.getRole());
			user.setUserStatus(userRequest.getUserStatus());
			user.setAuthKey(googleAuthkey.getKey());
			user.setGAuthState(false);
			user.setRequestDate(LocalDate.now());
			return user;
		} else {
			throw new ValidationException(note.errorMessage());
		}

	}

	public static User wsToModelUpdateUser(UserUpdateRequest userRequest) {
		User storedUser = new User();
		ErrorNotification note = new ErrorNotification();
		try {
			validatedFirstName(userRequest.getFirstName());
		} catch (Exception e) {
			note.addError(e.getMessage(), e);
		}

		try {
			validatedLastName(userRequest.getLastName());
		} catch (Exception e) {
			note.addError(e.getMessage(), e);
		}

		try {
			validatedEmail(userRequest.getEmail());
		} catch (Exception e) {
			note.addError(e.getMessage(), e);
		}

		if (!note.hasError()) {
			storedUser.setEmail(userRequest.getEmail());
			storedUser.setFirstName(userRequest.getFirstName());
			storedUser.setLastName(userRequest.getLastName());
			storedUser.setMiddleName(userRequest.getMiddleName());
			storedUser.setCountryCode(userRequest.getCountryCode());
			storedUser.setTelephoneNumber(userRequest.getTelephoneNumber());
			storedUser.setRoleId(userRequest.getRoleId());
			return storedUser;
		} else {
			throw new IllegalArgumentException(note.errorMessage());
		}
	}

	public static String modelToWs(String email) {
		return email;
	}

	public static User modelToWsAuthState(String totp, User user) {
		TwoFactorAuthentication tfa = new TwoFactorAuthentication();
		boolean state = tfa.performAuthentication(totp, user.getAuthKey());
		if ((!user.getGAuthState()) && state) {
			user.setGAuthState(state);
		}
		return user;
	}

	public static boolean authenticateUser(String totp, User user, String password) {
		TwoFactorAuthentication tfa = new TwoFactorAuthentication();
		Boolean totp_state = tfa.performAuthentication(totp, user.getAuthKey());
		Boolean password_state = user.getPassword().contentEquals(password);
		return (totp_state && password_state);
	}

	public static PasswordResetRequest wsToModelNewPassword(PasswordResetRequest passwordResetRequest) {

		ErrorNotification note = validateNewPassword(passwordResetRequest);
		if (!note.hasError()) {
			passwordResetRequest.setNewPassword(passwordResetRequest.getNewPassword());
			return passwordResetRequest;
		} else {
			throw new IllegalArgumentException(note.errorMessage());
		}

	}

	private static void validatedFirstName(String firstName) {

		Pattern p = Pattern.compile("[^A-Za-z0-9]");
		Matcher m = p.matcher(firstName);
		boolean b = m.find();
		if ((!firstName.matches("[a-zA-Z_]+")) || (b == true)) {
			throw new IllegalArgumentException("invalidFirstName");
		}
	}

	private static void validatedLastName(String lastName) {

		Pattern p = Pattern.compile("[^A-Za-z0-9]");
		Matcher m = p.matcher(lastName);
		boolean b = m.find();
		if ((!lastName.matches("[a-zA-Z_]+")) || (b == true)) {
			throw new IllegalArgumentException("invalidLastName");
		}
	}

	private static void validatedMiddleName(String midName) {

		Pattern p = Pattern.compile("[^A-Za-z0-9]");
		Matcher m = p.matcher(midName);
		boolean b = m.find();
		if ((!midName.matches("[a-zA-Z_]+")) || (b == true)) {
			throw new IllegalArgumentException("invalidMiddleName");
		}
	}

	private static void validatedEmail(String eMail) {

		final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
				Pattern.CASE_INSENSITIVE);
		Matcher m = VALID_EMAIL_ADDRESS_REGEX.matcher(eMail);
		boolean b = m.find();
		if (b == false) {
			throw new IllegalArgumentException("invalidemailpattern");
		}
	}

	private static void validatedPassword(String password) {

		String pattern = "(must contains one digit from 0-9, " + "must contains one lowercase characters, "
				+ "must contains one uppercase characters, "
				+ "must contains one special symbols in the list \"@#$%\", "
				+ "match anything with previous condition checking, "
				+ "length at least 6 characters and maximum of 20) ";

		final Pattern PASSWORD_PATTERN = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})");
		Matcher m = PASSWORD_PATTERN.matcher(password);
		boolean b = m.matches();
		if (b == false) {
			throw new IllegalArgumentException(pattern);
		}
	}

	private static ErrorNotification validate(UserRequest userRequest) {
		ErrorNotification note = new ErrorNotification();
		try {
			validatedFirstName(userRequest.getFirstName());
		} catch (Exception e) {
			note.addError(e.getMessage(), e);
		}

		try {
			validatedLastName(userRequest.getLastName());
		} catch (Exception e) {
			note.addError(e.getMessage(), e);
		}

		/*
		 * try { validatedMiddleName(userRequest.getMiddleName()); }catch (Exception e)
		 * { note.addError(e.getMessage(), e); }
		 */

		try {
			validatedEmail(userRequest.getEmail());
		} catch (Exception e) {
			note.addError(e.getMessage(), e);
		}

		try {
			validatedPassword(userRequest.getPassword());
		} catch (Exception e) {
			note.addError("Password pattern should consist of : " + e.getMessage(), e);
		}

		return note;
	}

	private static ErrorNotification validateNewPassword(PasswordResetRequest passwordResetRequest) {
		ErrorNotification note = new ErrorNotification();

		try {
			validatedEmail(passwordResetRequest.getEmail());
		} catch (Exception e) {
			note.addError(e.getMessage(), e);
		}

		try {
			validatedPassword(passwordResetRequest.getNewPassword());
		} catch (Exception e) {
			note.addError("New password pattern should consist of : " + e.getMessage(), e);
		}

		return note;
	}

}
