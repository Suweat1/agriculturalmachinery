package com.agriculturalmachinery.module.example;

import com.agriculturalmachinery.module.AgriculturalMachineryInfo;

public class Harvester  extends AgriculturalMachineryInfo {

    //割台高度
    double cuttingWidth;

    //粮仓容量
    double grainTankCapacity;

    public Harvester(double cuttingWidth, double grainTankCapacity) {
        super();
        this.cuttingWidth = cuttingWidth;
        this.grainTankCapacity = grainTankCapacity;
    }

    //calculate方法的重写与实现，租金计算方式：基础租金 + 割台宽度 × 10
    @Override
    public double calculateRent(){

    }
}
