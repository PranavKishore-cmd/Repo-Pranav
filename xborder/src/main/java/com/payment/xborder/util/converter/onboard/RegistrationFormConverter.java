package com.payment.xborder.util.converter.onboard;

import java.util.List;

import com.payment.xborder.exception.BusinessException;
import com.payment.xborder.model.onboard.Address;
import com.payment.xborder.model.onboard.BeneficiaryInformationDetails;
import com.payment.xborder.model.onboard.RegistrationForm;
import com.payment.xborder.model.onboard.UserCompanyDetails;
import com.payment.xborder.model.onboard.ws.RegistrationFormRequest;
import com.payment.xborder.model.onboard.ws.RegistrationFormResponse;
import com.payment.xborder.model.onboard.ws.UpdateBeneficiaryOwnerKycRequest;

public class RegistrationFormConverter {

	public static RegistrationForm wsToModel(RegistrationFormRequest registrationFormRequest) {
		RegistrationForm registrationForm = new RegistrationForm();
		
		Address billingAddress = new Address();
		Address locationAddress = new Address();
		Address controlOwnerAddress = new Address();


		billingAddress.setAddress(registrationFormRequest.getBillingAddress().getAddress());
		billingAddress.setCity(registrationFormRequest.getBillingAddress().getCity());
		billingAddress.setCountry(registrationFormRequest.getBillingAddress().getCountry());
		billingAddress.setFaxNumber(registrationFormRequest.getBillingAddress().getFaxNumber());
		billingAddress.setPhoneNumber(registrationFormRequest.getBillingAddress().getPhoneNumber());
		billingAddress.setState(registrationFormRequest.getBillingAddress().getState());
		billingAddress.setZip(registrationFormRequest.getBillingAddress().getZip());
		
		locationAddress.setAddress(registrationFormRequest.getLocationAddress().getAddress());
		locationAddress.setCity(registrationFormRequest.getBillingAddress().getCity());
		locationAddress.setCountry(registrationFormRequest.getBillingAddress().getCountry());
		locationAddress.setFaxNumber(registrationFormRequest.getBillingAddress().getFaxNumber());
		locationAddress.setPhoneNumber(registrationFormRequest.getBillingAddress().getPhoneNumber());
		locationAddress.setState(registrationFormRequest.getBillingAddress().getState());
		locationAddress.setZip(registrationFormRequest.getBillingAddress().getZip());

		controlOwnerAddress.setAddress(registrationFormRequest.getControlOwnerAddress().getAddress());
		controlOwnerAddress.setCity(registrationFormRequest.getControlOwnerAddress().getCity());
		controlOwnerAddress.setState(registrationFormRequest.getControlOwnerAddress().getState());
		controlOwnerAddress.setCountry(registrationFormRequest.getControlOwnerAddress().getCountry());
		controlOwnerAddress.setZip(registrationFormRequest.getControlOwnerAddress().getZip());
		controlOwnerAddress.setPhoneNumber(registrationFormRequest.getControlOwnerAddress().getPhoneNumber());
		
		registrationForm.setBillingAddress(billingAddress);
		registrationForm.setLocationAddress(locationAddress);
		registrationForm.setAddressSame(registrationFormRequest.isAddressSame());
		
		registrationForm.setBusinessLegalName(registrationFormRequest.getBusinessLegalName());
		registrationForm.setBusinessName(registrationFormRequest.getBusinessName());
		registrationForm.setContactName(registrationFormRequest.getContactName());
		registrationForm.setIfCorporateHeadquarters(registrationFormRequest.getIfCorporateHeadquarters());
		registrationForm.setEmailId(registrationFormRequest.getEmailId());
		registrationForm.setWebsite(registrationFormRequest.getWebsite());

		registrationForm.setFederalTaxId(registrationFormRequest.getFederalTaxId());
		registrationForm.setSaveAsDraft(registrationFormRequest.isSaveAsDraft());
		registrationForm.setTypeOfLegalEntiry(registrationFormRequest.getTypeOfLegalEntiry());
		registrationForm.setControlOwnerName(registrationFormRequest.getControlOwnerName());
		registrationForm.setControlOwnerPhone(registrationFormRequest.getControlOwnerPhone());
		registrationForm.setControlOwnerAddress(controlOwnerAddress);
		registrationForm.setTitle(registrationFormRequest.getTitle());
		registrationForm.setDateOfBirth(registrationFormRequest.getDateOfBirth());
		registrationForm.setSsn(registrationFormRequest.getSsn());
		registrationForm.setOwnershipPercentage(registrationFormRequest.getOwnershipPercentage());
		registrationForm.setBeneficiaryInformationDetailsList(registrationFormRequest.getBeneficiaryInformationDetailsList());
		registrationForm.setRegistrationStatus(registrationFormRequest.getRegistrationStatus());

		registrationForm.setAdmin(registrationFormRequest.isAdmin());
		registrationForm.setAdminFirstName(registrationFormRequest.getAdminFirstName());
		registrationForm.setAdminMiddleName(registrationFormRequest.getAdminMiddleName());
		registrationForm.setAdminLastName(registrationFormRequest.getAdminLastName());
		registrationForm.setAdminMailId(registrationFormRequest.getAdminMailId());

		registrationForm.setAuthorizer(registrationFormRequest.isAuthorizer());
		registrationForm.setAuthorizerFirstName(registrationFormRequest.getAuthorizerFirstName());
		registrationForm.setAuthorizerMiddleName(registrationFormRequest.getAuthorizerMiddleName());
		registrationForm.setAuthorizerLastName(registrationFormRequest.getAuthorizerLastName());
		registrationForm.setAuthorizerMailId(registrationFormRequest.getAuthorizerMailId());
		
		return registrationForm;
	}
	
	public static RegistrationFormResponse modelToWs(RegistrationForm registrationForm) {
		RegistrationFormResponse formSectionResponse = new RegistrationFormResponse();
		
		Address billingAddress = new Address();
		Address locationAddress = new Address();
		Address controlOwnerAddress = new Address();
		
		billingAddress.setAddress(registrationForm.getBillingAddress().getAddress());
		billingAddress.setCity(registrationForm.getBillingAddress().getCity());
		billingAddress.setCountry(registrationForm.getBillingAddress().getCountry());
		billingAddress.setFaxNumber(registrationForm.getBillingAddress().getFaxNumber());
		billingAddress.setPhoneNumber(registrationForm.getBillingAddress().getPhoneNumber());
		billingAddress.setState(registrationForm.getBillingAddress().getState());
		billingAddress.setZip(registrationForm.getBillingAddress().getZip());
		
		locationAddress.setAddress(registrationForm.getLocationAddress().getAddress());
		locationAddress.setCity(registrationForm.getBillingAddress().getCity());
		locationAddress.setCountry(registrationForm.getBillingAddress().getCountry());
		locationAddress.setFaxNumber(registrationForm.getBillingAddress().getFaxNumber());
		locationAddress.setPhoneNumber(registrationForm.getBillingAddress().getPhoneNumber());
		locationAddress.setState(registrationForm.getBillingAddress().getState());
		locationAddress.setZip(registrationForm.getBillingAddress().getZip());

		controlOwnerAddress.setAddress(registrationForm.getControlOwnerAddress().getAddress());
		controlOwnerAddress.setCity(registrationForm.getControlOwnerAddress().getCity());
		controlOwnerAddress.setState(registrationForm.getControlOwnerAddress().getState());
		controlOwnerAddress.setCountry(registrationForm.getControlOwnerAddress().getCountry());
		controlOwnerAddress.setPhoneNumber(registrationForm.getControlOwnerAddress().getPhoneNumber());
		controlOwnerAddress.setZip(registrationForm.getControlOwnerAddress().getZip());
		
		formSectionResponse.setBillingAddress(billingAddress);
		formSectionResponse.setLocationAddress(locationAddress);
		formSectionResponse.setAddressSame(registrationForm.isAddressSame());
		
		formSectionResponse.setBusinessLegalName(registrationForm.getBusinessLegalName());
		formSectionResponse.setBusinessName(registrationForm.getBusinessName());
		formSectionResponse.setContactName(registrationForm.getContactName());
		formSectionResponse.setIfCorporateHeadquarters(registrationForm.getIfCorporateHeadquarters());
		formSectionResponse.setEmailId(registrationForm.getEmailId());
		formSectionResponse.setWebsite(registrationForm.getWebsite());
	
		formSectionResponse.setFederalTaxId(registrationForm.getFederalTaxId());
		formSectionResponse.setSaveAsDraft(registrationForm.isSaveAsDraft());
		formSectionResponse.setTypeOfLegalEntiry(registrationForm.getTypeOfLegalEntiry());
		formSectionResponse.setControlOwnerName(registrationForm.getControlOwnerName());
		formSectionResponse.setControlOwnerPhone(registrationForm.getControlOwnerPhone());
		formSectionResponse.setControlOwnerAddress(controlOwnerAddress);
		formSectionResponse.setTitle(registrationForm.getTitle());
		formSectionResponse.setDateOfBirth(registrationForm.getDateOfBirth());
		formSectionResponse.setSsn(registrationForm.getSsn());
		formSectionResponse.setOwnershipPercentage(registrationForm.getOwnershipPercentage());
		formSectionResponse.setBeneficiaryInformationDetailsList(registrationForm.getBeneficiaryInformationDetailsList());
		formSectionResponse.setRegistrationStatus(registrationForm.getRegistrationStatus());

		formSectionResponse.setAdmin(registrationForm.isAdmin());
		formSectionResponse.setAdminFirstName(registrationForm.getAdminFirstName());
		formSectionResponse.setAdminMiddleName(registrationForm.getAdminMiddleName());
		formSectionResponse.setAdminLastName(registrationForm.getAdminLastName());
		formSectionResponse.setAdminMailId(registrationForm.getAdminMailId());
		formSectionResponse.setAuthorizer(registrationForm.isAuthorizer());
		formSectionResponse.setAuthorizerFirstName(registrationForm.getAuthorizerFirstName());
		formSectionResponse.setAuthorizerMiddleName(registrationForm.getAuthorizerMiddleName());
		formSectionResponse.setAuthorizerLastName(registrationForm.getAuthorizerLastName());
		formSectionResponse.setAuthorizerMailId(registrationForm.getAuthorizerMailId());
		
		return formSectionResponse;
	}
	
	public static List<UserCompanyDetails> modelToWs(List<UserCompanyDetails> userCompanyDetailsList) {
		return userCompanyDetailsList;
	}
	
	public static List<RegistrationForm> companyModelToWs(List<RegistrationForm> companyList) {
		return companyList;
	}
	
	private static void validatePhoneNumber(String phoneNumber)
   {
      if (phoneNumber != null)
      {
      String pattern =
            "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
      if (!phoneNumber.matches(pattern))
      {
         throw new BusinessException("Invalid Phone Number");
      }
      }
	}
	
	private static void validateEmailId(String email) {
      if (email != null)
      {
         String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
         if (!email.matches(regex))
         {
            throw new BusinessException("Invalid Mail ID");
         }
      }
	}
	
	private static void validateBeneficiaryPercentage(final List<BeneficiaryInformationDetails> beneficiaryList) {
		Integer ownershipPercentage = 0;
		for(BeneficiaryInformationDetails beneficiaryDetails: beneficiaryList) {
			Integer intVal = Integer.parseInt(beneficiaryDetails.getOwnershipPercentage());
			ownershipPercentage = ownershipPercentage + intVal;
		}
		if(ownershipPercentage > 100) {
			throw new BusinessException("Ownership Percentage cannot be more than 100%");
		}
		System.out.println(ownershipPercentage);
	}
	
	public BeneficiaryInformationDetails convertBeneficiaryInformationDetailsWsToModel(UpdateBeneficiaryOwnerKycRequest beneficiaryList) {
		BeneficiaryInformationDetails beneficiaryInformationDetails = beneficiaryList.getBeneficiaryDetailsList().get(0);
		return beneficiaryInformationDetails;
	}
}
