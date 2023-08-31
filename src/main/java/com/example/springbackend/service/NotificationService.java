package com.example.springbackend.service;

import com.example.springbackend.entity.UserInfo;
import org.springframework.scheduling.annotation.Async;

public interface NotificationService {
    @Async
    void sendNotification(UserInfo userInfo);
}
