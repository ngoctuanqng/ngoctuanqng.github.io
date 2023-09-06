package com.example.springbackend.dao;

import com.example.springbackend.entity.EmailSubscription;
import org.springframework.http.ResponseEntity;

public interface EmailSubscriptionDAO {

    public void save(EmailSubscription emailSubscription);

}
