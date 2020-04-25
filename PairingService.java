package com.payment.xborder.service.pairing;

import com.payment.xborder.dao.pairing.PairingDao;
import com.payment.xborder.enums.PairingStatus;
import com.payment.xborder.exception.BusinessException;
import com.payment.xborder.model.onboard.ProfileManager;
import com.payment.xborder.model.onboard.RegistrationForm;
import com.payment.xborder.model.onboard.XBProfileManager;
import com.payment.xborder.model.pairing.ws.Pairing;
import com.payment.xborder.model.pairing.ws.PairingResponse;
import com.payment.xborder.service.onboard.RegistrationService;
import com.payment.xborder.service.profile.ProfileManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PairingService {

   @Autowired
   private PairingDao pairingDao;
   
   @Autowired
   private ProfileManagementService profileManagementService;

   @Autowired
   private RegistrationService registrationService;

   @Autowired
   private MongoTemplate mongoTemplate;

   public void createPairing(Pairing pairing) {
	  XBProfileManager profileOfReceiver = profileManagementService.getProfile(pairing.getReceiverCompanyRefId());
	  XBProfileManager profileOfSender = profileManagementService.getProfile(pairing.getSenderCompanyRefId());

	  if(profileOfSender == null ){
	     throw new BusinessException("Pairing cannot be done. Sender Profile"
                                     + " is not set.");
	  }

	  if(profileOfReceiver == null ){
        throw new BusinessException("Pairing cannot be done. Receiver Profile"
                                    + " is not set.");
     }

	  // Commented by Pranav
	 // Map<String,String> docsToPairForReceiver = profileOfReceiver.getDocsRequiredToPair();
	 // Map<String,String> docsToPairForSender = profileOfSender.getDocsRequiredToPair();
	  

	 // boolean docStatus = docsToPairForReceiver.keySet().equals(docsToPairForSender.keySet());
		/*
		 * if(docStatus) pairingDao.createPairing(pairing); else { throw new
		 * BusinessException("Pairing cannot be done. Mismatch in the documents uploaded"
		 * ); }
		 */

   }

   /*public List<PairingResponse> getCompaniesWithPairingStatusRequestedInbound(String companyRefId) {
      Query query =
            new Query(Criteria.where("receiverCompanyRefId").is(companyRefId).and("pairingStatus").is(PairingStatus.PAIRING_REQUESTED));
      return buildPairingResponse(query);
   }

   public List<PairingResponse> companiesWithPairingStatusRequestedOutbound(String companyRefId) {
      Query query =
            new Query(Criteria.where("senderCompanyRefId").is(companyRefId).and(
                  "pairingStatus").is(PairingStatus.PAIRING_REQUESTED));
      return buildPairingResponse(query);
   }

   public List<PairingResponse> getCompaniesWithPairingStatusApproved(String companyRefId) {
      Query query = new Query(Criteria.where("senderCompanyRefId").is(companyRefId).and("pairingStatus").is(PairingStatus.PAIRING_APPROVED));
      return buildPairingResponse(query);
   }

   public List<PairingResponse> getCompaniesWithPairingStatusActive(String companyRefId) {
      Query query = new Query(Criteria.where("senderCompanyRefId").is(companyRefId).and("pairingStatus").is(PairingStatus.PAIRING_ACTIVE));
      return buildPairingResponse(query);
   }

   public void changePairingStatusFromReqToAppr(String senderCompanyRefId,String receiverCompanyRefId) {
      changePairingStatus(senderCompanyRefId,receiverCompanyRefId,PairingStatus.PAIRING_APPROVED);
   }

   public void changePairingStatusFromApprToActive(String senderCompanyRefId,String receiverCompanyRefId) {
      changePairingStatus(senderCompanyRefId,receiverCompanyRefId,PairingStatus.PAIRING_ACTIVE);
   }*/

   public boolean changePairingStatus(String senderCompanyRefId,
                                      String receiverCompanyRefId,
                                      PairingStatus newStatus) {
      Query query = new Query(Criteria.where("senderCompanyRefId").is(senderCompanyRefId).and("receiverCompanyRefId").is(receiverCompanyRefId));
      Pairing pairing = mongoTemplate.findOne(query, Pairing.class);
      pairing.setPairingStatus(newStatus);
      mongoTemplate.save(pairing);
      return true;
   }

   private List<PairingResponse> buildPairingResponse(Query query) {
      List<PairingResponse> pairingResponseList = new ArrayList<PairingResponse>();
      PairingResponse pairingResponse = new PairingResponse();
      List<Pairing> listPairing = mongoTemplate.find(query, Pairing.class);

      // get all active companies
      Map <String, String> companyNameIDMap =
            registrationService.getGetCompanyIdAndNameDtls();

      for(Pairing pairing:listPairing) {
         pairingResponse.setPairingID(pairing.getPairingId());
         pairingResponse.setPairingStatus(pairing.getPairingStatus());

         pairingResponse.setReceiverCompanyRefId(pairing.getReceiverCompanyRefId());
         pairingResponse.setReceiverCompanyName(
               companyNameIDMap.containsKey(pairing.getReceiverCompanyRefId())
               ? companyNameIDMap.get(pairing.getReceiverCompanyRefId())
               : "Receiver Company");
         pairingResponse.setReceiverPairingCurrency(pairing.getReceiverPairingCurrency());
         pairingResponse.setReceiverPairingPaymentType(pairing.getReceiverPairingPaymentType());

         pairingResponse.setSenderCompanyRefId(pairing.getSenderCompanyRefId());
         pairingResponse.setSenderCompanyName(
               companyNameIDMap.containsKey(pairing.getSenderCompanyRefId())
               ? companyNameIDMap.get(pairing.getSenderCompanyRefId())
               : "Sender Company");
         pairingResponse.setSenderPairingcurrency(pairing.getSenderPairingcurrency());
         pairingResponse.setSenderPairingPaymentType(pairing.getSenderPairingPaymentType());
         pairingResponseList.add(pairingResponse);
      }
      if(listPairing==null) {
         return Collections.emptyList();
      }else {
         return pairingResponseList;
      }
   }

   public List<PairingResponse> getInboundPairingStatus(String companyRefId)
   {
      Query query = new Query(Criteria.where("receiverCompanyRefId").is(companyRefId));
      return buildPairingResponse(query);
   }

   public List<PairingResponse> getOutboundPairingStatus(String companyRefId)
   {
      Query query = new Query(Criteria.where("senderCompanyRefId").is(companyRefId));
      return buildPairingResponse(query);
   }

   public List <RegistrationForm> getListOfUnpairedCompanies(String companyRefId){
      // Check if this ID exist in DB

      // get all active companies
      List <RegistrationForm> activeComps =
            registrationService.getActiveCompanyDetails();

      List<String> companiesWithProfiles =
            profileManagementService.getAllProfiles()
                                    .stream()
                                    //.map(ProfileManager::getCompanyRefId)
                                    .map(XBProfileManager::getCompanyRefId)
                                    .collect(Collectors.toList());

      // Already paired companies
      List<String> pairedCompanies = getOutboundPairingStatus(companyRefId)
                  .stream().map(PairingResponse::getReceiverCompanyRefId)
                  .collect(Collectors.toList());

      // filter outboundPaired companies
      return activeComps.stream()
                 .filter(comp -> !comp.getCompanyId().equals(companyRefId))
                 .filter(comp -> !pairedCompanies.contains(comp.getCompanyId()))
                 .filter(comp -> companiesWithProfiles.contains(comp.getCompanyId()))
                 .collect(Collectors.toList());
   }

}
