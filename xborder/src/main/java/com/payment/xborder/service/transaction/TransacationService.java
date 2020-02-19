package com.payment.xborder.service.transaction;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.google.common.base.Optional;
import com.payment.xborder.dao.transfer.TransactionDao;
import com.payment.xborder.model.transfer.ws.Account;
import com.payment.xborder.model.transfer.ws.TransactionResponse;

@Service
public class TransacationService {

	@Autowired
	private TransactionDao transactionDao;

	@Autowired
	private MongoTemplate mongoTemplate;

	private static final Logger log = LoggerFactory.getLogger(TransacationService.class);

	public void initailMoneyTransfer(Account transfer) {
		transactionDao.initailMoneyTransfer(transfer);

	}

	public List<TransactionResponse> findBalanceForSender(String senderCompanyRefId) {
		log.info("TransacationService : findBalanceForSender : Start");
		List<TransactionResponse> transferResponseList = new ArrayList<TransactionResponse>();
		List<Account> accountList = transactionDao.findAccount(senderCompanyRefId, true);
		if (accountList == null) {
			System.out.println("No Balance found for " + senderCompanyRefId);
			return null;
		} else {
			accountList.stream().forEach(account -> {
				TransactionResponse transerResponse = new TransactionResponse();
				transerResponse.setAmount(account.getAmount());
				transerResponse.setReceiverCompanyRefId(account.getReceiverCompanyRefId());
				transferResponseList.add(transerResponse);
			});
		}
		log.info("TransacationService : findBalanceForSender : End");
		return transferResponseList;
	}

	public List<TransactionResponse> findayBalanceForReceiver(String receiverCompanyRefId) {
		log.info("TransacationService : findayBalanceForReceiver : Start");
		List<TransactionResponse> transferResponseList = new ArrayList<TransactionResponse>();
		List<Account> accountList = transactionDao.findAccount(receiverCompanyRefId, false);
		if (accountList == null) {
			System.out.println("No Balance found for " + receiverCompanyRefId);
			return null;
		} else {
			accountList.stream().forEach(account -> {
				TransactionResponse transerResponse = new TransactionResponse();
				transerResponse.setAmount(account.getAmount());
				transerResponse.setSenderCompanyRefId(account.getSenderCompanyRefId());
				transferResponseList.add(transerResponse);
			});
		}
		log.info("TransacationService : findayBalanceForReceiver : End");
		return transferResponseList;
	}
	
	public void receiverAccountUpdate(Account account) {
		log.info("TransacationService : receiverAccountUpdated : Start");
		account.getReceiverCompanyRefId().stream().forEach(receiverCompanyRefID -> {
			Account receiverAccount = transactionDao.getAccount(receiverCompanyRefID, false);
			if (ObjectUtils.isEmpty(receiverAccount)) {
				transactionDao.initailMoneyTransfer(account);
			} else {
				transactionDao.accountTransferUpdate(receiverCompanyRefID, account.getAmount());
			}
		});
		log.info("TransacationService : receiverAccountUpdated : End");
	}

}
