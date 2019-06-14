package com.denys.toukdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class MailServiceImpl implements MailService{

    private static final String CONFIRMATION_EMAIL = "toukdemo@yahoo.com";
    private static final String CONFIRMATION_EMAIL_SUBJECT = "Touk demo booking confirmation";
    private static final String CONFIRMATION_EMAIL_BODY = "Hi! Please confirm your booking by link! ";
    private static final String CONFIRMATION_LINK = "http://localhost:8080/confirmation?no=";

    @Autowired
    private MailSender mailSender;

    @Override
    public String sendMailAndGetConfirmUrl(String emailAddress, String confirmNo) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailAddress);
        message.setSubject(CONFIRMATION_EMAIL_SUBJECT);
        message.setText( text(confirmNo) );
        message.setFrom(CONFIRMATION_EMAIL);
        mailSender.send(message);
        return CONFIRMATION_LINK + confirmNo;
    }

    private String text(String confirmNo){
        return CONFIRMATION_EMAIL_BODY + CONFIRMATION_LINK + confirmNo;
    }

}
