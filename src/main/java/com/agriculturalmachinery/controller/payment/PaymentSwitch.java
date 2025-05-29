package com.agriculturalmachinery.controller.payment;

import com.agriculturalmachinery.controller.payment.method.AlipayPayment;
import com.agriculturalmachinery.controller.payment.method.BankCardPayment;
import com.agriculturalmachinery.controller.payment.method.WechatPayment;
import java.util.Scanner;

//用户选择支付方式
public class PaymentSwitch {
    enum PaymentMethod{
        WechatPay,AliPay,BankCardPay
    }
    static  PaymentInfo creatPayment(double Amount, int Id, PaymentMethod method){
        switch (method) {
            case WechatPay:
                return new WechatPayment(Amount, Id);
                case AliPay:
                    return new AlipayPayment(Amount,Id);
                    case BankCardPay:
                        return new BankCardPayment(Amount,Id);
                        default:
                            return null;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


    }
}
