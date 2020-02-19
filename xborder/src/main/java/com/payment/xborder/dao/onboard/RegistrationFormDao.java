package com.payment.xborder.dao.onboard;

import com.payment.xborder.enums.CompanyStatus;
import com.payment.xborder.exception.BusinessException;
import com.payment.xborder.model.onboard.RegistrationForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegistrationFormDao {

	private static final Logger log = LoggerFactory.getLogger(RegistrationFormDao.class);

	public RegistrationFormDao() {

	}

	@Autowired
	private MongoTemplate mongoTemplate;

	public RegistrationForm saveRegistrationForm(RegistrationForm registrationForm) {
		log.info("RegistrationFormDao : saveRegistrationForm : Start");
		try {
			registrationForm = mongoTemplate.save(registrationForm);
		} catch (Exception e) {
			System.out.println(e);
			log.error(e.getMessage());
			throw new BusinessException("Error while saving the registration form");
		}
		log.info("RegistrationFormDao : saveRegistrationForm : End");
		return registrationForm;
	}

	public RegistrationForm getRegistrationFormData(String userId) {
		log.info("RegistrationFormDao : getRegistrationFormData : Start");
		Query query = new Query(Criteria.where("userId").is(userId));
		System.out.println(userId);
		RegistrationForm registrationForm = mongoTemplate.findOne(query, RegistrationForm.class);
		if (registrationForm == null) {
			System.out.println("No registration form found");
			return null;
		}
		log.info("RegistrationFormDao : getRegistrationFormData : End");
		return registrationForm;
	}

	public RegistrationForm getRegistrationFormDetails(String companyID) {
		log.info("RegistrationFormDao : getRegistrationFormDetails : Start");
		Query query = new Query(Criteria.where("_id").is(companyID));
		RegistrationForm registrationForm = mongoTemplate.findOne(query, RegistrationForm.class);
		if (registrationForm == null) {
			log.debug("No registration form found");
			return null;
		}
		log.info("RegistrationFormDao : getRegistrationFormDetails : End");
		return registrationForm;
	}

	public RegistrationForm getCompanyStatus(String companyRefId) {
		log.info("RegistrationFormDao : getCompanyStatus : Start");
		Query query = new Query(Criteria.where("companyId").is(companyRefId));
		RegistrationForm registrationForm = mongoTemplate.findOne(query, RegistrationForm.class);
		log.info("RegistrationFormDao : getCompanyStatus : End");
		return registrationForm;
	}

	public void changeCompanyStatus(String companyRefId, CompanyStatus registrationStatus) {
		log.info("RegistrationFormDao : changeCompanyStatus : Start");
		Query query = new Query(Criteria.where("companyId").is(companyRefId));
		Update update = new Update();
		update.set("registrationStatus", registrationStatus);
		mongoTemplate.updateFirst(query, update, RegistrationForm.class);
		log.info("RegistrationFormDao : changeCompanyStatus : End");
	}

	public RegistrationForm findByCompanyRefId(String companyRefId) {
		log.info("RegistrationFormDao : findByCompanyRefId : Start");
		Query query = new Query(Criteria.where("_id").is(companyRefId));
		RegistrationForm company = mongoTemplate.findOne(query, RegistrationForm.class);
		if (company == null) {
			System.out.println("No reference with Company Ref Id");
		}
		log.info("RegistrationFormDao : findByCompanyRefId : End");
		return company;
	}

	public List<RegistrationForm> getAllActiveCompanyDetails() {
		log.info("RegistrationFormDao : getAllActiveCompanyDetails : Start");
		Query query = new Query(
				Criteria.where("isSaveAsDraft").is(false).and("registrationStatus").is(CompanyStatus.ACTIVE));
		List<RegistrationForm> company = mongoTemplate.find(query, RegistrationForm.class);
		log.info("RegistrationFormDao : getAllActiveCompanyDetails : End");
		return company;
	}

	public List<RegistrationForm> getAllCompanyDetails() {
		log.info("RegistrationFormDao : getAllCompanyDetails : Start");
		List<RegistrationForm> companies = mongoTemplate.findAll(RegistrationForm.class);
		log.info("RegistrationFormDao : getAllCompanyDetails : End");
		return companies;
	}

	public List<RegistrationForm> findCompaniesByStatus(CompanyStatus status) {
		log.info("RegistrationFormDao : findCompaniesByStatus : Start");
		Query query = new Query(Criteria.where("isSaveAsDraft").is(false).and("registrationStatus").is(status));
		List<RegistrationForm> company = mongoTemplate.find(query, RegistrationForm.class);
		log.info("RegistrationFormDao : findCompaniesByStatus : End");
		return company;
	}

	public List<RegistrationForm> getAllCompanyDetailsForComplianceOfficer() {
		log.info("RegistrationFormDao : getAllCompanyDetails : Start");
		Query query = new Query(Criteria.where("isSaveAsDraft").is(false));
		List<RegistrationForm> company = mongoTemplate.find(query, RegistrationForm.class);
		log.info("RegistrationFormDao : getAllCompanyDetails : End");
		return company;
	}

	public RegistrationForm updateCompany(RegistrationForm company) {
		return mongoTemplate.save(company);
	}

}
