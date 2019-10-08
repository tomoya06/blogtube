package com.heavelop.blogtube.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
  @Autowired
  private JavaMailSender mailSender;
  @Value("${spring.mail.username}")
  private String sendFrom;
  
  public void sendTo(String email, String subject, String content) {
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setFrom(sendFrom);
    mailMessage.setTo(email);
    mailMessage.setSubject(subject);
    mailMessage.setText(content);

    mailSender.send(mailMessage);
  }
}