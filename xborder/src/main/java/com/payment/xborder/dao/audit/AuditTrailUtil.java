package com.payment.xborder.dao.audit;


import com.payment.xborder.model.audit.AuditTrail;
import com.payment.xborder.model.onboard.User;

public class AuditTrailUtil {

	AuditTrail auditTrail = new AuditTrail();
	
	public AuditTrail setAuditTrailDetails(User user, String screenName, boolean isCreate) {
		
		auditTrail.setAuditScreen(screenName);
		auditTrail.setEmailId(user.getEmail());
		/*
		 * if(isCreate) {
		 * auditTrail.setCreatedBy(user.getFirstName()+" "+user.getLastName());
		 * auditTrail.setCreatedDate(LocalDateTime.now()); }else {
		 * auditTrail.setModifiedBy(user.getFirstName()+" "+user.getLastName());
		 * auditTrail.setModifiedDate(LocalDateTime.now()); }
		 */
		return auditTrail;
	}
}
