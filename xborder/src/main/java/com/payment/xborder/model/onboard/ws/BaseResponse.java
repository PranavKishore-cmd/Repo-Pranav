package com.payment.xborder.model.onboard.ws;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Default Base Response class.
 * 
 * @author Pradeep
 *
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BaseResponse {

	public BaseResponse() {
	}

	public BaseResponse(String message) {
		this.message = message;
	}

	private String message;

	public String getResponse() {
		return message;
	}

	public void setResponse(String message) {
		this.message = message;
	}

}
