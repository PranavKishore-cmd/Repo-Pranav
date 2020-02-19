package com.payment.xborder.ws.profile;

import com.payment.xborder.model.onboard.ProfileManager;
import com.payment.xborder.model.profile.ws.ProfileManagementRequest;
import com.payment.xborder.model.profile.ws.ProfileManagementResponse;
import com.payment.xborder.service.profile.ProfileManagementService;
import com.payment.xborder.util.converter.profile.ProfileManagementConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("profile")
public class ProfileManagementController {

	
	@Autowired
	private ProfileManagementService profileManagementService;
	
	
	@PostMapping("/create")
	public ResponseEntity<ProfileManagementResponse> createProfile(@Valid @RequestBody ProfileManagementRequest profileManagementRequest) {
		profileManagementService.createProfile(ProfileManagementConverter.wsToModel(profileManagementRequest));
		return new ResponseEntity<>(new ProfileManagementResponse("Profile created successfully."), HttpStatus.CREATED);
	}
	
	  @GetMapping ("/getProfile")
	   public ResponseEntity <ProfileManager> getProfile(@RequestParam (value = "companyRefId") String companyRefId)
	   {
	      return ResponseEntity.ok().body(profileManagementService.getProfile(companyRefId));
	   }
	  
	  @GetMapping ("/getProfileType")
	   public ResponseEntity<String> getProfileType(@RequestParam (value = "companyRefId") String companyRefId)
	   {
	      return ResponseEntity.ok().body(profileManagementService.getProfileType(companyRefId));
	   }
	  
	  @GetMapping ("/getAllProfiles")
	   public ResponseEntity<List<ProfileManager>> getAllProfiles()
	   {
	      return ResponseEntity.ok().body(profileManagementService.getAllProfiles());
	   }
	  
	  @PutMapping ("/updateProfile")
	   public ResponseEntity <ProfileManagementResponse> updateProfile(@Valid @NotNull @RequestBody ProfileManagementRequest profileManagementRequest)
	   {
		  profileManagementService.updateProfile(ProfileManagementConverter.wsToModelUpdateProfile(profileManagementRequest));
	      return new ResponseEntity <>(new ProfileManagementResponse("Profile details updated "
	                                                    + "successfully."),
	                                   HttpStatus.OK);
	   }


	   // TODO: createProfile, must create a record in USER_DOCUMENTS table
      // TODO: Need another method just to upload documents, may be. This
   // goes to AWS first then then in USER_DOCUEMNTS.
      // TODO: What must be the status of those documents
      // TODO: Needs Validations

}
