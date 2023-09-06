package com.example.springbackend.service;

public interface EmailConfirmationService {

    public void sendConfirmationEmail(String email);

    public boolean confirmEmail(String token);

    public String generateToken();

    public void sendEmail(String email, String subject, String message);

}
