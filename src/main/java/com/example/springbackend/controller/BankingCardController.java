package com.example.springbackend.controller;

import com.example.springbackend.entity.BankingCard;
import com.example.springbackend.entity.UserInfo;
import com.example.springbackend.service.BankingCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BankingCardController {

    BankingCardService bankingCardService;

    @Autowired
    public BankingCardController(BankingCardService bankingCardService) {
        this.bankingCardService = bankingCardService;
    }

    @GetMapping("/user/findBankingCardByCardCode/{card_code}")
    public BankingCard findBankingCardByCardCode(@PathVariable("card_code") int cardCode) {
        return bankingCardService.findBankingCardByCardCode(cardCode);
    }


    @GetMapping("/user/findBankingCardByUserName/{userName}")
    public BankingCard findBankingCardByUserName(@PathVariable("userName") String userName) {

        return bankingCardService.findBankingCardByUserName(userName);
    }

    @GetMapping("/user/findBankingCardByPhoneNumber/{phoneNumber}")
    public BankingCard findBankingCardByPhoneNumber(@PathVariable("phoneNumber") int phoneNumber) {

        return bankingCardService.findBankingCardByPhoneNumber(phoneNumber);
    }


}
