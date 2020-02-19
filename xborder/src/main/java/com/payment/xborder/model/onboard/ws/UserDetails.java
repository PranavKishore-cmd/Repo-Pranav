package com.payment.xborder.model.onboard.ws;

import com.payment.xborder.enums.RegisterUserType;
import com.payment.xborder.enums.UserStatus;
import com.payment.xborder.model.onboard.User;

public class UserDetails
{
   private String userId;

   private String firstName;

   private String middleName;

   private String lastName;

   private RegisterUserType registerUserType;

   private String email;

   private UserStatus userStatus;

   private String companyRefId;

   private String roleId;

   private boolean gAuthEnabled;

   public UserDetails(
         String userId,
         String firstName,
         String middleName,
         String lastName,
         RegisterUserType registerUserType,
         String email,
         UserStatus userStatus,
         String companyRefId,
         String roleID,
         boolean gAuthEnabled
   )
   {
      this.userId = userId;
      this.firstName = firstName;
      this.middleName = middleName;
      this.lastName = lastName;
      this.registerUserType = registerUserType;
      this.email = email;
      this.userStatus = userStatus;
      this.companyRefId = companyRefId;
      this.roleId = roleID;
      this.gAuthEnabled = gAuthEnabled;
   }

   public UserDetails(User user){
      this.userId = user.getUserId();
      this.firstName = user.getFirstName();
      this.middleName = user.getMiddleName();
      this.lastName = user.getLastName();
      this.registerUserType = user.getRegisterUserType();
      this.email = user.getEmail();
      this.userStatus = user.getUserStatus();
      this.companyRefId = user.getCompanyRefId();
      this.roleId = user.getRoleId();
      this.gAuthEnabled = user.getGAuthState();
   }

   public String getUserId()
   {
      return userId;
   }

   public void setUserId(String userId)
   {
      this.userId = userId;
   }

   public String getFirstName()
   {
      return firstName;
   }

   public void setFirstName(String firstName)
   {
      this.firstName = firstName;
   }

   public String getMiddleName()
   {
      return middleName;
   }

   public void setMiddleName(String middleName)
   {
      this.middleName = middleName;
   }

   public String getLastName()
   {
      return lastName;
   }

   public void setLastName(String lastName)
   {
      this.lastName = lastName;
   }

   public RegisterUserType getRegisterUserType()
   {
      return registerUserType;
   }

   public void setRegisterUserType(RegisterUserType registerUserType)
   {
      this.registerUserType = registerUserType;
   }

   public String getEmail()
   {
      return email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   public UserStatus getUserStatus()
   {
      return userStatus;
   }

   public void setUserStatus(UserStatus userStatus)
   {
      this.userStatus = userStatus;
   }

   public String getCompanyRefId()
   {
      return companyRefId;
   }

   public void setCompanyRefId(String companyRefId)
   {
      this.companyRefId = companyRefId;
   }

   public String getRoleId()
   {
      return roleId;
   }

   public void setRoleId(String roleId)
   {
      this.roleId = roleId;
   }

   public boolean isgAuthEnabled()
   {
      return gAuthEnabled;
   }

   public void setgAuthEnabled(boolean gAuthEnabled)
   {
      this.gAuthEnabled = gAuthEnabled;
   }

   @Override
   public String toString()
   {
      return "UserDetails{" +
             "userId='" + userId + '\'' +
             ", firstName='" + firstName + '\'' +
             ", middleName='" + middleName + '\'' +
             ", lastName='" + lastName + '\'' +
             ", registerUserType=" + registerUserType +
             ", email='" + email + '\'' +
             ", userStatus=" + userStatus +
             ", companyRefId='" + companyRefId + '\'' +
             ", roleId =" + roleId +
             ", gAuthEnabled=" + gAuthEnabled +
             '}';
   }


}


