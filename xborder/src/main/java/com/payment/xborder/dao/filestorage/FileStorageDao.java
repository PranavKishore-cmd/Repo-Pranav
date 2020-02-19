package com.payment.xborder.dao.filestorage;

import com.payment.xborder.exception.BusinessException;
import com.payment.xborder.model.file.UserDocuments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.payment.xborder.model.file.UserFilePath;


@Component
public class FileStorageDao {

   private static final Logger
         log = LoggerFactory.getLogger(FileStorageDao.class);
	
    @Autowired
    private MongoTemplate mongoTemplate;
    
    public void createFileMap(UserFilePath userFilePath) {
        //Replace with upsert
        mongoTemplate.insert(userFilePath);
    }

    public UserDocuments getSavedDocuments(String userId){
       Query query = new Query(Criteria.where("userId").is(userId));
       return mongoTemplate.findOne(query, UserDocuments.class);
    }

   public UserDocuments insertUserDocument(UserDocuments userDocuments){
      return mongoTemplate.insert(userDocuments);
   }

   public void saveUserDocument(UserDocuments userDocuments){
      try {
         mongoTemplate.save(userDocuments);
      }catch(Exception e) {
         System.out.println(e);
         log.error(e.getMessage());
         throw new BusinessException("Error while saving the registration form");
      }
   }


}
