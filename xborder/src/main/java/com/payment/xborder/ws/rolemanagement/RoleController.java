package com.payment.xborder.ws.rolemanagement;


import com.payment.xborder.enums.ParentRoleType;
import com.payment.xborder.model.roles.Role;
import com.payment.xborder.model.roles.ws.RoleAssignmentRequest;
import com.payment.xborder.model.roles.ws.RoleRequest;
import com.payment.xborder.model.roles.ws.RoleResponse;
import com.payment.xborder.service.roles.RoleService;
import com.payment.xborder.util.converter.roles.RoleConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class RoleController
{

   @Autowired
   RoleService roleService;

   @GetMapping("/getRoles")
   public ResponseEntity<RoleResponse> getRoles() {
      RoleResponse roleResponse = new RoleResponse(roleService.getRoles());
      return new ResponseEntity<>(roleResponse, HttpStatus.ACCEPTED);
   }

   @GetMapping("/getRolesByType/{roleType}")
   public ResponseEntity<RoleResponse> getRolesByRoleType(@PathVariable ParentRoleType roleType) {
      RoleResponse roleResponse =
            new RoleResponse(roleService.getRolesByType(roleType.toString()));
      return new ResponseEntity<>(roleResponse, HttpStatus.ACCEPTED);
   }

   @GetMapping("/getRole/{roleName}")
   public ResponseEntity<Role> getRole(@PathVariable String roleName) {
      Role role = roleService.getRoleByRoleName(roleName);
      if(role == null){
         return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<Role>(role, HttpStatus.FOUND);
   }

   @PostMapping("/createRole")
   public ResponseEntity<String> createRole(@RequestBody RoleRequest roleDetails) {
      Role role = RoleConverter.wsToModel(roleDetails);
      roleService.createRole(role);
      return new ResponseEntity<String>("New role created", HttpStatus.CREATED);
   }

   @PostMapping("/assignRole")
   public ResponseEntity<String> assignRoleToUser(@RequestBody RoleAssignmentRequest roleAssignmentRequest) {
      //Role role = RoleConverter.wsToModel(roleDetails);
      roleService.assignOrUpdateRole(roleAssignmentRequest);
      return new ResponseEntity<String>("Role assigned to user",
                                        HttpStatus.CREATED);
   }

}
