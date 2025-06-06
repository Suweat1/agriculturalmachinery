package com.agriculturalmachinery.controller.payment;

import com.agriculturalmachinery.module.PaymentInfo;
import com.agriculturalmachinery.controller.payment.method.AlipayPayment;
import com.agriculturalmachinery.controller.payment.method.BankCardPayment;
import com.agriculturalmachinery.controller.payment.method.WechatPayment;

public class PaymentManager {

    public static PaymentResult processPayment(int option, double amount, int credential) {
        try {
            PaymentInfo payment;
            switch (option) {
                case 1:
                    payment = new WechatPayment(amount, credential);
                    break;
                case 2:
                    payment = new AlipayPayment(amount, credential);
                    break;
                case 3:
                    payment = new BankCardPayment(amount, credential);
                    break;
                default:
                    return PaymentResult.INVALID_INPUT;
            }
            if (payment.processPayment()) {
                return PaymentResult.SUCCESS;
            } else {
                return PaymentResult.FAILURE;
            }
        } catch (Exception e) {
            return PaymentResult.FAILURE;
        }
    }
}
