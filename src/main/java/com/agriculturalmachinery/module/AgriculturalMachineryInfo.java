package com.agriculturalmachinery.module;
    /**
     * 定义了农机的信息类，并且抽象
     * 并且使用枚举类定义了一些状态量
     */
public abstract class AgriculturalMachineryInfo {

    //农机编号
    private String machineId;

    //把农机编号get出来
    public String getMachineId() {
        return machineId;
    }

    //设置set农机编号
    public void setMachineId(String machineId) {
         this.machineId = machineId;
    }

    //在不同类中有不同的名字，在拖拉机中是TR，在收割机是HV
    public abstract String renameMachineId();


    //基础租金
    protected double baseRent;


    public AgriculturalMachineryInfo(String machineId, double baseRent) {
        this.machineId = machineId;
        this.baseRent = baseRent;
    }

    /**
     * 定义了Status枚举类，有IDLE闲置，IN_USE使用中，UNDER_MAINTENANCE维护中
     */

    public enum Status{
        IDLE("闲置"),IN_USE("使用中"),UNDER_MAINTENANCE("维护中");
        private final String status;
        Status(String status){
            this.status = status;
        }
        public String getStatus(){
            return status;
        }
    }

    //抽象方法 calculateRent()：用于计算租金，具体计费逻辑由子类实现
    public abstract double calculateRent();

}

