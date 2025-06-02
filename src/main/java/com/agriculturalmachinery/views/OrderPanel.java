package com.agriculturalmachinery.views;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class OrderPanel extends VBox {
    private final ListView<OrderItem> orderListView;
    private final Label totalCostLabel = new Label("总费用: ¥0.00");
    private final MachineService service;

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

        Button payButton = new Button("支付订单");
        payButton.setDisable(true);
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

    private void generateReceipt() {
        if (service.getOrderItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            alert.setHeaderText(null);
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