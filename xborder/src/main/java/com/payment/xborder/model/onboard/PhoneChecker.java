package com.payment.xborder.model.onboard;

import java.time.LocalDate;
import java.util.Calendar;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.payment.xborder.enums.VerificationStatus;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Document(collection = "PHONE_CHECKER")
public class PhoneChecker {

    @NotBlank
    private String phone;
    
    @NotBlank
    private String countryCode;
    
    @Id
    @NotBlank
    private String longPhoneNum;

    @NotBlank
    private long passcode;

    @NotNull
    private VerificationStatus status;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private LocalDate requestDate;
    
    private long updatedTimeInMillis;

    public PhoneChecker() {
     	this.updatedTimeInMillis = Calendar.getInstance().getTimeInMillis();
    }

    public PhoneChecker(@NotBlank String phone,
    		               @NotBlank String countryCode,
    		               @NotBlank String longPhoneNum,
    		               @NotBlank long passcode,
    		               @NotNull VerificationStatus status,
    		               @NotBlank String firstName,
    		               @NotBlank String lastName,
    		               LocalDate requestDate,
    		               long updatedTimeInMillis) {
        this.phone = phone;
        this.countryCode = countryCode;
        this.longPhoneNum = longPhoneNum;
        this.passcode = passcode;
        this.status = status;
        this.firstName = firstName;
        this.lastName = lastName;
        this.requestDate = requestDate;
        this.updatedTimeInMillis = updatedTimeInMillis;
    }

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getLongPhoneNum() {
		return longPhoneNum;
	}

	public void setLongPhoneNum(String longPhoneNum) {
		this.longPhoneNum = longPhoneNum;
	}

	public long getPasscode() {
		return passcode;
	}

	public void setPasscode(long passcode) {
		this.passcode = passcode;
	}

	public VerificationStatus getStatus() {
		return status;
	}

	public void setStatus(VerificationStatus status) {
		this.status = status;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(LocalDate requestDate) {
		this.requestDate = requestDate;
	}

	public long getUpdatedTimeInMillis() {
		return updatedTimeInMillis;
	}

	public void setUpdatedTimeInMillis(long updatedTimeInMillis) {
		this.updatedTimeInMillis = updatedTimeInMillis;
	}

}
