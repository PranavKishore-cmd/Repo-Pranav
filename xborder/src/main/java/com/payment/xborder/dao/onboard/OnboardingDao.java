package com.payment.xborder.dao.onboard;

import com.payment.xborder.enums.VerificationStatus;
import com.payment.xborder.exception.BusinessException;
import com.payment.xborder.model.onboard.MailChecker;
import com.payment.xborder.model.onboard.PhoneChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class OnboardingDao {
    public OnboardingDao() {
    }

    @Autowired
    private  MongoTemplate mongoTemplate;

    public void createMailChecker(MailChecker mailChecker) {
    	    Query query = new Query(Criteria.where("email").is(mailChecker.getEmail()));
    	    MailChecker mailCheckerFromDb = mongoTemplate.findOne(query, MailChecker.class);
    	    if(mailCheckerFromDb == null) {
    	    	    mailChecker.setUpdatedTimeInMillis(Calendar.getInstance().getTimeInMillis());
    	    	    mongoTemplate.save(mailChecker);
    	    } 
    	    else if(!mailCheckerFromDb.getStatus().equals(VerificationStatus.PENDING)) {
    	    	    throw new BusinessException("Email is already verified");
    	    }
    	    else {
    	    	   mailChecker.setUpdatedTimeInMillis(Calendar.getInstance().getTimeInMillis());
    	    	   mongoTemplate.save(mailChecker);
    	    }
    }
    
    public void updateMailChecker(MailChecker mailChecker) {
	    Query query = new Query(Criteria.where("email").is(mailChecker.getEmail()));
	    MailChecker mailCheckerFromDb = mongoTemplate.findOne(query, MailChecker.class);
	    if(mailCheckerFromDb == null) {
	    	    throw new BusinessException("Unsupported operation");
	    } 
	    mongoTemplate.save(mailChecker);
    }

    public MailChecker getMailChecker(String email) {
        Query query = new Query(Criteria.where("email").is(email));
        return mongoTemplate.findOne(query, MailChecker.class);
    }

	public void createPhoneChecker(PhoneChecker phoneChecker) {
		Query query = new Query(Criteria.where("longPhoneNum").is(phoneChecker.getLongPhoneNum()));
		PhoneChecker phoneCheckerFromDb = mongoTemplate.findOne(query, PhoneChecker.class);
	    if(phoneCheckerFromDb == null) {
	    	phoneChecker.setUpdatedTimeInMillis(Calendar.getInstance().getTimeInMillis());
	    	    mongoTemplate.save(phoneChecker);
	    } 
	    else if(!phoneCheckerFromDb.getStatus().equals(VerificationStatus.PENDING)) {
	    	    throw new BusinessException("Phone is already verified");
	    }
	    else {
	    	   phoneChecker.setUpdatedTimeInMillis(Calendar.getInstance().getTimeInMillis());
	    	   mongoTemplate.save(phoneChecker);
	    }
	}

	public PhoneChecker getPhoneChecker(String longPhoneNum) {
		Query query = new Query(Criteria.where("longPhoneNum").is(longPhoneNum));
        System.out.println(longPhoneNum);
        System.out.println( mongoTemplate.find(query, MailChecker.class));
        return mongoTemplate.findOne(query, PhoneChecker.class);
	}

	public void updatePhoneChecker(PhoneChecker phoneChecker) {
		Query query = new Query(Criteria.where("longPhoneNum").is(phoneChecker.getLongPhoneNum()));
		PhoneChecker phoneCheckerFromDb = mongoTemplate.findOne(query, PhoneChecker.class);
	    if(phoneCheckerFromDb == null) {
	    	    throw new BusinessException("Unsupported operation");
	    } 
	    mongoTemplate.save(phoneChecker);
		
	}

}
