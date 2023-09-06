package com.example.springbackend.dao.impl;

import com.example.springbackend.dao.EmailTokenDAO;
import com.example.springbackend.entity.EmailToken;
import com.example.springbackend.entity.UserInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class EmailTokenDAOImpl implements EmailTokenDAO {

    private EntityManager entityManager;

    public EmailTokenDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public EmailToken findByToken(String token) {
        TypedQuery<EmailToken> theQuery = entityManager.createQuery("FROM EmailToken WHERE token=:data", EmailToken.class);
        theQuery.setParameter("data", token);

        List<EmailToken> results = theQuery.getResultList();
        if (!results.isEmpty()) {
            return results.get(0);
        }

        return null;
    }

    @Override
    public void save(EmailToken emailToken) {
        entityManager.persist(emailToken);
    }

    @Override
    public EmailToken findByEmail(String email) {
        TypedQuery<EmailToken> theQuery = entityManager.createQuery("FROM EmailToken WHERE email=:data", EmailToken.class);
        theQuery.setParameter("data", email);

        List<EmailToken> results = theQuery.getResultList();
        if (!results.isEmpty()) {
            return results.get(0);
        }

        return null;
    }

    @Override
    public void deleteByEmail(String email) {

        TypedQuery<EmailToken> theQuery = entityManager.createQuery("FROM EmailToken WHERE email=:data", EmailToken.class);
        theQuery.setParameter("data", email);

        List<EmailToken> results = theQuery.getResultList();

        for (EmailToken token : results) {
            entityManager.remove(token);
        }

    }
}
