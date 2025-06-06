package com.agriculturalmachinery.module;

public abstract class PaymentInfo {
    public double paymentAmount;
    public PaymentInfo(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
    public abstract boolean processPayment();
}
