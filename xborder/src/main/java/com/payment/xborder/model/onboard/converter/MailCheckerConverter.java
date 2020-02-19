package com.payment.xborder.model.onboard.converter;


import com.payment.xborder.model.onboard.MailChecker;
import com.payment.xborder.model.onboard.ws.MailCheckerRequest;

public class MailCheckerConverter {

    public static MailChecker wsToModel(MailCheckerRequest mailCheckerRequest) {
        MailChecker mailChecker = new MailChecker();
        mailChecker.setEmail(mailCheckerRequest.getEmail());
        mailChecker.setFirstName(mailCheckerRequest.getFirstName());
        mailChecker.setLastName(mailCheckerRequest.getLastName());
        mailChecker.setStatus(mailCheckerRequest.getStatus());
        return mailChecker;
    }

    public static MailCheckerRequest modelToWs(MailChecker mailChecker) {
        MailCheckerRequest mailCheckerRequest = new MailCheckerRequest();
        mailCheckerRequest.setEmail(mailChecker.getEmail());
        mailCheckerRequest.setStatus(mailChecker.getStatus());
        return mailCheckerRequest;
    }
}
