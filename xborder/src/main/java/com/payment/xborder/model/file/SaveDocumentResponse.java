package com.payment.xborder.model.file;

import javax.validation.constraints.NotBlank;


public class SaveDocumentResponse
{

	@NotBlank
	private String userEmail;

	@NotBlank
	private String message;

   public SaveDocumentResponse()
   {
   }

   public SaveDocumentResponse(
         @NotBlank String userEmail,
         @NotBlank String message
   )
   {
      this.userEmail = userEmail;
      this.message = message;
   }

   public String getUserEmail()
   {
      return userEmail;
   }

   public void setUserEmail(String userEmail)
   {
      this.userEmail = userEmail;
   }

   public String getMessage()
   {
      return message;
   }

   public void setMessage(String message)
   {
      this.message = message;
   }
}
