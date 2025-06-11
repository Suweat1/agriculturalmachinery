package com.haubigdata24_12.agriculturalmachinery.controller.payment.method;


import com.haubigdata24_12.agriculturalmachinery.model.PaymentInfo;

public class AlipayPayment extends PaymentInfo {
    private long accountNo;
    public AlipayPayment(double paymentAmount, long accountNo) {
        super(paymentAmount);

        this.accountNo = accountNo;
    }
    public boolean processPayment() {
        return true;
    }
}