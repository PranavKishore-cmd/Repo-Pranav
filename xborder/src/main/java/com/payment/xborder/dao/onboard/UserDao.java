package com.payment.xborder.dao.onboard;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.payment.xborder.enums.UserStatus;
import com.payment.xborder.model.onboard.User;

@Component
public class UserDao {

    @Autowired
    private MongoTemplate mongoTemplate;
    
//    @Autowired
//    void setMapKeyDotReplacement(MappingMongoConverter mappingMongoConverter) {
//        mappingMongoConverter.setMapKeyDotReplacement("_");
//    }

    public void createUser(User user) {
      //Replace with upsert
      mongoTemplate.insert(user);
   }

    public User getUserDetails(String userEmail) {
      //Replace with upsert
      Query query = new Query(Criteria.where("email").is(userEmail));
	    	User userDetails = mongoTemplate.findOne(query, User.class);
	    if(userDetails == null) {
          System.out.println("No user details found for " + userEmail);
          return null;
	    }
        return userDetails;
    }
    
    public User getAdminUserDetails(String userEmail) {
        //Replace with upsert
        Query query = new Query(Criteria.where("email").is(userEmail).and("userStatus").is(UserStatus.ACTIVE));
  	    User userDetails = mongoTemplate.findOne(query, User.class);
  	    if(userDetails == null) {
            System.out.println("No user details found for " + userEmail);
            return null;
  	    }
          return userDetails;
      }
    
    public User updateUserDetails(User user) {
        //Replace with upsert
    	Query query = new Query(Criteria.where("email").is(user.getEmail()));
    	User userDetails = mongoTemplate.findOne(query, User.class);
	    if(userDetails == null) {
	    	    throw new IllegalArgumentException("No user details found for "+user.getEmail());
	    }else {
			Update update = new Update();

			if (!(userDetails.getFirstName().contentEquals(user.getFirstName()))) {
				update.set("firstName", user.getFirstName());
			}
			if (!(userDetails.getLastName().contentEquals(user.getLastName()))) {
				update.set("lastName", user.getLastName());
			}
			if (StringUtils.isNotEmpty(userDetails.getMiddleName()) && StringUtils.isNotEmpty(user.getMiddleName()) && 
					!(userDetails.getMiddleName().contentEquals(user.getMiddleName()))) {
				update.set("middleName", user.getMiddleName());
			}
			if(!(userDetails.getCountryCode().contentEquals(user.getCountryCode()))) {
				update.set("countryCode", user.getCountryCode());
			}
			if(!(userDetails.getTelephoneNumber().contentEquals(user.getTelephoneNumber()))) {
				update.set("telephoneNumber", user.getTelephoneNumber());
			}
			if(!(userDetails.getRoleId().contentEquals(user.getRoleId()))) {
				update.set("roleId", user.getRoleId());
			}

			if (!(update.toString().equals("{ }"))) {
				mongoTemplate.updateFirst(query, update, User.class);
			}
			
	    }
        return userDetails;
   }

   public User updateUser(User user) {
      //Replace with upsert
      Query query = new Query(Criteria.where("email").is(user.getEmail()));
      User userDetails = mongoTemplate.findOne(query, User.class);
      if(userDetails == null) {
         throw new IllegalArgumentException("No user details found for "+user.getEmail());
      }
      user.setUserId(userDetails.getUserId());
      mongoTemplate.save(user);
      return userDetails;
   }
    
    public boolean updateUserGAuthState(User user) {
    	Query query = new Query(Criteria.where("email").is(user.getEmail()));
    	User userDetails = mongoTemplate.findOne(query, User.class);

    	if(userDetails == null) {
    		throw new IllegalArgumentException("No user details found for "+user.getEmail());
    	}else {
    		Update update = new Update();

    		if (!userDetails.getGAuthState()){
	    		update.set("gAuthEnabled", true);
	    	} else{
            update.set("gAuthEnabled", false);
         }
    		if (!(update.toString().equals("{ }"))) {
	    		mongoTemplate.updateFirst(query,update, User.class);
	    	}
    	}
    	 return user.getGAuthState();
    }

   public boolean updateUserStatus(User user) {
      Query query = new Query(Criteria.where("email").is(user.getEmail()));
      User userDetails = mongoTemplate.findOne(query, User.class);

      if(userDetails == null) {
         throw new IllegalArgumentException("No user details found for "+user.getEmail());
      }else {
         Update update = new Update();

         if (!(userDetails.getUserStatus().equals(user.getUserStatus()))){
            update.set("userStatus", user.getUserStatus());
         }
         if (!(update.toString().equals("{ }"))) {
            mongoTemplate.updateFirst(query,update,User.class);
         }
      }
      return user.getGAuthState();
   }
    

   public String findUserIdByEmail(String userEmail)
   {
      //Replace with upsert
      Query query = new Query(Criteria.where("email").is(userEmail));
      User userDetails = mongoTemplate.findOne(query,
                                               User.class);

      if (userDetails == null)
      {
         System.out.println("No user details found for " + userEmail);
         return null;
      }

      return userDetails.getUserId();
   }

   public List findUsersByKeyValue(String key, String value)
   {
      //Replace with upsert
      Query query = new Query(Criteria.where(key).is(value));
      List<User> userDetailsList = mongoTemplate.find(query,
                                               User.class);
      if (userDetailsList == null)
      {
         System.out.println("Search returned empty");
         return Collections.EMPTY_LIST;
      }

      return userDetailsList;
   }

   public List findUsers(String searchQuery)
   {

      //Replace with upsert
      TextCriteria criteria = TextCriteria.forDefaultLanguage()
                                          .matching(searchQuery);

      Query query = TextQuery.queryText(criteria).sortByScore();

      List<User> userDetailsList = mongoTemplate.find(query, User.class);

      if (userDetailsList == null)
      {
         System.out.println("Search returned empty");
         return Collections.EMPTY_LIST;
      }

      return userDetailsList;
   }

   public List findAll()
   {
      //Replace with upsert
      List<User> userDetailsList = mongoTemplate.findAll(User.class);

      if (userDetailsList == null)
      {
         System.out.println("User table is empty");
         return Collections.EMPTY_LIST;
      }

      return userDetailsList;
   }
   
   
   public void changePassword(User user, String newPassword) {
	   Query query = new Query(Criteria.where("email").is(user.getEmail()));
	   User userDetails = mongoTemplate.findOne(query, User.class);

	   if(userDetails == null) {
		   throw new IllegalArgumentException("No user details found for "+user.getEmail());
	   }else {
		   Update update = new Update();
		   update.set("password", newPassword);
		   if (!(update.toString().equals("{ }"))) {
	    		mongoTemplate.updateFirst(query, update, User.class);
	    	}
	   }
   }

	public List<User> findAllActiveUsers() {
		Query query = new Query(Criteria.where("userStatus").is(UserStatus.ACTIVE));
		List<User> userDetailsList = mongoTemplate.find(query,User.class);

		if (userDetailsList == null) {
			System.out.println("User table is empty");
			return Collections.emptyList();
		}

		return userDetailsList;
	}
}
