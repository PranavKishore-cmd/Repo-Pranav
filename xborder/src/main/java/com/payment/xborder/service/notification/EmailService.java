package com.payment.xborder.service.notification;


import java.util.concurrent.CompletableFuture;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

/**
 * Integration class to send mail using configured Java Mail
 *
 *
 */
@Service
public class EmailService {


    private final JavaMailSender javaMailSender;
    private final MailContentBuilderService mailContentBuilderService;
    
    public EmailService(JavaMailSender javaMailSender, MailContentBuilderService mailContentBuilderService) {
        this.javaMailSender = javaMailSender;
        this.mailContentBuilderService = mailContentBuilderService;
    }

    public void send(String to, String subject, String mailContent) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setSubject(subject);
            messageHelper.setFrom("xbordertest@gmail.com");
            messageHelper.setTo(to);
            messageHelper.setText(mailContent, true);
        } catch (MessagingException e) {

        }
        javaMailSender.send(mimeMessage);
    }
    

    public void sendEmailAsync(String to, NotificationEnumeration subject, Context context) {
        CompletableFuture.runAsync(() -> sendEmail(to, subject, context)).exceptionally(ex -> {
            ex.printStackTrace();
            return null;
        });
    }

    private void sendEmail(String to, NotificationEnumeration subject, Context context) {
        send(to, subject.getNotificationSubject(), mailContentBuilderService.build(subject.getTemplateName(), context));
    }
}
