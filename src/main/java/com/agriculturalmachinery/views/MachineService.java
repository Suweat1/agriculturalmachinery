package com.agriculturalmachinery.views;

import com.agriculturalmachinery.controller.rentalorder.RentalOrder;
import com.agriculturalmachinery.module.AgriculturalMachineryInfo;
import com.agriculturalmachinery.module.example.Harvester;
import com.agriculturalmachinery.module.example.Tractor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import java.util.ArrayList;
import java.util.List;

public class MachineService {
    private final ObservableList<SelectableMachine> selectableMachines = FXCollections.observableArrayList(
            new SelectableMachine(new Tractor("TR", 100,  50 ,200)),
            new SelectableMachine(new Harvester("HV", 150, 100, 200))
    );
    private final ObservableList<OrderItem> orderItems = FXCollections.observableArrayList();
    private final IntegerProperty rentalDays = new SimpleIntegerProperty(1);
    private final RentalOrder currentOrder = new RentalOrder();

    public ObservableList<SelectableMachine> getSelectableMachines() {
        return selectableMachines;
    }

    public ObservableList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public IntegerProperty rentalDaysProperty() {
        return rentalDays;
    }

    public void addNewMachinery(TypeMachine type) {
        AgriculturalMachineryInfo newMachine;
        switch (type) {
            case TRACTOR:
                newMachine = new Tractor("TR", 0, 0, 0);
                break;
            case HARVESTER:
                newMachine = new Harvester("HV", 0, 0, 0);
                break;
            default:
                newMachine = new Tractor("TR", 0, 0, 0);
        }
        selectableMachines.add(new SelectableMachine(newMachine));
    }

    public void updateOrder(int days) {
        List<AgriculturalMachineryInfo> selectedMachines = new ArrayList<>();
        for (SelectableMachine sm : selectableMachines) {
            if (sm.isSelected()) {
                selectedMachines.add(sm.getMachine());
            }
        }

        currentOrder.getMachineryList().clear();
        selectedMachines.forEach(currentOrder::addMachinery);
        currentOrder.setRentalDays(days);
    }

    public List<OrderItem> generateOrderItems() {
        List<OrderItem> items = new ArrayList<>();
        for (int i = 0; i < currentOrder.getMachineryList().size(); i++) {
            AgriculturalMachineryInfo machine = currentOrder.getMachineryList().get(i);
            items.add(new OrderItem(
                    i + 1,
                    machine.getMachineryName(),
                    machine.getProperValue(),
                    rentalDays.get(),
                    rentalDays.get() * machine.calculateRent()
            ));
        }
        return items;
    }

    public double calculateTotalCost() {
        return currentOrder.calculateTotal();
    }

    public String generateReceiptText() {
        return currentOrder.generateReceiptText();
    }
}