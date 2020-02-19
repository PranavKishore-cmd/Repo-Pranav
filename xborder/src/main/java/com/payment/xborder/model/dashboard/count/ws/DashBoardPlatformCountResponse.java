package com.payment.xborder.model.dashboard.count.ws;

public class DashBoardPlatformCountResponse {
	
	private long activeCompanies;
	private long countKycApproved;
	private long pendingVerificationDocCount;
	private long verifiedStatusDocCount;
	private long activePairingStatusValue;

	public long getActiveCompanies() {
		return activeCompanies;
	}

	public void setActiveCompanies(long activeCompanies) {
		this.activeCompanies = activeCompanies;
	}

	public long getCountKycApproved() {
		return countKycApproved;
	}

	public void setCountKycApproved(long countKycApproved) {
		this.countKycApproved = countKycApproved;
	}

	public long getPendingVerificationDocCount() {
		return pendingVerificationDocCount;
	}

	public void setPendingVerificationDocCount(long pendingVerificationDocCount) {
		this.pendingVerificationDocCount = pendingVerificationDocCount;
	}

	public long getVerifiedStatusDocCount() {
		return verifiedStatusDocCount;
	}

	public void setVerifiedStatusDocCount(long verifiedStatusDocCount) {
		this.verifiedStatusDocCount = verifiedStatusDocCount;
	}

	
	 public long getActivePairingStatusValue() {
		 return activePairingStatusValue;
	 }
	 
	  public void setActivePairingStatusValue(long activePairingStatusValue) {
	  this.activePairingStatusValue = activePairingStatusValue; }
	 
	

}
