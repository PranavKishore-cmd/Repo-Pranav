package com.payment.xborder.model.file;

import javax.validation.constraints.NotBlank;
import java.util.List;


public class GetDocumentsResponse
{

	@NotBlank
	private String userEmail;

	@NotBlank
	private List DocumentDetails;

   public GetDocumentsResponse()
   {
   }

   public String getUserEmail()
   {
      return userEmail;
   }

   public void setUserEmail(String userEmail)
   {
      this.userEmail = userEmail;
   }

   public List getDocumentDetails()
   {
      return DocumentDetails;
   }

   public void setDocumentDetails(List documentDetails)
   {
      DocumentDetails = documentDetails;
   }
}
