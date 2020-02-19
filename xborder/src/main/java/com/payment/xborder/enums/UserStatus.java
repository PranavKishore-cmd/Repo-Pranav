package com.payment.xborder.enums;

import java.util.Arrays;

public enum UserStatus {

   NEW("NEW"),
   INACTIVE("INACTIVE"),
   ACTIVE("ACTIVE"),
   APPROVED("APPROVED"),
   PENDING_ACTIVATION("PENDING_ACTIVATION");

   private final String status;

   private UserStatus(String status) {
      this.status = status;
   }

   public UserStatus from(String value) {
      return Arrays.asList(UserStatus.values()).stream().filter(enumVal -> enumVal.status.equals(value)).findFirst()
                   .orElse(null);
   }
}
