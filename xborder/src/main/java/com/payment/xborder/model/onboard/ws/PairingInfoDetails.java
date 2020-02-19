package com.payment.xborder.model.onboard.ws;

import com.payment.xborder.enums.PairingStatus;
import com.payment.xborder.model.pairing.ws.Pairing;

public class PairingInfoDetails {
	
	private String pairingId;
	private PairingStatus pairingStatus;
	
	public PairingInfoDetails(String pairingId,PairingStatus pairingStatus) {
		
		this.pairingId = pairingId;
		this.pairingStatus = pairingStatus;
		
	}
	
	public PairingInfoDetails(Pairing pairing) {
		
		this.pairingId = pairing.getPairingId();
		this.pairingStatus = pairing.getPairingStatus();
		
		
	}
	
	
	public String getPairingId() {
		return pairingId;
	}
	public void setPairingId(String pairingId) {
		this.pairingId = pairingId;
	}
	public PairingStatus getPairingStatus() {
		return pairingStatus;
	}
	public void setPairingStatus(PairingStatus pairingStatus) {
		this.pairingStatus = pairingStatus;
	}
	
	

}
