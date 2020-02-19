package com.payment.xborder.model.pairing.ws;

import java.util.List;

public class PairingRequest {

	private String senderCompanyRefId;
	 
	private String receiverCompanyRefId;
	 
	private String senderCompanyName;
	
	private String senderCompanyAddress;
	
	private List<String> senderPairingPaymentType;
	
	private List<String> senderPairingcurrency;
	
	private String senderPairingUploadDocs;
	
	private List<String> receiverPairingPaymentType;
	
	private List<String> receiverPairingCurrency;
	
	private String receiverPairingUploadDocs;

	
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

	public String getSenderCompanyName() {
		return senderCompanyName;
	}

	public void setSenderCompanyName(String senderCompanyName) {
		this.senderCompanyName = senderCompanyName;
	}

	public String getSenderCompanyAddress() {
		return senderCompanyAddress;
	}

	public void setSenderCompanyAddress(String senderCompanyAddress) {
		this.senderCompanyAddress = senderCompanyAddress;
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

	public String getSenderPairingUploadDocs() {
		return senderPairingUploadDocs;
	}

	public void setSenderPairingUploadDocs(String senderPairingUploadDocs) {
		this.senderPairingUploadDocs = senderPairingUploadDocs;
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

	public String getReceiverPairingUploadDocs() {
		return receiverPairingUploadDocs;
	}

	public void setReceiverPairingUploadDocs(String receiverPairingUploadDocs) {
		this.receiverPairingUploadDocs = receiverPairingUploadDocs;
	}
	
	
}
