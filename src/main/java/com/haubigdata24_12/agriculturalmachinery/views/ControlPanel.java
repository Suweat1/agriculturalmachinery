package com.haubigdata24_12.agriculturalmachinery.views;

import com.haubigdata24_12.agriculturalmachinery.controller.payment.PaymentResult;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Optional;

/**
 * 图形各面板控制
 * 管理各个按钮的功能
 * 继承自HBox，调用HBox中的组件
 */
public class ControlPanel extends HBox {

    private final OrderPanel orderPanel;
    private final MachineService service;
    public ControlPanel(MachineTableView tableView, OrderPanel orderPanel, MachineService service) {
        this.orderPanel = orderPanel; // ✅ 初始化引用
        this.service = service;

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


        /*
        用户点击支付
            选择支付方式
            * 支付宝
            * 微信支付
            * 银行卡支付
        如果选择了 支付宝
            就输入支付宝账号
        然后完成支付
         */
        Button payBtn = new Button("支付");
        this.getChildren().add(payBtn);

        payBtn.setOnAction(e -> {
            if (service.getOrderItems().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "请先创建订单");
                alert.showAndWait();
                return;
            }

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("选择支付方式");
            VBox content = new VBox(10);
            ToggleGroup group = new ToggleGroup();

            RadioButton wechat = new RadioButton("微信支付");
            RadioButton alipay = new RadioButton("支付宝支付");
            RadioButton bankcard = new RadioButton("银行卡支付");
            group.getToggles().addAll(wechat, alipay, bankcard);
            content.getChildren().addAll(wechat, alipay, bankcard);

            dialog.getDialogPane().setContent(content);
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

            Optional<ButtonType> Result = dialog.showAndWait();
            if (Result.orElse(null) == ButtonType.OK) {
                int option;
                if (wechat.isSelected()) option = 1;
                else if (alipay.isSelected()) option = 2;
                else if (bankcard.isSelected()) option = 3;
                else {
                    option = 0;
                }

                if (option < 1 || option > 3) return;

                TextInputDialog inputDialog = new TextInputDialog();

                //分情况展示对话框
                switch (option) {
                    case 1:
                        inputDialog.setHeaderText("请输入微信OpenID");
                        inputDialog.setTitle("正在使用微信支付");
                        inputDialog.showAndWait().ifPresent(openId -> {
                            PaymentResult paymentResult = service.payOrder(option, Integer.parseInt(openId));
                            handlePaymentResult(paymentResult);
                        });
                        break;

                    case 2:
                        inputDialog.setHeaderText("请输入支付宝账号");
                        inputDialog.setTitle("正在使用支付宝支付");
                        inputDialog.showAndWait().ifPresent(account -> {
                            PaymentResult paymentResult = service.payOrder(option, Integer.parseInt(account));
                            handlePaymentResult(paymentResult);
                        });
                        break;

                    case 3:
                        inputDialog.setHeaderText("请输入银行卡号");
                        inputDialog.setTitle("正在使用银行卡支付");
                        inputDialog.showAndWait().ifPresent(card -> {
                            PaymentResult paymentResult = service.payOrder(option, Integer.parseInt(card));
                            handlePaymentResult(paymentResult);
                        });
                        break;
                }
            }
        });

    }

    /*
    根据PaymentResult枚举类
        分情况展示支付状况
    处理支付问题
     */
    private void handlePaymentResult(PaymentResult result) {
        switch (result) {
            case SUCCESS:
                Alert success = new Alert(Alert.AlertType.INFORMATION, "支付成功！");
                success.showAndWait();
                orderPanel.getOrderListView().getItems().clear();
                orderPanel.getTotalCostLabel().setText("总费用: ¥0.00");
                break;
            case FAILURE:
                Alert fail = new Alert(Alert.AlertType.ERROR, "支付失败，请重试");
                fail.showAndWait();
                break;
            case INVALID_INPUT:
                Alert invalid = new Alert(Alert.AlertType.WARNING, "输入信息无效");
                invalid.showAndWait();
                break;
            case NO_ORDER:
                Alert noOrder = new Alert(Alert.AlertType.WARNING, "当前没有订单");
                noOrder.showAndWait();
                break;
        }
    }

}
