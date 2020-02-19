package com.payment.xborder.exception;

public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -5077170997547938726L;

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }
    
    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

	
    
}
