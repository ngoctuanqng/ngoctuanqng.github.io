package com.example.springbackend.dao;

import com.example.springbackend.entity.BankingAccount;

import java.util.Optional;

public interface BankingAccountDAO {

    public BankingAccount findBankingAccountById(int id);

    public BankingAccount findBankingAccountByUserName(String userName);

    public BankingAccount findBankingAccountByPhoneNumber(int phoneNumber);
}
