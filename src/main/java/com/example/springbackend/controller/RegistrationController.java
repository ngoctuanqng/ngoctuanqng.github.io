package com.example.springbackend.controller;


import com.example.springbackend.service.EmailConfirmationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {

    private final EmailConfirmationService emailConfirmationService;

    @Autowired
    public RegistrationController(EmailConfirmationService emailConfirmationService) {
        this.emailConfirmationService = emailConfirmationService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody String email) {
        // Thực hiện đăng ký và gửi email xác nhận
        emailConfirmationService.sendConfirmationEmail(email);
        return ResponseEntity.ok("Đã gửi email xác nhận.");
    }

    @GetMapping("/confirm")
    public ResponseEntity<String> confirm(@RequestParam("token") String token) {
        // Xác nhận email bằng mã token
        boolean confirmed = emailConfirmationService.confirmEmail(token);
        if (confirmed) {
            return ResponseEntity.ok("Email đã được xác nhận.");
        } else {
            return ResponseEntity.badRequest().body("Không thể xác nhận email.");
        }
    }

}
