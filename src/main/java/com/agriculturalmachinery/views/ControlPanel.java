package com.agriculturalmachinery.views;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert;
import java.util.Optional;

public class ControlPanel extends HBox {
    public ControlPanel(TractorTableView tableView, OrderPanel orderPanel, MachineService service) {
        Button addBtn = new Button("添加");
        Button rentBtn = new Button("租赁");

        this.setSpacing(10);
        this.setAlignment(Pos.CENTER_RIGHT);
        this.getChildren().addAll(addBtn, rentBtn);

        addBtn.setOnAction(e -> {
            TypeMachine selectedType = tableView.getMachineTypeComboBox().getValue();
            service.addNewMachinery(selectedType); // ✅ 新增农机
        });

        rentBtn.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog("1");
            dialog.setTitle("租赁天数");
            dialog.setHeaderText("请输入租赁天数");
            dialog.setContentText("天数:");

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

    public Button getPayButton() {
        return (Button) getChildren().get(2);
    }
}