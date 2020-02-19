package com.payment.xborder.model.onboard.ws;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserResponse extends BaseResponse {

	public UserResponse() {
	}

	public UserResponse(String message) {
		super(message);
	}

}
