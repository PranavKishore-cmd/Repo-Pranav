package com.payment.xborder.model.pairing.ws;

import com.payment.xborder.enums.PairingStatus;

import java.util.List;

public class PairingResponse {

   private String        pairingID;
   private String        senderCompanyRefId;
   private String        senderCompanyName;
   private String        receiverCompanyRefId;
   private String        receiverCompanyName;
   private List<String>  senderPairingPaymentType;
   private List<String>  senderPairingcurrency;
   private List<String>  receiverPairingPaymentType;
   private List<String>  receiverPairingCurrency;
   private PairingStatus pairingStatus;

   public String getPairingID()
   {
      return pairingID;
   }

   public void setPairingID(String pairingID)
   {
      this.pairingID = pairingID;
   }

   public String getSenderCompanyRefId()
   {
      return senderCompanyRefId;
   }

   public void setSenderCompanyRefId(String senderCompanyRefId)
   {
      this.senderCompanyRefId = senderCompanyRefId;
   }

   public String getSenderCompanyName()
   {
      return senderCompanyName;
   }

   public void setSenderCompanyName(String senderCompanyName)
   {
      this.senderCompanyName = senderCompanyName;
   }

   public String getReceiverCompanyRefId()
   {
      return receiverCompanyRefId;
   }

   public void setReceiverCompanyRefId(String receiverCompanyRefId)
   {
      this.receiverCompanyRefId = receiverCompanyRefId;
   }

   public String getReceiverCompanyName()
   {
      return receiverCompanyName;
   }

   public void setReceiverCompanyName(String receiverCompanyName)
   {
      this.receiverCompanyName = receiverCompanyName;
   }

   public List <String> getSenderPairingPaymentType()
   {
      return senderPairingPaymentType;
   }

   public void setSenderPairingPaymentType(List <String> senderPairingPaymentType)
   {
      this.senderPairingPaymentType = senderPairingPaymentType;
   }

   public List <String> getSenderPairingcurrency()
   {
      return senderPairingcurrency;
   }

   public void setSenderPairingcurrency(List <String> senderPairingcurrency)
   {
      this.senderPairingcurrency = senderPairingcurrency;
   }

   public List <String> getReceiverPairingPaymentType()
   {
      return receiverPairingPaymentType;
   }

   public void setReceiverPairingPaymentType(List <String> receiverPairingPaymentType)
   {
      this.receiverPairingPaymentType = receiverPairingPaymentType;
   }

   public List <String> getReceiverPairingCurrency()
   {
      return receiverPairingCurrency;
   }

   public void setReceiverPairingCurrency(List <String> receiverPairingCurrency)
   {
      this.receiverPairingCurrency = receiverPairingCurrency;
   }

   public PairingStatus getPairingStatus()
   {
      return pairingStatus;
   }

   public void setPairingStatus(PairingStatus pairingStatus)
   {
      this.pairingStatus = pairingStatus;
   }

   public PairingResponse()
   {
   }

   public PairingResponse(
         String pairingID,
         String senderCompanyRefId,
         String senderCompanyName,
         String receiverCompanyRefId,
         String receiverCompanyName,
         List <String> senderPairingPaymentType,
         List <String> senderPairingcurrency,
         List <String> receiverPairingPaymentType,
         List <String> receiverPairingCurrency,
         PairingStatus pairingStatus
   )
   {
      this.pairingID = pairingID;
      this.senderCompanyRefId = senderCompanyRefId;
      this.senderCompanyName = senderCompanyName;
      this.receiverCompanyRefId = receiverCompanyRefId;
      this.receiverCompanyName = receiverCompanyName;
      this.senderPairingPaymentType = senderPairingPaymentType;
      this.senderPairingcurrency = senderPairingcurrency;
      this.receiverPairingPaymentType = receiverPairingPaymentType;
      this.receiverPairingCurrency = receiverPairingCurrency;
      this.pairingStatus = pairingStatus;
   }

   @Override
   public String toString()
   {
      return "PairingResponse{" +
             "pairingID='" + pairingID + '\'' +
             ", senderCompanyRefId='" + senderCompanyRefId + '\'' +
             ", senderCompanyName='" + senderCompanyName + '\'' +
             ", receiverCompanyRefId='" + receiverCompanyRefId + '\'' +
             ", receiverCompanyName='" + receiverCompanyName + '\'' +
             ", senderPairingPaymentType=" + senderPairingPaymentType +
             ", senderPairingcurrency=" + senderPairingcurrency +
             ", receiverPairingPaymentType=" + receiverPairingPaymentType +
             ", receiverPairingCurrency=" + receiverPairingCurrency +
             ", pairingStatus=" + pairingStatus +
             '}';
   }
}
