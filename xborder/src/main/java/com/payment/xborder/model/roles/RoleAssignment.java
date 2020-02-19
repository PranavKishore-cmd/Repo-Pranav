package com.payment.xborder.model.roles;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.payment.xborder.enums.ParentRoleType;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Document(collection = "USER_ROLE_ASSIGNMENT")
public class RoleAssignment
{

	@Id
	private String assignmentID;

	@NotBlank
	private String roleID;

	@NotBlank
	private String userID;

	public RoleAssignment(
			@NotBlank String roleID,
			@NotBlank String userID
	)
	{
		this.roleID = roleID;
		this.userID = userID;
	}

	public String getAssignmentID()
	{
		return assignmentID;
	}

	public void setAssignmentID(String assignmentID)
	{
		this.assignmentID = assignmentID;
	}

	public String getRoleID()
	{
		return roleID;
	}

	public void setRoleID(String roleID)
	{
		this.roleID = roleID;
	}

	public String getUserID()
	{
		return userID;
	}

	public void setUserID(String userID)
	{
		this.userID = userID;
	}

	@Override
	public String toString()
	{
		return "RoleAssignment{" +
				 "assignmentID='" + assignmentID + '\'' +
				 ", roleID='" + roleID + '\'' +
				 ", userID='" + userID + '\'' +
				 '}';
	}
}
