package com.example.springbackend.dao;

import com.example.springbackend.entity.BankingCard;
import com.example.springbackend.entity.UserInfo;

import java.util.List;

public interface BankingCardDAO {

    public BankingCard findBankingCardByCardCode(int cardCode);

    public BankingCard findBankingCardByUserName(String userName);

    public BankingCard findBankingCardByPhoneNumber(int phoneNumber);


}
