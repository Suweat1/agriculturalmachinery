package com.haubigdata24_12.agriculturalmachinery.model;

public abstract class PaymentInfo {
    public double paymentAmount;
    public PaymentInfo(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
    public abstract boolean processPayment();
}
