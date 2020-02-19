package com.payment.xborder.model.onboard.ws;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MailValidatorRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String passcode;

    public MailValidatorRequest() {

    }

    public MailValidatorRequest(@NotBlank String email,  @NotBlank String passcode) {
        this.email = email;
        this.passcode = passcode;
    }

    public String getEmail() {
        return email;
    }

	public String getPasscode() {
		return passcode;
	}

}
