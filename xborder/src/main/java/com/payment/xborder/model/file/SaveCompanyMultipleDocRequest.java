package com.payment.xborder.model.file;

import javax.validation.constraints.NotBlank;
import java.util.List;


public class SaveCompanyMultipleDocRequest
{

	@NotBlank
	private String companyID;

	private List<DocumentDetails> documentsDetails;

   public SaveCompanyMultipleDocRequest()
   {
   }

   public SaveCompanyMultipleDocRequest(
         @NotBlank String companyID,
         List <DocumentDetails> documentsDetails
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

   public List <DocumentDetails> getDocumentsDetails()
   {
      return documentsDetails;
   }

   public void setDocumentsDetails(List <DocumentDetails> documentsDetails)
   {
      this.documentsDetails = documentsDetails;
   }
}
