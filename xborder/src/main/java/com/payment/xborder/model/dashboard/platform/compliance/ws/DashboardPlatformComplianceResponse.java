package com.payment.xborder.model.dashboard.platform.compliance.ws;

public class DashboardPlatformComplianceResponse {

	private long activeCompanies;
	private long finalApproval;
	private long complianceApproval;
	private long kycApproval;
	private long docVerification;
	private long docReUpload;
	private long pairedMsbs;
	public long getActiveCompanies() {
		return activeCompanies;
	}
	public void setActiveCompanies(long activeCompanies) {
		this.activeCompanies = activeCompanies;
	}
	public long getFinalApproval() {
		return finalApproval;
	}
	public void setFinalApproval(long finalApproval) {
		this.finalApproval = finalApproval;
	}
	public long getComplianceApproval() {
		return complianceApproval;
	}
	public void setComplianceApproval(long complianceApproval) {
		this.complianceApproval = complianceApproval;
	}
	public long getKycApproval() {
		return kycApproval;
	}
	public void setKycApproval(long kycApproval) {
		this.kycApproval = kycApproval;
	}
	public long getDocVerification() {
		return docVerification;
	}
	public void setDocVerification(long docVerification) {
		this.docVerification = docVerification;
	}
	public long getDocReUpload() {
		return docReUpload;
	}
	public void setDocReUpload(long docReUpload) {
		this.docReUpload = docReUpload;
	}
	
	public long getPairedMsbs() {
		return pairedMsbs;
	}
	public void setPairedMsbs(long pairedMsbs) {
		this.pairedMsbs = pairedMsbs;
	}
	
	
}
