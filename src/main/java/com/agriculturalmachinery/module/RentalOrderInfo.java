package com.agriculturalmachinery.module;

import java.util.List;
    /*
     * 定义租赁订单模块的基本变量
     * 定义了抽象方法
     */
public abstract class RentalOrderInfo {

    //订单编号
    private int orderId;

    //农机订单list
    private List<AgriculturalMachineryInfo> machineryList;

    //租赁总天数
    private int rentalDays;

    //总花费
    private double totalCost;

    //将农机添加到订单中，输入一个AgriculturalMachinery的对象，使用add()方法添加到machineryList中
    public abstract void addMachinery(AgriculturalMachineryInfo machine);

    //遍历 machineryList，调用每个农机的 calculateRent() 方法计算订单总租金
    public abstract double calculateTotal();

    //打印最后的订单
    public abstract void generateReceipt();


}
