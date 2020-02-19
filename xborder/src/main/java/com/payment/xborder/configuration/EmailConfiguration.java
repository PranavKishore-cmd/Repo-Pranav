package com.payment.xborder.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfiguration {

    @Value("${xborder.mail.username}")
    private String tgEmail;
    @Value("${xborder.mail.password}")
    private String tgPassword;
    @Value("${xborder.mail.host}")
    private String host;
    @Value("${xborder.mail.port}")
    private String port;
    @Value("${xborder.mail.protocol}")
    private String protocol;

    @Bean
    public JavaMailSenderImpl getJavaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setPort(Integer.parseInt(port));
        javaMailSender.setUsername(tgEmail);
        javaMailSender.setPassword("codeth123");

        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.transport.protocol", "smtp");
        javaMailProperties.put("mail.debug", "true");

        javaMailSender.setJavaMailProperties(javaMailProperties);
        return javaMailSender;
    }


}
