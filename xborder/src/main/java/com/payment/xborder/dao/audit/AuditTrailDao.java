package com.payment.xborder.dao.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.payment.xborder.model.audit.AuditTrail;
import com.payment.xborder.model.onboard.User;

@Component
public class AuditTrailDao {

	@Autowired
	MongoTemplate mongoTemplate;
	
	AuditTrailUtil auditUtil = new AuditTrailUtil();
	
	public void saveAuditTrail(AuditTrail auditTrail) {
		
		mongoTemplate.save(auditTrail);
	}
	
	public User getUserDetails(MongoTemplate mongoTemplate, String emailId) {
		Query query = new Query(Criteria.where("email").is(emailId));
		return mongoTemplate.findOne(query, User.class);
	}
}
