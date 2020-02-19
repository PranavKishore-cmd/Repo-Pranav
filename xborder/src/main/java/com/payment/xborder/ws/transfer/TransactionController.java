package com.payment.xborder.ws.transfer;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.payment.xborder.model.transfer.ws.TransactionRequest;
import com.payment.xborder.model.transfer.ws.TransactionResponse;
import com.payment.xborder.service.transaction.TransacationService;
import com.payment.xborder.util.converter.transfer.TransactionConverter;

@RestController
@RequestMapping("transfer")
public class TransactionController {

	@Autowired
	TransacationService transferService;

	@PostMapping("/initialMoneyTransfer")
	public ResponseEntity<TransactionResponse> initialMoneyTransfer(
			@Valid @RequestBody TransactionRequest transferRequest) {
		transferService.initailMoneyTransfer((TransactionConverter.wsToModel(transferRequest)));
		return new ResponseEntity<>(new TransactionResponse("Initial money transferred successfully."),
				HttpStatus.CREATED);
	}

	@GetMapping("/findBalanceForSender")
	public ResponseEntity<List<TransactionResponse>> findBalanceForSender(
			@RequestParam(value = "senderCompanyRefId") String senderCompanyRefId) {
		return ResponseEntity.ok().body(transferService.findBalanceForSender(senderCompanyRefId));
	}

	@GetMapping("/findBalanceForReceiver")
	public ResponseEntity<List<TransactionResponse>> findBalanceForReceiver(
			@RequestParam(value = "receiverCompanyRefId") String receiverCompanyRefId) {
		return ResponseEntity.ok().body(transferService.findayBalanceForReceiver(receiverCompanyRefId));
	}
	
	@PostMapping("/fundTransfer")
	public void fundTransfer(@Valid @RequestBody TransactionRequest transactionRequest) {
		transferService.receiverAccountUpdate(TransactionConverter.wsToModel(transactionRequest));
	}
	
	@GetMapping("/getForexValue")
	public String getForexValue() {
		final String serviceURL = "https://openexchangerates.org/api/latest.json?app_id=742bc70eb13c4a00a7fc44194567094c&base=USD";
		RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(serviceURL, String.class);

	    return result;
	}
}
