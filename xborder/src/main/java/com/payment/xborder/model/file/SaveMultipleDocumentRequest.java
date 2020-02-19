package com.payment.xborder.model.file;

import javax.validation.constraints.NotBlank;
import java.util.List;


public class SaveMultipleDocumentRequest
{

	@NotBlank
	private String userEmail;

	private List<DocumentDetails> documentsDetails;

   public SaveMultipleDocumentRequest()
   {
   }

   public SaveMultipleDocumentRequest(
         @NotBlank String userEmail,
         List <DocumentDetails> documentsDetails
   )
   {
      this.userEmail = userEmail;
      this.documentsDetails = documentsDetails;
   }

   public String getUserEmail()
   {
      return userEmail;
   }

   public void setUserEmail(String userEmail)
   {
      this.userEmail = userEmail;
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
