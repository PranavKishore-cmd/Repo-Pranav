package com.payment.xborder.model.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.payment.xborder.enums.PaymentFileStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Calendar;

@JsonInclude (JsonInclude.Include.NON_EMPTY)
@Document (collection = "PAYMENT_FILE_RECORD")
public class PaymentFileRecord
{

   @Id
   private String            paymentFileId;

   private String            paymentSenderID;
   private String            paymentReceiverID;
   private String            paymentFileName;
   private String            paymentFilePath;
   private PaymentFileStatus paymentFileStatus;
   private long              paymentRecordCreatedTime;
   private String            uploaderUserID;

   public PaymentFileRecord()
   {
      this.paymentRecordCreatedTime = Calendar.getInstance().getTimeInMillis();
   }

   public PaymentFileRecord(
         String paymentSenderID,
         String paymentReceiverID,
         String paymentFileName,
         String paymentFilePath,
         PaymentFileStatus paymentFileStatus,
         String uploaderUserID
   )
   {
      this.paymentSenderID = paymentSenderID;
      this.paymentReceiverID = paymentReceiverID;
      this.paymentFileName = paymentFileName;
      this.paymentFilePath = paymentFilePath;
      this.paymentFileStatus = paymentFileStatus;
      this.paymentRecordCreatedTime = Calendar.getInstance().getTimeInMillis();
      this.uploaderUserID = uploaderUserID;
   }

   public String getPaymentFileId()
   {
      return paymentFileId;
   }

   public void setPaymentFileId(String paymentFileId)
   {
      this.paymentFileId = paymentFileId;
   }

   public String getPaymentSenderID()
   {
      return paymentSenderID;
   }

   public void setPaymentSenderID(String paymentSenderID)
   {
      this.paymentSenderID = paymentSenderID;
   }

   public String getPaymentReceiverID()
   {
      return paymentReceiverID;
   }

   public void setPaymentReceiverID(String paymentReceiverID)
   {
      this.paymentReceiverID = paymentReceiverID;
   }

   public String getPaymentFileName()
   {
      return paymentFileName;
   }

   public void setPaymentFileName(String paymentFileName)
   {
      this.paymentFileName = paymentFileName;
   }

   public String getPaymentFilePath()
   {
      return paymentFilePath;
   }

   public void setPaymentFilePath(String paymentFilePath)
   {
      this.paymentFilePath = paymentFilePath;
   }

   public PaymentFileStatus getPaymentFileStatus()
   {
      return paymentFileStatus;
   }

   public void setPaymentFileStatus(PaymentFileStatus paymentFileStatus)
   {
      this.paymentFileStatus = paymentFileStatus;
   }

   public long getPaymentRecordCreatedTime()
   {
      return paymentRecordCreatedTime;
   }

   public void setPaymentRecordCreatedTime(long paymentRecordCreatedTime)
   {
      this.paymentRecordCreatedTime = paymentRecordCreatedTime;
   }

   public String getUploaderUserID()
   {
      return uploaderUserID;
   }

   public void setUploaderUserID(String uploaderUserID)
   {
      this.uploaderUserID = uploaderUserID;
   }
}

