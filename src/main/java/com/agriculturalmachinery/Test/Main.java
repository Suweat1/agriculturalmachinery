package com.agriculturalmachinery.Test;

import com.agriculturalmachinery.controller.rentalorder.RentalOrder;
import com.agriculturalmachinery.module.RentalOrderInfo;
import com.agriculturalmachinery.module.example.Tractor;

public class Main {
    public static void main(String[] args) {
        RentalOrderInfo rentalOrder = new RentalOrder();
        rentalOrder.addMachinery(new Tractor("TR", 100, 100, 100));
        rentalOrder.generateReceipt();
    }
}
