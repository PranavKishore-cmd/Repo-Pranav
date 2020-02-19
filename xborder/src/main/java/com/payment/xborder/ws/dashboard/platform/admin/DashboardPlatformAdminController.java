package com.payment.xborder.ws.dashboard.platform.admin;

import com.payment.xborder.exception.BusinessException;
import com.payment.xborder.exception.ValidationException;
import com.payment.xborder.model.dashboard.platform.admin.ws.DashboardPlatformAdminReponse;
import com.payment.xborder.service.dashboard.platform.admin.DashboardPlatformAdminService;
import com.payment.xborder.ws.AbstractRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/DashboardPlatformAdmin")
public class DashboardPlatformAdminController extends AbstractRestController{

	   @Autowired
	   private DashboardPlatformAdminService dashboardPlatformAdminService;

	   
	   @GetMapping("/enterpriseAdminUsersCount")
	   public ResponseEntity<DashboardPlatformAdminReponse> getEnterpriseAdminUsersCount(){
		   try {
			   return ResponseEntity.ok().body(dashboardPlatformAdminService.getEnterpriseAdminUsersCount());
		   }catch (ValidationException | BusinessException e) {
				HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
						: HttpStatus.valueOf(505);
				return new ResponseEntity(httpStatus);
			}
	   }
	   
	   @GetMapping("/enterpriseComplianceUsersCount")
	   public ResponseEntity<DashboardPlatformAdminReponse> getEnterpriseComplianceUsersCount(){
		   try {
			   return ResponseEntity.ok().body(dashboardPlatformAdminService.getEnterpriseComplianceUsersCount());
		   }catch (ValidationException | BusinessException e) {
				HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
						: HttpStatus.valueOf(505);
				return new ResponseEntity(httpStatus);
			}
	   }
	   
	   @GetMapping("/enterpriseSupportUsersCount")
	   public ResponseEntity<DashboardPlatformAdminReponse> getEnterpriseSupportUsersCount(){
		   try {
			   return ResponseEntity.ok().body(dashboardPlatformAdminService.getEnterpriseSupportOfficersUsersCount());
		   }catch (ValidationException | BusinessException e) {
				HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
						: HttpStatus.valueOf(505);
				return new ResponseEntity(httpStatus);
			}
	   }
	   
	   @GetMapping("/enterpriseTotalUsersCount")
	   public ResponseEntity<DashboardPlatformAdminReponse> getEnterpriseTotalUsersCount(){
		   try {
			   return ResponseEntity.ok().body(dashboardPlatformAdminService.getTotalEnterpriseUsersCount());
		   }catch (ValidationException | BusinessException e) {
				HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
						: HttpStatus.valueOf(505);
				return new ResponseEntity(httpStatus);
			}
	   }
	   
	   @GetMapping("/AllEnterpriseAdminDashboardDetails")
	   public ResponseEntity<DashboardPlatformAdminReponse> getAllEnterpriseAdminDashboardDetails(){
		   try {
			   return ResponseEntity.ok().body(dashboardPlatformAdminService.allEnterpriseAdminDashboardDetails());
		   }catch (ValidationException | BusinessException e) {
				HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
						: HttpStatus.valueOf(505);
				return new ResponseEntity(httpStatus);
			}
	   }
}
