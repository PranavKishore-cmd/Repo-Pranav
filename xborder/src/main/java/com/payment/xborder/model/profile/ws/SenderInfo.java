package com.payment.xborder.model.profile.ws;

import java.util.List;

public class SenderInfo {

	private List<String> senderRegion;
	private List<String> senderCurrency;
	private List<String> senderPaymentFormat;
	private String       senderPaymentLimit;
	private List<String> senderPaymentType;

	public List<String> getSenderRegion() {
		return senderRegion;
	}
	public void setSenderRegion(List<String> senderRegion) {
		this.senderRegion = senderRegion;
	}
	public List<String> getSenderCurrency() {
		return senderCurrency;
	}
	public void setSenderCurrency(List<String> senderCurrency) {
		this.senderCurrency = senderCurrency;
	}

   public List <String> getSenderPaymentFormat()
   {
      return senderPaymentFormat;
   }

   public void setSenderPaymentFormat(List <String> senderPaymentFormat)
   {
      this.senderPaymentFormat = senderPaymentFormat;
   }

   public String getSenderPaymentLimit()
   {
      return senderPaymentLimit;
   }

   public void setSenderPaymentLimit(String senderPaymentLimit)
   {
      this.senderPaymentLimit = senderPaymentLimit;
   }

   public List <String> getSenderPaymentType()
   {
      return senderPaymentType;
   }

   public void setSenderPaymentType(List <String> senderPaymentType)
   {
      this.senderPaymentType = senderPaymentType;
   }
}
