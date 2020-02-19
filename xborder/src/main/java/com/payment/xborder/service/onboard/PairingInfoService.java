package com.payment.xborder.service.onboard;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.xborder.configuration.ApplicationProperties;
import com.payment.xborder.dao.onboard.PairingInfoDao;
import com.payment.xborder.model.onboard.ws.PairingInfoDetails;
import com.payment.xborder.model.pairing.ws.Pairing;

@Service
public class PairingInfoService {
	

	@Autowired
	PairingInfoDao pairingInfoDao;
	
	@Autowired 
	ApplicationProperties applicationProperties;
	
	public List<PairingInfoDetails> getActivePairingStatusList(){
		
		List<Pairing> activePairingStatusList = pairingInfoDao.getAllPairingActiveInfo();
		if(!activePairingStatusList.isEmpty()) {
			
			List<PairingInfoDetails> filteredStatusList = activePairingStatusList.stream().map(p -> new PairingInfoDetails(p))
					.collect(Collectors.toList());
			
			return filteredStatusList;
		}
		return Collections.EMPTY_LIST;
	}

}
