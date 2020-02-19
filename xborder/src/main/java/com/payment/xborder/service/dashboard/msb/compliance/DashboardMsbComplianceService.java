package com.payment.xborder.service.dashboard.msb.compliance;

import com.payment.xborder.model.dashboard.msb.compliance.ws.DashboardMsbComplianceResponse;
import org.springframework.stereotype.Service;

@Service
public class DashboardMsbComplianceService {

	public DashboardMsbComplianceResponse getMsbDashboardComplianceOfficerDetails() {
		DashboardMsbComplianceResponse dashboardPlatformComplianceResponse = new DashboardMsbComplianceResponse();
		long pairedMember = 10;
		long pairingPending = 10;
		long completedTransaction = 10;
		long transactionsPerWeek = 10;
		long pendingTransactions = 10;
		long cancelledTransactions = 10;
		dashboardPlatformComplianceResponse.setPairedMember(pairedMember);
		dashboardPlatformComplianceResponse.setPairingPending(pairingPending);
		dashboardPlatformComplianceResponse.setCompletedTransaction(completedTransaction);
		dashboardPlatformComplianceResponse.setTransactionsPerWeek(transactionsPerWeek);
		dashboardPlatformComplianceResponse.setPendingTransactions(pendingTransactions);
		dashboardPlatformComplianceResponse.setCancelledTransactions(cancelledTransactions);
		return dashboardPlatformComplianceResponse;
	}
}
