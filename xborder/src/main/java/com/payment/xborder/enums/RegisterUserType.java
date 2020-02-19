package com.payment.xborder.enums;

import java.util.Arrays;

public enum RegisterUserType {

    MSB("MSB"), ENTERPRISE("ENTERPRISE");

    private final String role;

    private RegisterUserType(String role) {
        this.role = role;
    }

    public RegisterUserType from(String value) {
        return Arrays.asList(RegisterUserType.values()).stream().filter(enumVal -> enumVal.role.equals(value)).findFirst()
                .orElse(null);
    }
}
