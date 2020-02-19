package com.payment.xborder.model.file;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.payment.xborder.enums.DocumentVerificationStatus;
import org.joda.time.DateTime;

import javax.validation.constraints.NotBlank;

@JsonInclude (JsonInclude.Include.NON_EMPTY)
public class DocumentDetails
{

	@NotBlank
	private String documentName;

	@NotBlank
	private String documentType;

   @NotBlank
   private String documentDownloadPath;

	private String documentSize;

	@NotBlank
	private String documentExpiryDate;

   @NotBlank
   private DocumentVerificationStatus documentVerificationStatus;

   private long uploadedTime;

	public DocumentDetails() {
	}

   public DocumentDetails(
         @NotBlank String documentName,
         @NotBlank String documentType,
         @NotBlank String documentDownloadPath,
         String documentSize,
         @NotBlank String documentExpiryDate,
         @NotBlank DocumentVerificationStatus documentVerificationStatus,
         DateTime documentUploadDate,
         long uploadedTime
   )
   {
      this.documentName = documentName;
      this.documentType = documentType;
      this.documentDownloadPath = documentDownloadPath;
      this.documentSize = documentSize;
      this.documentExpiryDate = documentExpiryDate;
      this.documentVerificationStatus = documentVerificationStatus;
      this.uploadedTime = uploadedTime;
   }

   public String getDocumentName()
   {
      return documentName;
   }

   public void setDocumentName(String documentName)
   {
      this.documentName = documentName;
   }

   public String getDocumentType()
   {
      return documentType;
   }

   public void setDocumentType(String documentType)
   {
      this.documentType = documentType;
   }

   public String getDocumentDownloadPath()
   {
      return documentDownloadPath;
   }

   public void setDocumentDownloadPath(String documentDownloadPath)
   {
      this.documentDownloadPath = documentDownloadPath;
   }

   public String getDocumentSize()
   {
      return documentSize;
   }

   public void setDocumentSize(String documentSize)
   {
      this.documentSize = documentSize;
   }

   public String getDocumentExpiryDate()
   {
      return documentExpiryDate;
   }

   public void setDocumentExpiryDate(String documentExpiryDate)
   {
      this.documentExpiryDate = documentExpiryDate;
   }

   public DocumentVerificationStatus getDocumentVerificationStatus()
   {
      return documentVerificationStatus;
   }

   public void setDocumentVerificationStatus(DocumentVerificationStatus documentVerificationStatus)
   {
      this.documentVerificationStatus = documentVerificationStatus;
   }

   public long getUploadedTime()
   {
      return uploadedTime;
   }

   public void setUploadedTime(long uploadedTime)
   {
      this.uploadedTime = uploadedTime;
   }
}
