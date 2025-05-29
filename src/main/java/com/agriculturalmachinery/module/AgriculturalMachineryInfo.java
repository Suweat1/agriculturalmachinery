package com.agriculturalmachinery.module;

public abstract class AgriculturalMachineryInfo {
    private int machineId;
    protected double baseRent;
    /*
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

