package com.payment.xborder.model.onboard;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BeneficiaryInformationDetails {
	
	public BeneficiaryInformationDetails() {
		
	}
	
	private String beneficiaryOwnerName;
	private String homeAddress;
	private String city;
	private String state;
	private String zip;
	private String phoneNumber;
	private String country;
	private String title;
	@JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private String dateOfBirth;
	private String ssn;
	private String ownershipPercentage;
	private boolean isKycVerified = false;
	/**
	 * @return the beneficiaryOwnerName
	 */
	public String getBeneficiaryOwnerName() {
		return beneficiaryOwnerName;
	}
	/**
	 * @param beneficiaryOwnerName the beneficiaryOwnerName to set
	 */
	public void setBeneficiaryOwnerName(String beneficiaryOwnerName) {
		this.beneficiaryOwnerName = beneficiaryOwnerName;
	}
	/**
	 * @return the homeAddress
	 */
	public String getHomeAddress() {
		return homeAddress;
	}
	/**
	 * @param homeAddress the homeAddress to set
	 */
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}
	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	/**
	 * @return the ssn
	 */
	public String getSsn() {
		return ssn;
	}
	/**
	 * @param ssn the ssn to set
	 */
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	/**
	 * @return the ownershipPercentage
	 */
	public String getOwnershipPercentage() {
		return ownershipPercentage;
	}
	/**
	 * @param ownershipPercentage the ownershipPercentage to set
	 */
	public void setOwnershipPercentage(String ownershipPercentage) {
		this.ownershipPercentage = ownershipPercentage;
	}
	/**
	 * @return the isKycVerified
	 */
	public boolean isKycVerified() {
		return isKycVerified;
	}
	/**
	 * @param isKycVerified the isKycVerified to set
	 */
	public void setKycVerified(boolean isKycVerified) {
		this.isKycVerified = isKycVerified;
	}
	/**
	 * @param beneficiaryOwnerName
	 * @param homeAddress
	 * @param city
	 * @param state
	 * @param zip
	 * @param phoneNumber
	 * @param country
	 * @param title
	 * @param dateOfBirth
	 * @param ssn
	 * @param ownershipPercentage
	 * @param isKycVerified
	 */
	public BeneficiaryInformationDetails(String beneficiaryOwnerName, String homeAddress, String city, String state,
			String zip, String phoneNumber, String country, String title, String dateOfBirth, String ssn,
			String ownershipPercentage, boolean isKycVerified) {
		super();
		this.beneficiaryOwnerName = beneficiaryOwnerName;
		this.homeAddress = homeAddress;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phoneNumber = phoneNumber;
		this.country = country;
		this.title = title;
		this.dateOfBirth = dateOfBirth;
		this.ssn = ssn;
		this.ownershipPercentage = ownershipPercentage;
		this.isKycVerified = isKycVerified;
	}

	
}
