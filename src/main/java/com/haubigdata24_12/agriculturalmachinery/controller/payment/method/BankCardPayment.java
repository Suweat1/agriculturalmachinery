package com.haubigdata24_12.agriculturalmachinery.controller.payment.method;

import com.haubigdata24_12.agriculturalmachinery.model.PaymentInfo;

public class BankCardPayment extends PaymentInfo {
    private long cardNo;
    public BankCardPayment(double paymentAmount, long cardNo) {
        super(paymentAmount);

        this.cardNo = cardNo;
    }
    public boolean processPayment() {
        return true;
    }
}