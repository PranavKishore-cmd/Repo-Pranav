package com.payment.xborder.model.file;

import javax.validation.constraints.NotBlank;


public class SaveCompanyDocumentResponse
{

	@NotBlank
	private String companyID;

	@NotBlank
	private String message;

   public SaveCompanyDocumentResponse()
   {
   }

   public SaveCompanyDocumentResponse(
         @NotBlank String companyID,
         @NotBlank String message
   )
   {
      this.companyID = companyID;
      this.message = message;
   }

   public String getCompanyID()
   {
      return companyID;
   }

   public void setCompanyID(String companyID)
   {
      this.companyID = companyID;
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
