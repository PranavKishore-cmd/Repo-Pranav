package com.payment.xborder.model.roles.ws;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.payment.xborder.enums.RegisterUserType;
import com.payment.xborder.enums.ParentRoleType;
import com.payment.xborder.enums.UserStatus;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RoleRequest
{

	@NotBlank
	private String roleName;

	private String roleDescription;

	@NotBlank
	private ParentRoleType roleType;

	private String parentRoleId;

	public RoleRequest(
			@NotBlank String roleName,
			String roleDescription,
			@NotBlank ParentRoleType roleType,
			String parentRoleId
	)
	{
		this.roleName = roleName;
		this.roleDescription = roleDescription;
		this.roleType = roleType;
		this.parentRoleId = parentRoleId;
	}

	public RoleRequest()
	{
	}

	public String getRoleName()
	{
		return roleName;
	}

	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

	public String getRoleDescription()
	{
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription)
	{
		this.roleDescription = roleDescription;
	}

	public ParentRoleType getRoleType()
	{
		return roleType;
	}

	public void setRoleType(ParentRoleType roleType)
	{
		this.roleType = roleType;
	}

	public String getParentRoleId()
	{
		return parentRoleId;
	}

	public void setParentRoleId(String parentRoleId)
	{
		this.parentRoleId = parentRoleId;
	}

	@Override
	public String toString()
	{
		return "RoleRequest{" +
				 "roleName='" + roleName + '\'' +
				 ", roleDescription='" + roleDescription + '\'' +
				 ", roleType='" + roleType + '\'' +
				 ", parentRoleId='" + parentRoleId + '\'' +
				 '}';
	}
}
