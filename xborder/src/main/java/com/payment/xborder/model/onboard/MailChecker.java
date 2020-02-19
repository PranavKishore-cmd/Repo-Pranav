package com.payment.xborder.model.onboard;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.payment.xborder.enums.VerificationStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Calendar;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Document(collection = "MAIL_CHECKER")
public class MailChecker {


    @Id
    @NotBlank
    private String email;

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

    public MailChecker() {
     	this.updatedTimeInMillis = Calendar.getInstance().getTimeInMillis();
    }

    public MailChecker(@NotBlank String email,
    		               @NotBlank long passcode,
    		               @NotNull VerificationStatus status,
    		               @NotBlank String firstName,
    		               @NotBlank String lastName,
    		               LocalDate requestDate,
    		               long updatedTimeInMillis) {
        this.email = email;
        this.passcode = passcode;
        this.status = status;
        this.firstName = firstName;
        this.lastName = lastName;
        this.requestDate = requestDate;
        this.updatedTimeInMillis = updatedTimeInMillis;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
