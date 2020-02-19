package com.payment.xborder.enums;

import java.util.Arrays;

public enum ParentRoleType {

	MSB("MSB"), ENTERPRISE("ENTERPRISE");

	private final String role;

	private ParentRoleType(String role) {
		this.role = role;
	}

	public ParentRoleType from(String value) {
		return Arrays.asList(ParentRoleType.values()).stream().filter(enumVal -> enumVal.role.equals(value)).findFirst()
				.orElse(null);
	}
}
