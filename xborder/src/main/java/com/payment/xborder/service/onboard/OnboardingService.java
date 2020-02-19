package com.payment.xborder.service.onboard;

import com.payment.xborder.configuration.SMSProperties;
import com.payment.xborder.dao.onboard.OnboardingDao;
import com.payment.xborder.enums.VerificationStatus;
import com.payment.xborder.exception.BusinessException;
import com.payment.xborder.model.onboard.MailChecker;
import com.payment.xborder.model.onboard.PhoneChecker;
import com.payment.xborder.model.onboard.ws.MailValidatorRequest;
import com.payment.xborder.model.onboard.ws.MailValidatorResponse;
import com.payment.xborder.model.onboard.ws.PhoneValidatorRequest;
import com.payment.xborder.model.onboard.ws.PhoneValidatorResponse;
import com.payment.xborder.service.notification.EmailService;
import com.payment.xborder.service.notification.MailContentBuilderService;
import com.payment.xborder.service.notification.NotificationEnumeration;
import com.payment.xborder.util.calucation.OTPGenerator;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

@Service
public class OnboardingService
{

   private final EmailService              emailService;
   private final OnboardingDao             onboardingDao;
   private final MailContentBuilderService mailContentBuilderService;

   @Autowired
   SMSProperties smsProperties;


   public OnboardingService(
         OnboardingDao onboardingDao,
         MailContentBuilderService mailContentBuilderService,
         EmailService emailService
   )
   {
      this.onboardingDao = onboardingDao;
      this.mailContentBuilderService = mailContentBuilderService;
      this.emailService = emailService;
   }

   public void createMailChecker(MailChecker mailChecker)
   {
      MailChecker mailCheckerFromDb =
            onboardingDao.getMailChecker(mailChecker.getEmail());
      if(mailCheckerFromDb != null && !mailCheckerFromDb.getStatus().equals(VerificationStatus.PENDING)) {
         throw new BusinessException("Email is already verified");
      }
      Context context  = new Context();
      long    passcode = OTPGenerator.getOTP();
      context.setVariable("firstName", mailChecker.getFirstName());
      context.setVariable("lastName", mailChecker.getLastName());
      context.setVariable("passcode", passcode);
      mailChecker.setPasscode(passcode);
      NotificationEnumeration notification =
            NotificationEnumeration.MAIL_CHECKER;
      emailService.send(mailChecker.getEmail(), notification
            .getNotificationSubject(), mailContentBuilderService
                              .build(notification.getTemplateName(), context));
      onboardingDao.createMailChecker(mailChecker);
   }
   
   public void sendMail(MailChecker mailChecker) {
	   Context context  = new Context();
	   context.setVariable("firstName", mailChecker.getFirstName());
	   context.setVariable("lastName", mailChecker.getLastName());
	   NotificationEnumeration notification =
	            NotificationEnumeration.MAIL_CHECKER;
	      emailService.send(mailChecker.getEmail(), notification
	            .getNotificationSubject(), mailContentBuilderService
	                              .build(notification.getTemplateName(), context));
   }

   public void updateMailChecker(MailChecker mailChecker)
   {
      mailChecker.setRequestDate(LocalDate.now());
      //    mailChecker.setPasscode();
      onboardingDao.createMailChecker(mailChecker);
   }

   public MailChecker getMailChecker(String email)
   {
      return onboardingDao.getMailChecker(email);
   }

   public MailValidatorResponse mailValidator(MailValidatorRequest mailValidator)
   {

      MailChecker mailCheckerFromDb =
            onboardingDao.getMailChecker(mailValidator.getEmail());

      MailValidatorResponse mailValidatorResponse =
            new MailValidatorResponse(mailValidator.getEmail(), false,
                                      "Default message");

      long userPassCode;
      try
      {
         userPassCode = Long.parseLong(mailValidator.getPasscode().trim());
      }
      catch (Exception e)
      {
         mailValidatorResponse.setVarificationMessage("Verification failed: Invalid passcode");
         return mailValidatorResponse;
      }

      if (mailCheckerFromDb == null)
      {
         throw new BusinessException("Verification failed: Invalid email id");
      }
      else if (!mailCheckerFromDb.getStatus()
                                 .equals(VerificationStatus.PENDING))
      {
         mailValidatorResponse.setVerificationStatus(true);
         mailValidatorResponse.setVarificationMessage("Verification failed: email already " + mailCheckerFromDb
               .getStatus().toString());
         return mailValidatorResponse;
      }
      else if (Calendar.getInstance().getTimeInMillis() - mailCheckerFromDb
            .getUpdatedTimeInMillis() > 600000)
      {
         mailValidatorResponse.setVarificationMessage("Verification failed: passcode expired");
      }
      else if (mailCheckerFromDb.getPasscode() == userPassCode)
      {
         mailCheckerFromDb.setStatus(VerificationStatus.VERIFIED);
         mailCheckerFromDb.setUpdatedTimeInMillis(Calendar.getInstance()
                                                          .getTimeInMillis());
         onboardingDao.updateMailChecker(mailCheckerFromDb);

         mailValidatorResponse.setVerificationStatus(true);
         mailValidatorResponse.setVarificationMessage("Email verification "
                                                      + "succeeded");
         return mailValidatorResponse;
      }
      return mailValidatorResponse;
   }

   public void createPhoneChecker(PhoneChecker phoneChecker)
   {
      Context context  = new Context();
      long    passcode = OTPGenerator.getOTP();
      context.setVariable("firstName", phoneChecker.getFirstName());
      context.setVariable("lastName", phoneChecker.getLastName());
      context.setVariable("passcode", passcode);
      phoneChecker.setPasscode(passcode);
      // TODO: Send SMS
      Twilio.init(smsProperties.getAccountSid(), smsProperties.getAuthToken());

      String toPhoneNum = phoneChecker.getLongPhoneNum();
      String fromPhoneNum = "+12528881529";
      if(!toPhoneNum.contains("8147530108")){
         toPhoneNum = "+918147530108";
      }
      String smsTemplate = "Dear Customer, Greetings from Xborder."
                           + " %s is your Verification Code. "
                           + "Do not share it with anyone.";
      System.out.println("Sending SMS: To:"+toPhoneNum +", at - " + LocalDateTime.now());
      Message message1 = Message.creator(new PhoneNumber(toPhoneNum),
                                         new PhoneNumber(fromPhoneNum),
                                         String.format(smsTemplate, passcode)).create();
      System.out.println(message1.getSid());
      onboardingDao.createPhoneChecker(phoneChecker);

   }

   public PhoneChecker getPhoneChecker(
         String code,
         String phone
   )
   {

      return onboardingDao.getPhoneChecker(code + phone);
   }

   public PhoneValidatorResponse phoneValidator(@Valid @NotNull PhoneValidatorRequest phoneValidator)
   {
      // TODO Auto-generated method stub
      PhoneChecker phoneCheckerFromDb = onboardingDao.getPhoneChecker(
            phoneValidator.getCountryCode() + phoneValidator.getPhone());

      PhoneValidatorResponse phoneValidatorResponse =
            new PhoneValidatorResponse(phoneValidator.getPhone(),
                                       phoneValidator.getCountryCode(),
                                       false,
                                       "Default message");

      long userPassCode;
      try
      {
         userPassCode = Long.parseLong(phoneValidator.getPasscode().trim());
      }
      catch (Exception e)
      {
         phoneValidatorResponse.setVarificationMessage("Verification failed: Invalid passcode");
         return phoneValidatorResponse;
      }

      if (phoneCheckerFromDb == null)
      {
         throw new BusinessException("Verification failed: Invalid Phone "
                                     + "number.");
      }
      else if (!phoneCheckerFromDb.getStatus()
                                  .equals(VerificationStatus.PENDING))
      {
         phoneValidatorResponse.setVerificationStatus(true);
         phoneValidatorResponse.setVarificationMessage(
               "Verification failed: phone already " + phoneCheckerFromDb
               .getStatus().toString());
      }
      else if (Calendar.getInstance().getTimeInMillis() - phoneCheckerFromDb
            .getUpdatedTimeInMillis() > 100000)
      {
         phoneValidatorResponse.setVarificationMessage(
               "Verification failed: passcode expired");
      }
      else if (phoneCheckerFromDb.getPasscode() == userPassCode)
      {
         System.out.println("Phone verified");
         phoneCheckerFromDb.setStatus(VerificationStatus.VERIFIED);
         phoneCheckerFromDb.setUpdatedTimeInMillis(Calendar.getInstance()
                                                           .getTimeInMillis());
         onboardingDao.updatePhoneChecker(phoneCheckerFromDb);
         System.out.println("Db updated");
         phoneValidatorResponse.setVerificationStatus(true);
         phoneValidatorResponse.setVarificationMessage("Phone verification "
                                                      + "succeeded");
         return phoneValidatorResponse;
      }

      return phoneValidatorResponse;
   }
}
