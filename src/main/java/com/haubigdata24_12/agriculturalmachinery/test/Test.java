package com.haubigdata24_12.agriculturalmachinery.test;

import com.haubigdata24_12.agriculturalmachinery.controller.rentalorder.RentalOrder;
import com.haubigdata24_12.agriculturalmachinery.model.RentalOrderInfo;
import com.haubigdata24_12.agriculturalmachinery.model.example.Tractor;

public class Test {
    public static void main(String[] args) {
        RentalOrderInfo rentalOrder = new RentalOrder();
        rentalOrder.addMachinery(new Tractor("TR", 100, 100, 100));
        rentalOrder.generateReceipt();
    }
}