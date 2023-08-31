package com.example.springbackend.service.impl;

import com.example.springbackend.dao.BankingCardDAO;
import com.example.springbackend.entity.BankingCard;
import com.example.springbackend.service.BankingCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankingCardServiceImpl implements BankingCardService {

    private BankingCardDAO bankingCardDAO;

    @Autowired
    public BankingCardServiceImpl(BankingCardDAO bankingCardDAO) {
        this.bankingCardDAO = bankingCardDAO;
    }
    @Override
    public BankingCard findBankingCardByCardCode(int cardCode) {
        return bankingCardDAO.findBankingCardByCardCode(cardCode);
    }

    @Override
    public BankingCard findBankingCardByUserName(String userName) {
        return bankingCardDAO.findBankingCardByUserName(userName);
    }

    @Override
    public BankingCard findBankingCardByPhoneNumber(int phoneNumber) {
        return bankingCardDAO.findBankingCardByPhoneNumber(phoneNumber);
    }
}
