package com.payment.xborder.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties ("xborder.twillio.sms")
public class SMSProperties
{

    private String accountSid;
    private String authToken;

    public SMSProperties()
    {
    }

   public SMSProperties(
         String accountSid,
         String authToken
   )
   {
      this.accountSid = accountSid;
      this.authToken = authToken;
   }

   public String getAccountSid()
   {
      return accountSid;
   }

   public void setAccountSid(String accountSid)
   {
      this.accountSid = accountSid;
   }

   public String getAuthToken()
   {
      return authToken;
   }

   public void setAuthToken(String authToken)
   {
      this.authToken = authToken;
   }
}
