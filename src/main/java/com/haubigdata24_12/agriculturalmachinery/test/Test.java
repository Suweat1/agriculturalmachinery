package com.haubigdata24_12.agriculturalmachinery.test;

import com.haubigdata24_12.agriculturalmachinery.controller.payment.PaymentManager;
import com.haubigdata24_12.agriculturalmachinery.controller.payment.PaymentResult;
import com.haubigdata24_12.agriculturalmachinery.controller.rentalorder.RentalOrder;
import com.haubigdata24_12.agriculturalmachinery.model.RentalOrderInfo;
import com.haubigdata24_12.agriculturalmachinery.model.example.Tractor;

public class Test {
    /* 测试订单模块
    public static void main(String[] args) {
        RentalOrderInfo rentalOrder = new RentalOrder();
        rentalOrder.addMachinery(new Tractor("TR", 100, 100, 100));
        rentalOrder.generateReceipt();
    }

     */

     public static void main(String[] args) {
        System.out.println("=== PaymentManager 测试 ===");

        testValidOptions();
        testInvalidOptions();
    }

    private static void testValidOptions() {
        // 测试选项WechatPayment
        PaymentResult result = PaymentManager.processPayment(1, 100.0, 123);
        System.out.println("Test 1 (option 1): " + (result == PaymentResult.SUCCESS ? "Passed" : "Failed"));

        // 测试选项AlipayPayment
        result = PaymentManager.processPayment(2, 200.0, 456);
        System.out.println("Test 2 (option 2): " + (result == PaymentResult.SUCCESS ? "Passed" : "Failed"));

        // 测试选项BankCardPayment
        result = PaymentManager.processPayment(3, 300.0, 789);
        System.out.println("Test 3 (option 3): " + (result == PaymentResult.SUCCESS ? "Passed" : "Failed"));
    }

    private static void testInvalidOptions() {
        // 测试无效选项 0
        PaymentResult result = PaymentManager.processPayment(0, 100.0, 123);
        System.out.println("Test 4 (invalid option 0): " + (result == PaymentResult.INVALID_INPUT ? "Passed" : "Failed"));

        // 测试无效选项 4
        result = PaymentManager.processPayment(4, 200.0, 456);
        System.out.println("Test 5 (invalid option 4): " + (result == PaymentResult.INVALID_INPUT ? "Passed" : "Failed"));

        // 测试负数选项
        result = PaymentManager.processPayment(-1, 300.0, 789);
        System.out.println("Test 6 (negative option): " + (result == PaymentResult.INVALID_INPUT ? "Passed" : "Failed"));
    }
}