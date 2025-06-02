package com.agriculturalmachinery.views;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OrderItem {
    private final int index;
    private final StringProperty machineryName = new SimpleStringProperty();
    private final StringProperty property = new SimpleStringProperty();
    private final int days;
    private final double cost;

    public OrderItem(int index, String machineryName, String property, int days, double cost) {
        this.index = index;
        this.machineryName.set(machineryName);
        this.property.set(property);
        this.days = days;
        this.cost = cost;
    }

    public String getMachineryName() {
        return machineryName.get();
    }

    public StringProperty machineryNameProperty() {
        return machineryName;
    }

    public String getProperty() {
        return property.get();
    }

    public StringProperty propertyProperty() {
        return property;
    }

    @Override
    public String toString() {
        return String.format("%d. %s（%s） × %d天 → 租金：%.2f元", index, getMachineryName(), getProperty(), days, cost);
    }
}