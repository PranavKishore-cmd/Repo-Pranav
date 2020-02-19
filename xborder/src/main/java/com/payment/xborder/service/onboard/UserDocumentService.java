package com.payment.xborder.service.onboard;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.xborder.configuration.ApplicationProperties;
import com.payment.xborder.dao.onboard.PairingInfoDao;
import com.payment.xborder.dao.onboard.UserDocumentsDao;
import com.payment.xborder.model.file.UserDocuments;
import com.payment.xborder.model.onboard.ws.UserDocsStatus;

@Service
public class UserDocumentService {
	
	@Autowired
	UserDocumentsDao userDocumentsDao;
	
	@Autowired
	PairingInfoDao pairingInfoDao;
	
	@Autowired 
	ApplicationProperties applicationProperties;
	
	
public List<UserDocsStatus> getAllPendingAndVerifiedVerificationStatusList(){
	
		List<UserDocuments> pendingDocVerificationList = userDocumentsDao.getAllUserDocumentsInfo();
		
		if(!pendingDocVerificationList.isEmpty()) {
			
			List<UserDocsStatus> filteredPendingVerList = pendingDocVerificationList.stream().map(p-> new UserDocsStatus(p))
					.collect(Collectors.toList());
			return filteredPendingVerList;
		}
		return Collections.EMPTY_LIST;
	}

	





}
