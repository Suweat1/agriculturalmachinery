package com.haubigdata24_12.agriculturalmachinery.views;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

/**
 * 订单支付类
 *
 */
public class OrderPanel extends VBox {
    //初始化为一个表格
    private final ListView<OrderItem> orderListView;
    //初始化一个标签 总费用
    private final Label totalCostLabel = new Label("总费用: ¥0.00");
    //MachineService的实例
    private final MachineService service;

    /*
    把订单信息初始化为一个表格
    输入 MachineService的一个实例
    把实例传入到
        表格中
     */
    public OrderPanel(MachineService service) {
        this.service = service;
        this.orderListView = new ListView<>(service.getOrderItems());
        orderListView.setCellFactory(list -> new ListCell<>() {
            @Override
            protected void updateItem(OrderItem item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.toString());
                }
            }
        });

        Button payButton = new Button("订单收据");
        payButton.setDisable(false);
        payButton.setOnAction(e -> generateReceipt());

        this.getChildren().addAll(new Label("订单信息"), orderListView, totalCostLabel, payButton);
        this.setAlignment(Pos.CENTER_LEFT);
    }

    public ListView<OrderItem> getOrderListView() {
        return orderListView;
    }

    public Label getTotalCostLabel() {
        return totalCostLabel;
    }

    //产生订单
    private void generateReceipt() {
        if (service.getOrderItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            alert.setContentText("当前没有订单可支付。");
            alert.showAndWait();
            return;
        }


        String receiptText = service.generateReceiptText();
        TextArea receiptArea = new TextArea(receiptText);
        receiptArea.setEditable(false);

        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("订单收据");
        dialog.getDialogPane().setContent(receiptArea);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.showAndWait();
    }
}