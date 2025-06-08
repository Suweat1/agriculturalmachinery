package com.haubigdata24_12.agriculturalmachinery.controller.payment.method;

import com.haubigdata24_12.agriculturalmachinery.model.PaymentInfo;

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