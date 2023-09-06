package com.example.springbackend.service.impl;

import com.example.springbackend.dao.EmailSubscriptionDAO;
import com.example.springbackend.entity.EmailSubscription;
import com.example.springbackend.service.EmailSubscriptionService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class EmailSubscriptionServiceImpl implements EmailSubscriptionService {

    private EmailSubscriptionDAO emailSubscriptionDAO;
    EmailSubscriptionServiceImpl(EmailSubscriptionDAO emailSubscriptionDAO) {
        this.emailSubscriptionDAO = emailSubscriptionDAO;
    }


    @Override
    @Transactional
    public void save(EmailSubscription emailSubscription) {
        emailSubscriptionDAO.save(emailSubscription);
    }
}
