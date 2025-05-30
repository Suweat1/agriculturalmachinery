package com.agriculturalmachinery.module;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
    /**
     * 定义租赁订单模块的基本变量
     * 定义了抽象方法
     * 部分属性需要通过Scanner输入
     */
public abstract class RentalOrderInfo {

        /**
         * 构造器
         * 给农机订单list赋值为new ArrayList<>()
         */
    public RentalOrderInfo(){
        machineryList = new ArrayList<>();
    }

    //订单编号
    private String orderId;

    //get订单编号
    public String getOrderId() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String datePart = sdf.format(now);
        orderId = "ORD" + datePart;
        return orderId;
    }

    //农机订单list
    private List<AgriculturalMachineryInfo> machineryList;

    //get农机订单
    public List<AgriculturalMachineryInfo> getMachineryList() {
        return machineryList;
    }

    //租赁总天数
    private int rentalDays;

    //get租赁天数
    public int getRentalDays() {
        return rentalDays;
    }

    //总花费
    private double totalCost;

    //get总花费
    public double getTotalCost() {
        return totalCost;
    }

    //将农机添加到订单中，输入一个AgriculturalMachinery的对象，使用add()方法添加到machineryList中
    public abstract void addMachinery(AgriculturalMachineryInfo machine);

    //遍历 machineryList，调用每个农机的 calculateRent() 方法计算订单总租金
    public abstract double calculateTotal();

    //打印最后的订单
    public abstract void generateReceipt();


}
