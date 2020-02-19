package com.payment.xborder.util.converter.profile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.payment.xborder.model.onboard.ProfileManager;
import com.payment.xborder.model.profile.ws.ProfileManagementRequest;

public class ProfileManagementConverter {

	public static ProfileManager wsToModel(ProfileManagementRequest profileManagementRequest) {
    	return prepareProfileInfo(profileManagementRequest);
	}

	public static ProfileManager wsToModelUpdateProfile(@Valid @NotNull ProfileManagementRequest profileManagementRequest) {
		return prepareProfileInfo(profileManagementRequest);
	}
	
	private static ProfileManager prepareProfileInfo(ProfileManagementRequest profileManagementRequest) {
		ProfileManager profileManager = new ProfileManager();
    	profileManager.setCompanyRefId(profileManagementRequest.getCompanyRefId());
    	profileManager.setComments(profileManagementRequest.getComments());
    	profileManager.setEntityName(profileManagementRequest.getEntityName());
    	profileManager.setCutOffTimeInHours(profileManagementRequest.getCutOffTimeInHours());
    	profileManager.setSenderInfo(profileManagementRequest.getSenderInfo());
    	profileManager.setReceiverInfo(profileManagementRequest.getReceiverInfo());
    	profileManager.setProfileType(profileManagementRequest.getProfileType());
    	profileManager.setServiceOffered(profileManagementRequest.getServiceOffered());
    	profileManager.setDocsRequiredToPair(profileManagementRequest.getDocsRequired());
    	return profileManager;
	}


	
}