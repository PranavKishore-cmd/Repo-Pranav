package com.payment.xborder.service.payment;

import com.payment.xborder.dao.onboard.UserDao;
import com.payment.xborder.dao.payment.PaymentFileDao;
import com.payment.xborder.dao.payment.PaymentRecordDao;
import com.payment.xborder.enums.PaymentRecordStatus;
import com.payment.xborder.exception.BusinessException;
import com.payment.xborder.model.onboard.User;
import com.payment.xborder.model.payment.PaymentFileRecord;
import com.payment.xborder.model.payment.PaymentRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Calendar;
import java.util.List;

@Service
public class PaymentFileManagementService
{

   @Autowired
   PaymentRecordDao paymentRecordDao;

   @Autowired
   PaymentFileDao paymentFileDao;

   @Autowired
   UserDao userDao;


   @Autowired
   PaymentFileStorageService fileStorageService;

   public void processFile(
         MultipartFile file,
         String senderEmailId,
         String receiverEmailId,
         String uploaderEmailId
   )
   {

      //TODO: 1. Check if SenderID and ReceiverID is valid

      User   senderUserDtls = userDao.getAdminUserDetails(senderEmailId);
      User   receiverUserDtls = userDao.getAdminUserDetails(receiverEmailId);
      String uploaderUserId = userDao.findUserIdByEmail(uploaderEmailId);

      if (senderUserDtls == null ){
         throw new BusinessException("Could not find active User with email ID"
                                     + " - " + senderEmailId);
      }
      else if (receiverUserDtls == null ){
         throw new BusinessException("Could not find active User with email ID"
                                     + " - " + receiverEmailId);
      }
      else if (uploaderUserId == null){
         throw new BusinessException("Could not find User with email ID - " + uploaderEmailId);
      }

      String senderUserId = senderUserDtls.getUserId();
      String receiverUserId = receiverUserDtls.getUserId();

      // 2. Save File to Folder and File details to DB
      PaymentFileManagementSystem managementSystem =
            new PaymentFileManagementSystem();
      String fileName = fileStorageService.saveFileToServer(file);

      //TODO: Record the file and path details to DB
      PaymentFileRecord paymentFileRecord = paymentFileDao.save(fileName,
                                                                fileName,
                                                                senderUserId,
                                                                receiverUserId,
                                                                uploaderUserId);

      // TODO: 3. Validate file and update DB

      // 4. Parse file and create Payment records
      String paymentBatchID = paymentFileRecord.getPaymentFileId();
      managementSystem.parseFile(fileName);
      for (PaymentRecord paymentRecord :
            managementSystem.getPaymentRecords())
      {
         paymentRecord.setPaymentSenderID(senderUserId);
         paymentRecord.setPaymentSenderCompID(senderUserDtls.getCompanyRefId());
         paymentRecord.setPaymentReceiverID(receiverUserId);
         paymentRecord.setPaymentReceiverCompID(receiverUserDtls.getCompanyRefId());
         paymentRecord.setPaymentBatchId(paymentBatchID);
         paymentRecord.setPaymentRecordStatus(PaymentRecordStatus.CREATED);
         paymentRecord.setPaymentRecordCreatedTime(Calendar.getInstance().getTimeInMillis());
         paymentRecordDao.save(paymentRecord);
      }

      // 5. Delete File from Directory

   }

   public List <PaymentRecord> getOutgoingPaymentRecordsByStatus(
         String companyID,
         PaymentRecordStatus paymentRecordStatus)
   {
      return paymentRecordDao.findPaymentRecords("paymentSenderCompID",
                                          companyID,
                                          paymentRecordStatus);
   }

   public List <PaymentRecord> getIncomingPaymentRecordsByStatus(
         String companyID,
         PaymentRecordStatus paymentRecordStatus)
   {
      return paymentRecordDao.findPaymentRecords("paymentReceiverCompID",
                                                 companyID,
                                                 paymentRecordStatus);
   }


   public boolean changePaymentRecordStatus(
         String paymentRecordId,
         PaymentRecordStatus paymentRecordStatus
   )
   {
      PaymentRecord paymentRecord =
            paymentRecordDao.updatePaymentStatus(paymentRecordId,
                                                 paymentRecordStatus);
      if(paymentRecord.getPaymentRecordStatus() == paymentRecordStatus){
         System.out.println("Payment Status updated.");
         return true;
      }
      else{
         System.out.println("Payment Status not updated.");
         return false;
      }
   }
}
