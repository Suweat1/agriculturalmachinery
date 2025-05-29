package com.agriculturalmachinery.controller.payment;

import com.agriculturalmachinery.module.AgriculturalMachineryInfo;
import com.agriculturalmachinery.module.PaymentInfo;

public class BankCardPayment extends PaymentInfo {
    private int cardNo;
    public BankCardPayment(double paymentAmount, int cardNo) {
        this.paymentAmount = paymentAmount;
        this.cardNo = cardNo;
    }
    public boolean processPayment() {

    }

}
