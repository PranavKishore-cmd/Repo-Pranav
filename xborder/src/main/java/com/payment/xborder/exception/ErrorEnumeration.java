package com.payment.xborder.exception;

public enum ErrorEnumeration {

	// AWS S3 exceptions
	UNABLE_TO_CREATE_DIRECTORY(2001, "UNABLE_TO_CREATE_DIRECTORY"), INVALID_FILE_PATH(2002, "INVALID_FILE_PATH"), ISSUE_WITH_FILE_STORE(2003, "ISSUE_WITH_FILE_STORE"),
	FILE_NOT_FOUND(2004, "FILE_NOT_FOUND"), ISSUE_WHILE_DOWNLOAD(2005, "ISSUE_WHILE_DOWNLOAD"), USER_DOES_NOT_EXIST(1200, "User does not exist"),
	USER_ALREADY_ACTIVE(1201, "User is already Active"), INVALID_RESET_TOKEN(1202, "Invalid password reset token"),
	RESET_TOKEN_EXPIRED(1203, "Password reset token expired. Please try resetting password again"),
	INVALID_ROLE(1204, "Invalid Role Id passed"), EMAIL_ID_ALREADY_EXISTS(1205, "Email id already exists"), 
	USER_INACTIVE(1206, "User found Inactive"), PASSWORD_MISMATCH(1207, "Password and Confirm Password missmatch");

	private int errorCode;

	private String message;

	ErrorEnumeration(int errorCode, String messageKey) {
		this.errorCode = errorCode;
		this.message = messageKey;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessageKey(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return message;
	}
}
