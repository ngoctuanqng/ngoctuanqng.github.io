package com.example.springbackend.service.impl;

import com.example.springbackend.dao.TransactionHistoryDAO;
import com.example.springbackend.entity.TransactionHistory;
import com.example.springbackend.entity.UserInfo;
import com.example.springbackend.service.TransactionHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

    private TransactionHistoryDAO transactionHistoryDAO;

    public TransactionHistoryServiceImpl(TransactionHistoryDAO transactionHistoryDAO) {
        this.transactionHistoryDAO = transactionHistoryDAO;
    }
    @Override
    public TransactionHistory findTransactionHistoryById(int id) {
        return transactionHistoryDAO.findTransactionHistoryById(id);
    }

    @Override
    public List<TransactionHistory> findTransactionHistoryByUserInfo(String userName) {
        return transactionHistoryDAO.findTransactionHistoryByUserName(userName);
    }
}
