/**
 * 
 */
package com.payment.xborder.model.onboard;

/**
 * @author Vinay Muralidhara
 *
 */
public class PlatformCODashboardCount {
	
	private int activeCompanies;
	private int finalApprovalPending;
	private int complianceApproval;
	private int documentVerification;
	private int pairedMembers;
	private int complianceVerification;
	private int approvalPending;
	private int docUploadPending;
	private int kycVerification;
	private int pendingActivation;
	
	public PlatformCODashboardCount() {
		
	}
	
	

	/**
	 * @param activeCompanies
	 * @param finalApprovalPending
	 * @param complianceApproval
	 * @param documentVerification
	 * @param pairedMembers
	 * @param complianceVerification
	 * @param approvalPending
	 * @param docUploadPending
	 * @param kycVerification
	 * @param pendingActivation
	 */
	public PlatformCODashboardCount(int activeCompanies, int finalApprovalPending, int complianceApproval,
			int documentVerification, int pairedMembers, int complianceVerification, int approvalPending,
			int docUploadPending, int kycVerification, int pendingActivation) {
		this.activeCompanies = activeCompanies;
		this.finalApprovalPending = finalApprovalPending;
		this.complianceApproval = complianceApproval;
		this.documentVerification = documentVerification;
		this.pairedMembers = pairedMembers;
		this.complianceVerification = complianceVerification;
		this.approvalPending = approvalPending;
		this.docUploadPending = docUploadPending;
		this.kycVerification = kycVerification;
		this.pendingActivation = pendingActivation;
	}



	/**
	 * @return the activeCompanies
	 */
	public int getActiveCompanies() {
		return activeCompanies;
	}
	/**
	 * @param activeCompanies the activeCompanies to set
	 */
	public void setActiveCompanies(int activeCompanies) {
		this.activeCompanies = activeCompanies;
	}
	/**
	 * @return the finalApprovalPending
	 */
	public int getFinalApprovalPending() {
		return finalApprovalPending;
	}
	/**
	 * @param finalApprovalPending the finalApprovalPending to set
	 */
	public void setFinalApprovalPending(int finalApprovalPending) {
		this.finalApprovalPending = finalApprovalPending;
	}
	/**
	 * @return the complianceApproval
	 */
	public int getComplianceApproval() {
		return complianceApproval;
	}
	/**
	 * @param complianceApproval the complianceApproval to set
	 */
	public void setComplianceApproval(int complianceApproval) {
		this.complianceApproval = complianceApproval;
	}
	/**
	 * @return the documentVerification
	 */
	public int getDocumentVerification() {
		return documentVerification;
	}
	/**
	 * @param documentVerification the documentVerification to set
	 */
	public void setDocumentVerification(int documentVerification) {
		this.documentVerification = documentVerification;
	}
	/**
	 * @return the pairedMembers
	 */
	public int getPairedMembers() {
		return pairedMembers;
	}
	/**
	 * @param pairedMembers the pairedMembers to set
	 */
	public void setPairedMembers(int pairedMembers) {
		this.pairedMembers = pairedMembers;
	}
	/**
	 * @return the complianceVerification
	 */
	public int getComplianceVerification() {
		return complianceVerification;
	}
	/**
	 * @param complianceVerification the complianceVerification to set
	 */
	public void setComplianceVerification(int complianceVerification) {
		this.complianceVerification = complianceVerification;
	}
	/**
	 * @return the approvalPending
	 */
	public int getApprovalPending() {
		return approvalPending;
	}
	/**
	 * @param approvalPending the approvalPending to set
	 */
	public void setApprovalPending(int approvalPending) {
		this.approvalPending = approvalPending;
	}
	/**
	 * @return the docUploadPending
	 */
	public int getDocUploadPending() {
		return docUploadPending;
	}
	/**
	 * @param docUploadPending the docUploadPending to set
	 */
	public void setDocUploadPending(int docUploadPending) {
		this.docUploadPending = docUploadPending;
	}



	/**
	 * @return the kycVerification
	 */
	public int getKycVerification() {
		return kycVerification;
	}



	/**
	 * @param kycVerification the kycVerification to set
	 */
	public void setKycVerification(int kycVerification) {
		this.kycVerification = kycVerification;
	}



	/**
	 * @return the pendingActivation
	 */
	public int getPendingActivation() {
		return pendingActivation;
	}



	/**
	 * @param pendingActivation the pendingActivation to set
	 */
	public void setPendingActivation(int pendingActivation) {
		this.pendingActivation = pendingActivation;
	}
	
	
}
