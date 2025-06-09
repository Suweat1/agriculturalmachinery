package com.haubigdata24_12.agriculturalmachinery.views;

import com.haubigdata24_12.agriculturalmachinery.controller.payment.PaymentManager;
import com.haubigdata24_12.agriculturalmachinery.controller.payment.PaymentResult;
import com.haubigdata24_12.agriculturalmachinery.controller.rentalorder.RentalOrder;
import com.haubigdata24_12.agriculturalmachinery.model.AgriculturalMachineryInfo;
import com.haubigdata24_12.agriculturalmachinery.model.example.Harvester;
import com.haubigdata24_12.agriculturalmachinery.model.example.Tractor;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * MachineService类
 * 用于实现后端的逻辑处理与计算 并把数据传入到前端图形化界面中
 */
public class MachineService {
    /*
    初始化表格中的前两行数据
    马力 100 最大马力 200
    割台宽度 100 粮仓容量 200
     */                                                                    //提供了observableArrayList的监听器属性
    private final ObservableList<SelectableMachine> selectableMachines = FXCollections.observableArrayList(
            new SelectableMachine(new Tractor("TR", 100,  50 ,200)),
            new SelectableMachine(new Harvester("HV", 150, 100, 200))
    );
    //定义一个变量orderItems变量 list类型 用于存储所有数据
    private final ObservableList<OrderItem> orderItems = FXCollections.observableArrayList();
    //定义租赁天数
    private final IntegerProperty rentalDays = new SimpleIntegerProperty(1);
    //定义当前订单
    private final RentalOrder currentOrder = new RentalOrder();

    //返回被选择的列表
    public ObservableList<SelectableMachine> getSelectableMachines() {
        return selectableMachines;
    }

    //返回作为订单的项目
    public ObservableList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public IntegerProperty rentalDaysProperty() {
        return rentalDays;
    }

    /*
    添加按钮的逻辑处理
    选择 拖拉机
        初始化 拖拉机中的数据
        传入表格中
    把拖拉机添加为
        可被选择的元素
    后面计算租金使用的变量为可被选择的元素
     */
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

    /*
    更新订单数据功能
    输入
        租赁天数
    操作所有 被选择的表格
    清空 currentOrder中的所有数据
    添加 被选择的表格的数据
        到
    currentOrder中
    把输入的 租赁天数
        传入
    currentOrder中
     */
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
        rentalDays.set(days);
    }

    /*
    遍历currentOrder中的所有元素
        把 元素索引都 +1
        把 农机名称
           特征值
           租赁天数
        都传入 局部变量 items
        计算农机的每日租金 * 租赁天数
     返回 items
     */
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

    //计算支付所需的所有费用
    public double calculateTotalCost() {
        return currentOrder.calculateTotal();
    }

    /*
    把currentOrder中的订单详情
        作为字符串
     返回
     */
    public String generateReceiptText() {
        return currentOrder.generateReceiptText();
    }

    /*
    清空订单
    把租赁订单的租赁天数设置为1
     */
    public void clearOrder() {
        currentOrder.getMachineryList().clear();
        rentalDays.set(1);
    }

    /*
    根据输入的option和credential
        option决定哪个支付方式
        credential是输入的账号
     */
    public PaymentResult payOrder(int option, int credential) {
        if (currentOrder.getMachineryList().isEmpty()) {
            return PaymentResult.NO_ORDER;
        }

        double totalCost = calculateTotalCost();
        PaymentResult result = PaymentManager.processPayment(option, totalCost, credential);

        if (result == PaymentResult.SUCCESS) {
            clearOrder(); // 清空订单
            return PaymentResult.SUCCESS;
        } else {
            return result;
        }
    }

}