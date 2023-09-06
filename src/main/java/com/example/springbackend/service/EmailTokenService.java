package com.example.springbackend.service;

import com.example.springbackend.entity.EmailToken;

public interface EmailTokenService {

    public EmailToken findByToken(String token);

    public void save(EmailToken emailToken);

    public EmailToken findByEmail(String email);

    public void deleteByEmail(String email);

}
