package com.payment.xborder.model.pairing.ws;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.payment.xborder.enums.PairingStatus;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Document(collection = "PAIRING")
public class Pairing {

	 @Id
	 private String pairingId;
	
	 @NotBlank
	 private String senderCompanyRefId;
	 
	 @NotBlank
	 private String receiverCompanyRefId;
	 
	 @NotBlank
	 private List<String> senderPairingPaymentType; 
	
	 @NotBlank
	 private List<String> senderPairingcurrency;
	
	 @NotBlank
	 private List<String> receiverPairingPaymentType; 
	
	 @NotBlank
	 private List<String> receiverPairingCurrency; 
	 
	 private PairingStatus pairingStatus;

	public String getSenderCompanyRefId() {
		return senderCompanyRefId;
	}

	public void setSenderCompanyRefId(String senderCompanyRefId) {
		this.senderCompanyRefId = senderCompanyRefId;
	}

	public String getReceiverCompanyRefId() {
		return receiverCompanyRefId;
	}

	public void setReceiverCompanyRefId(String receiverCompanyRefId) {
		this.receiverCompanyRefId = receiverCompanyRefId;
	}

	public List<String> getSenderPairingPaymentType() {
		return senderPairingPaymentType;
	}

	public void setSenderPairingPaymentType(List<String> senderPairingPaymentType) {
		this.senderPairingPaymentType = senderPairingPaymentType;
	}

	public List<String> getSenderPairingcurrency() {
		return senderPairingcurrency;
	}

	public void setSenderPairingcurrency(List<String> senderPairingcurrency) {
		this.senderPairingcurrency = senderPairingcurrency;
	}

	public List<String> getReceiverPairingPaymentType() {
		return receiverPairingPaymentType;
	}

	public void setReceiverPairingPaymentType(List<String> receiverPairingPaymentType) {
		this.receiverPairingPaymentType = receiverPairingPaymentType;
	}

	public List<String> getReceiverPairingCurrency() {
		return receiverPairingCurrency;
	}

	public void setReceiverPairingCurrency(List<String> receiverPairingCurrency) {
		this.receiverPairingCurrency = receiverPairingCurrency;
	}



	public PairingStatus getPairingStatus() {
		return pairingStatus;
	}

	public void setPairingStatus(PairingStatus pairingStatus) {
		this.pairingStatus = pairingStatus;
	}

	public String getPairingId() {
		return pairingId;
	}

	public void setPairingId(String pairingId) {
		this.pairingId = pairingId;
	}
	 
	 
}
