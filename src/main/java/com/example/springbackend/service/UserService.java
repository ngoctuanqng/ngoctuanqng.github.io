package com.example.springbackend.service;

import com.example.springbackend.entity.UserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface UserService {

    UserInfo findUserById(int userId);

    List<UserInfo> findAllUsers();

    @Transactional
    void saveUser(UserInfo userInfo);

    UserInfo findUserByUserName(String userName);

    UserInfo findUserByPhoneNumber(int phoneNumber);

    @Transactional
    void updateUser(UserInfo userEntity);

    void deleteUserByUserName(String userName);

    void deleteUserById(int userId);

    public ResponseEntity<String> transferMoney(String nameOfAccountSend, String nameOfAccountReceive, float amount);

    public ResponseEntity<String> withDrawMoney(String nameOfAccountWithDraw, float amount);

    public ResponseEntity<String> payTheBill(String nameOfAccountPayTheBill, float amount);
}
