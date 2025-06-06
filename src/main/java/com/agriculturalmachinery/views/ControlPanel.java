package com.agriculturalmachinery.views;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert;
import java.util.Optional;

/**
 * 图形各面板控制
 * 管理各个按钮的功能
 * 继承自HBox，调用HBox中的组件
 */
public class ControlPanel extends HBox {
    public ControlPanel(TractorTableView tableView, OrderPanel orderPanel, MachineService service) {
        /*
        添加“租赁”按钮
        添加“添加”按钮
         */
        Button addBtn = new Button("添加");
        Button rentBtn = new Button("租赁");

        //设置按钮之间间隔10个像素点
        this.setSpacing(10);
        //设置元素为居中且右对齐
        this.setAlignment(Pos.CENTER_RIGHT);
        //将所有子节点添加到父容器中
        this.getChildren().addAll(addBtn, rentBtn);

        /*
        用户在选择框中选择
        * 拖拉机
        * 收割机
        用户点击其中一个
        用户再点击添加按钮
        如果选了拖拉机
        点击添加 会在表格中添加拖拉机的一行表格
         */
        addBtn.setOnAction(e -> {
            TypeMachine selectedType = tableView.getMachineTypeComboBox().getValue();
            service.addNewMachinery(selectedType);
        });

        /*
        点击 租赁按钮
            会跳出一个对话框
        输入
            租赁天数(默认为1)
        点击确定
         */
        rentBtn.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog("1");
            dialog.setTitle("租赁天数");
            dialog.setHeaderText("请输入租赁天数");
            dialog.setContentText("天数:");

            /*
            用户输入完租赁天数后
            点击确定后的逻辑处理
            先判断
                输入天数 是否小于零
                将这个天数更新到MachineService的实例中（便于MachineService计算租金）
            通过OrderPanel将订单详情信息打印出来
            通过OrderPanel计算总租金 并显示在右下角
             */
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(daysStr -> {
                try {
                    int days = Integer.parseInt(daysStr);
                    if (days <= 0) throw new NumberFormatException();
                    service.updateOrder(days);
                    orderPanel.getOrderListView().getItems().setAll(service.generateOrderItems());
                    orderPanel.getTotalCostLabel().setText(String.format("总费用: ¥%.2f元", service.calculateTotalCost()));
                } catch (NumberFormatException ex) {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("输入错误");
                    errorAlert.setHeaderText("无效的天数");
                    errorAlert.setContentText("请输入有效的正整数天数。");
                    errorAlert.showAndWait();
                }
            });
        });
    }

    //支付按钮（暂未实现相关功能）
    public Button getPayButton() {
        return (Button) getChildren().get(2);
    }
}