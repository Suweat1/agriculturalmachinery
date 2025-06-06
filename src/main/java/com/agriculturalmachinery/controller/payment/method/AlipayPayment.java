package com.agriculturalmachinery.controller.payment.method;

import com.agriculturalmachinery.module.PaymentInfo;

public class AlipayPayment extends PaymentInfo {
    private int accountNo;
    public AlipayPayment(double paymentAmount, int accountNo) {
        super(paymentAmount);

        this.accountNo = accountNo;
    }
    public boolean processPayment() {
        return true;
    }
}
