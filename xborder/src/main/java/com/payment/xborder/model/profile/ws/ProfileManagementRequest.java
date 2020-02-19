package com.payment.xborder.model.profile.ws;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.payment.xborder.model.onboard.ReceiverInfo;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProfileManagementRequest {

	
	 
	 @NotBlank
	 private String entityName;

	 private List<String> serviceOffered;
	 
	 @NotBlank
	 private String comments;
	 
	 private String companyRefId;
	 
	 private String CutOffTimeInHours;

	 private SenderInfo senderInfo;
	 
	 private List<ReceiverInfo> receiverInfo;
	 
	 private String profileType;
	 
	 private Map<String,String> docsRequired;
	 
	 
	 
	public Map<String, String> getDocsRequired() {
		return docsRequired;
	}

	public void setDocsRequired(Map<String, String> docsRequired) {
		this.docsRequired = docsRequired;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

   public List <String> getServiceOffered()
   {
      return serviceOffered;
   }

   public void setServiceOffered(List <String> serviceOffered)
   {
      this.serviceOffered = serviceOffered;
   }

   public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCutOffTimeInHours() {
		return CutOffTimeInHours;
	}

	public void setCutOffTimeInHours(String cutOffTimeInHours) {
		CutOffTimeInHours = cutOffTimeInHours;
	}

	public SenderInfo getSenderInfo() {
		return senderInfo;
	}

	public void setSenderInfo(SenderInfo senderInfo) {
		this.senderInfo = senderInfo;
	}

	public List<ReceiverInfo> getReceiverInfo() {
		return receiverInfo;
	}

	public void setReceiverInfo(List<ReceiverInfo> receiverInfo) {
		this.receiverInfo = receiverInfo;
	}

	public String getProfileType() {
		return profileType;
	}

	public void setProfileType(String profileType) {
		this.profileType = profileType;
	}

	public String getCompanyRefId() {
		return companyRefId;
	}

	public void setCompanyRefId(String companyRefId) {
		this.companyRefId = companyRefId;
	}

	
	 
}