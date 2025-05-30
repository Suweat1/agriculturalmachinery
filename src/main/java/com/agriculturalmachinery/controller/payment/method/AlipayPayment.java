package com.agriculturalmachinery.controller.payment.method;

import com.agriculturalmachinery.module.PaymentInfo;

public class AlipayPayment extends PaymentInfo {
    private int accountNo;
    public AlipayPayment(double paymentAmount, int accountNo) {
        super(paymentAmount);

        this.accountNo = accountNo;
    }
    public boolean processPayment() {
        System.out.println("正在通过支付宝支付金额" + paymentAmount + " 账号为" + accountNo);
        return true;
    }

}
