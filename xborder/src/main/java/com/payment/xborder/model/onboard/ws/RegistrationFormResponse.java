package com.payment.xborder.model.onboard.ws;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.payment.xborder.enums.CompanyStatus;
import com.payment.xborder.model.onboard.Address;
import com.payment.xborder.model.onboard.BeneficiaryInformationDetails;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RegistrationFormResponse {

	/**
	 * 
	 */
	private String businessLegalName;
	private String contactName;
	private String businessName;
	private String ifCorporateHeadquarters;
	private String emailId;
	private String website;

	private boolean isSaveAsDraft;
	private String faxNumber2;
	private String federalTaxId;
	private String typeOfLegalEntiry;
	private String controlOwnerName;
	private String title;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private String dateOfBirth;
	private String ssn;
	private String ownershipPercentage;
	private List<BeneficiaryInformationDetails> beneficiaryInformationDetailsList;
	private String controlOwnerCountry;
	private String controlOwnerPhone;
	private Address controlOwnerAddress;

	private Address billingAddress;
	private Address locationAddress;
	private boolean isAddressSame;
	private CompanyStatus registrationStatus;

	private boolean isAdmin;
	private String adminFirstName;
	private String adminMiddleName;
	private String adminLastName;
	private String adminMailId;

	private boolean isAuthorizer;
	private String authorizerFirstName;
	private String authorizerMiddleName;
	private String authorizerLastName;
	private String authorizerMailId;
	private boolean controlOwnerKycStatus;

	private String message;

	public RegistrationFormResponse() {

	}

	public RegistrationFormResponse(String response) {
		this.message = response;
	}

	

	/**
	 * @param businessLegalName
	 * @param contactName
	 * @param businessName
	 * @param ifCorporateHeadquarters
	 * @param emailId
	 * @param website
	 * @param isSaveAsDraft
	 * @param faxNumber2
	 * @param federalTaxId
	 * @param typeOfLegalEntiry
	 * @param controlOwnerName
	 * @param title
	 * @param dateOfBirth
	 * @param ssn
	 * @param ownershipPercentage
	 * @param beneficiaryInformationDetailsList
	 * @param controlOwnerCountry
	 * @param controlOwnerPhone
	 * @param controlOwnerAddress
	 * @param billingAddress
	 * @param locationAddress
	 * @param isAddressSame
	 * @param registrationStatus
	 * @param isAdmin
	 * @param adminFirstName
	 * @param adminMiddleName
	 * @param adminLastName
	 * @param adminMailId
	 * @param isAuthorizer
	 * @param authorizerFirstName
	 * @param authorizerMiddleName
	 * @param authorizerLastName
	 * @param authorizerMailId
	 * @param controlOwnerKycStatus
	 * @param message
	 */
	public RegistrationFormResponse(String businessLegalName, String contactName, String businessName,
			String ifCorporateHeadquarters, String emailId, String website, boolean isSaveAsDraft, String faxNumber2,
			String federalTaxId, String typeOfLegalEntiry, String controlOwnerName, String title, String dateOfBirth,
			String ssn, String ownershipPercentage,
			List<BeneficiaryInformationDetails> beneficiaryInformationDetailsList, String controlOwnerCountry,
			String controlOwnerPhone, Address controlOwnerAddress, Address billingAddress, Address locationAddress,
			boolean isAddressSame, CompanyStatus registrationStatus, boolean isAdmin, String adminFirstName,
			String adminMiddleName, String adminLastName, String adminMailId, boolean isAuthorizer,
			String authorizerFirstName, String authorizerMiddleName, String authorizerLastName, String authorizerMailId,
			boolean controlOwnerKycStatus, String message) {
		super();
		this.businessLegalName = businessLegalName;
		this.contactName = contactName;
		this.businessName = businessName;
		this.ifCorporateHeadquarters = ifCorporateHeadquarters;
		this.emailId = emailId;
		this.website = website;
		this.isSaveAsDraft = isSaveAsDraft;
		this.faxNumber2 = faxNumber2;
		this.federalTaxId = federalTaxId;
		this.typeOfLegalEntiry = typeOfLegalEntiry;
		this.controlOwnerName = controlOwnerName;
		this.title = title;
		this.dateOfBirth = dateOfBirth;
		this.ssn = ssn;
		this.ownershipPercentage = ownershipPercentage;
		this.beneficiaryInformationDetailsList = beneficiaryInformationDetailsList;
		this.controlOwnerCountry = controlOwnerCountry;
		this.controlOwnerPhone = controlOwnerPhone;
		this.controlOwnerAddress = controlOwnerAddress;
		this.billingAddress = billingAddress;
		this.locationAddress = locationAddress;
		this.isAddressSame = isAddressSame;
		this.registrationStatus = registrationStatus;
		this.isAdmin = isAdmin;
		this.adminFirstName = adminFirstName;
		this.adminMiddleName = adminMiddleName;
		this.adminLastName = adminLastName;
		this.adminMailId = adminMailId;
		this.isAuthorizer = isAuthorizer;
		this.authorizerFirstName = authorizerFirstName;
		this.authorizerMiddleName = authorizerMiddleName;
		this.authorizerLastName = authorizerLastName;
		this.authorizerMailId = authorizerMailId;
		this.controlOwnerKycStatus = controlOwnerKycStatus;
		this.message = message;
	}

	/**
	 * @return the controlOwnerKycStatus
	 */
	public boolean isControlOwnerKycStatus() {
		return controlOwnerKycStatus;
	}

	/**
	 * @param controlOwnerKycStatus the controlOwnerKycStatus to set
	 */
	public void setControlOwnerKycStatus(boolean controlOwnerKycStatus) {
		this.controlOwnerKycStatus = controlOwnerKycStatus;
	}

	public String getBusinessLegalName() {
		return businessLegalName;
	}

	public void setBusinessLegalName(String businessLegalName) {
		this.businessLegalName = businessLegalName;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getIfCorporateHeadquarters() {
		return ifCorporateHeadquarters;
	}

	public void setIfCorporateHeadquarters(String ifCorporateHeadquarters) {
		this.ifCorporateHeadquarters = ifCorporateHeadquarters;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public boolean isSaveAsDraft() {
		return isSaveAsDraft;
	}

	public void setSaveAsDraft(boolean saveAsDraft) {
		isSaveAsDraft = saveAsDraft;
	}

	public String getFaxNumber2() {
		return faxNumber2;
	}

	public void setFaxNumber2(String faxNumber2) {
		this.faxNumber2 = faxNumber2;
	}

	public String getFederalTaxId() {
		return federalTaxId;
	}

	public void setFederalTaxId(String federalTaxId) {
		this.federalTaxId = federalTaxId;
	}

	public String getTypeOfLegalEntiry() {
		return typeOfLegalEntiry;
	}

	public void setTypeOfLegalEntiry(String typeOfLegalEntiry) {
		this.typeOfLegalEntiry = typeOfLegalEntiry;
	}

	public String getControlOwnerName() {
		return controlOwnerName;
	}

	public void setControlOwnerName(String controlOwnerName) {
		this.controlOwnerName = controlOwnerName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getOwnershipPercentage() {
		return ownershipPercentage;
	}

	public void setOwnershipPercentage(String ownershipPercentage) {
		this.ownershipPercentage = ownershipPercentage;
	}

	public List<BeneficiaryInformationDetails> getBeneficiaryInformationDetailsList() {
		return beneficiaryInformationDetailsList;
	}

	public void setBeneficiaryInformationDetailsList(
			List<BeneficiaryInformationDetails> beneficiaryInformationDetailsList) {
		this.beneficiaryInformationDetailsList = beneficiaryInformationDetailsList;
	}

	public String getControlOwnerCountry() {
		return controlOwnerCountry;
	}

	public void setControlOwnerCountry(String controlOwnerCountry) {
		this.controlOwnerCountry = controlOwnerCountry;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Address getLocationAddress() {
		return locationAddress;
	}

	public void setLocationAddress(Address locationAddress) {
		this.locationAddress = locationAddress;
	}

	public CompanyStatus getRegistrationStatus() {
		return registrationStatus;
	}

	public void setRegistrationStatus(CompanyStatus registrationStatus) {
		this.registrationStatus = registrationStatus;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean admin) {
		isAdmin = admin;
	}

	public String getAdminFirstName() {
		return adminFirstName;
	}

	public void setAdminFirstName(String adminFirstName) {
		this.adminFirstName = adminFirstName;
	}

	public String getAdminMiddleName() {
		return adminMiddleName;
	}

	public void setAdminMiddleName(String adminMiddleName) {
		this.adminMiddleName = adminMiddleName;
	}

	public String getAdminLastName() {
		return adminLastName;
	}

	public void setAdminLastName(String adminLastName) {
		this.adminLastName = adminLastName;
	}

	public String getAdminMailId() {
		return adminMailId;
	}

	public void setAdminMailId(String adminMailId) {
		this.adminMailId = adminMailId;
	}

	public String getAuthorizerFirstName() {
		return authorizerFirstName;
	}

	public void setAuthorizerFirstName(String authorizerFirstName) {
		this.authorizerFirstName = authorizerFirstName;
	}

	public String getAuthorizerMiddleName() {
		return authorizerMiddleName;
	}

	public void setAuthorizerMiddleName(String authorizerMiddleName) {
		this.authorizerMiddleName = authorizerMiddleName;
	}

	public String getAuthorizerLastName() {
		return authorizerLastName;
	}

	public void setAuthorizerLastName(String authorizerLastName) {
		this.authorizerLastName = authorizerLastName;
	}

	public String getAuthorizerMailId() {
		return authorizerMailId;
	}

	public void setAuthorizerMailId(String authorizerMailId) {
		this.authorizerMailId = authorizerMailId;
	}

	public boolean isAuthorizer() {
		return isAuthorizer;
	}

	public void setAuthorizer(boolean authorizer) {
		isAuthorizer = authorizer;
	}

	public boolean isAddressSame() {
		return isAddressSame;
	}

	public void setAddressSame(boolean addressSame) {
		isAddressSame = addressSame;
	}

	public String getControlOwnerPhone() {
		return controlOwnerPhone;
	}

	public void setControlOwnerPhone(String controlOwnerPhone) {
		this.controlOwnerPhone = controlOwnerPhone;
	}

	public Address getControlOwnerAddress() {
		return controlOwnerAddress;
	}

	public void setControlOwnerAddress(Address controlOwnerAddress) {
		this.controlOwnerAddress = controlOwnerAddress;
	}
}
