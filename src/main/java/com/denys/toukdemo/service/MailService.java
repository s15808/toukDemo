package com.denys.toukdemo.service;

public interface MailService {

    String sendMailAndGetConfirmUrl(String emailAddress, String confirmNo);
}
