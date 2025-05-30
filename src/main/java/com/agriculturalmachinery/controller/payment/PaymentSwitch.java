package com.agriculturalmachinery.controller.payment;

import com.agriculturalmachinery.controller.payment.method.AlipayPayment;
import com.agriculturalmachinery.controller.payment.method.BankCardPayment;
import com.agriculturalmachinery.controller.payment.method.WechatPayment;

import java.util.Scanner;

//用户选择支付方式
public class PaymentSwitch {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int option=0;

        do{
            System.out.println("请选择支付方式: ");
            System.out.println("1,微信支付");
            System.out.println("2，支付宝支付");
            System.out.println("3,银行卡支付");
            System.out.println("0,退出程序");
            System.out.println("请输入数字编号:");
            option = sc.nextInt();
            switch (option){
                case 1:
                    WechatPayment wechatPayment = new WechatPayment(sc.nextDouble(),sc.nextInt());
                case 2:
                    AlipayPayment alipayPayment = new AlipayPayment(sc.nextDouble(),sc.nextInt());
                case 3:
                    BankCardPayment bankCardPayment = new BankCardPayment(sc.nextDouble(),sc.nextInt());
                default:
                    break;
            }
        }while(option!=0);
    }

    }

