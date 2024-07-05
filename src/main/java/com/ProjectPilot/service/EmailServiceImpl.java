package com.ProjectPilot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {


  @Autowired
  private JavaMailSender javaMailSender;

  @Override
  public void sendEmailWithToken(String userEmail, String link) throws Exception {
    MimeMessage mimeMessagge = javaMailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(mimeMessagge,"utf-8");

    String subject = "Join Project Team Invitation";
    String text = "Click the Link to join the project team: " + link;
    helper.setSubject(subject);
    helper.setText(text);
    helper.setTo(userEmail);


    try{
      javaMailSender.send(mimeMessagge);
    }
    catch(Exception e){
      e.printStackTrace();
    }
  }
  
}
