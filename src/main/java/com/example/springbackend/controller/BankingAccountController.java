package com.example.springbackend.controller;

import com.example.springbackend.entity.BankingAccount;
import com.example.springbackend.service.BankingAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BankingAccountController {

    private BankingAccountService bankingAccountService;

    @Autowired
    public BankingAccountController(BankingAccountService bankingAccountService) {
        this.bankingAccountService = bankingAccountService;
    }

    @GetMapping("/user/findBankingAccountById/{id}")
    public BankingAccount findBankingAccountById(@PathVariable("id") int id) {
        return bankingAccountService.findBankingAccountById(id);
    }

    @GetMapping("/user/findBankingAccountByUserName/{userName}")
    public BankingAccount findBankingAccountByUserName(@PathVariable("userName") String userName) {
        return bankingAccountService.findBankingAccountByUserName(userName);
    }

    @GetMapping("/user/findBankingAccountByPhoneNumber/{phoneNumber}")
    public BankingAccount findBankingAccountByPhoneNumber(@PathVariable("phoneNumber") int phoneNumber) {
        return bankingAccountService.findBankingAccountByPhoneNumber(phoneNumber);
    }
}
