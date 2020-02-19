package com.payment.xborder.ws.onboard;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.xborder.model.onboard.MailChecker;
import com.payment.xborder.model.onboard.PhoneChecker;
import com.payment.xborder.model.onboard.ws.MailCheckerRequest;
import com.payment.xborder.model.onboard.ws.MailCheckerResponse;
import com.payment.xborder.model.onboard.ws.MailValidatorRequest;
import com.payment.xborder.model.onboard.ws.MailValidatorResponse;
import com.payment.xborder.model.onboard.ws.PhoneCheckerRequest;
import com.payment.xborder.model.onboard.ws.PhoneValidatorRequest;
import com.payment.xborder.model.onboard.ws.PhoneValidatorResponse;
import com.payment.xborder.service.onboard.OnboardingService;
import com.payment.xborder.util.converter.onboard.MailCheckerConverter;
import com.payment.xborder.util.converter.onboard.PhoneCheckerConverter;

@RestController
@RequestMapping("onboard")
public class OnboardingController {

	@Autowired
	private OnboardingService onboardingService;

	@PostMapping("/mail")
	public ResponseEntity<MailCheckerResponse> createMailChecker(@Valid @NotNull @RequestBody MailCheckerRequest mailCheckerRequest) {
		onboardingService.createMailChecker(MailCheckerConverter.wsToModel(mailCheckerRequest));
		return new ResponseEntity<>(new MailCheckerResponse("New mail sent successfully."), HttpStatus.CREATED);
	}

	@GetMapping("/mail/{email}")
	public ResponseEntity<MailChecker> getMailChecker(@PathVariable String email) {
		return ResponseEntity.ok().body(onboardingService.getMailChecker(email));
	}
	
	@PutMapping("/mail/validator")
	public ResponseEntity<MailValidatorResponse> validateMailPasscode(@Valid @NotNull @RequestBody MailValidatorRequest mailValidatorRequest) {
		return ResponseEntity.ok().body(onboardingService.mailValidator(mailValidatorRequest));
	}
	
	@PostMapping("/phone")
	public ResponseEntity<String> createPhoneChecker(@Valid @NotNull @RequestBody PhoneCheckerRequest phoneCheckerRequest) {
		onboardingService.createPhoneChecker(PhoneCheckerConverter.wsToModel(phoneCheckerRequest));
		return new ResponseEntity<>("New message sent successfully.", HttpStatus.CREATED);
	}
	
	@GetMapping("/phone/{code}/{phone}")
	public ResponseEntity<PhoneChecker> getPhoneChecker(@PathVariable String code, @PathVariable String phone) {
		return ResponseEntity.ok().body(onboardingService.getPhoneChecker(code, phone));
	}
	
	@PutMapping("/phone/validator")
	public ResponseEntity<PhoneValidatorResponse> validatePhonePasscode(@Valid @NotNull @RequestBody PhoneValidatorRequest phoneValidatorRequest) {
		return ResponseEntity.ok().body(onboardingService.phoneValidator(phoneValidatorRequest));
	}

	@GetMapping
	public ResponseEntity<String> listContactRequest() {
		return ResponseEntity.ok().body("Welcome TH");
	}

}
