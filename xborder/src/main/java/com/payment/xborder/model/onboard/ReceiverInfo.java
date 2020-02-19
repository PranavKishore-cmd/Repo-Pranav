package com.payment.xborder.model.onboard;

import java.util.List;

public class ReceiverInfo {

	private List<String> receiverRegion;
	private List<String> receiverCurrency;
	private List<String> receiverPaymentFormat;
	private String       receiverPaymentLimit;
	private List<String> receiverPaymentType;

   public List<String> getReceiverRegion()
   {
      return receiverRegion;
   }

   public void setReceiverRegion(List<String> receiverRegion)
   {
      this.receiverRegion = receiverRegion;
   }

   public List<String> getReceiverCurrency()
   {
      return receiverCurrency;
   }

   public void setReceiverCurrency(List<String> receiverCurrency)
   {
      this.receiverCurrency = receiverCurrency;
   }

   public List <String> getReceiverPaymentFormat()
   {
      return receiverPaymentFormat;
   }

   public void setReceiverPaymentFormat(List <String> receiverPaymentFormat)
   {
      this.receiverPaymentFormat = receiverPaymentFormat;
   }

   public String getReceiverPaymentLimit()
   {
      return receiverPaymentLimit;
   }

   public void setReceiverPaymentLimit(String receiverPaymentLimit)
   {
      this.receiverPaymentLimit = receiverPaymentLimit;
   }

   public List <String> getReceiverPaymentType()
   {
      return receiverPaymentType;
   }

   public void setReceiverPaymentType(List <String> receiverPaymentType)
   {
      this.receiverPaymentType = receiverPaymentType;
   }
}
