package com.example.springbackend.service;

import com.example.springbackend.entity.TransactionHistory;
import com.example.springbackend.entity.UserInfo;

import java.util.List;

public interface TransactionHistoryService {

    public TransactionHistory findTransactionHistoryById(int id);

    public List<TransactionHistory> findTransactionHistoryByUserInfo(String userName);
}
