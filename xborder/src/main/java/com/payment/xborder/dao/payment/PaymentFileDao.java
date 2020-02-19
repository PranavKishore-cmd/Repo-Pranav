package com.payment.xborder.dao.payment;


import com.payment.xborder.enums.PaymentFileStatus;
import com.payment.xborder.model.payment.PaymentFileRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class PaymentFileDao
{
   @Autowired
   MongoTemplate mongoTemplate;

   public PaymentFileRecord save(PaymentFileRecord paymentFileRecord) {
      //TODO: Do validations
      paymentFileRecord.setPaymentFileStatus(PaymentFileStatus.CREATED);
      paymentFileRecord.setPaymentRecordCreatedTime(Calendar.getInstance().getTimeInMillis());
      return mongoTemplate.save(paymentFileRecord);
   }

   public PaymentFileRecord save(String paymentFileName,
                                 String paymentFilePath,
                                 String senderID,
                                 String receiverID,
                                 String uploaderUserID) {
      //TODO: Do validations
      PaymentFileRecord paymentFileRecord = new PaymentFileRecord(senderID,
                                                                  receiverID,
                                                                  paymentFileName,
                                                                  paymentFilePath,
                                                                  PaymentFileStatus.CREATED,
                                                                  uploaderUserID);
      return mongoTemplate.save(paymentFileRecord);
   }
}
