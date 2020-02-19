package com.payment.xborder.ws.payment;

import com.payment.xborder.enums.PaymentRecordStatus;
import com.payment.xborder.exception.BusinessException;
import com.payment.xborder.exception.ValidationException;
import com.payment.xborder.model.onboard.ws.BaseResponse;
import com.payment.xborder.model.payment.PaymentRecord;
import com.payment.xborder.service.payment.PaymentFileManagementService;
import com.payment.xborder.ws.AbstractRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping ("payment")
public class PaymentFileManagementController extends AbstractRestController
{

	@Autowired
   PaymentFileManagementService fileManagementService;
	
	@PostMapping ("/upload")
   ResponseEntity<String> uploadFile(@RequestParam ("file") MultipartFile file,
                                     @RequestParam ("senderEmailId") String senderEmailId,
                                     @RequestParam ("receiverEmailId") String receiverEmailId,
                                     @RequestParam ("uploaderEmailId") String uploaderEmailId) {
		fileManagementService.processFile(file,
                                        senderEmailId,
                                        receiverEmailId,
                                        uploaderEmailId);
		return ResponseEntity.ok().body("Success");
    }
	
	@GetMapping("/getOutboundPayments")
   ResponseEntity<List<PaymentRecord>> getOutgoingPaymentRecordsByStatus(
         @RequestParam ("companyId") String companyID,
         @RequestParam ("paymentStatus") PaymentRecordStatus paymentRecordStatus)
   {
      List <PaymentRecord> records =
            fileManagementService.getOutgoingPaymentRecordsByStatus(companyID,
                                                                    paymentRecordStatus);
      return ResponseEntity.ok().body(records);
	}

   @GetMapping("getInboundPayments")
   ResponseEntity<List<PaymentRecord>> getIncomingPaymentRecordsByStatus(
         @RequestParam ("companyId") String companyID,
         @RequestParam ("paymentStatus") PaymentRecordStatus paymentRecordStatus)
   {

      List <PaymentRecord> records =
            fileManagementService.getIncomingPaymentRecordsByStatus(companyID,
                                                                    paymentRecordStatus);
      return ResponseEntity.ok().body(records);
   }

   @GetMapping ("/changePairingStatus")
   public ResponseEntity <BaseResponse> changePaymentRecordStatus(
         @RequestParam (value = "paymentRecordId") String paymentRecordId,
         @RequestParam (value = "status") PaymentRecordStatus paymentRecordStatus)
   {

      try {
         fileManagementService.changePaymentRecordStatus(paymentRecordId,
                                            paymentRecordStatus);
         return baseResponse("Payment status updated successfully.",
                             HttpStatus.OK);
      } catch (ValidationException | BusinessException e) {
         HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
                                                                    : HttpStatus.valueOf(505);
         return baseResponse(e.getMessage(), httpStatus);
      }

   }

}
