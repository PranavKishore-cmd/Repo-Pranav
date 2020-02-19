package com.payment.xborder.dao.pairing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.payment.xborder.model.pairing.ws.Pairing;

@Component
public class PairingDao {

	@Autowired
	private  MongoTemplate mongoTemplate;

	
	public void createPairing(Pairing pairing) {
		mongoTemplate.save(pairing);
	}
	
}
