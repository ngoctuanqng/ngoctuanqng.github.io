package com.example.springbackend.dao;

import com.example.springbackend.entity.TransactionHistory;
import com.example.springbackend.entity.UserInfo;

import java.util.List;

public interface TransactionHistoryDAO {

    public TransactionHistory findTransactionHistoryById(int id);

    public List<TransactionHistory> findTransactionHistoryByUserName(String userName);

}
