package com.payment.xborder.model.file;

import javax.validation.constraints.NotBlank;


public class SaveCompanyDocumentRequest
{

	@NotBlank
	private String companyID;

	private DocumentDetails documentsDetails;

   public SaveCompanyDocumentRequest()
   {
   }

   public SaveCompanyDocumentRequest(
         @NotBlank String companyID,
         DocumentDetails documentsDetails
   )
   {
      this.companyID = companyID;
      this.documentsDetails = documentsDetails;
   }

   public String getCompanyID()
   {
      return companyID;
   }

   public void setCompanyID(String companyID)
   {
      this.companyID = companyID;
   }

   public DocumentDetails getDocumentsDetails()
   {
      return documentsDetails;
   }

   public void setDocumentsDetails(DocumentDetails documentsDetails)
   {
      this.documentsDetails = documentsDetails;
   }
}
