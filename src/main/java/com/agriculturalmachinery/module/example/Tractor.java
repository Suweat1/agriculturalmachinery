package com.agriculturalmachinery.module.example;

import com.agriculturalmachinery.module.AgriculturalMachineryInfo;

public class Tractor extends AgriculturalMachineryInfo {
    //马力
    double horsePower;

    //最大牵引力
    double maxPullForce;

    //构造方法
    public Tractor(double horsePower, double maxPullForce) {
        super();
        this.horsePower = horsePower;
        this.maxPullForce = maxPullForce;
    }

    //calculate方法的实现，基础租金 + 马力 × 0.5
    @Override
    public double calculateRent(){

    }

}
