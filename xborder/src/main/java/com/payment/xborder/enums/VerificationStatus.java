package com.payment.xborder.enums;

import java.util.Arrays;

public enum VerificationStatus {

	PENDING("PENDING"), VERIFIED("VERIFIED"), REGISTERED("REGISTERED");

	private final String status;

	private VerificationStatus(String status) {
		this.status = status;
	}

	public VerificationStatus from(String value) {
		return Arrays.asList(VerificationStatus.values()).stream().filter(enumVal -> enumVal.status.equals(value)).findFirst()
				.orElse(null);
	}
}
