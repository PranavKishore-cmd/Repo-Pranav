package com.payment.xborder.dao.onboard;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.payment.xborder.enums.PairingStatus;
import com.payment.xborder.model.pairing.ws.Pairing;

@Component
public class PairingInfoDao {
	private static final Logger log = LoggerFactory.getLogger(PairingInfoDao.class);
	
	public PairingInfoDao() {
		
	}
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public List<Pairing> getAllPairingActiveInfo(){
		log.info("PairingInfoDao : getAllPairingActiveInfo : Start");
		Query query = new Query(Criteria.where("pairingStatus").is(PairingStatus.PAIRING_ACTIVE));
		List<Pairing> pairingActivStatusList = mongoTemplate.find(query, Pairing.class);
		log.info("PairingInfoDao : getAllPairingActiveInfo : End");
		return pairingActivStatusList;
		
		
	}

}
