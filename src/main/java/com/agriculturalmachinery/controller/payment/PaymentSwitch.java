package com.agriculturalmachinery.controller.payment;

import com.agriculturalmachinery.controller.payment.method.AlipayPayment;
import com.agriculturalmachinery.controller.payment.method.BankCardPayment;
import com.agriculturalmachinery.controller.payment.method.WechatPayment;
import com.agriculturalmachinery.controller.rentalorder.RentalOrder;

import java.util.Scanner;

//用户选择支付方式
public class PaymentSwitch {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 创建一个 RentalOrder 实例来获取订单的总花费
        RentalOrder rentalOrder = new RentalOrder();
        double totalCost = rentalOrder.getTotalCost();  // 获取总金额


        int option = 0;

        while (true) {
            System.out.println("请选择支付方式: ");
            System.out.println("1,微信支付");
            System.out.println("2，支付宝支付");
            System.out.println("3,银行卡支付");
            System.out.println("4,退出程序");
            System.out.println("请输入数字编号(1-4):");
            option = sc.nextInt();
            //捕获非法数字并抛出
            int lowOption=1;
            int highOption=4;
            if(option<1 || option>4) {
                System.out.println("输入的数字应该在"+lowOption+"和"+highOption+"之间!请重新输入");
                continue;
            }
            switch (option) {
                case 1:
                    System.out.println("请输入微信openId:");
                    int openId = sc.nextInt();
                    WechatPayment wechatPayment = new WechatPayment(totalCost,openId);
                    wechatPayment.processPayment();
                    System.out.println("支付成功！即将退出程序...");
                    return;//执行完程序后退出
                case 2:
                    System.out.println("请输入支付宝账号：");
                    int accountNo = sc.nextInt();

                    AlipayPayment alipayPayment = new AlipayPayment(totalCost,accountNo);
                    alipayPayment.processPayment();
                    System.out.println("支付成功！即将退出程序...");
                    return;
                case 3:
                    System.out.println("请输入银行卡号：");
                    int cardNo = sc.nextInt();
                    BankCardPayment bankCardPayment = new BankCardPayment(totalCost,cardNo);
                    bankCardPayment.processPayment();
                    System.out.println("支付成功！即将退出程序...");
                    break;
                default:
                    System.out.println("退出程序");
                    return;
            }
        }
    }
}



