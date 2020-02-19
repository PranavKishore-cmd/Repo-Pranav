package com.payment.xborder.service.profile;

import com.payment.xborder.dao.profile.ProfileManagementDao;
import com.payment.xborder.model.onboard.ProfileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProfileManagementService {

		@Autowired
		private ProfileManagementDao profileManagementDao;

		
		public void createProfile(ProfileManager profileManager) {
			profileManagementDao.createProfile(profileManager);
			
		}

		public ProfileManager getProfile(String companyRefId) {
         return profileManagementDao.getProfileDetails(companyRefId);
		}

		public String getProfileType(String companyRefId) {
          ProfileManager profileManager =
                profileManagementDao.getProfileDetails(companyRefId);
          if(profileManager == null) {
               System.out.println("No Profile details found for " + companyRefId);
               return null;
          }
          return profileManager.getProfileType();
		}

		public List<ProfileManager> getAllProfiles() {
			List<ProfileManager> allProfiles =
               profileManagementDao.getAllProfiles();
			if(allProfiles == null || allProfiles.isEmpty()){
			   return Collections.emptyList();
         }
			return allProfiles;
		}
		public ProfileManager updateProfile(ProfileManager profileManager) {
			 return profileManagementDao.updateProfilerDetails(profileManager);
		}
		
}
