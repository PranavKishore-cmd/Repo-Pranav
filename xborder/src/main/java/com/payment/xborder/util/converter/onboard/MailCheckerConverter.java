package com.payment.xborder.util.converter.onboard;


import com.payment.xborder.model.onboard.MailChecker;
import com.payment.xborder.model.onboard.ws.MailCheckerRequest;

import java.time.LocalDate;

public class MailCheckerConverter {

    public static MailChecker wsToModel(MailCheckerRequest mailCheckerRequest) {
        MailChecker mailChecker = new MailChecker();
        mailChecker.setFirstName(mailCheckerRequest.getFirstName());
        mailChecker.setLastName(mailCheckerRequest.getLastName());
        mailChecker.setEmail(mailCheckerRequest.getEmail());
        mailChecker.setStatus(mailCheckerRequest.getStatus());
        mailChecker.setRequestDate(LocalDate.now());
        return mailChecker;
    }

    public static MailCheckerRequest modelToWs(MailChecker mailChecker) {
        MailCheckerRequest mailCheckerRequest = new MailCheckerRequest();
        mailCheckerRequest.setEmail(mailChecker.getEmail());
        mailCheckerRequest.setStatus(mailChecker.getStatus());
        return mailCheckerRequest;
    }
}
