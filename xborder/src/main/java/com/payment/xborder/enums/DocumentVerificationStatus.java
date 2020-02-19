package com.payment.xborder.enums;

import java.util.Arrays;

public enum DocumentVerificationStatus
{

	PENDING("PENDING"),
   VERIFIED("VERIFIED"),
   INVALID("INVALID"),
   DELETED("DELETED") ;

	private final String status;

	private DocumentVerificationStatus(String status) {
		this.status = status;
	}

	public DocumentVerificationStatus from(String value) {
		return Arrays.asList(DocumentVerificationStatus.values()).stream().filter(enumVal -> enumVal.status.equals(value)).findFirst()
                   .orElse(null);
	}
}
