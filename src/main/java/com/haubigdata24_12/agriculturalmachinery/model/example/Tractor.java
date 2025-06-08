package com.haubigdata24_12.agriculturalmachinery.model.example;

import com.haubigdata24_12.agriculturalmachinery.model.AgriculturalMachineryInfo;

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
    public Tractor(String machineId, double baseRent, double horsePower, double maxPullForce) {
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
     * 返回特征值
     * 并格式化成：马力：i HP
     */
    //返回特征值的getProperValue方法的实现
    @Override
    public String getProperValue(){
        String properValue = "马力：" + this.horsePower + "HP";
        return properValue;
    }

    public String getHorsePower() {
        return String.valueOf(horsePower);
    }

    public double getHorsePowerDouble() {
        return horsePower;
    }

    public void setHorsePower(double hp) {
        this.horsePower = hp;
    }



    public String getMaxPullForce() {
        return String.valueOf(maxPullForce);
    }

    public double getMaxPullForceDouble() {
        return maxPullForce;
    }

    public void setMaxPullForce(double force) {
        this.maxPullForce = force;
    }
}
