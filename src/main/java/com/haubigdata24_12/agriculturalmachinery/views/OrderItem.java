package com.haubigdata24_12.agriculturalmachinery.views;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OrderItem {
    //定义索引
    private final int index;
    //定义农机名
    private final StringProperty machineryName = new SimpleStringProperty();
    //定义特征值
    private final StringProperty property = new SimpleStringProperty();
    //定义租赁天数
    private final int days;
    //定义花费
    private final double cost;

    //构造器
    public OrderItem(int index, String machineryName, String property, int days, double cost) {
        this.index = index;
        this.machineryName.set(machineryName);
        this.property.set(property);
        this.days = days;
        this.cost = cost;
    }

    //get农机名
    public String getMachineryName() {
        return machineryName.get();
    }

    public StringProperty machineryNameProperty() {
        return machineryName;
    }

    //get特征值
    public String getProperty() {
        return property.get();
    }

    public StringProperty propertyProperty() {
        return property;
    }

    //格式化字符串
    @Override
    public String toString() {
        return String.format("%d. %s（%s） × %d天 → 租金：%.2f元", index, getMachineryName(), getProperty(), days, cost);
    }
}
