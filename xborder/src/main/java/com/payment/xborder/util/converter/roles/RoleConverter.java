package com.payment.xborder.util.converter.roles;


import com.payment.xborder.model.roles.Role;
import com.payment.xborder.model.roles.ws.RoleRequest;

public class RoleConverter
{

    public static Role wsToModel(RoleRequest roleRequest) {

        Role role = null;
        if(validateRole(roleRequest)){
            role = new Role(roleRequest.getRoleName(),
                            roleRequest.getRoleDescription(),
                            roleRequest.getRoleType(),
                            roleRequest.getParentRoleId());
        }
        return role;
    }

    private static boolean validateRole(RoleRequest roleRequest)
    {
        return true;
    }

}
