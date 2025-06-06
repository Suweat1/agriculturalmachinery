package com.agriculturalmachinery.controller.payment.method;

import com.agriculturalmachinery.module.PaymentInfo;

public class WechatPayment extends PaymentInfo {
    private int OpenId;
    public WechatPayment(double paymentAmount ,int OpenId) {
        super(paymentAmount);

        this.OpenId=OpenId;
    }
    public boolean processPayment() {
        return true;
    }
}
