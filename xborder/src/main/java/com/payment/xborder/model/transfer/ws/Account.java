package com.payment.xborder.model.transfer.ws;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Document(collection = "ACCOUNT")
public class Account {

	 @NotBlank
	 private String senderCompanyRefId;
	 
	 @NotBlank
	 private List<String> receiverCompanyRefId;
	 
	 @NotBlank
	 private String senderCurrencyType;
	 
	 @NotBlank
	 private String amount;
	 

	public String getSenderCompanyRefId() {
		return senderCompanyRefId;
	}

	public void setSenderCompanyRefId(String senderCompanyRefId) {
		this.senderCompanyRefId = senderCompanyRefId;
	}

	

	public List<String> getReceiverCompanyRefId() {
		return receiverCompanyRefId;
	}

	public void setReceiverCompanyRefId(List<String> receiverCompanyRefId) {
		this.receiverCompanyRefId = receiverCompanyRefId;
	}

	public String getSenderCurrencyType() {
		return senderCurrencyType;
	}

	public void setSenderCurrencyType(String senderCurrencyType) {
		this.senderCurrencyType = senderCurrencyType;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	
	 
	 
	 
	
}
