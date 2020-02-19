package com.payment.xborder.model.onboard;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.payment.xborder.model.profile.ws.SenderInfo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Document(collection = "PROFILE_MANAGER")
public class ProfileManager {
	
	 @Id
	 private String profileManagerID;

	 @NotBlank
	 private String companyRefId;
	
	 @NotBlank
	 private String entityName;

	 private List<String> serviceOffered;
	 
	 @NotBlank
	 private String comments;
	 
	 private SenderInfo senderInfo;
	 
	 private List<ReceiverInfo> receiverInfo;
	 
	 @NotBlank
	 private String cutOffTimeInHours;
	 
	 private String profileType;
	 
	 private Map<String,String> docsRequiredToPair = Collections.emptyMap();

	 
	public Map<String, String> getDocsRequiredToPair() {
		return docsRequiredToPair;
	}

	public void setDocsRequiredToPair(Map<String, String> docsRequiredToPair) {
		this.docsRequiredToPair = docsRequiredToPair;
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

	public SenderInfo getSenderInfo() {
		return senderInfo;
	}

	public String getCutOffTimeInHours() {
		return cutOffTimeInHours;
	}

	public void setCutOffTimeInHours(String cutOffTimeInHours) {
		this.cutOffTimeInHours = cutOffTimeInHours;
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

	public String getProfileManagerID() {
		return profileManagerID;
	}

	public void setProfileManagerID(String profileManagerID) {
		this.profileManagerID = profileManagerID;
	}
	
}