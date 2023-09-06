package com.example.springbackend.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "email_subscription")
public class EmailSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "email")
    private String email;

    public EmailSubscription() {

    }

    public EmailSubscription(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "EmailSubscription{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
