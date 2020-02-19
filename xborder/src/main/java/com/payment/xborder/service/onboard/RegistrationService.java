package com.payment.xborder.service.onboard;

import com.payment.xborder.configuration.ApplicationProperties;
import com.payment.xborder.dao.onboard.RegistrationFormDao;
import com.payment.xborder.dao.onboard.UserDao;
import com.payment.xborder.dao.roles.RolesDao;
import com.payment.xborder.enums.CompanyStatus;
import com.payment.xborder.enums.RegisterUserType;
import com.payment.xborder.enums.UserRole;
import com.payment.xborder.enums.UserStatus;
import com.payment.xborder.exception.BusinessException;
import com.payment.xborder.model.onboard.*;
import com.payment.xborder.model.onboard.ws.ChangeCompanyStatusRequest;
import com.payment.xborder.model.onboard.ws.CompanyDetails;
import com.payment.xborder.model.onboard.ws.UpdateBeneficiaryOwnerKycRequest;
import com.payment.xborder.model.onboard.ws.UserDetails;
import com.payment.xborder.service.notification.EmailService;
import com.payment.xborder.service.notification.MailContentBuilderService;
import com.payment.xborder.service.notification.NotificationEnumeration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

	@Autowired
	UserDao userDao;

	@Autowired
	RolesDao rolesDao;

	@Autowired
	RegistrationFormDao registrationFormDao;

	@Autowired
	AuditTrailService auditTrailService;

	@Autowired
	OnboardingService onboardingService;

	@Autowired
	EmailService emailService;

	@Autowired
	MailContentBuilderService mailContentBuilderService;

	@Autowired
	ApplicationProperties applicationProperties;

	private static final Logger log = LoggerFactory.getLogger(RegistrationFormDao.class);

	public void registerFormData(RegistrationForm registrationForm) {
		User userDtls = findUserByEmail(registrationForm.getEmailId());
		registrationForm.setUserId(userDtls.getUserId());
		boolean isSaveAsDraft = registrationForm.isSaveAsDraft();

		// Get existing Registration if any
		RegistrationForm registrationDetailsFromDB = null;
		if (userDtls.getCompanyRefId() != null) {
			registrationDetailsFromDB = registrationFormDao.getRegistrationFormDetails(userDtls.getCompanyRefId());
			registrationForm.setCompanyId(registrationDetailsFromDB.getCompanyId());
		}

		if (isSaveAsDraft) {
			registrationForm.setRegistrationStatus(CompanyStatus.REG_INCOMPLETE);
		} else {
			registrationForm.setRegistrationStatus(CompanyStatus.DOC_UPLOAD_PENDING);
		}
		registrationFormDao.saveRegistrationForm(registrationForm);
		if (!registrationForm.isAdmin()) {
			validateAdminDetails(registrationForm);
		}

		// If user is admin. Save user ID, Update
		if (registrationForm.isAdmin()) {
			registrationForm.setUserId(userDtls.getUserId());
			if (!isSaveAsDraft) {
				userDtls.setRoleId(rolesDao.getRole(UserRole.MSB_ADMIN.toString()).getRoleId());
			}
			registrationFormDao.saveRegistrationForm(registrationForm);
		} else if (!isSaveAsDraft) {
			userDtls.setRoleId(rolesDao.getRole(UserRole.MSB_REGISTRAR.toString()).getRoleId());

			User updateAdminUserDetails = new User();
			MailChecker mailChecker = new MailChecker();
			updateAdminUserDetails.setFirstName(registrationForm.getAdminFirstName());
			updateAdminUserDetails.setMiddleName(registrationForm.getAdminMiddleName());
			updateAdminUserDetails.setLastName(registrationForm.getAdminLastName());
			updateAdminUserDetails.setEmail(registrationForm.getAdminMailId());
			updateAdminUserDetails.setRoleId(rolesDao.getRole(UserRole.MSB_ADMIN.toString()).getRoleId());
			updateAdminUserDetails.setRegisterUserType(RegisterUserType.MSB);
			updateAdminUserDetails.setPassword("Millennium@123");
			updateAdminUserDetails.setUserStatus(UserStatus.NEW);
			updateAdminUserDetails.setCompanyRefId(registrationForm.getCompanyId());
			User adminUser = userDao.getUserDetails(registrationForm.getAdminMailId());
			if (adminUser == null) {
				userDao.createUser(updateAdminUserDetails);
			} else {
				userDao.updateUser(updateAdminUserDetails);
			}

			updateAdminUserDetails.setPasswordResetToken(UUID.randomUUID().toString());
			updateAdminUserDetails.setTokenExpiryTime(LocalDateTime.now().plusHours(240));
			userDao.updateUser(updateAdminUserDetails);

			Context context = new Context();
			context.setVariable("firstName", registrationForm.getAdminFirstName());
			context.setVariable("lastName", registrationForm.getAdminLastName());
			context.setVariable("userId", registrationForm.getAdminMailId());
			context.setVariable("passwordResetToken", updateAdminUserDetails.getPasswordResetToken());
			context.setVariable("url", applicationProperties.getFrontendUrl());
			emailService.sendEmailAsync(registrationForm.getAdminMailId(), NotificationEnumeration.ADD_USER, context);

			registrationForm.setUserId(adminUser.getUserId());
			registrationFormDao.saveRegistrationForm(registrationForm);

		}

		// If user is admin. Save user ID, Update
		if (registrationForm.isAuthorizer()) {
			if (!isSaveAsDraft && !registrationForm.isAdmin()) {
				userDtls.setRoleId(rolesDao.getRole(UserRole.MSB_AUTHORIZER.toString()).getRoleId());
			}
		} else if (!isSaveAsDraft) {
			User createAuthorizer = new User();
			createAuthorizer.setFirstName(registrationForm.getAuthorizerFirstName());
			createAuthorizer.setMiddleName(registrationForm.getAuthorizerMiddleName());
			createAuthorizer.setLastName(registrationForm.getAuthorizerLastName());
			createAuthorizer.setEmail(registrationForm.getAuthorizerMailId());
			createAuthorizer.setRoleId(UserRole.MSB_AUTHORIZER.name());
			createAuthorizer.setUserStatus(UserStatus.NEW);
			createAuthorizer.setPassword("Millennium@123");
			createAuthorizer.setRegisterUserType(RegisterUserType.MSB);
			createAuthorizer.setCompanyRefId(registrationForm.getCompanyId());
			createAuthorizer.setPasswordResetToken(UUID.randomUUID().toString());
			createAuthorizer.setTokenExpiryTime(LocalDateTime.now().plusHours(240));

			User authUser = userDao.getUserDetails(registrationForm.getAuthorizerMailId());
			if (authUser == null) {
				userDao.createUser(createAuthorizer);
			} else {
				userDao.updateUser(createAuthorizer);
			}

			Context context = new Context();
			context.setVariable("firstName", registrationForm.getAuthorizerFirstName());
			context.setVariable("lastName", registrationForm.getAuthorizerLastName());
			context.setVariable("userId", registrationForm.getAuthorizerMailId());
			context.setVariable("passwordResetToken", createAuthorizer.getPasswordResetToken());
			context.setVariable("url", applicationProperties.getFrontendUrl());
			emailService.sendEmailAsync(registrationForm.getAuthorizerMailId(), NotificationEnumeration.ADD_USER,
					context);
		}

		// Update Registrar details
		userDtls.setCompanyRefId(registrationForm.getCompanyId());
		log.debug("Update user status of user" + registrationForm.getEmailId() + "to" + " " + UserStatus.ACTIVE);
		if (!isSaveAsDraft) {
			userDtls.setUserStatus(UserStatus.ACTIVE);
		}
		userDao.updateUser(userDtls);
		auditTrailService.saveAuditDetails(registrationForm);
	}

	public RegistrationForm getRegistrationFormData(String emailId) {
		return registrationFormDao.getRegistrationFormData(findUserByEmail(emailId).getUserId());
	}

	private User findUserByEmail(String emailId) {
		User userDtls = userDao.getUserDetails(emailId);
		if (userDtls == null) {
			throw new BusinessException("User not found");
		}
		return userDtls;
	}

	private boolean isAdminMailIdExists(String emailId) {
		User userDtls = userDao.getAdminUserDetails(emailId);
		if (userDtls == null) {
			return false;
		}
		return true;
	}

	public RegistrationForm findByCompanyRefId(String companyRefId) {
		RegistrationForm company = registrationFormDao.findByCompanyRefId(companyRefId);
		if (company == null) {
			throw new BusinessException("Company details not found");
		}
		return company;
	}

	public CompanyStatus getCompanyStatus(String companyId) {
		log.info("registrationService : getCompanyStatus : Start");
		RegistrationForm registrationForm = registrationFormDao.getCompanyStatus(companyId);
		if (registrationForm == null) {
			throw new BusinessException("Company Details Not Found");
		}
		log.info("registrationService : getCompanyStatus : End");
		return registrationForm.getRegistrationStatus();
	}

	public void changeCompanyStatus(ChangeCompanyStatusRequest changeCompanyStatusRequest) {
		log.info("registrationService : changeCompanyStatus : Start");

		// TODO: validate State change Sanity
		registrationFormDao.changeCompanyStatus(changeCompanyStatusRequest.getCompanyRefID(),
				changeCompanyStatusRequest.getNewStatus());
		log.info("registrationService : changeCompanyStatus : End");
	}

	private String getStatus(CompanyStatus registrationStatus) {
		String updateStatus = "";
		switch (registrationStatus) {
		case NEW_REGISTRATION:
			updateStatus = CompanyStatus.REG_INCOMPLETE.name();
			break;
		case REG_INCOMPLETE:
			updateStatus = CompanyStatus.APPROVAL_PENDING.name();
			break;
		case APPROVAL_PENDING:
			updateStatus = CompanyStatus.APPROVAL_PENDING.name();
			break;
		case DOC_VERIFICATION:
			updateStatus = CompanyStatus.DOC_VERIFICATION.name();
			break;
		case KYC_VERIFICATION:
			updateStatus = CompanyStatus.KYC_VERIFICATION.name();
			break;
		case COMPLIANCE_VERIFICATION:
			updateStatus = CompanyStatus.APPROVAL_PENDING.name();
			break;
		case PENDING_ACTIVATION:
			updateStatus = CompanyStatus.PENDING_ACTIVATION.name();
			break;
		case ACTIVE:
			updateStatus = CompanyStatus.ACTIVE.name();
			break;
		case INACTIVE:
			updateStatus = CompanyStatus.INACTIVE.name();
			break;
		case DOC_VERIFICATION_FAILED:
			updateStatus = CompanyStatus.DOC_VERIFICATION_FAILED.name();
			break;
		case KYC_VERIFICATION_FAILED:
			updateStatus = CompanyStatus.KYC_VERIFICATION_FAILED.name();
			break;
		case DOC_UPLOAD_PENDING:
			updateStatus = CompanyStatus.DOC_UPLOAD_PENDING.name();
			break;
		}
		return updateStatus;
	}

	private void validateAdminDetails(RegistrationForm registrationForm) {
		String adminFirstName = registrationForm.getAdminFirstName();
		String adminLastName = registrationForm.getAdminLastName();
		boolean isSaveAsDraft = registrationForm.isSaveAsDraft();

		if (adminFirstName == null) {
			throw new BusinessException("Admin First Name is missing");
		}
		if (adminLastName == null) {
			throw new BusinessException("Admin Last Name is missing");
		}
		if (registrationForm.getAdminMailId() == null) {
			throw new BusinessException("Admin email ID is missing");
		}
		if (!isSaveAsDraft && !CompanyStatus.APPROVAL_PENDING.name().equals(registrationForm.getRegistrationStatus())
				&& isAdminMailIdExists(registrationForm.getAdminMailId())) {
			throw new BusinessException("Admin email " + registrationForm.getAdminMailId() + ", is already registered");
		}
	}

	public List<UserCompanyDetails> getAllCompanyDetails() {
		log.info("RegistrationFormService : getAllCompanyDetails : Start");
		List<UserCompanyDetails> userCompanyDetailsList = new ArrayList<>();
		List<User> usersList = userDao.findAllActiveUsers();
		usersList.stream().forEach(userDetails -> {
			UserCompanyDetails userCompanyDetails = new UserCompanyDetails();
			RegistrationForm companyDetailsForm = registrationFormDao.findByCompanyRefId(userDetails.getCompanyRefId());
			userCompanyDetails.setCompanyDetails(companyDetailsForm);
			userCompanyDetails.setUser(userDetails);
			userCompanyDetailsList.add(userCompanyDetails);
		});

		log.info("RegistrationFormService : getAllCompanyDetails : End");
		return userCompanyDetailsList;
	}

	public List<RegistrationForm> getActiveCompanyDetails(String emailId) {
		log.info("RegistrationFormService : getActiveCompanyDetails : Start");
		List<RegistrationForm> companyList = registrationFormDao.getAllActiveCompanyDetails();
		User user = userDao.getUserDetails(emailId);
		companyList.stream().filter(company -> company.getCompanyId().equals(user.getCompanyRefId())).findFirst()
				.map(company -> companyList.remove(company));
		log.info("RegistrationFormService : getActiveCompanyDetails : End");
		return companyList;
	}

	public List<RegistrationForm> getActiveCompanyDetails() {
		log.info("RegistrationFormService : getActiveCompanyDetails : Start");
		List<RegistrationForm> companyList = registrationFormDao.getAllCompanyDetailsForComplianceOfficer();
		log.info("RegistrationFormService : getActiveCompanyDetails : End");
		return companyList;
	}

	public Map<String, String> getGetCompanyIdAndNameDtls() {
		log.info("RegistrationFormService : getGetCompanyIdAndNameDtls : Start");
		// get all active companies
		List<RegistrationForm> activeComps = registrationFormDao.getAllCompanyDetails();

		// Already paired companies
		if (activeComps != null) {
			Map<String, String> companyNameIDMap = activeComps.stream()
					.collect(Collectors.toMap(item -> item.getCompanyId(), item -> item.getBusinessName()));
			log.info("RegistrationFormService : getGetCompanyIdAndNameDtls : End");
			return companyNameIDMap;
		} else {
			log.info("RegistrationFormService : getGetCompanyIdAndNameDtls : End");
			return Collections.emptyMap();
		}

	}

	public UpdateBeneficiaryOwnerKycRequest updateBeneficiaryOwnerKycStatus(
			BeneficiaryInformationDetails beneficiaryDetails, String companyRefId) {
		RegistrationForm company = findByCompanyRefId(companyRefId);
		List<BeneficiaryInformationDetails> beneficiaryList = company.getBeneficiaryInformationDetailsList();
		beneficiaryList.stream().forEach(beneficiary -> {
			if (beneficiary.getBeneficiaryOwnerName().equals(beneficiaryDetails.getBeneficiaryOwnerName())) {
				beneficiary.setKycVerified(beneficiaryDetails.isKycVerified());
			}
		});
		RegistrationForm updatedCompany = registrationFormDao.updateCompany(company);
		UpdateBeneficiaryOwnerKycRequest beneficiary = new UpdateBeneficiaryOwnerKycRequest();
		beneficiary.setBeneficiaryDetailsList(updatedCompany.getBeneficiaryInformationDetailsList());
		beneficiary.setCompanyRefID(updatedCompany.getCompanyId());
		return beneficiary;
	}

	public void updateControlOwnerKycStatus(String companyRefId) {
		RegistrationForm company = findByCompanyRefId(companyRefId);
		company.setControlOwnerKycStatus(Boolean.TRUE);
		registrationFormDao.updateCompany(company);
	}

	public PlatformCODashboardCount getPlatformCODashboardCount(String emailId) {
		PlatformCODashboardCount platformCODashboard = new PlatformCODashboardCount();
		int activeCompaniesCount = 0;
		int approvalPendingCount = 0;
		int complianceVerificationCount = 0;
		int docUploadPendingCount = 0;
		int documentVerificationCount = 0;
		int kycVerificationCount = 0;
		User user = userDao.getUserDetails(emailId);
		List<RegistrationForm> companies = registrationFormDao.getAllCompanyDetailsForComplianceOfficer();
		for (RegistrationForm company : companies) {
			if (!company.getCompanyId().equals(user.getCompanyRefId())) {
				switch (company.getRegistrationStatus()) {
				case ACTIVE:
					platformCODashboard.setActiveCompanies(++activeCompaniesCount);
					break;
				case APPROVAL_PENDING:
					platformCODashboard.setApprovalPending(++approvalPendingCount);
					break;
				case COMPLIANCE_VERIFICATION:
					platformCODashboard.setComplianceVerification(++complianceVerificationCount);
					break;
				case DOC_UPLOAD_PENDING:
					platformCODashboard.setDocUploadPending(++docUploadPendingCount);
					break;
				case DOC_VERIFICATION:
					platformCODashboard.setDocumentVerification(++documentVerificationCount);
					break;
				case DOC_VERIFICATION_FAILED:
					break;
				case INACTIVE:
					break;
				case KYC_VERIFICATION:
					platformCODashboard.setKycVerification(++kycVerificationCount);
					break;
				case KYC_VERIFICATION_FAILED:
					break;
				case NEW_REGISTRATION:
					break;
				case PENDING_ACTIVATION:
					break;
				case REG_INCOMPLETE:
					break;
				default:
					break;
				}
			}
		}
		return platformCODashboard;
	}

	// Method added by Pranav
	public List<CompanyDetails> getAllActiveCompanyStatus() {
		List<RegistrationForm> companyList = registrationFormDao.getAllActiveCompanyDetails();
		if (!companyList.isEmpty()) {
			List<CompanyDetails> companyDetailsList = companyList.stream().map(p -> new CompanyDetails(p))
					.collect(Collectors.toList());
			return companyDetailsList;
		}
		return Collections.EMPTY_LIST;
	}

	// Method added by Pranav
	public List<CompanyDetails> getAllKycApprovedDetails() {

		List<RegistrationForm> kycApprovedCompanyList = registrationFormDao.getAllCompanyDetails();
		if (!kycApprovedCompanyList.isEmpty()) {

			List<CompanyDetails> filteredList = kycApprovedCompanyList.stream().map(p -> new CompanyDetails(p))
					.collect(Collectors.toList());
			return filteredList;
		}
		return Collections.EMPTY_LIST;
	}
}
