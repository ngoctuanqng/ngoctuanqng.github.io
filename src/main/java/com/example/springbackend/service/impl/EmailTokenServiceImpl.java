package com.example.springbackend.service.impl;

import com.example.springbackend.dao.EmailTokenDAO;
import com.example.springbackend.dao.impl.EmailTokenDAOImpl;
import com.example.springbackend.entity.EmailToken;
import com.example.springbackend.service.EmailConfirmationService;
import com.example.springbackend.service.EmailTokenService;
import org.springframework.stereotype.Service;

@Service
public class EmailTokenServiceImpl implements EmailTokenService {

    private EmailTokenDAO emailTokenDAO;

    public EmailTokenServiceImpl(EmailTokenDAO emailTokenDAO) {
        this.emailTokenDAO = emailTokenDAO;
    }


    @Override
    public EmailToken findByToken(String token) {
        return emailTokenDAO.findByToken(token);
    }

    @Override
    public void save(EmailToken emailToken) {
        emailTokenDAO.save(emailToken);
    }

    @Override
    public EmailToken findByEmail(String email) {
        return emailTokenDAO.findByEmail(email);
    }

    @Override
    public void deleteByEmail(String email) {
        emailTokenDAO.deleteByEmail(email);
    }
}
