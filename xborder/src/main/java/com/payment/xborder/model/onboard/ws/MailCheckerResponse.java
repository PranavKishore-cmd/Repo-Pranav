package com.payment.xborder.model.onboard.ws;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MailCheckerResponse {

    public MailCheckerResponse() {
    }

    public MailCheckerResponse(String message) {
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
