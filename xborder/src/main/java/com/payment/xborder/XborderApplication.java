package com.payment.xborder;

import com.payment.xborder.configuration.FileStorageProperties;
import com.payment.xborder.dao.onboard.RegistrationFormDao;
import com.payment.xborder.dao.onboard.UserDao;
import com.payment.xborder.dao.roles.RolesDao;
import com.payment.xborder.enums.ParentRoleType;
import com.payment.xborder.enums.RegisterUserType;
import com.payment.xborder.enums.UserRole;
import com.payment.xborder.enums.UserStatus;
import com.payment.xborder.model.onboard.Address;
import com.payment.xborder.model.onboard.RegistrationForm;
import com.payment.xborder.model.onboard.User;
import com.payment.xborder.model.roles.Role;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableConfigurationProperties({ FileStorageProperties.class })
public class XborderApplication {
	private static final Logger log = LoggerFactory.getLogger(XborderApplication.class);

	@Autowired
	UserDao userDao = new UserDao();
	@Autowired
	RolesDao rolesDao = new RolesDao();
	@Autowired
	RegistrationFormDao registrationDao = new RegistrationFormDao();

	public static void main(String[] args) {
		SpringApplication.run(XborderApplication.class, args);
	}

	@Bean
	InitializingBean sendDatabase() {
		return () -> {
			log.info("****Started sendDatabase******");
         createDefaultRoles();
         createPlatformData();
         log.info("****Exit sendDatabase******");
		};
	}

   private void createPlatformData()
   {
      String defaultUserID = userDao.findUserIdByEmail("admin@xborder.com");
      if (defaultUserID == null) {
         // Create Platform Admin
         setUpUserDetailsOnStartup();
         // Create Platform Company
         setUpPlatformCompany();
      }

   }

   private void setUpPlatformCompany()
   {
      RegistrationForm registrationForm = new RegistrationForm();
      Address address = new Address();
      address.setAddress("USA");
      registrationForm.setBillingAddress(address);
      registrationForm.setUserId("PLATFORM_ADMIN");
      registrationForm.setCompanyId("MILLENIUM_PLATFORM");
      registrationForm.setBusinessLegalName("MILLENIUM_PLATFORM");
      registrationForm.setBusinessName("MILLENIUM_PLATFORM");
      registrationForm.setContactName("admin@xborder.com");
      String defaultUserID = userDao.findUserIdByEmail("admin@xborder.com");
      if (defaultUserID != null) {
         registrationForm.setUserId(defaultUserID);
      }
      registrationDao.saveRegistrationForm(registrationForm);
      log.info("Created PLATFORM COMPANY");
   }

   private User setUpUserDetailsOnStartup() {
      User user = new User();
      final GoogleAuthenticator gAuth = new GoogleAuthenticator();
      final GoogleAuthenticatorKey googleAuthkey = gAuth.createCredentials();
      LocalDate today = LocalDate.now();
      user.setUserId("PLATFORM_ADMIN");
      user.setEmail("admin@xborder.com");
      user.setFirstName("PLATFORM_ADMIN");
      user.setLastName("PLATFORM_ADMIN");
      user.setMiddleName("PLATFORM_ADMIN");
      user.setPassword("Test@123");
      user.setRegisterUserType(RegisterUserType.ENTERPRISE);
      user.setRequestDate(today);
      user.setRoleId(rolesDao.getRole(UserRole.ENTERPRISE_ADMIN.toString()).getRoleId());
      user.setTelephoneNumber("9844884568");
      user.setUserStatus(UserStatus.ACTIVE);
      user.setAuthKey(googleAuthkey.getKey());
      user.setGAuthState(false);
      user.setCompanyRefId("MILLENIUM_PLATFORM");
      userDao.createUser(user);
      log.info("Created PLATFORM ADMIN");
      return user;
   }

	private void createDefaultRoles(){
      // Insert Roles
      //Enterprise default roles
      List<Role> defaultRoles = new ArrayList<>();
      defaultRoles.add(new Role(UserRole.ENTERPRISE_ADMIN.toString(), UserRole.ENTERPRISE_ADMIN.toString(),
                                "Enterprise Admin Role", ParentRoleType.ENTERPRISE, ""));


      defaultRoles.add(new Role(UserRole.ENTERPRISE_COMPLIANCE_OFFICER.toString(), UserRole.ENTERPRISE_COMPLIANCE_OFFICER.toString(),
                                "Enterprise Compliance Officer Role", ParentRoleType.ENTERPRISE, ""));
      defaultRoles.add(new Role(UserRole.ENTERPRISE_SUPPORT_OFFICER.toString(), UserRole.ENTERPRISE_SUPPORT_OFFICER.toString(),
                                "Enterprise Support Officer Role", ParentRoleType.ENTERPRISE, ""));

      //MSB default roles
      defaultRoles.add(new Role(UserRole.MSB_ADMIN.toString(), UserRole.MSB_ADMIN.toString(),
                                "MSB Admin Role", ParentRoleType.MSB, ""));
      defaultRoles.add(new Role(UserRole.MSB_AUTHORIZER.toString(), UserRole.MSB_AUTHORIZER.toString(),
                                "MSB Authorizer Role", ParentRoleType.MSB, ""));
      defaultRoles.add(new Role(UserRole.MSB_COMPLIANCE_OFFICER.toString(), UserRole.MSB_COMPLIANCE_OFFICER.toString(),
                                "MSB Compliance Officer Role", ParentRoleType.MSB, ""));
      defaultRoles.add(new Role(UserRole.MSB_FINANCE_OFFICER.toString(), UserRole.MSB_FINANCE_OFFICER.toString(),
                                "MSB Finance Officer Role", ParentRoleType.MSB, ""));
      defaultRoles.add(new Role(UserRole.MSB_APPROVER.toString(), UserRole.MSB_APPROVER.toString(),
                                "MSB Approver Officer Role", ParentRoleType.MSB, ""));
      defaultRoles.add(new Role(UserRole.MSB_USER.toString(), UserRole.MSB_USER.toString(), "Business User Role",
                                ParentRoleType.MSB, ""));
      defaultRoles.add(new Role(UserRole.MSB_REGISTRAR.toString(), UserRole.MSB_REGISTRAR.toString(),
                                "MSB Registrar Role", ParentRoleType.MSB, ""));


      List<Role> existingRoles = rolesDao.getRoles();
      List<String> existingRoleNames = Collections.EMPTY_LIST;
      if (existingRoles != null) {
         existingRoleNames = rolesDao.getRoles().stream().map(role -> role.getRoleName())
                                     .collect(Collectors.toList());
      }

      for (Role role : defaultRoles) {
         if (!existingRoleNames.contains(role.getRoleName())) {
            log.debug("Creating deafult role: " + role.getRoleName());
            rolesDao.createRole(role);
         }
      }
   }
}
