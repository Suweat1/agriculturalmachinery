package com.agriculturalmachinery.module.example;

import com.agriculturalmachinery.module.AgriculturalMachineryInfo;
 /**
     * 定义了一些变量
     * 继承了AgriculturalMachineryInfo，并且重写其中的calculateRent方法，进行计算租金
     * 拖拉机的构造方法
     */
public class Tractor extends AgriculturalMachineryInfo {
    //马力
    double horsePower;

    //最大牵引力
    double maxPullForce;

    //构造方法
    public Tractor(double horsePower, double maxPullForce, String machineId, double baseRent) {
        super(machineId, baseRent);
        this.horsePower = horsePower;
        this.maxPullForce = maxPullForce;
    }

    //calculate方法的实现，基础租金 + 马力 × 0.5
    @Override
    public double calculateRent(){
        return baseRent + horsePower * 0.5;
    }

    //getMachineryName的方法实现
     /**
      * 输出拖拉机
       */
     @Override
     public String getMachineryName() {
         return "拖拉机";
     }

    /**
     * 开始
     * 把农机编号格式化成TR001 HV002这样的String格式
     * 此类定义抽象方法
     */
}
