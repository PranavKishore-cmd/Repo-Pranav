package com.payment.xborder.service.dashboard.count;

import com.payment.xborder.enums.CompanyStatus;
import com.payment.xborder.enums.DocumentVerificationStatus;
import com.payment.xborder.enums.PairingStatus;
import com.payment.xborder.model.dashboard.count.ws.DashBoardPlatformCountResponse;
import com.payment.xborder.model.file.DocumentDetails;
import com.payment.xborder.model.onboard.BeneficiaryInformationDetails;
import com.payment.xborder.model.onboard.ws.CompanyDetails;
import com.payment.xborder.model.onboard.ws.PairingInfoDetails;
import com.payment.xborder.model.onboard.ws.UserDocsStatus;
import com.payment.xborder.service.onboard.PairingInfoService;
import com.payment.xborder.service.onboard.RegistrationService;
import com.payment.xborder.service.onboard.UserDocumentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashBoardPlatformCountService {

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private UserDocumentService userDocumentService;

	@Autowired
	private PairingInfoService pairingInfoService;

	public DashBoardPlatformCountResponse getActiveStatusCompanyCount() {

		DashBoardPlatformCountResponse dashBoardPlatformCountResponse = new DashBoardPlatformCountResponse();
		long activeStatusCompanyCount = 0;
		List<CompanyDetails> compDetails = registrationService.getAllActiveCompanyStatus();
		if (compDetails != null) {
			activeStatusCompanyCount = compDetails.stream().filter(company -> CompanyStatus
					.valueOf(company.getRegistrationStatus().toString()).equals(CompanyStatus.ACTIVE)).count();
		}

		dashBoardPlatformCountResponse.setActiveCompanies(activeStatusCompanyCount);
		return dashBoardPlatformCountResponse;

	}

	public DashBoardPlatformCountResponse getKycApprovedCountValue() {

		DashBoardPlatformCountResponse dashBoardPlatformCountResponse = new DashBoardPlatformCountResponse();

		int countKycApproved = 0;
		List<CompanyDetails> filteredCompDetails = registrationService.getAllKycApprovedDetails();
		List<BeneficiaryInformationDetails> biDetailsList = new ArrayList<BeneficiaryInformationDetails>();

		for (CompanyDetails companyDetails : filteredCompDetails) {

			biDetailsList = companyDetails.getBeneficiaryInformationDetailsList();

			if (biDetailsList != null) {

				for (BeneficiaryInformationDetails beneficiaryInformationDetails : biDetailsList) {

					if (beneficiaryInformationDetails.isKycVerified() == true) {

						countKycApproved = countKycApproved + 1;
					} else {

					}
				}
			}

		}

		dashBoardPlatformCountResponse.setCountKycApproved(countKycApproved);

		return dashBoardPlatformCountResponse;

	}

	public DashBoardPlatformCountResponse getPendingVerificationStatusCount() {

		long pendingVerificationDocCount = 0;

		DashBoardPlatformCountResponse dashBoardPlatformCountResponse = new DashBoardPlatformCountResponse();
		List<UserDocsStatus> filteredStatusDetails = userDocumentService
				.getAllPendingAndVerifiedVerificationStatusList();
		Map<String, DocumentDetails> mapCountService = new HashMap<String, DocumentDetails>();
		if (filteredStatusDetails != null) {
			for (UserDocsStatus userDocsStatus : filteredStatusDetails) {
				mapCountService = userDocsStatus.getDocumentsUploaded();
				for (Map.Entry<String, DocumentDetails> entry : mapCountService.entrySet()) {
					DocumentDetails value = entry.getValue();
					DocumentVerificationStatus dvstatus = value.getDocumentVerificationStatus();
					if (dvstatus != null && dvstatus.equals(DocumentVerificationStatus.PENDING)) {
						pendingVerificationDocCount = pendingVerificationDocCount + 1;
					} else {
					}

				}

			}
		}

		dashBoardPlatformCountResponse.setPendingVerificationDocCount(pendingVerificationDocCount);
		return dashBoardPlatformCountResponse;
	}

	public DashBoardPlatformCountResponse getVerifiedVerificationStatusCount() {

		long verifiedStatusDocCount = 0;

		DashBoardPlatformCountResponse dashBoardPlatformCountResponse = new DashBoardPlatformCountResponse();
		List<UserDocsStatus> filteredStatusDetails = userDocumentService
				.getAllPendingAndVerifiedVerificationStatusList();
		Map<String, DocumentDetails> mapCountService = new HashMap<String, DocumentDetails>();

		if (filteredStatusDetails != null) {
			for (UserDocsStatus userDocsStatus : filteredStatusDetails) {
				mapCountService = userDocsStatus.getDocumentsUploaded();
				for (Map.Entry<String, DocumentDetails> entry : mapCountService.entrySet()) {
					DocumentDetails value = entry.getValue();
					DocumentVerificationStatus dvstatus = value.getDocumentVerificationStatus();
					if (dvstatus != null && dvstatus.equals(DocumentVerificationStatus.VERIFIED)) {
						verifiedStatusDocCount = verifiedStatusDocCount + 1;
					} else {
					}

				}

			}
		}

		dashBoardPlatformCountResponse.setVerifiedStatusDocCount(verifiedStatusDocCount);
		return dashBoardPlatformCountResponse;
	}

	public DashBoardPlatformCountResponse getActiveStatusPairingCount() {

		long activePairingStatusValue = 0;
		DashBoardPlatformCountResponse dashBoardPlatformCountResponse = new DashBoardPlatformCountResponse();
		List<PairingInfoDetails> pairingList = pairingInfoService.getActivePairingStatusList();
		if (pairingList != null) {
			activePairingStatusValue = pairingList.stream().filter(pair -> PairingStatus
					.valueOf(pair.getPairingStatus().toString()).equals(PairingStatus.PAIRING_ACTIVE)).count();
		}
		dashBoardPlatformCountResponse.setActivePairingStatusValue(activePairingStatusValue);
		return dashBoardPlatformCountResponse;

	}

}
