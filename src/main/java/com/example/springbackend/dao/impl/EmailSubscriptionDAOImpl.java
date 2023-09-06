package com.example.springbackend.dao.impl;

import com.example.springbackend.dao.EmailSubscriptionDAO;
import com.example.springbackend.entity.EmailSubscription;
import jakarta.persistence.EntityManager;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class EmailSubscriptionDAOImpl implements EmailSubscriptionDAO {

    private EntityManager entityManager;

    public EmailSubscriptionDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }



    @Override
    public void save(EmailSubscription emailSubscription) {
        entityManager.persist(emailSubscription);
    }
}
