package com.payment.xborder.dao.onboard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.payment.xborder.enums.DocumentVerificationStatus;
import com.payment.xborder.enums.UserStatus;
import com.payment.xborder.model.file.DocumentDetails;
import com.payment.xborder.model.file.UserDocuments;

@Component
public class UserDocumentsDao {
	
	private static final Logger log = LoggerFactory.getLogger(UserDocumentsDao.class);
	
	public UserDocumentsDao(){
		
	}
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public List<UserDocuments> getAllUserDocumentsInfo(){
		
		log.info("UserDocumentsDao :getAllUserDocumentsInfo() : start ");
		
		List<UserDocuments> allDataInfo = mongoTemplate.findAll(UserDocuments.class);
		log.info("UserDocumentsDao :getAllUserDocumentsInfo() : end ");
		
		return allDataInfo;
		
		
	}
	
	
			
	
	
	public List<UserDocuments> getAllUserDocsForVerifiedStatus(){
		
		log.info("UserDocumentsDao :getAllUserDocsForVerifiedStatus() : start ");
		
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").exists(true).and("documentsUploaded.documentVerificationStatus").is("PENDING"));
		query.fields().include("documentsUploaded").elemMatch("documentVerificationStatus", Criteria.where("documentVerificationStatus").is(DocumentVerificationStatus.PENDING));
		List<UserDocuments> pendingDocList = mongoTemplate.find(query,UserDocuments.class);
		log.info("UserDocumentsDao :getAllUserDocsForVerifiedStatus() : end ");
		return pendingDocList;
	}
	

}
