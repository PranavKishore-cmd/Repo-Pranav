package com.payment.xborder.dao.profile;


import com.payment.xborder.model.onboard.ProfileManager;
import com.payment.xborder.model.onboard.ReceiverInfo;
import com.payment.xborder.model.onboard.XBProfileManager;
import com.payment.xborder.model.profile.ws.SenderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProfileManagementDao {

	public ProfileManagementDao() {

	}

	@Autowired
	private MongoTemplate mongoTemplate;

	public void createProfile(ProfileManager profileManager) {
		mongoTemplate.save(profileManager);
	}

	// Added by Pranav

	public void createProfileXB(XBProfileManager profileManager) {
		mongoTemplate.save(profileManager);
	}

	public List<XBProfileManager> getAllProfiles() {
		return mongoTemplate.findAll(XBProfileManager.class);
	}

	public XBProfileManager getProfileDetails(String companyRefId) {
		Query query = new Query(Criteria.where("companyRefId").is(companyRefId));
		XBProfileManager profileManager = mongoTemplate.findOne(query, XBProfileManager.class);
		if (profileManager == null) {
			System.out.println("No Profile details found for " + companyRefId);
			return null;
		}
		return profileManager;
	}

	public ProfileManager updateProfilerDetails(ProfileManager profileManager) {
		// Replace with upsert
		Query query = new Query(Criteria.where("companyRefId").is(profileManager.getCompanyRefId()));
		ProfileManager profileManagerDetailsFromDB = mongoTemplate.findOne(query, ProfileManager.class);
		if (profileManagerDetailsFromDB == null) {
			throw new IllegalArgumentException("No profile details found for given companyId");
		} else {
			SenderInfo senderInfo = new SenderInfo();
			List<ReceiverInfo> receiver = new ArrayList<ReceiverInfo>();
			profileManagerDetailsFromDB.setComments(profileManager.getComments());
			profileManagerDetailsFromDB.setEntityName(profileManager.getEntityName());
			profileManagerDetailsFromDB.setCutOffTimeInHours(profileManager.getCutOffTimeInHours());
			profileManagerDetailsFromDB.setSenderInfo(profileManager.getSenderInfo());
			profileManagerDetailsFromDB.setReceiverInfo(profileManager.getReceiverInfo());
			profileManagerDetailsFromDB.setProfileType(profileManager.getProfileType());
			profileManagerDetailsFromDB.setServiceOffered(profileManager.getServiceOffered());
			senderInfo.setSenderCurrency(profileManager.getSenderInfo().getSenderCurrency());
			senderInfo.setSenderPaymentFormat(profileManager.getSenderInfo().getSenderPaymentFormat());
			senderInfo.setSenderPaymentLimit(profileManager.getSenderInfo().getSenderPaymentLimit());
			senderInfo.setSenderPaymentType(profileManager.getSenderInfo().getSenderPaymentType());
			senderInfo.setSenderRegion(profileManager.getSenderInfo().getSenderRegion());
			profileManagerDetailsFromDB.setSenderInfo(senderInfo);
			mongoTemplate.save(profileManagerDetailsFromDB);
//		    	for(ReceiverInfo updateReceiver:profileManager.getReceiverInfo()) {
//		    		ReceiverInfo receiverInfo = new ReceiverInfo();
//		    		receiverInfo.setReceiverCurrency(updateReceiver.getReceiverCurrency());
//		    		receiverInfo.setReceiverPaymentFormat(updateReceiver.getReceiverPaymentFormat());
//		    		receiverInfo.setReceiverPaymentLimit(updateReceiver.getReceiverPaymentLimit());
//		    		receiverInfo.setReceiverPaymentType(updateReceiver.getReceiverPaymentType());
//		    		receiverInfo.setReceiverRegion(updateReceiver.getReceiverRegion());
//		    		receiver.add(receiverInfo);
//		    	}
//		    	profileManagerDetailsFromDB.setReceiverInfo(receiver);
//		    	MongoClient client = new MongoClient("localhost", 27017);
//		    	CodecRegistry pojoCodecRegistry = org.bson.codecs.configuration.CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), org.bson.codecs.configuration.CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
//				MongoDatabase database = client.getDatabase("x_border").withCodecRegistry(pojoCodecRegistry);
//				MongoCollection<org.bson.Document> collection = database
//						.getCollection("PROFILE_MANAGER");
//				Bson filter = new Document("companyRefId",profileManager.getCompanyRefId() );
//				Bson updateOperationDocument = new Document("$set", profileManagerDetailsFromDB);
//				collection.updateOne(filter, updateOperationDocument);
//
//				client.close();

		}
		return profileManagerDetailsFromDB;
	}
}
