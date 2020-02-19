package com.payment.xborder.enums;

import java.util.Arrays;

public enum PairingStatus {

	PAIRING_REQUESTED("PAIRING_REQUESTED"),
   PAIRING_APPROVED("PAIRING_APPROVED"),
   PAIRING_REJECTED("PAIRING_REJECTED"),
   PAIRING_ACTIVE("PAIRING_ACTIVE"),
   PAIRING_INACTIVE("PAIRING_INACTIVE");

	private final String status;

	private PairingStatus(String status) {
		this.status = status;
	}

	public PairingStatus from(String value) {
		return Arrays.asList(PairingStatus.values()).stream().filter(enumVal -> enumVal.status.equals(value)).findFirst()
				.orElse(null);
	}
}
