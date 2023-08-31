package com.example.springbackend.dao.impl;

import com.example.springbackend.dao.BankingCardDAO;
import com.example.springbackend.entity.BankingCard;
import com.example.springbackend.entity.UserInfo;
import com.example.springbackend.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BankingCardDAOImpl implements BankingCardDAO {

    private EntityManager entityManager;
    private UserService userService;

    @Autowired
    public BankingCardDAOImpl(EntityManager entityManager, UserService userService)
    {
        this.entityManager = entityManager;
        this.userService = userService;
    }


    @Override
    public BankingCard findBankingCardByCardCode(int cardCode) {
        BankingCard bankingCard = entityManager.find(BankingCard.class, cardCode);
        return bankingCard;
    }

    @Override
    public BankingCard findBankingCardByUserName(String userName) {
        UserInfo userInfo = userService.findUserByUserName(userName);
        return userInfo.getBankingCard();
    }

    @Override
    public BankingCard findBankingCardByPhoneNumber(int phoneNumber) {
        UserInfo userInfo = userService.findUserByPhoneNumber(phoneNumber);
        return userInfo.getBankingCard();
    }
}
