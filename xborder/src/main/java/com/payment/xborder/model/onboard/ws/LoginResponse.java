package com.payment.xborder.model.onboard.ws;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude (JsonInclude.Include.NON_EMPTY)
public class LoginResponse
{

   public LoginResponse()
   {
   }

   public LoginResponse(
         String message,
         String sessionId,
         boolean gAuthEnabled
   )
   {
      this.message = message;
      this.sessionId = sessionId;
      this.gAuthEnabled = gAuthEnabled;
   }

   private String message;

   private String sessionId;

   private boolean gAuthEnabled;

   public String getMessage()
   {
      return message;
   }

   public void setMessage(String message)
   {
      this.message = message;
   }

   public String getSessionId()
   {
      return sessionId;
   }

   public void setSessionId(String sessionId)
   {
      this.sessionId = sessionId;
   }

   public boolean isgAuthEnabled()
   {
      return gAuthEnabled;
   }

   public void setgAuthEnabled(boolean gAuthEnabled)
   {
      this.gAuthEnabled = gAuthEnabled;
   }
}
