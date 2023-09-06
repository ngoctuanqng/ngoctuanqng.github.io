package com.example.springbackend.controller;

import com.example.springbackend.entity.EmailSubscription;
import com.example.springbackend.service.EmailSubscriptionService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class EmailSubscriptionController {

    private final JavaMailSender javaMailSender;

    private EmailSubscriptionService emailSubscriptionService;

    public EmailSubscriptionController(JavaMailSender javaMailSender, EmailSubscriptionService emailSubscriptionService) {
        this.javaMailSender = javaMailSender;
        this.emailSubscriptionService = emailSubscriptionService;
    }

    @PostMapping("/subscribe")
    public String subscribe(@RequestBody String email, Model model) throws UnsupportedEncodingException {

        String decodedEmail = URLDecoder.decode(email, StandardCharsets.UTF_8.toString());

        decodedEmail = decodedEmail.substring("email=".length());

        EmailSubscription emailSubscription = new EmailSubscription(decodedEmail);

        emailSubscriptionService.save(emailSubscription);

        sendConfirmationEmail(emailSubscription.getEmail());

        return "redirect:/";
    }

    private void sendConfirmationEmail(String recipientEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Xác nhận đăng ký nhận email");
        message.setText("Chúng tôi đã nhận được yêu cầu của bạn để đăng ký nhận email.");
        javaMailSender.send(message);
    }

}
