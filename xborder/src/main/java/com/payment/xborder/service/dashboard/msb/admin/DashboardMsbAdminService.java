package com.payment.xborder.service.dashboard.msb.admin;

import com.payment.xborder.enums.UserRole;
import com.payment.xborder.model.dashboard.msb.admin.ws.DashboardMsbAdminResponse;
import com.payment.xborder.model.onboard.ws.UserDetails;
import com.payment.xborder.service.onboard.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardMsbAdminService {

	@Autowired
	private UserService userService;
	
	public DashboardMsbAdminResponse getMsbAdminUsersCount() {
		DashboardMsbAdminResponse dashboardMsbAdminResponse = new DashboardMsbAdminResponse();
		long msbAdminUsersCount = 0;
		List<UserDetails> allUsers = userService.getAllUsers();
		msbAdminUsersCount = allUsers.stream()
		.filter(users -> UserRole.valueOf(users.getRoleId()).equals(UserRole.MSB_ADMIN))
		.count();
		dashboardMsbAdminResponse.setMsbAdmin(msbAdminUsersCount);
		return dashboardMsbAdminResponse;
	}
	
	public DashboardMsbAdminResponse getMsbComplianceUsersCount() {
		DashboardMsbAdminResponse dashboardMsbAdminResponse = new DashboardMsbAdminResponse();
		long msbComplianceUsersCount = 0;
		List<UserDetails> allUsers = userService.getAllUsers();
		msbComplianceUsersCount = allUsers.stream()
		.filter(users -> UserRole.valueOf(users.getRoleId()).equals(UserRole.MSB_COMPLIANCE_OFFICER))
		.count();
		dashboardMsbAdminResponse.setMsbCompliance(msbComplianceUsersCount);
		return dashboardMsbAdminResponse;
	}
	
	public DashboardMsbAdminResponse getMsbFinanceUsersCount() {
		DashboardMsbAdminResponse dashboardMsbAdminResponse = new DashboardMsbAdminResponse();
		long msbFinanceOfficerUsersCount = 0;
		List<UserDetails> allUsers = userService.getAllUsers();
		msbFinanceOfficerUsersCount = allUsers.stream()
		.filter(users -> UserRole.valueOf(users.getRoleId()).equals(UserRole.MSB_FINANCE_OFFICER))
		.count();
		dashboardMsbAdminResponse.setMsbFinancier(msbFinanceOfficerUsersCount);
		return dashboardMsbAdminResponse;
	}
	
	public DashboardMsbAdminResponse getMsbApproverUsersCount() {
		DashboardMsbAdminResponse dashboardMsbAdminResponse = new DashboardMsbAdminResponse();
		long msbFinanceOpproverUsersCount = 0;
		List<UserDetails> allUsers = userService.getAllUsers();
		msbFinanceOpproverUsersCount = allUsers.stream()
		.filter(users -> UserRole.valueOf(users.getRoleId()).equals(UserRole.MSB_APPROVER))
		.count();
		dashboardMsbAdminResponse.setMsbApprover(msbFinanceOpproverUsersCount);
		return dashboardMsbAdminResponse;
	}
	
	public DashboardMsbAdminResponse getMsbUserCount() {
		DashboardMsbAdminResponse dashboardMsbAdminResponse = new DashboardMsbAdminResponse();
		long msbUsersCount = 0;
		List<UserDetails> allUsers = userService.getAllUsers();
		msbUsersCount = allUsers.stream()
		.filter(users -> UserRole.valueOf(users.getRoleId()).equals(UserRole.MSB_USER))
		.count();
		dashboardMsbAdminResponse.setMsbUser(msbUsersCount);
		return dashboardMsbAdminResponse;
	}
	
	public DashboardMsbAdminResponse getMsbRegistrarCount() {
		DashboardMsbAdminResponse dashboardMsbAdminResponse = new DashboardMsbAdminResponse();
		long msbRegistrarCount = 0;
		List<UserDetails> allUsers = userService.getAllUsers();
		msbRegistrarCount = allUsers.stream()
		.filter(users -> UserRole.valueOf(users.getRoleId()).equals(UserRole.MSB_REGISTRAR))
		.count();
		dashboardMsbAdminResponse.setMsbRegistrar(msbRegistrarCount);
		return dashboardMsbAdminResponse;
	}

	public DashboardMsbAdminResponse getTotalMsbAdminCount() {
		DashboardMsbAdminResponse dashboardMsbAdminResponse = new DashboardMsbAdminResponse();
		long msbAdminUsersCount = 0;
		long msbComplianceUsersCount = 0;
		long msbFinanceOfficerUsersCount = 0;
		long msbFinanceOpproverUsersCount = 0;
		long msbUsersCount = 0;
		long msbRegistrarCount = 0;
		
		List<UserDetails> allUsers = userService.getAllUsers();
		msbAdminUsersCount = allUsers.stream()
		.filter(users -> UserRole.valueOf(users.getRoleId()).equals(UserRole.MSB_ADMIN))
		.count();
		msbComplianceUsersCount = allUsers.stream()
		.filter(users -> UserRole.valueOf(users.getRoleId()).equals(UserRole.MSB_COMPLIANCE_OFFICER))
		.count();
		msbFinanceOfficerUsersCount = allUsers.stream()
		.filter(users -> UserRole.valueOf(users.getRoleId()).equals(UserRole.MSB_FINANCE_OFFICER))
		.count();
		msbFinanceOpproverUsersCount = allUsers.stream()
		.filter(users -> UserRole.valueOf(users.getRoleId()).equals(UserRole.MSB_APPROVER))
		.count();
		msbUsersCount = allUsers.stream()
		.filter(users -> UserRole.valueOf(users.getRoleId()).equals(UserRole.MSB_USER))
		.count();
		msbRegistrarCount = allUsers.stream()
		.filter(users -> UserRole.valueOf(users.getRoleId()).equals(UserRole.MSB_REGISTRAR))
		.count();
		dashboardMsbAdminResponse.setTotalMsbUsers(msbAdminUsersCount+msbComplianceUsersCount+msbFinanceOfficerUsersCount+msbFinanceOpproverUsersCount+msbUsersCount
												  +msbRegistrarCount);
		return dashboardMsbAdminResponse;
	}

	
	public DashboardMsbAdminResponse getAllMsbAdminDashboardDetails() {
		DashboardMsbAdminResponse dashboardMsbAdminResponse = new DashboardMsbAdminResponse();
		long msbAdminUsersCount = 0;
		long msbComplianceUsersCount = 0;
		long msbFinanceOfficerUsersCount = 0;
		long msbFinanceOpproverUsersCount = 0;
		long msbUsersCount = 0;
		long msbRegistrarCount = 0;
		
		List<UserDetails> allUsers = userService.getAllUsers();
		msbAdminUsersCount = allUsers.stream()
		.filter(users -> UserRole.valueOf(users.getRoleId()).equals(UserRole.MSB_ADMIN))
		.count();
		msbComplianceUsersCount = allUsers.stream()
		.filter(users -> UserRole.valueOf(users.getRoleId()).equals(UserRole.MSB_COMPLIANCE_OFFICER))
		.count();
		msbFinanceOfficerUsersCount = allUsers.stream()
		.filter(users -> UserRole.valueOf(users.getRoleId()).equals(UserRole.MSB_FINANCE_OFFICER))
		.count();
		msbFinanceOpproverUsersCount = allUsers.stream()
		.filter(users -> UserRole.valueOf(users.getRoleId()).equals(UserRole.MSB_APPROVER))
		.count();
		msbUsersCount = allUsers.stream()
		.filter(users -> UserRole.valueOf(users.getRoleId()).equals(UserRole.MSB_USER))
		.count();
		msbRegistrarCount = allUsers.stream()
		.filter(users -> UserRole.valueOf(users.getRoleId()).equals(UserRole.MSB_REGISTRAR))
		.count();
		dashboardMsbAdminResponse.setMsbRegistrar(msbRegistrarCount);
		dashboardMsbAdminResponse.setMsbUser(msbUsersCount);
		dashboardMsbAdminResponse.setMsbApprover(msbFinanceOpproverUsersCount);
		dashboardMsbAdminResponse.setMsbFinancier(msbFinanceOfficerUsersCount);
		dashboardMsbAdminResponse.setMsbCompliance(msbComplianceUsersCount);
		dashboardMsbAdminResponse.setMsbAdmin(msbAdminUsersCount);
		return dashboardMsbAdminResponse;
		
		
	}

}
