package com.payment.xborder.service.roles;

import com.payment.xborder.dao.onboard.UserDao;
import com.payment.xborder.dao.roles.RoleAssignmentDao;
import com.payment.xborder.dao.roles.RolesDao;
import com.payment.xborder.exception.BusinessException;
import com.payment.xborder.model.roles.Role;
import com.payment.xborder.model.roles.RoleAssignment;
import com.payment.xborder.model.roles.ws.RoleAssignmentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService
{

   @Autowired
   RolesDao          rolesDao;
   @Autowired
   UserDao           userDao;
   @Autowired
   RoleAssignmentDao roleAssignmentDao;

   public void createRole(Role role)
   {
      performValidationsForCreateRole(role);
      rolesDao.createRole(role);
   }

   public List <Role> getRoles()
   {
      return rolesDao.getRoles();
   }

   public List <Role> getRolesByType(String roleType)
   {
      return rolesDao.getRolesByType(roleType);
   }

   public Role getRoleByRoleName(String roleName)
   {
      return rolesDao.getRole(roleName);
   }

   public Role getRoleByRoleId(String roleId)
   {
      return rolesDao.getRoleByID(roleId);
   }


   private void performValidationsForCreateRole(Role role)
   {
      Role dbRole = rolesDao.getRoleBasedOnParentRoleAndRoleName(role);
      if (dbRole != null)
      {
         throw new BusinessException(
               role.getRoleName() + " role already exists for the " + role
                     .getRoleType() + " role type.");
      }
   }

   public void assignOrUpdateRole(RoleAssignmentRequest roleAssignmentRequest)
   {

      String roleId = null;
      try
      {
         if (roleAssignmentRequest.getRoleName() != null
             || !roleAssignmentRequest.getRoleName().isEmpty())
         {
            roleId =
                  rolesDao.getRole(roleAssignmentRequest.getRoleName())
                          .getRoleId();
         }
      }
      catch (Exception e)
      {
         throw new BusinessException("Role not present");
      }

      String userId = null;

      try
      {
         if (roleAssignmentRequest.getUserEmailId() != null
             || !roleAssignmentRequest.getUserEmailId().isEmpty())
         {
            userId = userDao
                  .findUserIdByEmail(roleAssignmentRequest.getUserEmailId());
         }
      }
      catch (Exception e)
      {
         throw new BusinessException("User not present");
      }

      roleAssignmentDao.assignRole(new RoleAssignment(roleId, userId));

   }
}

