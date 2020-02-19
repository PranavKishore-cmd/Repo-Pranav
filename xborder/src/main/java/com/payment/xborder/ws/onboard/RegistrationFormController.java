package com.payment.xborder.ws.onboard;

import com.payment.xborder.enums.CompanyStatus;
import com.payment.xborder.exception.BusinessException;
import com.payment.xborder.exception.ValidationException;
import com.payment.xborder.model.onboard.RegistrationForm;
import com.payment.xborder.model.onboard.UserCompanyDetails;
import com.payment.xborder.model.onboard.ws.*;
import com.payment.xborder.service.onboard.RegistrationService;
import com.payment.xborder.util.converter.onboard.RegistrationFormConverter;
import com.payment.xborder.ws.AbstractRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("onboard")
public class RegistrationFormController {
	
	@Autowired
	RegistrationService registrationService;

	
	@PostMapping("/registration")
	public ResponseEntity<RegistrationFormResponse> registerFormData(@Valid @RequestBody RegistrationFormRequest formRequest) {
		try {
			registrationService.registerFormData(RegistrationFormConverter.wsToModel(formRequest));
			return new ResponseEntity<>(new RegistrationFormResponse("Form Data Saved successfully."), HttpStatus.CREATED);
		}catch(BusinessException be) {
			return new ResponseEntity<>(new RegistrationFormResponse(be.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/registration/{email}")
	public ResponseEntity<RegistrationFormResponse> getRegistrationFormData(@PathVariable @NotNull String email) {
		return new ResponseEntity <>(RegistrationFormConverter.modelToWs(registrationService.getRegistrationFormData(email)), HttpStatus.OK);
	}
	
	@GetMapping("/registration/findByCompanyRefId/{companyRefId}")
	public ResponseEntity<RegistrationFormResponse> findByCompanyRefId(@PathVariable @NotNull @Value(value = "companyRefId") String companyRefId){
		return new ResponseEntity<> (RegistrationFormConverter.modelToWs(registrationService.findByCompanyRefId(companyRefId)), HttpStatus.OK);
	}
	
	@GetMapping("/registration/getCompanyStatus/{companyId}")
	public ResponseEntity<CompanyStatus> getCompanyStatus(@PathVariable @NotNull @Value(value = "companyId") String companyId){
		return new ResponseEntity<> (registrationService.getCompanyStatus(companyId), HttpStatus.OK);
	}
	
	@PostMapping("/registration/changeCompanyStatus/")
	public ResponseEntity <BaseResponse> changeCompanyStatus(@RequestBody ChangeCompanyStatusRequest changeCompanyStatusRequest ){
      try {
         registrationService.changeCompanyStatus(changeCompanyStatusRequest);
         return AbstractRestController
               .baseResponse("User added successfully" + ".", HttpStatus.CREATED);
      } catch (ValidationException | BusinessException e) {
         HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
                                                                    : HttpStatus.valueOf(505);
         return AbstractRestController.baseResponse(e.getMessage(), httpStatus);
      }
	}
	
	@GetMapping("/registration/getUserCompanyDetailsList")
	public ResponseEntity<List<UserCompanyDetails>> getUserCompanyDetailsList(){
		return new ResponseEntity<List<UserCompanyDetails>>(RegistrationFormConverter.modelToWs(registrationService.getAllCompanyDetails()), HttpStatus.OK);
	}
	
	@GetMapping("/registration/getAllCompanyDetails/{email}")
	public ResponseEntity<List<RegistrationForm>> getAllCompanyDetails(String emailId){
		return new ResponseEntity<List<RegistrationForm>>(RegistrationFormConverter.companyModelToWs(registrationService.getActiveCompanyDetails(emailId)), HttpStatus.OK);
	}
	
	@GetMapping("/registration/getAllCompanyDetails")
	public ResponseEntity<List<RegistrationForm>> getAllCompanyDetails(){
		return new ResponseEntity<List<RegistrationForm>>(RegistrationFormConverter.companyModelToWs(registrationService.getActiveCompanyDetails()), HttpStatus.OK);
	}
	
	@PostMapping("/registration/updateBeneficiaryOwnerKycStatus")
	public ResponseEntity<UpdateBeneficiaryOwnerKycRequest> updateBeneficiaryOwnerKycStatus(@RequestBody UpdateBeneficiaryOwnerKycRequest updateBeneficiaryOwnerKycRequest) {
		return new ResponseEntity <>(registrationService.updateBeneficiaryOwnerKycStatus(updateBeneficiaryOwnerKycRequest.getBeneficiaryDetailsList().get(0), updateBeneficiaryOwnerKycRequest.getCompanyRefID()), HttpStatus.OK);
	}
	
	@PostMapping("/registration/updateControlOwnerKycStatus")
	public ResponseEntity <BaseResponse> updateControlOwnerKycStatus(@RequestBody @Value(value = "companyRefId") String companyRefId) {
		try {
			registrationService.updateControlOwnerKycStatus(companyRefId);
			return AbstractRestController.baseResponse("Control Owner Kyc Status updated successfully" + ".", HttpStatus.CREATED);
		}catch(Exception e) {
			HttpStatus httpStatus = (e instanceof Exception) ? HttpStatus.BAD_REQUEST : HttpStatus.valueOf(505);
			return AbstractRestController.baseResponse(e.getMessage(), httpStatus);
		}
	}
}
