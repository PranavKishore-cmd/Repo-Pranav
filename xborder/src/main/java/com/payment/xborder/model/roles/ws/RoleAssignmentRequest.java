package com.payment.xborder.model.roles.ws;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.payment.xborder.enums.ParentRoleType;

import javax.validation.constraints.NotBlank;

@JsonInclude (JsonInclude.Include.NON_EMPTY)
public class RoleAssignmentRequest
{

   @NotBlank
   private String roleName;

   @NotBlank
   private String userEmailId;

   public RoleAssignmentRequest(
         @NotBlank String roleName,
         @NotBlank String userEmailId
   )
   {
      this.roleName = roleName;
      this.userEmailId = userEmailId;
   }

   public RoleAssignmentRequest()
   {
   }

   public String getRoleName()
   {
      return roleName;
   }

   public void setRoleName(String roleName)
   {
      this.roleName = roleName;
   }

   public String getUserEmailId()
   {
      return userEmailId;
   }

   public void setUserEmailId(String userEmailId)
   {
      this.userEmailId = userEmailId;
   }

   @Override
   public String toString()
   {
      return "RoleAssignmentRequest{" +
             "roleName='" + roleName + '\'' +
             ", userEmailId='" + userEmailId + '\'' +
             '}';
   }
}
