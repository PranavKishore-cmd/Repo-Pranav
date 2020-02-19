package com.payment.xborder.ws.dashboard.platform.compliance;

import com.payment.xborder.exception.BusinessException;
import com.payment.xborder.exception.ValidationException;
import com.payment.xborder.model.dashboard.platform.compliance.ws.DashboardPlatformComplianceResponse;
import com.payment.xborder.service.dashboard.platform.compliance.DashboardPlatformComplianceService;
import com.payment.xborder.ws.AbstractRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/DashboardPlatformCompliance")
public class DashboardPlatformComplianceController extends AbstractRestController{

	@Autowired
	private DashboardPlatformComplianceService dashboardPlatformComplianceService;
	
	 @GetMapping("/enterpriseDashboardComplianceOfficerDetails")
	   public ResponseEntity<DashboardPlatformComplianceResponse> getEnterpriseDashboardComplianceOfficerDetails(){
		   try {
			   return ResponseEntity.ok().body(dashboardPlatformComplianceService.getEnterpriseDashboardComplianceOfficerDetails());
		   }catch (ValidationException | BusinessException e) {
				HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
						: HttpStatus.valueOf(505);
				return new ResponseEntity(httpStatus);
			}
	   }
}
