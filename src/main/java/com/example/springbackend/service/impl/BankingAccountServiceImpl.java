package com.example.springbackend.service.impl;

import com.example.springbackend.dao.BankingAccountDAO;
import com.example.springbackend.entity.BankingAccount;
import com.example.springbackend.service.BankingAccountService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankingAccountServiceImpl implements BankingAccountService {


    private BankingAccountDAO bankingAccountDAO;

    public BankingAccountServiceImpl(BankingAccountDAO bankingAccountDAO) {
        this.bankingAccountDAO = bankingAccountDAO;
    }
    @Override
    public BankingAccount findBankingAccountById(int id) {
        return bankingAccountDAO.findBankingAccountById(id);
    }

    @Override
    public BankingAccount findBankingAccountByUserName(String userName) {
        return bankingAccountDAO.findBankingAccountByUserName(userName);
    }

    @Override
    public BankingAccount findBankingAccountByPhoneNumber(int phoneNumber) {
        return bankingAccountDAO.findBankingAccountByPhoneNumber(phoneNumber);
    }
}
