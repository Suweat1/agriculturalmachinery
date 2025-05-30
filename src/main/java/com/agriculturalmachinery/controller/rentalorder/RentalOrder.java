package com.agriculturalmachinery.controller.rentalorder;

import com.agriculturalmachinery.module.AgriculturalMachineryInfo;
import com.agriculturalmachinery.module.RentalOrderInfo;

import java.util.Iterator;

public class RentalOrder extends RentalOrderInfo {
    //构造器
    public RentalOrder() {
        super();
    }

    //重写addMachinery方法，把农机（拖拉机、收割机）输入该方法，添加到列表
    @Override
    public void addMachinery(AgriculturalMachineryInfo machine) {
        getMachineryList().add(machine);
    }

    //使用迭代器遍历列表，将列表中的各个农机的租金加在一起，就是总租金
    @Override
    public double calculateTotal() {
        double total = 0.0;
        Iterator<AgriculturalMachineryInfo> iterator = getMachineryList().iterator();
        while (iterator.hasNext()) {
            AgriculturalMachineryInfo machine = iterator.next();
            total = total + machine.calculateRent();
        }
        return total;
    }

    //用于返回一个

    //打印订单
    public void generateReceipt(){
        String orderId = getOrderId();
        int rentalDays = getRentalDays();
        double totalCost = calculateTotal();
        System.out.println("【订单编号】" + orderId);
        System.out.println("【租赁农机】");
        for()

    }
}
