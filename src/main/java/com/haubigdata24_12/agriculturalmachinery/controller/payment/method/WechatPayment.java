package com.haubigdata24_12.agriculturalmachinery.controller.payment.method;

import com.haubigdata24_12.agriculturalmachinery.model.PaymentInfo;

public class WechatPayment extends PaymentInfo {
    private long OpenId;
    public WechatPayment(double paymentAmount ,long OpenId) {
        super(paymentAmount);

        this.OpenId=OpenId;
    }
    public boolean processPayment() {
        return true;
    }
}