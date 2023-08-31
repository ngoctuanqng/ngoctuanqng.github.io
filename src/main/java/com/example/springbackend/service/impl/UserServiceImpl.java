package com.example.springbackend.service.impl;

import com.example.springbackend.dao.UserDAO;
import com.example.springbackend.entity.BankingAccount;
import com.example.springbackend.entity.TransactionHistory;
import com.example.springbackend.entity.UserInfo;
import com.example.springbackend.handler.NotFoundException;
import com.example.springbackend.service.BankingAccountService;
import com.example.springbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDao;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDao = userDAO;
    }

    @Override
    public UserInfo findUserById(int userId) {

        UserInfo user = userDao.findUserById(userId);

        if (user == null) {
            throw new NotFoundException("User id not found - " + userId);
        }
        return user;
    }

    @Override
    public List<UserInfo> findAllUsers() {
        return userDao.findAllUsers();
    }

    @Transactional
    @Override
    public void saveUser(UserInfo userInfo) {
        userDao.saveUser(userInfo);
    }



    @Override
    public UserInfo findUserByUserName(String userName) {

        return userDao.findUserByUserName(userName);

    }

    @Override
    public UserInfo findUserByPhoneNumber(int phoneNumber) {
        return userDao.findUserByPhoneNumber(phoneNumber);
    }

    @Override
    public void updateUser(UserInfo userEntity) {

        userDao.updateUser(userEntity);

    }

    @Transactional
    @Override
    public void deleteUserByUserName(String userName) {

        userDao.deleteUserByUserName(userName);

    }

    @Transactional
    @Override
    public void deleteUserById(int userId) {

        userDao.deleteUserById(userId);

    }

    @Transactional
    @Override
    public ResponseEntity<String> transferMoney(String nameOfAccountSend, String nameOfAccountReceive, float amount) {

        UserInfo userInfoSent = userDao.findUserByUserName(nameOfAccountSend);
        UserInfo userInfoReceive = userDao.findUserByUserName(nameOfAccountReceive);

        if (userInfoSent == null || userInfoReceive == null)
            return ResponseEntity.badRequest().body("Invalid account username");

        if (userInfoSent.getBankingAccount().getAccountBalance() < amount)
            return ResponseEntity.badRequest().body("Not enough balance");

        userInfoSent.getBankingAccount().setAccountBalance(userInfoSent.getBankingAccount().getAccountBalance() - amount);
        userInfoReceive.getBankingAccount().setAccountBalance(userInfoReceive.getBankingAccount().getAccountBalance() + amount);

        TransactionHistory tempTransactionHistorySent = new TransactionHistory("Transfer", -amount, Timestamp.valueOf(LocalDateTime.now()));
        userInfoSent.addTransactionHistory(tempTransactionHistorySent);

        TransactionHistory tempTransactionHistoryReceive = new TransactionHistory("Receive", +amount, Timestamp.valueOf(LocalDateTime.now()));
        userInfoReceive.addTransactionHistory(tempTransactionHistoryReceive);

        userDao.updateUser(userInfoSent);
        userDao.updateUser(userInfoReceive);

        return ResponseEntity.ok("Money transferred successfully");
    }

    @Transactional
    @Override
    public ResponseEntity<String> withDrawMoney(String nameOfAccountWithDraw, float amount) {

        UserInfo userInfoWithDraw = userDao.findUserByUserName(nameOfAccountWithDraw);

        if (userInfoWithDraw == null)
            return ResponseEntity.badRequest().body("Invalid account numbers");

        if (userInfoWithDraw.getBankingAccount().getAccountBalance() < amount)
            return ResponseEntity.badRequest().body("Not enough balance");

        userInfoWithDraw.getBankingAccount().setAccountBalance(userInfoWithDraw.getBankingAccount().getAccountBalance() - amount);

        TransactionHistory tempTransactionHistoryWithdraw = new TransactionHistory("Withdraw", -amount, Timestamp.valueOf(LocalDateTime.now()));
        userInfoWithDraw.addTransactionHistory(tempTransactionHistoryWithdraw);

        userDao.updateUser(userInfoWithDraw);

        return ResponseEntity.ok("Money withdrew successfully");
    }

    @Transactional
    @Override
    public ResponseEntity<String> payTheBill(String nameOfAccountPayTheBill, float amount) {

        UserInfo userInfoPayTheBill = userDao.findUserByUserName(nameOfAccountPayTheBill);

        if (userInfoPayTheBill == null)
            return ResponseEntity.badRequest().body("Invalid account numbers");

        if (userInfoPayTheBill.getBankingAccount().getAccountBalance() < amount)
            return ResponseEntity.badRequest().body("Not enough balance");

        userInfoPayTheBill.getBankingAccount().setAccountBalance(userInfoPayTheBill.getBankingAccount().getAccountBalance() - amount);

        TransactionHistory tempTransactionHistoryWithdraw = new TransactionHistory("Pay the bill", -amount, Timestamp.valueOf(LocalDateTime.now()));
        userInfoPayTheBill.addTransactionHistory(tempTransactionHistoryWithdraw);

        userDao.updateUser(userInfoPayTheBill);

        return ResponseEntity.ok("Pay the bill successfully");
    }


}
