package com.sm.service;

import com.sm.rabbitmq.model.MailModel;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService {
    private final JavaMailSender javaMailSender;

    public void sendMail(MailModel model){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("${smmailusername}");
        mailMessage.setTo(model.getEmail());
        mailMessage.setSubject("Activation code: ");
        mailMessage.setText(model.getEmail()+" succesfull");
        mailMessage.setText("Activation code: "+ model.getActivationCode());
        javaMailSender.send(mailMessage);
    }
}
