package com.payment.xborder.model.onboard.ws;

import java.util.List;

import com.payment.xborder.enums.CompanyStatus;
import com.payment.xborder.model.onboard.BeneficiaryInformationDetails;
import com.payment.xborder.model.onboard.RegistrationForm;

public class CompanyDetails {
	
	private String id;
	private String businessLegalName;
	private String federalTaxId;
	private CompanyStatus registrationStatus;
	private String userid;
	private String emailid;
	private List<BeneficiaryInformationDetails> beneficiaryInformationDetailsList;
	
	public CompanyDetails(
		
			String id,
			String businessLegalName,
			String federalTaxId,
			CompanyStatus registrationStatus,
			String userid,
			String emailid,
			List<BeneficiaryInformationDetails> beneficiaryInformationDetailsList
			)
	{
		this.id = id;
		this.businessLegalName = businessLegalName;
		this.federalTaxId = federalTaxId;
		this.registrationStatus = registrationStatus;
		this.userid = userid;
		this.emailid = emailid;
		this.beneficiaryInformationDetailsList = beneficiaryInformationDetailsList;
	}
	
	 public CompanyDetails(RegistrationForm registrationForm){
	      this.id = registrationForm.getCompanyId();
	      this.businessLegalName = registrationForm.getBusinessLegalName();
	      this.federalTaxId = registrationForm.getFederalTaxId();
	      this.registrationStatus = registrationForm.getRegistrationStatus();
	      this.userid = registrationForm.getUserId();
	      this.emailid = registrationForm.getEmailId();
	      this.beneficiaryInformationDetailsList = registrationForm.getBeneficiaryInformationDetailsList();
	   }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBusinessLegalName() {
		return businessLegalName;
	}

	public void setBusinessLegalName(String businessLegalName) {
		this.businessLegalName = businessLegalName;
	}

	public String getFederalTaxId() {
		return federalTaxId;
	}

	public void setFederalTaxId(String federalTaxId) {
		this.federalTaxId = federalTaxId;
	}

	public CompanyStatus getRegistrationStatus() {
		return registrationStatus;
	}

	public void setRegistrationStatus(CompanyStatus registrationStatus) {
		this.registrationStatus = registrationStatus;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public List<BeneficiaryInformationDetails> getBeneficiaryInformationDetailsList() {
		return beneficiaryInformationDetailsList;
	}

	public void setBeneficiaryInformationDetailsList(
			List<BeneficiaryInformationDetails> beneficiaryInformationDetailsList) {
		this.beneficiaryInformationDetailsList = beneficiaryInformationDetailsList;
	}
	
	

}
