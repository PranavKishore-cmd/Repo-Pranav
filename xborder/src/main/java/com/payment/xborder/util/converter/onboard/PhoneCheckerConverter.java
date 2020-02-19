package com.payment.xborder.util.converter.onboard;


import com.payment.xborder.model.onboard.MailChecker;
import com.payment.xborder.model.onboard.PhoneChecker;
import com.payment.xborder.model.onboard.ws.MailCheckerRequest;
import com.payment.xborder.model.onboard.ws.PhoneCheckerRequest;

import java.time.LocalDate;

public class PhoneCheckerConverter {

    public static PhoneChecker wsToModel(PhoneCheckerRequest phoneCheckerRequest) {
	    	PhoneChecker phoneChecker = new PhoneChecker();
	    	
	    	//TODO: validate phone number and country code
	    	
	    	phoneChecker.setFirstName(phoneCheckerRequest.getFirstName());
	    	phoneChecker.setLastName(phoneCheckerRequest.getLastName());
	    	phoneChecker.setPhone(phoneCheckerRequest.getPhone());
	    	phoneChecker.setCountryCode(phoneCheckerRequest.getCountryCode());
	    	phoneChecker.setLongPhoneNum(phoneCheckerRequest.getCountryCode() + phoneCheckerRequest.getPhone());
	    	phoneChecker.setStatus(phoneCheckerRequest.getStatus());
	    	phoneChecker.setRequestDate(LocalDate.now());
	    return phoneChecker;
    }

    public static PhoneCheckerRequest modelToWs(PhoneChecker phoneChecker) {
	    	PhoneCheckerRequest phoneCheckerRequest = new PhoneCheckerRequest();
	    	phoneCheckerRequest.setPhone(phoneChecker.getPhone());
	    	phoneCheckerRequest.setCountryCode(phoneChecker.getCountryCode());
	    	phoneCheckerRequest.setStatus(phoneChecker.getStatus());
        return phoneCheckerRequest;
    }
}
