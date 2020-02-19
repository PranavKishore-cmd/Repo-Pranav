package com.payment.xborder.model.roles;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.payment.xborder.enums.ParentRoleType;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Document(collection = "ROLES_TABLE")
public class Role
{

	@Id
	private String roleId;

	@NotBlank
	@UniqueElements
	private String roleName;

	private String roleDescription;

	@NotBlank
	private ParentRoleType roleType;

	private String parentRoleId;

	public Role(
			@NotBlank @UniqueElements String roleName,
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

	public Role(
			String roleId,
			@NotBlank @UniqueElements String roleName,
			String roleDescription,
			@NotBlank ParentRoleType roleType,
			String parentRoleId
	)
	{
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleDescription = roleDescription;
		this.roleType = roleType;
		this.parentRoleId = parentRoleId;
	}

	public Role()
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

	public String getRoleId()
	{
		return roleId;
	}

	public void setRoleId(String roleId)
	{
		this.roleId = roleId;
	}

	@Override
	public String toString()
	{
		return "Role{" +
				 "roleId='" + roleId + '\'' +
				 ", roleName='" + roleName + '\'' +
				 ", roleDescription='" + roleDescription + '\'' +
				 ", roleType=" + roleType +
				 ", parentRoleId='" + parentRoleId + '\'' +
				 '}';
	}
}
