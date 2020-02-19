package com.payment.xborder.util.converter.transfer;

import com.payment.xborder.model.transfer.ws.Account;
import com.payment.xborder.model.transfer.ws.TransactionRequest;

public class TransactionConverter {

	public static Account wsToModel(TransactionRequest transferRequest) {
    	return prepareInitialMoneyTransferInfo(transferRequest);
	}

	/*
	 * public static ProfileManager wsToModelUpdateProfile(@Valid @NotNull
	 * ProfileManagementRequest profileManagementRequest) { return
	 * prepareProfileInfo(profileManagementRequest); }
	 */
	
	private static Account prepareInitialMoneyTransferInfo(TransactionRequest transactionRequest) {
		Account account = new Account();
		account.setSenderCompanyRefId(transactionRequest.getSenderCompanyRefId());
		account.setReceiverCompanyRefId(transactionRequest.getReceiverCompanyRefId());
		account.setAmount(transactionRequest.getAmount());
		account.setSenderCurrencyType(transactionRequest.getSenderCurrencyType());
    	return account;
	}

}
