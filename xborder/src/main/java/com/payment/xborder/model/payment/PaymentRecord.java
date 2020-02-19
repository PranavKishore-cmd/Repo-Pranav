package com.payment.xborder.model.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.payment.xborder.enums.PaymentRecordStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Calendar;

@JsonInclude (JsonInclude.Include.NON_EMPTY)
@Document (collection = "PAYMENT_RECORD")
public class PaymentRecord
{

   @Id
   private String              paymentRecordId;
   private String              paymentBatchId;
   private String              paymentSenderID;
   private String              paymentSenderCompID;
   private String              paymentReceiverID;
   private String              paymentReceiverCompID;
   private PaymentRecordStatus paymentRecordStatus;
   private long                paymentRecordCreatedTime;
   private long                paymentRecordUpdatedTime;

   private String              firstName;
   private String              middleName;
   private String              lastName;
   private String              companyName;
   private String              addressLine1;
   private String              addressLine2;
   private String              country;
   private String              state;
   private String              cityName;
   private String              zipCode;
   private String              landLineNumber;
   private String              mobileNumber;
   private String              emailAddress;
   private String              idType;
   private String              idNumber;
   private String              iDIssueDate;
   private String              iDExpiryDate;
   private String              iDIssueAuthority;
   private String              dateOfBirth;
   private String              birthCityName;
   private String              birthCountry;
   private String              gender;
   private String              occupation;
   private String              accountType;
   private String              accountNo;
   private String              routingNumber;
   private String              bankName;
   private String              branchName;
   private String              senderKey;

   /**
    *
    */
   public PaymentRecord()
   {
      this.paymentRecordCreatedTime = Calendar.getInstance().getTimeInMillis();
   }

   public PaymentRecord(
         String paymentBatchId,
         String paymentSenderID,
         String paymentSenderCompID,
         String paymentReceiverID,
         String paymentReceiveCompID,
         PaymentRecordStatus paymentRecordStatus
   )
   {
      this.paymentBatchId = paymentBatchId;
      this.paymentSenderID = paymentSenderID;
      this.paymentReceiverID = paymentReceiverID;
      this.paymentRecordStatus = paymentRecordStatus;
      this.paymentRecordCreatedTime = Calendar.getInstance().getTimeInMillis();
   }

   public PaymentRecord(
         String paymentBatchId,
         String paymentSenderID,
         String paymentSenderCompID,
         String paymentReceiverID,
         String paymentReceiverCompID,
         PaymentRecordStatus paymentRecordStatus,
         long paymentRecordCreatedTime,
         long paymentRecordUpdatedTime,
         String firstName,
         String middleName,
         String lastName,
         String companyName,
         String addressLine1,
         String addressLine2,
         String country,
         String state,
         String cityName,
         String zipCode,
         String landLineNumber,
         String mobileNumber,
         String emailAddress,
         String idType,
         String idNumber,
         String iDIssueDate,
         String iDExpiryDate,
         String iDIssueAuthority,
         String dateOfBirth,
         String birthCityName,
         String birthCountry,
         String gender,
         String occupation,
         String accountType,
         String accountNo,
         String routingNumber,
         String bankName,
         String branchName,
         String senderKey
   )
   {
      this.paymentBatchId = paymentBatchId;
      this.paymentSenderID = paymentSenderID;
      this.paymentSenderCompID = paymentSenderCompID;
      this.paymentReceiverID = paymentReceiverID;
      this.paymentReceiverCompID = paymentReceiverCompID;
      this.paymentRecordStatus = paymentRecordStatus;
      this.paymentRecordCreatedTime = paymentRecordCreatedTime;
      this.paymentRecordUpdatedTime = paymentRecordUpdatedTime;
      this.firstName = firstName;
      this.middleName = middleName;
      this.lastName = lastName;
      this.companyName = companyName;
      this.addressLine1 = addressLine1;
      this.addressLine2 = addressLine2;
      this.country = country;
      this.state = state;
      this.cityName = cityName;
      this.zipCode = zipCode;
      this.landLineNumber = landLineNumber;
      this.mobileNumber = mobileNumber;
      this.emailAddress = emailAddress;
      this.idType = idType;
      this.idNumber = idNumber;
      this.iDIssueDate = iDIssueDate;
      this.iDExpiryDate = iDExpiryDate;
      this.iDIssueAuthority = iDIssueAuthority;
      this.dateOfBirth = dateOfBirth;
      this.birthCityName = birthCityName;
      this.birthCountry = birthCountry;
      this.gender = gender;
      this.occupation = occupation;
      this.accountType = accountType;
      this.accountNo = accountNo;
      this.routingNumber = routingNumber;
      this.bankName = bankName;
      this.branchName = branchName;
      this.senderKey = senderKey;
   }

   public String getPaymentRecordId()
   {
      return paymentRecordId;
   }

   public void setPaymentRecordId(String paymentRecordId)
   {
      this.paymentRecordId = paymentRecordId;
   }

   public String getPaymentBatchId()
   {
      return paymentBatchId;
   }

   public void setPaymentBatchId(String paymentBatchId)
   {
      this.paymentBatchId = paymentBatchId;
   }

   public String getPaymentSenderID()
   {
      return paymentSenderID;
   }

   public void setPaymentSenderID(String paymentSenderID)
   {
      this.paymentSenderID = paymentSenderID;
   }

   public String getPaymentSenderCompID()
   {
      return paymentSenderCompID;
   }

   public void setPaymentSenderCompID(String paymentSenderCompID)
   {
      this.paymentSenderCompID = paymentSenderCompID;
   }

   public String getPaymentReceiverID()
   {
      return paymentReceiverID;
   }

   public void setPaymentReceiverID(String paymentReceiverID)
   {
      this.paymentReceiverID = paymentReceiverID;
   }

   public String getPaymentReceiverCompID()
   {
      return paymentReceiverCompID;
   }

   public void setPaymentReceiverCompID(String paymentReceiverCompID)
   {
      this.paymentReceiverCompID = paymentReceiverCompID;
   }

   public PaymentRecordStatus getPaymentRecordStatus()
   {
      return paymentRecordStatus;
   }

   public void setPaymentRecordStatus(PaymentRecordStatus paymentRecordStatus)
   {
      this.paymentRecordStatus = paymentRecordStatus;
   }

   public long getPaymentRecordCreatedTime()
   {
      return paymentRecordCreatedTime;
   }

   public void setPaymentRecordCreatedTime(long paymentRecordCreatedTime)
   {
      this.paymentRecordCreatedTime = paymentRecordCreatedTime;
   }

   public long getPaymentRecordUpdatedTime()
   {
      return paymentRecordUpdatedTime;
   }

   public void setPaymentRecordUpdatedTime(long paymentRecordUpdatedTime)
   {
      this.paymentRecordUpdatedTime = paymentRecordUpdatedTime;
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

   public String getCompanyName()
   {
      return companyName;
   }

   public void setCompanyName(String companyName)
   {
      this.companyName = companyName;
   }

   public String getAddressLine1()
   {
      return addressLine1;
   }

   public void setAddressLine1(String addressLine1)
   {
      this.addressLine1 = addressLine1;
   }

   public String getAddressLine2()
   {
      return addressLine2;
   }

   public void setAddressLine2(String addressLine2)
   {
      this.addressLine2 = addressLine2;
   }

   public String getCountry()
   {
      return country;
   }

   public void setCountry(String country)
   {
      this.country = country;
   }

   public String getState()
   {
      return state;
   }

   public void setState(String state)
   {
      this.state = state;
   }

   public String getCityName()
   {
      return cityName;
   }

   public void setCityName(String cityName)
   {
      this.cityName = cityName;
   }

   public String getZipCode()
   {
      return zipCode;
   }

   public void setZipCode(String zipCode)
   {
      this.zipCode = zipCode;
   }

   public String getLandLineNumber()
   {
      return landLineNumber;
   }

   public void setLandLineNumber(String landLineNumber)
   {
      this.landLineNumber = landLineNumber;
   }

   public String getMobileNumber()
   {
      return mobileNumber;
   }

   public void setMobileNumber(String mobileNumber)
   {
      this.mobileNumber = mobileNumber;
   }

   public String getEmailAddress()
   {
      return emailAddress;
   }

   public void setEmailAddress(String emailAddress)
   {
      this.emailAddress = emailAddress;
   }

   public String getIdType()
   {
      return idType;
   }

   public void setIdType(String idType)
   {
      this.idType = idType;
   }

   public String getIdNumber()
   {
      return idNumber;
   }

   public void setIdNumber(String idNumber)
   {
      this.idNumber = idNumber;
   }

   public String getiDIssueDate()
   {
      return iDIssueDate;
   }

   public void setiDIssueDate(String iDIssueDate)
   {
      this.iDIssueDate = iDIssueDate;
   }

   public String getiDExpiryDate()
   {
      return iDExpiryDate;
   }

   public void setiDExpiryDate(String iDExpiryDate)
   {
      this.iDExpiryDate = iDExpiryDate;
   }

   public String getiDIssueAuthority()
   {
      return iDIssueAuthority;
   }

   public void setiDIssueAuthority(String iDIssueAuthority)
   {
      this.iDIssueAuthority = iDIssueAuthority;
   }

   public String getDateOfBirth()
   {
      return dateOfBirth;
   }

   public void setDateOfBirth(String dateOfBirth)
   {
      this.dateOfBirth = dateOfBirth;
   }

   public String getBirthCityName()
   {
      return birthCityName;
   }

   public void setBirthCityName(String birthCityName)
   {
      this.birthCityName = birthCityName;
   }

   public String getBirthCountry()
   {
      return birthCountry;
   }

   public void setBirthCountry(String birthCountry)
   {
      this.birthCountry = birthCountry;
   }

   public String getGender()
   {
      return gender;
   }

   public void setGender(String gender)
   {
      this.gender = gender;
   }

   public String getOccupation()
   {
      return occupation;
   }

   public void setOccupation(String occupation)
   {
      this.occupation = occupation;
   }

   public String getAccountType()
   {
      return accountType;
   }

   public void setAccountType(String accountType)
   {
      this.accountType = accountType;
   }

   public String getAccountNo()
   {
      return accountNo;
   }

   public void setAccountNo(String accountNo)
   {
      this.accountNo = accountNo;
   }

   public String getRoutingNumber()
   {
      return routingNumber;
   }

   public void setRoutingNumber(String routingNumber)
   {
      this.routingNumber = routingNumber;
   }

   public String getBankName()
   {
      return bankName;
   }

   public void setBankName(String bankName)
   {
      this.bankName = bankName;
   }

   public String getBranchName()
   {
      return branchName;
   }

   public void setBranchName(String branchName)
   {
      this.branchName = branchName;
   }

   public String getSenderKey()
   {
      return senderKey;
   }

   public void setSenderKey(String senderKey)
   {
      this.senderKey = senderKey;
   }
}

