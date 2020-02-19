package com.payment.xborder.model.audit;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Document(collection = "AUDIT_TRAIL")
public class AuditTrail {

	@Id
	private String auditId;
	
	@NotNull
	private String auditScreen;
	
	@NotNull
	private String emailId;
	
	@NotNull
	private String createdBy;
	
	@NotNull
	private String modifiedBy;
	
	@NotNull
	private Date createdDate;
	
	@NotNull
	private Date modifiedDate;

	public AuditTrail() {
		
	}
	
	/**
	 * @param auditId
	 * @param auditScreen
	 * @param emailId
	 * @param createdBy
	 * @param modifiedBy
	 * @param createdDate
	 * @param modifiedDate
	 */
	public AuditTrail(String auditId, @NotNull String auditScreen, @NotNull String emailId, @NotNull String createdBy,
			@NotNull String modifiedBy, @NotNull Date createdDate, @NotNull Date modifiedDate) {
		this.auditId = auditId;
		this.auditScreen = auditScreen;
		this.emailId = emailId;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @return the auditId
	 */
	public final String getAuditId() {
		return auditId;
	}

	/**
	 * @param auditId the auditId to set
	 */
	public final void setAuditId(String auditId) {
		this.auditId = auditId;
	}

	/**
	 * @return the auditScreen
	 */
	public final String getAuditScreen() {
		return auditScreen;
	}

	/**
	 * @param auditScreen the auditScreen to set
	 */
	public final void setAuditScreen(String auditScreen) {
		this.auditScreen = auditScreen;
	}

	/**
	 * @return the emailId
	 */
	public final String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public final void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the createdBy
	 */
	public final String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public final void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the modifiedBy
	 */
	public final String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public final void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return the createdDate
	 */
	public final Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public final void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the modifiedDate
	 */
	public final Date getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public final void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	
}
