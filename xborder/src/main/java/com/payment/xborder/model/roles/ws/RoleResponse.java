package com.payment.xborder.model.roles.ws;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.payment.xborder.enums.RegisterUserType;
import com.payment.xborder.enums.UserRole;
import com.payment.xborder.enums.UserStatus;
import com.payment.xborder.model.roles.Role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RoleResponse
{

	List<Role> roles;

	public RoleResponse(List <Role> roles)
	{
		this.roles = roles;
	}

	public RoleResponse()
	{
	}

	public List <Role> getRoles()
	{
		return roles;
	}

	public void setRoles(List <Role> roles)
	{
		this.roles = roles;
	}

	@Override
	public String toString()
	{
		return "RoleResponse{" +
				 "roles=" + roles +
				 '}';
	}


}
