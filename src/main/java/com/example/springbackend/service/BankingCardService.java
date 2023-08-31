package com.example.springbackend.service;

import com.example.springbackend.entity.BankingCard;
import org.springframework.stereotype.Service;


public interface BankingCardService {

    public BankingCard findBankingCardByCardCode(int cardCode);

    public BankingCard findBankingCardByUserName(String userName);

    public BankingCard findBankingCardByPhoneNumber(int phoneNumber);
}
