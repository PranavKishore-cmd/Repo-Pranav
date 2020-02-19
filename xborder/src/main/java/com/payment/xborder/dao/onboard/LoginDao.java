package com.payment.xborder.dao.onboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.payment.xborder.exception.BusinessException;
import com.payment.xborder.model.onboard.UserSession;

@Component
public class LoginDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void createSession(UserSession login) {
        mongoTemplate.insert(login);
    }
    
    public void updateSession(UserSession sessionDetails) {
        Query query = new Query(Criteria.where("sessionId").is(sessionDetails.getSessionId()));
	    UserSession loginDetails = mongoTemplate.findOne(query, UserSession.class);
	    if(loginDetails == null) {
	    	    throw new BusinessException("No session saved for " + sessionDetails.getEmail());
	    } 
        mongoTemplate.save(sessionDetails);
    }
    
    public UserSession getSession(String sessionId) {
    	    Query query = new Query(Criteria.where("sessionId").is(sessionId));
    	    UserSession loginDetails = mongoTemplate.findOne(query, UserSession.class);
	    if(loginDetails == null) {
	    	    System.out.println("No session saved for " +sessionId);
	    	    return null;
	    } 
	    return loginDetails;
    }
    
    public UserSession getSessionByEmail(String email) {
	    Query query = new Query(Criteria.where("email").is(email));
	    UserSession loginDetails = mongoTemplate.findOne(query, UserSession.class);
	    if(loginDetails == null) {
	    	    System.out.println("No session saved for " +email);
	    	    return null;
	    } 
	    return loginDetails;
    }
    
    public void removeSession(String sessionId) {
    	  Query query = new Query(Criteria.where("sessionId").is(sessionId));
        mongoTemplate.remove(query, UserSession.class);
    }

}
