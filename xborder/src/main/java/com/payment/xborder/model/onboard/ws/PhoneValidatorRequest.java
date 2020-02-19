package com.payment.xborder.model.onboard.ws;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PhoneValidatorRequest {

    @NotBlank
    private String phone;
    
    @NotBlank
    private String countryCode;

    @NotBlank
    private String passcode;

    public PhoneValidatorRequest() {

    }

    public PhoneValidatorRequest(@NotBlank String phone,  @NotBlank String countryCode, @NotBlank String passcode) {
        this.phone= phone;
        this.countryCode = countryCode;
        this.passcode = passcode;
    }

	public String getPhone() {
		return phone;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public String getPasscode() {
		return passcode;
	}

}
