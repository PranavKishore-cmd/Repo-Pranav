package com.payment.xborder.exception;


public class BusinessException extends BaseException {

	private static final long serialVersionUID = 5597771876927501238L;

	public BusinessException() {
		super();
	}
	
	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(ErrorEnumeration errorEnumeration) {
		super(errorEnumeration.toString());
	}

	public BusinessException(ErrorEnumeration errorEnumeration, Throwable cause) {
		super(errorEnumeration.toString(), cause);
	}
	
	
}
