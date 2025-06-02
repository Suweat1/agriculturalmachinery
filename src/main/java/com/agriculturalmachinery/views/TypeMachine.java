package com.agriculturalmachinery.views;

public enum TypeMachine {
    TRACTOR("拖拉机"), HARVESTER("收割机");

    private final String displayName;

    TypeMachine(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static TypeMachine fromDisplayName(String name) {
        for (TypeMachine type : values()) {
            if (type.getDisplayName().equals(name)) return type;
        }
        return TRACTOR;
    }
}