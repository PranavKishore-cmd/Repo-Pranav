package com.payment.xborder.enums;

import java.util.Arrays;

public enum PaymentRecordStatus
{

	CREATED("CREATED"),
   PROCESSED("PROCESSED"),
   DELETED("DELETED"),
   SENDER_APPROVAL_PENDING("SENDER_APPROVAL_PENDING"),
   SENDER_AUDITOR_PENDING("SENDER_AUDITOR_PENDING"),
   AUDITED("AUDITED")
   ;

	private final String status;

	private PaymentRecordStatus(String status) {
		this.status = status;
	}

	public PaymentRecordStatus from(String value) {
		return Arrays.asList(PaymentRecordStatus.values()).stream().filter(enumVal -> enumVal.status.equals(value)).findFirst()
                   .orElse(null);
	}
}
