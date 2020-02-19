package com.payment.xborder.dao.transfer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.payment.xborder.model.transfer.ws.Account;

@Component
public class TransactionDao {

	@Autowired
	private  MongoTemplate mongoTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(TransactionDao.class);

	public void initailMoneyTransfer(Account account) {
		log.info("TransactionDao : initailMoneyTransfer : Start");
		mongoTemplate.save(account);
		log.info("TransactionDao : initailMoneyTransfer : End");
	}
	
	public void transfer(Account account) {
		log.info("TransactionDao : transfer : Start");
		mongoTemplate.save(account);
		log.info("TransactionDao : transfer : End");
	}
	
	public List<Account> findAccount(String companyRefId, boolean isSender){
		log.info("TransactionDao : findAccount : Start");
		Query query = null;
		if(isSender) {
			query = new Query(Criteria.where("senderCompanyRefId").is(companyRefId));
		}else {
			query = new Query(Criteria.where("receiverCompanyRefId").is(companyRefId));
		}
		List<Account> accountList = mongoTemplate.find(query, Account.class);
		log.info("TransactionDao : findAccount : End");
		return accountList;
	}
	
	public Account getAccount(String companyRefId, boolean isSender){
		log.info("TransactionDao : findAccount : Start");
		Query query = null;
		if(isSender) {
			query = new Query(Criteria.where("senderCompanyRefId").is(companyRefId));
		}else {
			query = new Query(Criteria.where("receiverCompanyRefId").is(companyRefId));
		}
		Account accountList = mongoTemplate.findOne(query, Account.class);
		log.info("TransactionDao : findAccount : End");
		return accountList;
	}
	
	public void accountTransferUpdate(String companyRefId, String amount) {
		log.info("TransactionDao : accountTransfer : Start");
		Query query = new Query(Criteria.where("receiverCompanyRefId").is(companyRefId));
		Update update = new Update();
		update.set("amount", amount);
		mongoTemplate.updateFirst(query, update, Account.class);
		log.info("TransactionDao : accountTransfer : End");
	}
}
