package com.haubigdata24_12.agriculturalmachinery.views;

import com.haubigdata24_12.agriculturalmachinery.model.AgriculturalMachineryInfo;
import com.haubigdata24_12.agriculturalmachinery.model.example.Harvester;
import com.haubigdata24_12.agriculturalmachinery.model.example.Tractor;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SelectableMachine {

    //实例化AgriculturalMachineryInfo，方便后续方法调用
    private AgriculturalMachineryInfo machine;
    //定义是否被选择的bool对象，可被监听
    private final BooleanProperty selected = new SimpleBooleanProperty(false);

    //StringProperty 字段，用于绑定表格列
    private final StringProperty firstProperty = new SimpleStringProperty();
    private final StringProperty secondProperty = new SimpleStringProperty();

    public SelectableMachine(AgriculturalMachineryInfo machine) {
        this.machine = machine;
        updateProperties(); // 初始化特征值
    }

    // 获取当前农机实例
    public AgriculturalMachineryInfo getMachine() {
        return machine;
    }

    // 获取特征值1（动态绑定）
    public String getFirstProperty() {
        return firstProperty.get();
    }

    // 提供绑定属性
    public StringProperty firstPropertyProperty() {
        return firstProperty;
    }

    // 获取特征值2（动态绑定）
    public String getSecondProperty() {
        return secondProperty.get();
    }

    // 提供绑定属性
    public StringProperty secondPropertyProperty() {
        return secondProperty;
    }

    // 当农机类型变化时更新特征值
    public void setMachineType(TypeMachine type) {
        switch (type) {
            case TRACTOR:
                machine = new Tractor("TR", 0, 0, 0); // 根据实际构造函数调整
                break;
            case HARVESTER:
                machine = new Harvester("HV", 0, 0, 0); // 根据实际构造函数调整
                break;
            default:
                machine = new Tractor("TR", 0, 0, 0);
        }
        updateProperties(); // 更新绑定属性
    }

    // 更新特征值绑定属性
    private void updateProperties() {
        if (machine instanceof Tractor) {
            firstProperty.set("马力：" + ((Tractor) machine).getHorsePower() + "HP");
            secondProperty.set("最大马力：" + ((Tractor) machine).getMaxPullForce() + "N");
        } else if (machine instanceof Harvester) {
            firstProperty.set("割台宽度：" + ((Harvester) machine).getCuttingWidth() + "m");
            secondProperty.set("粮仓容量：" + ((Harvester) machine).getGrainTankCapacity() + "kg");
        } else {
            firstProperty.set("");
            secondProperty.set("");
        }
    }

    // 更新特征值1（马力 / 割台宽度）
    public void updateFirstProperty(String value) {
        try {
            double parsedValue = Double.parseDouble(value.replaceAll("[^\\d.]", ""));
            if (machine instanceof Tractor) {
                ((Tractor) machine).setHorsePower(parsedValue);
            } else if (machine instanceof Harvester) {
                ((Harvester) machine).setCuttingWidth(parsedValue);
            }
            updateProperties(); // 更新绑定属性
        } catch (NumberFormatException ignored) {}
    }

    // 更新特征值2（最大马力 / 粮仓容量）
    public void updateSecondProperty(String value) {
        try {
            double parsedValue = Double.parseDouble(value.replaceAll("[^\\d.]", ""));
            if (machine instanceof Tractor) {
                ((Tractor) machine).setMaxPullForce(parsedValue);
            } else if (machine instanceof Harvester) {
                ((Harvester) machine).setGrainTankCapacity(parsedValue);
            }
            updateProperties(); // 更新绑定属性
        } catch (NumberFormatException ignored) {}
    }

    // 获取选中状态
    public BooleanProperty selectedProperty() {
        return selected;
    }

    public boolean isSelected() {
        return selected.get();
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }
}
