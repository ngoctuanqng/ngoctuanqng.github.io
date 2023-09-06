package com.example.springbackend.service.impl;

import com.example.springbackend.dao.EmailTokenDAO;
import com.example.springbackend.entity.EmailToken;
import com.example.springbackend.service.EmailConfirmationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class EmailConfirmationServiceImpl implements EmailConfirmationService {

    private EmailTokenDAO emailTokenDAO;
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailConfirmationServiceImpl(EmailTokenDAO emailTokenDAO, JavaMailSender javaMailSender) {
        this.emailTokenDAO = emailTokenDAO;
        this.javaMailSender = javaMailSender;
    }

    @Transactional
    public void sendConfirmationEmail(String email) {

        emailTokenDAO.deleteByEmail(email);

        String token = generateToken();
        EmailToken emailToken = new EmailToken(email, token);
        emailTokenDAO.save(emailToken);

        String confirmationLink = "http://localhost:8080/confirm?token=" + token;
        String subject = "Xác nhận email";
//        String message = "Nhấp vào liên kết sau để xác nhận email của bạn: " + confirmationLink;
        String message = emailToken.getToken();
        sendEmail(email, subject, message);
    }


    @Transactional
    public boolean confirmEmail(String token) {

        EmailToken emailToken = emailTokenDAO.findByToken(token);
        if (emailToken != null && !emailToken.isConfirmed()) {

            emailToken.setConfirmed(true);
            emailTokenDAO.save(emailToken);
            return true;
        }
        return false;
    }

    public String generateToken() {

        return UUID.randomUUID().toString();
    }

    public void sendEmail(String email, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        javaMailSender.send(mailMessage);
    }


}
