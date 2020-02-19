package com.payment.xborder.enums;

import java.util.Arrays;

public enum CompanyStatus
{

	NEW_REGISTRATION("NEW_REGISTRATION"),
	REG_INCOMPLETE("REG_INCOMPLETE"),
   DOC_UPLOAD_PENDING("DOC_UPLOAD_PENDING"),
	APPROVAL_PENDING("APPROVAL_PENDING"),
	DOC_VERIFICATION("DOC_VERIFICATION"),
	KYC_VERIFICATION("KYC_VERIFICATION"),
	COMPLIANCE_VERIFICATION("COMPLIANCE_VERIFICATION"),
   PENDING_ACTIVATION("PENDING_ACTIVATION"),
	ACTIVE("ACTIVATE"),
	INACTIVE("INACTIVE"),
	DOC_VERIFICATION_FAILED("DOC_VERIFICATION_FAILED"),
	KYC_VERIFICATION_FAILED("KYC_VERIFICATION_FAILED");

	private final String role;

	private CompanyStatus(String role) {
		this.role = role;
	}

	public CompanyStatus from(String value) {
		return Arrays.asList(CompanyStatus.values()).stream().filter(enumVal -> enumVal.role.equals(value)).findFirst()
				.orElse(null);
	}
}
