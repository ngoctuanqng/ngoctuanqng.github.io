package com.example.springbackend.dao.impl;

import com.example.springbackend.dao.BankingAccountDAO;
import com.example.springbackend.entity.BankingAccount;
import com.example.springbackend.entity.UserInfo;
import com.example.springbackend.service.UserService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BankingAccountDAOImpl implements BankingAccountDAO {

    private EntityManager entityManager;
    private UserService userService;

    public BankingAccountDAOImpl(EntityManager entityManager, UserService userService) {
        this.entityManager = entityManager;
        this.userService = userService;
    }

    @Override
    public BankingAccount findBankingAccountById(int id) {
        return entityManager.find(BankingAccount.class, id);
    }

    @Override
    public BankingAccount findBankingAccountByUserName(String userName) {
        UserInfo userInfo = userService.findUserByUserName(userName);
        if (userInfo == null) {
            return null;
        }
        BankingAccount bankingAccount = userInfo.getBankingAccount();
        return bankingAccount;
    }

    @Override
    public BankingAccount findBankingAccountByPhoneNumber(int phoneNumber) {
        UserInfo userInfo = userService.findUserByPhoneNumber(phoneNumber);
        BankingAccount bankingAccount = userInfo.getBankingAccount();
        return bankingAccount;
    }
}
