package com.payment.xborder.service.onboard;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.xborder.dao.audit.AuditTrailDao;
import com.payment.xborder.model.audit.AuditTrail;
import com.payment.xborder.model.onboard.RegistrationForm;

@Service
public class AuditTrailService {

	@Autowired
	AuditTrailDao auditTrailDao;
	
	AuditTrail auditTrail = new AuditTrail();
	
	public <T> void saveAuditDetails(T object) {
		
		if(object instanceof RegistrationForm) {
			RegistrationForm form = (RegistrationForm)object;
			auditTrail.setAuditScreen("MSB Registration");
			auditTrail.setCreatedBy("Vini");
			auditTrail.setCreatedDate(new Date());
			auditTrail.setEmailId(form.getEmailId());
			auditTrail.setModifiedBy(form.getUserId());
			auditTrail.setModifiedDate(new Date());
			auditTrailDao.saveAuditTrail(auditTrail);
		}
	}
}
