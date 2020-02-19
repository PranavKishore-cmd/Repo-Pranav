package com.payment.xborder.service.dashboard.platform.admin;

import com.payment.xborder.enums.UserRole;
import com.payment.xborder.model.dashboard.platform.admin.ws.DashboardPlatformAdminReponse;
import com.payment.xborder.model.onboard.ws.UserDetails;
import com.payment.xborder.service.onboard.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardPlatformAdminService {

	 @Autowired
	 private UserService userService;


	public DashboardPlatformAdminReponse getEnterpriseAdminUsersCount() {
		DashboardPlatformAdminReponse dashboardPlatformAdminReponse = new DashboardPlatformAdminReponse();
		long platformAdminUsersCount = 0;
		List<UserDetails> allUsers = userService.getAllUsers();
		platformAdminUsersCount = allUsers.stream()
		.filter(users -> UserRole.valueOf(users.getRoleId()).equals(UserRole.ENTERPRISE_ADMIN))
		.count();
		dashboardPlatformAdminReponse.setPlatformAdminUsers(platformAdminUsersCount);
		return dashboardPlatformAdminReponse;
	}
	

	public DashboardPlatformAdminReponse getEnterpriseComplianceUsersCount() {
		DashboardPlatformAdminReponse dashboardPlatformAdminReponse = new DashboardPlatformAdminReponse();
		long platformComplianceUsersCount = 0;
		List<UserDetails> allUsers = userService.getAllUsers();
		platformComplianceUsersCount = allUsers.stream()
		.filter(users -> UserRole.valueOf(users.getRoleId()).equals(UserRole.ENTERPRISE_COMPLIANCE_OFFICER))
		.count();
		dashboardPlatformAdminReponse.setPlatformComplainceUsers(platformComplianceUsersCount);
		return dashboardPlatformAdminReponse;
	}
	
	public DashboardPlatformAdminReponse getEnterpriseSupportOfficersUsersCount() {
		DashboardPlatformAdminReponse dashboardPlatformAdminReponse = new DashboardPlatformAdminReponse();
		long platformSupportOfficersUsersCount = 0;
		List<UserDetails> allUsers = userService.getAllUsers();
		platformSupportOfficersUsersCount = allUsers.stream()
		.filter(users -> UserRole.valueOf(users.getRoleId()).equals(UserRole.ENTERPRISE_SUPPORT_OFFICER))
		.count();
		dashboardPlatformAdminReponse.setPlatformSupportUsers(platformSupportOfficersUsersCount);
		return dashboardPlatformAdminReponse;
	}
	
	public DashboardPlatformAdminReponse getTotalEnterpriseUsersCount() {
		DashboardPlatformAdminReponse dashboardPlatformAdminReponse = new DashboardPlatformAdminReponse();
		long platformSupportOfficersUsersCount = 0;
		long platformAdminUsersCount = 0;
		long platformComplianceUsersCount = 0;
		
		List<UserDetails> allUsers = userService.getAllUsers();
		platformAdminUsersCount = allUsers.stream()
				.filter(users -> UserRole.valueOf(users.getRoleId()).equals(UserRole.ENTERPRISE_ADMIN))
				.count();
		
		platformComplianceUsersCount = allUsers.stream()
				.filter(users -> UserRole.valueOf(users.getRoleId()).equals(UserRole.ENTERPRISE_COMPLIANCE_OFFICER))
				.count();
		platformSupportOfficersUsersCount = allUsers.stream()
		.filter(users -> UserRole.valueOf(users.getRoleId()).equals(UserRole.ENTERPRISE_SUPPORT_OFFICER))
		.count();
		dashboardPlatformAdminReponse.setTotalPlaformUsers(platformSupportOfficersUsersCount+platformComplianceUsersCount+platformAdminUsersCount);
		return dashboardPlatformAdminReponse;
	}


	public DashboardPlatformAdminReponse allEnterpriseAdminDashboardDetails() {
		DashboardPlatformAdminReponse dashboardPlatformAdminReponse = new DashboardPlatformAdminReponse();
		long platformSupportOfficersUsersCount = 0;
		long platformAdminUsersCount = 0;
		long platformComplianceUsersCount = 0;
		
		List<UserDetails> allUsers = userService.getAllUsers();
		platformAdminUsersCount = allUsers.stream()
				.filter(users -> UserRole.valueOf(users.getRoleId()).equals(UserRole.ENTERPRISE_ADMIN))
				.count();
		
		platformComplianceUsersCount = allUsers.stream()
				.filter(users -> UserRole.valueOf(users.getRoleId()).equals(UserRole.ENTERPRISE_COMPLIANCE_OFFICER))
				.count();
		platformSupportOfficersUsersCount = allUsers.stream()
		.filter(users -> UserRole.valueOf(users.getRoleId()).equals(UserRole.ENTERPRISE_SUPPORT_OFFICER))
		.count();
		dashboardPlatformAdminReponse.setPlatformAdminUsers(platformAdminUsersCount);
		dashboardPlatformAdminReponse.setPlatformComplainceUsers(platformComplianceUsersCount);
		dashboardPlatformAdminReponse.setPlatformSupportUsers(platformSupportOfficersUsersCount);
		dashboardPlatformAdminReponse.setTotalPlaformUsers(platformSupportOfficersUsersCount+platformComplianceUsersCount+platformAdminUsersCount);
		return dashboardPlatformAdminReponse;
	}
}
