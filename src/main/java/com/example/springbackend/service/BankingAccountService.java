package com.example.springbackend.service;

import com.example.springbackend.entity.BankingAccount;

import java.util.Optional;

public interface BankingAccountService {

    public BankingAccount findBankingAccountById(int id);

    public BankingAccount findBankingAccountByUserName(String userName);

    public BankingAccount findBankingAccountByPhoneNumber(int phoneNumber);
}
