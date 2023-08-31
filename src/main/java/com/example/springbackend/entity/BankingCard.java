package com.example.springbackend.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "banking_card")
public class BankingCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_code")
    private int cardCode;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "due_date")
    private Timestamp dueDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_info_id")
    private UserInfo user;

    public BankingCard() {

    }

    public BankingCard(boolean isActive, Timestamp dueDate) {
        this.isActive = isActive;
        this.dueDate = dueDate;
    }

    public int getCardCode() {
        return cardCode;
    }

    public void setCardCode(int cardCode) {
        this.cardCode = cardCode;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "BankingCard{" +
                "cardCode=" + cardCode +
                ", isActive=" + isActive +
                ", dueDate=" + dueDate +
                '}';
    }
}
