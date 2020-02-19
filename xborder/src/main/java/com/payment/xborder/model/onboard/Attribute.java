package com.payment.xborder.model.onboard;

public class Attribute {

	/**
	 * 
	 */
	public Attribute() {
		super();
	}

	private String attributeName;
	private String attributeValue;

	public Attribute(String attributeName, String attributeValue) {
		super();
		this.attributeName = attributeName;
		this.attributeValue = attributeValue;
	}

	/**
	 * @return the attributeName
	 */
	public final String getAttributeName() {
		return attributeName;
	}

	/**
	 * @param attributeName the attributeName to set
	 */
	public final void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	/**
	 * @return the attributeValue
	 */
	public final String getAttributeValue() {
		return attributeValue;
	}

	/**
	 * @param attributeValue the attributeValue to set
	 */
	public final void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	@Override
	public String toString() {
		return "Attribute [attributeName=" + attributeName + ", attributeValue=" + attributeValue + "]";
	}

}
