package com.payment.xborder.enums;

import java.util.Arrays;

public enum UserRole {

   ENTERPRISE_ADMIN("ENTERPRISE_ADMIN"),
   ENTERPRISE_COMPLIANCE_OFFICER("ENTERPRISE_COMPLIANCE_OFFICER"),
   ENTERPRISE_SUPPORT_OFFICER("ENTERPRISE_COMPLIANCE_OFFICER"),
   MSB_ADMIN("MSB_ADMIN"),
   MSB_AUTHORIZER("MSB_AUTHORIZER"),
   MSB_COMPLIANCE_OFFICER("MSB_COMPLIANCE_OFFICER"),
   MSB_FINANCE_OFFICER("MSB_FINANCE_OFFICER"),
   MSB_APPROVER("MSB_FINANCE_OFFICER"),
   MSB_USER("MSB_USER"),
   MSB_REGISTRAR("MSB_REGISTRAR")
   ;

	private final String role;

	private UserRole(String role) {
		this.role = role;
	}

	public UserRole from(String value) {
		return Arrays.asList(UserRole.values()).stream().filter(enumVal -> enumVal.role.equals(value)).findFirst()
				.orElse(null);
	}
}
