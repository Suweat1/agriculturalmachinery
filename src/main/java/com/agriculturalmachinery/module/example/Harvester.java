package com.agriculturalmachinery.module.example;

import com.agriculturalmachinery.module.AgriculturalMachineryInfo;
    /**
     * 定义了一些变量
     * 继承了AgriculturalMachineryInfo，并且重写其中的calculateRent方法，进行计算租金
     * 收割机的构造方法
     */
public class Harvester extends AgriculturalMachineryInfo {

    //割台高度
    double cuttingWidth;

    //粮仓容量
    double grainTankCapacity;

    public Harvester(double cuttingWidth, double grainTankCapacity, String machineId, double baseRent) {
        super(machineId, baseRent);
        this.cuttingWidth = cuttingWidth;
        this.grainTankCapacity = grainTankCapacity;
    }

    //calculate方法的重写与实现，租金计算方式：基础租金 + 割台宽度 × 10
    @Override
    public double calculateRent(){
        return baseRent + cuttingWidth * 10;
    }

    //getMachineryName的方法实现
     /**
      * 输出收割机
       */
     @Override
     public String getMachineryName() {
         return "收割机";
     }



     /**
      * 开始
      * 返回特征值
      * 并格式化成：割台宽度：i m
      */
     //返回特征值的getProperValue方法的实现
     @Override
     public String getProperValue(){
         String properValue = "割台宽度：" + this.cuttingWidth + "m";
         return properValue;
     }


}
