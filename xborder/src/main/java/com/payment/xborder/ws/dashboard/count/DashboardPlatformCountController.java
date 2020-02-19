package com.payment.xborder.ws.dashboard.count;

import com.payment.xborder.exception.BusinessException;
import com.payment.xborder.exception.ValidationException;
import com.payment.xborder.model.dashboard.count.ws.DashBoardPlatformCountResponse;
import com.payment.xborder.service.dashboard.count.DashBoardPlatformCountService;
import com.payment.xborder.ws.AbstractRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/DashBoardPlatformCountUtil")
public class DashboardPlatformCountController extends AbstractRestController {

	@Autowired
	private DashBoardPlatformCountService dashBoardPlatformCountService;

	@GetMapping("/activeStatusCompanyCount")
	public ResponseEntity<DashBoardPlatformCountResponse> getActiveCompanyCountDetails() {

		try {
			return ResponseEntity.ok().body(dashBoardPlatformCountService.getActiveStatusCompanyCount());
		} catch (ValidationException | BusinessException e) {
			HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
					: HttpStatus.valueOf(505);
			return new ResponseEntity(httpStatus);
		}

	}

	@GetMapping("/approvedKycCount")
	public ResponseEntity<DashBoardPlatformCountResponse> getApprovedKycCountDetails() {

		try {
			return ResponseEntity.ok().body(dashBoardPlatformCountService.getKycApprovedCountValue());
		} catch (ValidationException | BusinessException e) {
			HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
					: HttpStatus.valueOf(505);
			return new ResponseEntity(httpStatus);
		}

	}

	@GetMapping("/pendingDocVeriStatusCount")
	public ResponseEntity<DashBoardPlatformCountResponse> getPendingDocVerificationStatus() {

		try {
			return ResponseEntity.ok().body(dashBoardPlatformCountService.getPendingVerificationStatusCount());
		} catch (ValidationException | BusinessException e) {
			HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
					: HttpStatus.valueOf(505);
			return new ResponseEntity(httpStatus);
		}

	}

	@GetMapping("/verifiedDocVeriStatusCount")
	public ResponseEntity<DashBoardPlatformCountResponse> getVerifiedDocVerificationStatus() {

		try {
			return ResponseEntity.ok().body(dashBoardPlatformCountService.getVerifiedVerificationStatusCount());
		} catch (ValidationException | BusinessException e) {
			HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
					: HttpStatus.valueOf(505);
			return new ResponseEntity(httpStatus);
		}

	}

	@GetMapping("/activeParingCount")
	public ResponseEntity<DashBoardPlatformCountResponse> getActiveMSBPairingCountStatus() {

		try {
			return ResponseEntity.ok().body(dashBoardPlatformCountService.getActiveStatusPairingCount());
		} catch (ValidationException | BusinessException e) {
			HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
					: HttpStatus.valueOf(505);
			return new ResponseEntity(httpStatus);
		}

	}

}
