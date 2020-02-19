package com.payment.xborder.enums;

import java.util.Arrays;

public enum PaymentFileStatus
{

	CREATED("CREATED"),
   VALIDATED("VALIDATED"),
   INVALIDATED("INVALIDATED"),
   PROCESSED("PROCESSED"),
   DELETED("DELETED");

	private final String status;

	private PaymentFileStatus(String status) {
		this.status = status;
	}

	public PaymentFileStatus from(String value) {
		return Arrays.asList(PaymentFileStatus.values()).stream().filter(enumVal -> enumVal.status.equals(value)).findFirst()
                   .orElse(null);
	}
}
