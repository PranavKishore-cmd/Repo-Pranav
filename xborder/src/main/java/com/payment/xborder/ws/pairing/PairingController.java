package com.payment.xborder.ws.pairing;

import com.payment.xborder.enums.PairingStatus;
import com.payment.xborder.exception.BusinessException;
import com.payment.xborder.exception.ValidationException;
import com.payment.xborder.model.onboard.RegistrationForm;
import com.payment.xborder.model.onboard.ws.BaseResponse;
import com.payment.xborder.model.pairing.ws.PairingRequest;
import com.payment.xborder.model.pairing.ws.PairingResponse;
import com.payment.xborder.service.pairing.PairingService;
import com.payment.xborder.util.converter.pairing.PairingConverter;
import com.payment.xborder.ws.AbstractRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("Pairing")
public class PairingController extends AbstractRestController{

   @Autowired
   private PairingService pairingService;

   /*@GetMapping ("/companiesWithPairingStatusRequestedInbound")
   public ResponseEntity<List<PairingResponse>> companiesWithPairingStatusRequestedInbound(@RequestParam (value = "companyRefId") String companyRefId)
   {

      try {
         return ResponseEntity.ok().body(pairingService.getCompaniesWithPairingStatusRequestedInbound(companyRefId));
      } catch (ValidationException | BusinessException e) {
         HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
                                                                    : HttpStatus.valueOf(505);
         return new ResponseEntity(httpStatus);
      }
   }

   @GetMapping ("/companiesWithPairingStatusRequestedOutbound")
   public ResponseEntity<List<PairingResponse>> companiesWithPairingStatusRequestedOutbound(@RequestParam (value = "companyRefId") String companyRefId)
   {

      try {
         return ResponseEntity.ok().body(pairingService.companiesWithPairingStatusRequestedOutbound(companyRefId));
      } catch (ValidationException | BusinessException e) {
         HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
                                                                    : HttpStatus.valueOf(505);
         return new ResponseEntity(httpStatus);
      }
   }

   @GetMapping ("/companiesWithPairingStatusApproved")
   public ResponseEntity<List<PairingResponse>> getCompaniesWithPairingStatusApproved(@RequestParam (value = "companyRefId") String companyRefId)
   {

      try {
         return ResponseEntity.ok().body(pairingService.getCompaniesWithPairingStatusApproved(companyRefId));
      } catch (ValidationException | BusinessException e) {
         HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
                                                                    : HttpStatus.valueOf(505);
         return new ResponseEntity(httpStatus);
      }
   }

   @GetMapping ("/companiesWithPairingStatusActive")
   public ResponseEntity<List<PairingResponse>> getCompaniesWithPairingStatusActive(@RequestParam (value = "companyRefId") String companyRefId)
   {
      try {
         return ResponseEntity.ok().body(pairingService.getCompaniesWithPairingStatusActive(companyRefId));
      } catch (ValidationException | BusinessException e) {
         HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
                                                                    : HttpStatus.valueOf(505);
         return new ResponseEntity(httpStatus);
      }
   }

   @GetMapping ("/changePairingStatusFromReqToAppr")
   public ResponseEntity <BaseResponse> changePairingStatusFromReqToAppr(@RequestParam (value = "senderCompanyRefId") String senderCompanyRefId,
                                                                         @RequestParam (value = "receiverCompanyRefId") String receiverCompanyRefId)
   {

      try {
         pairingService.changePairingStatusFromReqToAppr(senderCompanyRefId, receiverCompanyRefId);
         return baseResponse("Pairing status updated successfully.", HttpStatus.OK);
      } catch (ValidationException | BusinessException e) {
         HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
                                                                    : HttpStatus.valueOf(505);
         return baseResponse(e.getMessage(), httpStatus);
      }

   }

   @GetMapping ("/changePairingStatusFromApprToActive")
   public ResponseEntity<BaseResponse> changePairingStatusFromApprToActive(@RequestParam (value = "senderCompanyRefId") String senderCompanyRefId,
                                                                           @RequestParam (value = "receiverCompanyRefId") String receiverCompanyRefId)
   {
      try {
         pairingService.changePairingStatusFromApprToActive(senderCompanyRefId, receiverCompanyRefId);
         return baseResponse("Pairing status updated successfully.", HttpStatus.OK);
      } catch (ValidationException | BusinessException e) {
         HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
                                                                    : HttpStatus.valueOf(505);
         return baseResponse(e.getMessage(), httpStatus);
      }

   }*/


   @PostMapping("/create")
   public ResponseEntity<BaseResponse> createPairing(@Valid @RequestBody PairingRequest pairingRequest) {
      try {
         pairingService.createPairing(PairingConverter.wsToModel(pairingRequest));
         return baseResponse("Pairing created successfully.", HttpStatus.CREATED);
      } catch (ValidationException | BusinessException e) {
         HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
                                                                    : HttpStatus.valueOf(505);
         return baseResponse(e.getMessage(), httpStatus);
      }

   }

   @GetMapping ("/getInboundPairingStatus")
   public ResponseEntity<List<PairingResponse>> getInboundPairingStatus(@RequestParam (value = "companyRefId") String companyRefId)
   {
      try {
         return ResponseEntity.ok().body(pairingService.getInboundPairingStatus(companyRefId));
      } catch (ValidationException | BusinessException e) {
         HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
                                                                    : HttpStatus.valueOf(505);
         return new ResponseEntity(httpStatus);
      }
   }

   @GetMapping ("/getOutboundPairingStatus")
   public ResponseEntity<List<PairingResponse>> getOutboundPairingStatus(@RequestParam (value = "companyRefId") String companyRefId)
   {

      try {
         return ResponseEntity.ok().body(pairingService.getOutboundPairingStatus(companyRefId));
      } catch (ValidationException | BusinessException e) {
         HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
                                                                    : HttpStatus.valueOf(505);
         return new ResponseEntity(httpStatus);
      }
   }

   @GetMapping ("/getListOfUnpairedCompanies")
   public ResponseEntity <List<RegistrationForm>> getListOfUnpairedCompanies(@RequestParam (value = "companyRefId") String companyRefId)
   {
      try {
         return ResponseEntity.ok().body(pairingService.getListOfUnpairedCompanies(companyRefId));
      } catch (ValidationException | BusinessException e) {
         HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
                                                                    : HttpStatus.valueOf(505);
         return new ResponseEntity(httpStatus);
      }
   }

   @GetMapping ("/changePairingStatus")
   public ResponseEntity <BaseResponse> changePairingStatus(@RequestParam (value = "senderCompanyRefId") String senderCompanyRefId,
                                                            @RequestParam (value = "receiverCompanyRefId") String receiverCompanyRefId,
                                                            @RequestParam (value = "status") PairingStatus status)
   {

      try {
         pairingService.changePairingStatus(senderCompanyRefId,
                                            receiverCompanyRefId,
                                            status);
         return baseResponse("Pairing status updated successfully.", HttpStatus.OK);
      } catch (ValidationException | BusinessException e) {
         HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
                                                                    : HttpStatus.valueOf(505);
         return baseResponse(e.getMessage(), httpStatus);
      }

   }

}
