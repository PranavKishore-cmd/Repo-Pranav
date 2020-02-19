package com.payment.xborder.ws;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.payment.xborder.model.onboard.ws.BaseResponse;

public class AbstractRestController {

	public static String DEFAULT_RESPONSE = "SUCCESS";
	public static String BOOLEAN_TRUE_RESPONSE = "TRUE";
	public static String BOOLEAN_FALSE_RESPONSE = "FALSE";
	
	public static ResponseEntity<BaseResponse> baseResponse(String message, HttpStatus status) {
		return new ResponseEntity<>(new BaseResponse(message), status);
	}
}
