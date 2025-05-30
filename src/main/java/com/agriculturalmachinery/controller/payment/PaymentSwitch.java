package com.agriculturalmachinery.controller.payment;

import com.agriculturalmachinery.controller.payment.method.AlipayPayment;
import com.agriculturalmachinery.controller.payment.method.BankCardPayment;
import com.agriculturalmachinery.controller.payment.method.WechatPayment;
import com.agriculturalmachinery.module.PaymentInfo;

import java.util.Scanner;

//用户选择支付方式
public class PaymentSwitch {
    enum PaymentMethod{
        WechatPay,AliPay,BankCardPay
    }
    static PaymentInfo creatPayment(double Amount, int Id, PaymentMethod method){
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
        //是否选择有效支付方式
         boolean Choice=false;
         //储存支付方式
         PaymentInfo paymentInfo = null;
         while(!Choice){
             try{
                 System.out.println("请选择支付方式: ");
                 System.out.println("1,微信支付");
                 System.out.println("2，支付宝支付");
                 System.out.println("3,银行卡支付");
                 System.out.println("请输入数字编号");

             }

         }


    }
}
