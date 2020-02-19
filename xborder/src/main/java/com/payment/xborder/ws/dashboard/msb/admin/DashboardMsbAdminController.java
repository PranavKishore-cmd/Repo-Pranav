package com.payment.xborder.ws.dashboard.msb.admin;

import com.payment.xborder.exception.BusinessException;
import com.payment.xborder.exception.ValidationException;
import com.payment.xborder.model.dashboard.msb.admin.ws.DashboardMsbAdminResponse;
import com.payment.xborder.service.dashboard.msb.admin.DashboardMsbAdminService;
import com.payment.xborder.ws.AbstractRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/DashboardPlatformAdmin")
public class DashboardMsbAdminController extends AbstractRestController{

	 @Autowired
	 private DashboardMsbAdminService dashboardMsbAdminService;
	 
	 
	   @GetMapping("/msbAdminUsersCount")
	   public ResponseEntity<DashboardMsbAdminResponse> getMsbAdminUsersCount(){
		   try {
			   return ResponseEntity.ok().body(dashboardMsbAdminService.getMsbAdminUsersCount());
		   }catch (ValidationException | BusinessException e) {
				HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
						: HttpStatus.valueOf(505);
				return new ResponseEntity(httpStatus);
			}
	   }
	   
	   @GetMapping("/msbComplianceUsersCount")
	   public ResponseEntity<DashboardMsbAdminResponse> getMsbComplianceUsersCount(){
		   try {
			   return ResponseEntity.ok().body(dashboardMsbAdminService.getMsbComplianceUsersCount());
		   }catch (ValidationException | BusinessException e) {
				HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
						: HttpStatus.valueOf(505);
				return new ResponseEntity(httpStatus);
			}
	   }
	   
	   @GetMapping("/msbFinanceUsersCount")
	   public ResponseEntity<DashboardMsbAdminResponse> getMsbFinanceUsersCount(){
		   try {
			   return ResponseEntity.ok().body(dashboardMsbAdminService.getMsbFinanceUsersCount());
		   }catch (ValidationException | BusinessException e) {
				HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
						: HttpStatus.valueOf(505);
				return new ResponseEntity(httpStatus);
			}
	   }
	   
	   @GetMapping("/msbApproverUsersCount")
	   public ResponseEntity<DashboardMsbAdminResponse> getMsbApproverUsersCount(){
		   try {
			   return ResponseEntity.ok().body(dashboardMsbAdminService.getMsbApproverUsersCount());
		   }catch (ValidationException | BusinessException e) {
				HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
						: HttpStatus.valueOf(505);
				return new ResponseEntity(httpStatus);
			}
	   }
	   
	   @GetMapping("/msbUserCount")
	   public ResponseEntity<DashboardMsbAdminResponse> getMsbUserCount(){
		   try {
			   return ResponseEntity.ok().body(dashboardMsbAdminService.getMsbUserCount());
		   }catch (ValidationException | BusinessException e) {
				HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
						: HttpStatus.valueOf(505);
				return new ResponseEntity(httpStatus);
			}
	   }
	   
	   @GetMapping("/msbRegistrarCount")
	   public ResponseEntity<DashboardMsbAdminResponse> getMsbRegistrarCount(){
		   try {
			   return ResponseEntity.ok().body(dashboardMsbAdminService.getMsbRegistrarCount());
		   }catch (ValidationException | BusinessException e) {
				HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
						: HttpStatus.valueOf(505);
				return new ResponseEntity(httpStatus);
			}
	   }
	   
	   @GetMapping("/AllMsbAdminDashboardDetails")
	   public ResponseEntity<DashboardMsbAdminResponse> getAllMsbAdminDashboardDetails(){
		   try {
			   return ResponseEntity.ok().body(dashboardMsbAdminService.getAllMsbAdminDashboardDetails());
		   }catch (ValidationException | BusinessException e) {
				HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
						: HttpStatus.valueOf(505);
				return new ResponseEntity(httpStatus);
			}
	   }
}
