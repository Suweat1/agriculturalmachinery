package com.agriculturalmachinery.controller.payment.method;

import com.agriculturalmachinery.module.PaymentInfo;

public class BankCardPayment extends PaymentInfo {
    private int cardNo;
    public BankCardPayment(double paymentAmount, int cardNo) {
        super(paymentAmount);

        this.cardNo = cardNo;
    }
    public boolean processPayment() {
        return true;
    }
}
