package com.payment.xborder.service.dashboard.platform.compliance;

import com.payment.xborder.model.dashboard.platform.compliance.ws.DashboardPlatformComplianceResponse;
import org.springframework.stereotype.Service;

@Service
public class DashboardPlatformComplianceService {

	public DashboardPlatformComplianceResponse getEnterpriseDashboardComplianceOfficerDetails() {
		DashboardPlatformComplianceResponse dashboardPlatformComplianceResponse = new DashboardPlatformComplianceResponse();
		long enterpriseComplianceActiveCompanies = 10;
		long enterpriseFinalApproval = 10;
		long enterpriseComplianceApproval = 10;
		long enterpriseKycApproval = 10;
		long enterpriseDocVerification = 10;
		long enterpriseDocReUpload = 10;
		long enterprisepairedMsbs = 10;
		dashboardPlatformComplianceResponse.setActiveCompanies(enterpriseComplianceActiveCompanies);
		dashboardPlatformComplianceResponse.setComplianceApproval(enterpriseComplianceApproval);
		dashboardPlatformComplianceResponse.setDocReUpload(enterpriseDocReUpload);
		dashboardPlatformComplianceResponse.setDocVerification(enterpriseDocVerification);
		dashboardPlatformComplianceResponse.setFinalApproval(enterpriseFinalApproval);
		dashboardPlatformComplianceResponse.setKycApproval(enterpriseKycApproval);
		dashboardPlatformComplianceResponse.setPairedMsbs(enterprisepairedMsbs);
		return dashboardPlatformComplianceResponse;
	}

}
