package com.payment.xborder.model.dashboard.msb.compliance.ws;

public class DashboardMsbComplianceResponse {

	private long pairedMember;
	private long pairingPending;
	private long completedTransaction;
	private long transactionsPerWeek;
	private long pendingTransactions;
	private long cancelledTransactions;
	public long getPairedMember() {
		return pairedMember;
	}
	public void setPairedMember(long pairedMember) {
		this.pairedMember = pairedMember;
	}
	public long getPairingPending() {
		return pairingPending;
	}
	public void setPairingPending(long pairingPending) {
		this.pairingPending = pairingPending;
	}
	public long getCompletedTransaction() {
		return completedTransaction;
	}
	public void setCompletedTransaction(long completedTransaction) {
		this.completedTransaction = completedTransaction;
	}
	public long getTransactionsPerWeek() {
		return transactionsPerWeek;
	}
	public void setTransactionsPerWeek(long transactionsPerWeek) {
		this.transactionsPerWeek = transactionsPerWeek;
	}
	public long getPendingTransactions() {
		return pendingTransactions;
	}
	public void setPendingTransactions(long pendingTransactions) {
		this.pendingTransactions = pendingTransactions;
	}
	public long getCancelledTransactions() {
		return cancelledTransactions;
	}
	public void setCancelledTransactions(long cancelledTransactions) {
		this.cancelledTransactions = cancelledTransactions;
	}
	
	
}
