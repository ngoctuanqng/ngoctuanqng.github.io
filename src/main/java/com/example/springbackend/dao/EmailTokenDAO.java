package com.example.springbackend.dao;

import com.example.springbackend.entity.EmailToken;

public interface EmailTokenDAO {

    public EmailToken findByToken(String token);

    public void save(EmailToken emailToken);

    public EmailToken findByEmail(String email);

    public void deleteByEmail(String email);

}
