package com.payment.xborder.exception;


public class ValidationException extends BaseException {

	private static final long serialVersionUID = 5597771876927501238L;

	public ValidationException() {
		super();
	}
	
	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(ErrorEnumeration errorEnumeration) {
		super(errorEnumeration.toString());
	}

	public ValidationException(ErrorEnumeration errorEnumeration, Throwable cause) {
		super(errorEnumeration.toString(), cause);
	}
	
	
}
