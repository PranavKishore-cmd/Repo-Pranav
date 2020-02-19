package com.payment.xborder.dao.roles;

import com.payment.xborder.model.roles.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class RolesDao
{

    @Autowired
    private MongoTemplate mongoTemplate;
    
    public List<Role> getRoles() {
       List<Role> roles = mongoTemplate.findAll(Role.class);
	    if(roles == null) {
	    	    System.out.println("No roles found ");
	    	    return Collections.emptyList();
	    }
        return roles;
    }

   public void createRole(Role role)
   {
      mongoTemplate.insert(role);
   }

   public Role getRole(String roleName) {
      Query query = new Query(Criteria.where("roleName").is(roleName));
      Role role = mongoTemplate.findOne(query, Role.class);
      if(role == null) {
         System.out.println("No matching role found in database, rolename "+ roleName);
         return null;
      }
      return role;
   }

   public Role getRoleByID(String roleId) {
      Query query = new Query(Criteria.where("roleId").is(roleId));
      Role role = mongoTemplate.findOne(query, Role.class);
      if(role == null) {
         System.out.println("No matching role found in database, roleId "+ roleId);
         return null;
      }
      return role;
   }


    public Role getRoleBasedOnParentRoleAndRoleName(Role role) {
        Query query = new Query(Criteria.where("roleName").is(role.getRoleName()).and("roleType").is(role.getRoleType()));
        Role dbRole = mongoTemplate.findOne(query, Role.class);
        if(dbRole == null) {
            System.out.println("No matching role found in database, rolename "+ role.getRoleName());
            return null;
        }
        return role;
    }

   public List<Role> getRolesByType(String roleType)
   {
      Query query = new Query(Criteria.where("roleType").is(roleType));
      List<Role> roles = mongoTemplate.find(query, Role.class);
      if(roles == null) {
         System.out.println("No roles found ");
         return null;
      }
      return roles;

   }
}
