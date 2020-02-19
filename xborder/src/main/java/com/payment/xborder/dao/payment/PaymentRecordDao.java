package com.payment.xborder.dao.payment;

import com.payment.xborder.enums.PaymentRecordStatus;
import com.payment.xborder.model.payment.PaymentRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class PaymentRecordDao
{
   @Autowired
   MongoTemplate mongoTemplate;

   public PaymentRecord save(PaymentRecord paymentRecord) {
      //TODO: Do validations
      return mongoTemplate.save(paymentRecord);
   }

   public List <PaymentRecord> findPaymentRecords(
         String companyCriterion,
         String companyID,
         PaymentRecordStatus paymentRecordStatus
   )
   {
      Query query = new Query(Criteria.where(companyCriterion).is(companyID).and(
                  "paymentRecordStatus").is(paymentRecordStatus));
      List<PaymentRecord> paymentRecords = mongoTemplate.find(query, PaymentRecord.class);
      if(paymentRecords == null || paymentRecords.isEmpty()){
         System.out.println("Payment Records not found");
         return Collections.emptyList();
      }

      return paymentRecords;
   }

   public PaymentRecord updatePaymentStatus(String paymentRecordId,
                                   PaymentRecordStatus paymentRecordStatus) {
      //Replace with upsert
      Query query = new Query(Criteria.where("paymentRecordId").is(paymentRecordId));
      PaymentRecord paymentRecordDtls = mongoTemplate.findOne(query,
                                                              PaymentRecord.class);
      if(paymentRecordDtls == null) {
         throw new IllegalArgumentException("No Payment details found for "+paymentRecordId);
      }
      paymentRecordDtls.setPaymentRecordStatus(paymentRecordStatus);
      mongoTemplate.save(paymentRecordDtls);
      return paymentRecordDtls;
   }
}
