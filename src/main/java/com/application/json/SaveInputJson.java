package com.application.json;

import java.sql.Timestamp;

public class SaveInputJson {
private Timestamp time;
private double amount;

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
