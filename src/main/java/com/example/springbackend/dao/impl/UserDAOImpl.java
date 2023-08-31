package com.example.springbackend.dao.impl;

import com.example.springbackend.dao.UserDAO;
import com.example.springbackend.entity.BankingAccount;
import com.example.springbackend.entity.BankingCard;
import com.example.springbackend.entity.UserInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public UserInfo findUserById(int userId) {

        UserInfo user = entityManager.find(UserInfo.class, userId);

        return user;
    }

    @Override
    public List<UserInfo> findAllUsers() {

        TypedQuery<UserInfo> theQuery = entityManager.createQuery("from UserInfo", UserInfo.class);
        List<UserInfo> userLists = theQuery.getResultList();
        return userLists;
    }

    @Override
    public void saveUser(UserInfo userInfo) {

        BankingAccount bankingAccount = new BankingAccount(1000000);
        userInfo.setBankingAccount(bankingAccount);
        bankingAccount.setUser(userInfo);

        Timestamp timestamp = Timestamp.valueOf("2030-01-01 00:00:00");
        BankingCard bankingCard = new BankingCard(true, timestamp);
        userInfo.setBankingCard(bankingCard);
        bankingCard.setUser(userInfo);

        entityManager.persist(userInfo);
    }

    @Override
    public UserInfo findUserByUserName(String userName) {
        TypedQuery<UserInfo> theQuery = entityManager.createQuery("FROM UserInfo WHERE userName=:data", UserInfo.class);
        theQuery.setParameter("data", userName);

        List<UserInfo> results = theQuery.getResultList();
        if (!results.isEmpty()) {
            return results.get(0);
        }

        return null;
    }

    @Override
    public UserInfo findUserByPhoneNumber(int phoneNumber) {
        TypedQuery<UserInfo> theQuery = entityManager.createQuery("FROM UserInfo WHERE phoneNumber=:data", UserInfo.class);
        theQuery.setParameter("data", phoneNumber);
        return theQuery.getSingleResult();
    }

    @Override
    public void updateUser(UserInfo userEntity) {
        entityManager.merge(userEntity);
    }

    @Override
    public void deleteUserByUserName(String userName) {

        int deleteCount = entityManager.createQuery("DELETE FROM UserInfo WHERE userName=:data", UserInfo.class)
                                                                        .setParameter("data", userName)
                                                                        .executeUpdate();

    }

    @Override
    public void deleteUserById(int userId) {

        UserInfo user = entityManager.find(UserInfo.class, userId);

        entityManager.remove(user);

    }


}
