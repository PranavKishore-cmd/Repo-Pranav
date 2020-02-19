package com.payment.xborder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author pradeep p
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileNotFoundException  extends BaseException {
	public FileNotFoundException(String message) {
		super(message);
	}

	public FileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
