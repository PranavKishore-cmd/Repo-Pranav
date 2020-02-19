package com.payment.xborder.model.onboard.ws;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;

@JsonInclude (JsonInclude.Include.NON_EMPTY)
public class PhoneValidatorResponse
{

   @NotBlank
   private String phone;

   @NotBlank
   private String countryCode;

   private boolean verificationStatus;

   private String varificationMessage;

   public PhoneValidatorResponse()
   {

   }

   public PhoneValidatorResponse(
         @NotBlank String phone,
         @NotBlank String countryCode,
         boolean verificationStatus,
         String varificationMessage
   )
   {
      this.phone = phone;
      this.countryCode = countryCode;
      this.verificationStatus = verificationStatus;
      this.varificationMessage = varificationMessage;
   }

   public String getPhone()
   {
      return phone;
   }

   public void setPhone(String phone)
   {
      this.phone = phone;
   }

   public String getCountryCode()
   {
      return countryCode;
   }

   public void setCountryCode(String countryCode)
   {
      this.countryCode = countryCode;
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
      return "PhoneValidatorResponse{" +
             "phone='" + phone + '\'' +
             ", countryCode='" + countryCode + '\'' +
             ", verificationStatus=" + verificationStatus +
             ", varificationMessage='" + varificationMessage + '\'' +
             '}';
   }
}
