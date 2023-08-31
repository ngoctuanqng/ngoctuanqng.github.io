package com.example.springbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "transaction_history")
public class TransactionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "transaction_category")
    private String TransactionCategory;

    @Column(name = "amount")
    private float amount;

    @Column(name = "time")
    private Timestamp time;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_info_id")
    private UserInfo user;

    public TransactionHistory() {

    }

    public TransactionHistory(String transactionCategory, float amount, Timestamp time) {
        TransactionCategory = transactionCategory;
        this.amount = amount;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransactionCategory() {
        return TransactionCategory;
    }

    public void setTransactionCategory(String transactionCategory) {
        TransactionCategory = transactionCategory;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "TransactionHistory{" +
                "id=" + id +
                ", TransactionCategory='" + TransactionCategory + '\'' +
                ", amount=" + amount +
                ", time=" + time +
                '}';
    }
}
