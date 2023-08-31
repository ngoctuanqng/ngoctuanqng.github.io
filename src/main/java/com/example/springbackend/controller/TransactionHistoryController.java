package com.example.springbackend.controller;

import com.example.springbackend.entity.TransactionHistory;
import com.example.springbackend.entity.UserInfo;
import com.example.springbackend.service.TransactionHistoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class TransactionHistoryController {

    private TransactionHistoryService transactionHistoryService;

    public TransactionHistoryController(TransactionHistoryService transactionHistoryService) {
        this.transactionHistoryService = transactionHistoryService;
    }

    @GetMapping("/user/findTransactionHistoryById/{id}")
    public TransactionHistory findTransactionHistoryById(@PathVariable("id") int id) {
        return transactionHistoryService.findTransactionHistoryById(id);
    }

    @GetMapping("/user/findTransactionHistoryByUserInfo/{userName}")
    public List<TransactionHistory> findTransactionHistoryByUserInfo(@PathVariable("userName") String userName) {
        return transactionHistoryService.findTransactionHistoryByUserInfo(userName);
    }
}
