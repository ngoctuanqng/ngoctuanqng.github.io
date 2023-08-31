package com.example.springbackend.dao;

import com.example.springbackend.entity.UserInfo;

import java.util.List;

public interface UserDAO {

    UserInfo findUserById(int userId);

    List<UserInfo> findAllUsers();

    void saveUser(UserInfo userInfo);

    UserInfo findUserByUserName(String userName);

    UserInfo findUserByPhoneNumber(int phoneNumber);

    void updateUser(UserInfo userEntity);

    void deleteUserByUserName(String userName);

    void deleteUserById(int userId);

}
