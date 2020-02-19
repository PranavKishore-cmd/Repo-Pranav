package com.payment.xborder.model.file;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.payment.xborder.enums.DocumentVerificationStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;


public class SaveDocumentRequest
{

	@NotBlank
	private String userEmail;

	private DocumentDetails documentsDetails;

   public SaveDocumentRequest()
   {
   }

   public SaveDocumentRequest(
         @NotBlank String userEmail,
         DocumentDetails documentsDetails
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

   public DocumentDetails getDocumentsDetails()
   {
      return documentsDetails;
   }

   public void setDocumentsDetails(DocumentDetails documentsDetails)
   {
      this.documentsDetails = documentsDetails;
   }
}
