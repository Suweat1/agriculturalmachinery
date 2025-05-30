package com.agriculturalmachinery.controller.payment.method;

import com.agriculturalmachinery.module.PaymentInfo;

public class BankCardPayment extends PaymentInfo {
    private int cardNo;
    public BankCardPayment(double paymentAmount, int cardNo) {
        super(paymentAmount);

        this.cardNo = cardNo;
    }
    public boolean processPayment() {
        System.out.println("正在通过银行卡支付金额："+paymentAmount+"卡号为"+cardNo);
        return true;

    }

}
