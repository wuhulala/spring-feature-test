package com.wuhulala.springboot.chapter12.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MimeMailServiceImpl implements MailService {

    @Autowired
    private MailSender mailSender;

    @Override
    public void send(String address, String content) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("xxxx@163.com");
        msg.setSubject("hello");
        msg.setTo(address);
        msg.setText(content);
        try{
            this.mailSender.send(msg);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }


}