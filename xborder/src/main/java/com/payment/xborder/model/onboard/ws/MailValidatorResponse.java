package com.payment.xborder.model.onboard.ws;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MailValidatorResponse
{

    @NotBlank
    private String email;

    private boolean verificationStatus;

    private String varificationMessage;

    public MailValidatorResponse() {

    }

    public MailValidatorResponse(
          @NotBlank String email,
          boolean verificationStatus,
          String varificationMessage
    )
    {
        this.email = email;
        this.verificationStatus = verificationStatus;
        this.varificationMessage = varificationMessage;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public boolean isVerificationStatus()
    {
        return verificationStatus;
    }

    public void setVerificationStatus(boolean verificationStatus)
    {
        this.verificationStatus = verificationStatus;
    }

    public String getVarificationMessage()
    {
        return varificationMessage;
    }

    public void setVarificationMessage(String varificationMessage)
    {
        this.varificationMessage = varificationMessage;
    }

    @Override
    public String toString()
    {
        return "MailValidatorResponse{" +
               "email='" + email + '\'' +
               ", verificationStatus=" + verificationStatus +
               ", varificationMessage='" + varificationMessage + '\'' +
               '}';
    }
}
