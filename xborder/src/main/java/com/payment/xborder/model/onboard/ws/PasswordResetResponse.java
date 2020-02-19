package com.payment.xborder.model.onboard.ws;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PasswordResetResponse
{

    @NotBlank
    private String email;

    private boolean resetStatus;

    private String message;

    public PasswordResetResponse() {

    }

    public PasswordResetResponse(
          @NotBlank String email,
          boolean resetStatus,
          String message
    )
    {
        this.email = email;
        this.resetStatus = resetStatus;
        this.message = message;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public boolean isResetStatus()
    {
        return resetStatus;
    }

    public void setResetStatus(boolean resetStatus)
    {
        this.resetStatus = resetStatus;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    @Override
    public String toString()
    {
        return "PasswordResetResponse{" +
               "email='" + email + '\'' +
               ", resetStatus=" + resetStatus +
               ", message='" + message + '\'' +
               '}';
    }
}
