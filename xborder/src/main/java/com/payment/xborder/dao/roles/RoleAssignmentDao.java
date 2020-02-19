package com.payment.xborder.dao.roles;

import com.payment.xborder.exception.BusinessException;
import com.payment.xborder.model.onboard.UserSession;
import com.payment.xborder.model.roles.Role;
import com.payment.xborder.model.roles.RoleAssignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class RoleAssignmentDao
{

   @Autowired
   private MongoTemplate mongoTemplate;

   public List <RoleAssignment> getRoleAssignments()
   {
      List <RoleAssignment> roleAssignments =
            mongoTemplate.findAll(RoleAssignment.class);
      if (roleAssignments == null)
      {
         System.out.println("No roles found ");
         return Collections.emptyList();
      }
      return roleAssignments;
   }

   public void assignRole(RoleAssignment roleAssignment)
   {
      RoleAssignment prevAssignment =
            findAssignmentByUser(roleAssignment.getUserID());
      if(prevAssignment == null){
         mongoTemplate.insert(roleAssignment);
      }
      else{
         roleAssignment.setAssignmentID(prevAssignment.getAssignmentID());
         mongoTemplate.save(roleAssignment);
      }
   }

   public void deleteRoleAssignment(RoleAssignment roleAssignment)
   {
      if(findAssignmentByUser(roleAssignment.getUserID()) == null){
         throw new BusinessException("No assignemnet found");
      }
      else{
         mongoTemplate.remove(roleAssignment);
      }
   }

   public RoleAssignment findAssignmentByUser(String userId)
   {

      Query query = new Query(Criteria.where("userID").is(userId));
      RoleAssignment roleAssignment =
            mongoTemplate.findOne(query, RoleAssignment.class);
      if(roleAssignment==null)
      {
         return null;
      }
      else {
         return roleAssignment;
      }
   }

}
