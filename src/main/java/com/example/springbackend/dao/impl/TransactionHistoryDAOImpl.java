package com.example.springbackend.dao.impl;

import com.example.springbackend.dao.TransactionHistoryDAO;
import com.example.springbackend.entity.TransactionHistory;
import com.example.springbackend.entity.UserInfo;
import com.example.springbackend.service.UserService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionHistoryDAOImpl implements TransactionHistoryDAO {

    private EntityManager entityManager;
    private UserService userService;

    public TransactionHistoryDAOImpl(EntityManager entityManager, UserService userService) {
        this.entityManager = entityManager;
        this.userService = userService;
    }
    @Override
    public TransactionHistory findTransactionHistoryById(int id) {
        return entityManager.find(TransactionHistory.class, id);
    }

    @Override
    public List<TransactionHistory> findTransactionHistoryByUserName(String userName) {
        UserInfo userInfo = userService.findUserByUserName(userName);
        return userInfo.getTransactionHistory();
    }
}
