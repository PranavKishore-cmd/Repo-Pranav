package com.payment.xborder.ws.dashboard.msb.compliance;

import com.payment.xborder.exception.BusinessException;
import com.payment.xborder.exception.ValidationException;
import com.payment.xborder.model.dashboard.msb.compliance.ws.DashboardMsbComplianceResponse;
import com.payment.xborder.service.dashboard.msb.compliance.DashboardMsbComplianceService;
import com.payment.xborder.ws.AbstractRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboardMsbCompliance")
public class DashboardMsbComplianceController extends AbstractRestController{

	@Autowired
	private DashboardMsbComplianceService dashboardMsbComplianceService;
	 
    @GetMapping("/msbComplianceDetails")
	   public ResponseEntity<DashboardMsbComplianceResponse> getMsbDashboardComplianceOfficerDetails(){
		   try {
			   return ResponseEntity.ok().body(dashboardMsbComplianceService.getMsbDashboardComplianceOfficerDetails());
		   }catch (ValidationException | BusinessException e) {
				HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
						: HttpStatus.valueOf(505);
				return new ResponseEntity(httpStatus);
			}
	   }
}
