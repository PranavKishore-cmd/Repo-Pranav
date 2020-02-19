package com.payment.xborder.model.file;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.payment.xborder.enums.DocumentVerificationStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@JsonInclude (JsonInclude.Include.NON_EMPTY)
@Document (collection = "USER_DOCUMENTS")
public class UserDocuments
{
	@Id
	private String userDocumentID;

	@NotBlank
	private String userId;

	private Map<String, DocumentDetails> documentsUploaded;

	private long lastModifiedTime;

	public UserDocuments()
	{
      this.lastModifiedTime = Calendar.getInstance().getTimeInMillis();
	}

   public UserDocuments(
         String userDocumentID,
         Map <String, DocumentDetails> documentsUploaded,
         long lastModifiedTime
   )
   {
      this.userDocumentID = userDocumentID;
      this.documentsUploaded = documentsUploaded;
      this.lastModifiedTime = lastModifiedTime;
   }

   public String getUserDocumentID()
   {
      return userDocumentID;
   }

   public void setUserDocumentID(String userDocumentID)
   {
      this.userDocumentID = userDocumentID;
   }

   public String getUserId()
   {
      return userId;
   }

   public void setUserId(String userId)
   {
      this.userId = userId;
   }

   public Map <String, DocumentDetails> getDocumentsUploaded()
   {
      return documentsUploaded;
   }

   public void setDocumentsUploaded(Map <String, DocumentDetails> documentsUploaded)
   {
      this.documentsUploaded = documentsUploaded;
   }

   public long getLastModifiedTime()
   {
      return lastModifiedTime;
   }

   public void setLastModifiedTime(long lastModifiedTime)
   {
      this.lastModifiedTime = lastModifiedTime;
   }
}
