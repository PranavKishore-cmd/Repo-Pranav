package com.payment.xborder.model.onboard;

public class Address {

	private String address;
	private String phoneNumber;
	private String country;
	private String city;
	private String state;
	private String zip;
	private String faxNumber;
	
	
	public Address() {
		
	}
	/**
	 * @param address
	 * @param phoneNumber
	 * @param country
	 * @param city
	 * @param state
	 * @param zip
	 * @param faxNumber
	 */
	public Address(String address, String phoneNumber, String country, String city, String state,
			String zip, String faxNumber) {
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.country = country;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.faxNumber = faxNumber;
	}
	/**
	 * @return the phoneNumber
	 */
	public final String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public final void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the country
	 */
	public final String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public final void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the city
	 */
	public final String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public final void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the state
	 */
	public final String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public final void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the zip
	 */
	public final String getZip() {
		return zip;
	}
	/**
	 * @param zip the zip to set
	 */
	public final void setZip(String zip) {
		this.zip = zip;
	}
	/**
	 * @return the faxNumber
	 */
	public final String getFaxNumber() {
		return faxNumber;
	}
	/**
	 * @param faxNumber the faxNumber to set
	 */
	public final void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	/**
	 * @return the address
	 */
	public final String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public final void setAddress(String address) {
		this.address = address;
	}
	
}
