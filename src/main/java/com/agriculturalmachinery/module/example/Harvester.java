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
        return 0;
    }

    //重写renameMachineId方法，Harvester代号是HV
    @Override
    public String renameMachineId(){
        return "HV";
    }

}
