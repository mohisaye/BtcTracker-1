package com.application.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "BTCInfo")
public class BTCInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "amount", nullable = false)
    private double amount;
    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
