package com.payment.xborder.model.profile.ws;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProfileManagementResponse {

	 public ProfileManagementResponse() {
	    }

	    public ProfileManagementResponse(String message) {
	        this.message = message;
	    }

	    private String message;

	    public String getResponse() {
	        return message;
	    }

	    public void setResponse(String response) {
	        this.message = message;
	    }
}
