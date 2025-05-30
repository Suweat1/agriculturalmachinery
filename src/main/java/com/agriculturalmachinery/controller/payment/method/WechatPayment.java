package com.agriculturalmachinery.controller.payment.method;

import com.agriculturalmachinery.module.PaymentInfo;

public class WechatPayment extends PaymentInfo {
    private int OpenId;
    public WechatPayment(double paymentAmount ,int OpenId) {
        super(paymentAmount);

        this.OpenId=OpenId;
    }
    public boolean processPayment() {
    System.out.println("正在通过微信支付金额:"+paymentAmount+"账号为："+OpenId);
    return true;


    }
}
