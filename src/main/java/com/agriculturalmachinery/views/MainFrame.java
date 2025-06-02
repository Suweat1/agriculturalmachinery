package com.agriculturalmachinery.views;

import com.agriculturalmachinery.module.AgriculturalMachineryInfo;
import com.agriculturalmachinery.module.example.Tractor;
import com.agriculturalmachinery.module.example.Harvester;
import com.agriculturalmachinery.controller.rentalorder.RentalOrder;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

/**
 * UI(农机系统课程设计[HENAU-BigData24-12])
 * 两栏
 * 上栏为
 * --------------------------------
 * 租赁信息：                     添加按钮
 * 订单序号  农机型号 特征值1  特征值2  选择
 * (示例) 01  拖拉机   马力：   最大马力   ☑️
 *                               租赁
 * ---------------------------------
 * 展示面板
 * （订单信息）
 *                                支付按钮
 * ---------------------------------------
 */

public class MainFrame {
    public static class SelectableMachine {
        private final AgriculturalMachineryInfo machine;
        private BooleanProperty selected = new SimpleBooleanProperty(false);

        public SelectableMachine(AgriculturalMachineryInfo machine) {
            this.machine = machine;
        }

        public AgriculturalMachineryInfo getMachine() {
            return machine;
        }

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

    private final ObservableList<SelectableMachine> selectableMachines = FXCollections.observableArrayList(
            new SelectableMachine(new Tractor(120, 500, "TR", 100)),
            new SelectableMachine(new Harvester(3.5, 2000, "HV", 150))
    );

    private final ObservableList<OrderItem> orderItems = FXCollections.observableArrayList();
    private final AtomicInteger nextId = new AtomicInteger(1);

    private TableView<SelectableMachine> tableView;
    private ListView<OrderItem> orderListView;
    private Label totalCostLabel;
    private RentalOrder currentOrder = new RentalOrder();
    private int rentalDays = 1;

    public VBox getRoot() {
        VBox root = new VBox(10);
        root.setPrefSize(1500, 900);

        VBox upperPane = createUpperPane();
        VBox lowerPane = createLowerPane();

        root.getChildren().addAll(upperPane, lowerPane);
        VBox.setVgrow(upperPane, Priority.ALWAYS); // 上栏自动扩展
        VBox.setVgrow(lowerPane, Priority.NEVER);  // 下栏固定高度

        return root;
    }

    private VBox createUpperPane() {
        VBox upperPane = new VBox(20);

        HBox header = new HBox(new Label("租赁信息："));
        header.setAlignment(Pos.CENTER_LEFT);

        tableView = new TableView<>();
        tableView.setItems(selectableMachines);
        tableView.setEditable(true);
        setupTableColumns();

        Button addBtn = new Button("添加");
        addBtn.setOnAction(e -> addNewMachinery());

        Button rentBtn = new Button("租赁");
        rentBtn.setOnAction(e -> addToOrder());

        GridPane gridPane = new GridPane();
        gridPane.addColumn(0, tableView);
        gridPane.add(addBtn, 1, 0);
        gridPane.add(rentBtn, 1, 1);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        ColumnConstraints col0 = new ColumnConstraints();
        col0.setHgrow(Priority.ALWAYS);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setFillWidth(true);
        col1.setHgrow(Priority.NEVER);
        gridPane.getColumnConstraints().addAll(col0, col1);

        RowConstraints row0 = new RowConstraints();
        row0.setVgrow(Priority.ALWAYS);
        RowConstraints row1 = new RowConstraints();
        row1.setVgrow(Priority.NEVER);
        gridPane.getRowConstraints().addAll(row0, row1);

        GridPane.setHgrow(tableView, Priority.ALWAYS);
        GridPane.setVgrow(tableView, Priority.ALWAYS);

        upperPane.getChildren().addAll(header, gridPane);
        return upperPane;
    }

    private void setupTableColumns() {
        // 复选框列
        TableColumn<SelectableMachine, Boolean> selectCol = new TableColumn<>("选择");
        selectCol.setCellFactory(col -> new CheckBoxTableCell<>());
        selectCol.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());

        // 订单序号列
        TableColumn<SelectableMachine, String> indexCol = new TableColumn<>("订单序号");
        indexCol.setCellValueFactory(data -> {
            int index = selectableMachines.indexOf(data.getValue()) + 1;
            return new SimpleStringProperty(String.format("%02d", index));
        });

        // 农机型号列
        TableColumn<SelectableMachine, String> nameCol = new TableColumn<>("农机型号");
        nameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMachine().getMachineryName()));

        // 特征值1列
        TableColumn<SelectableMachine, String> prop1Col = new TableColumn<>("特征值1");
        prop1Col.setCellValueFactory(data -> new SimpleStringProperty(getFirstPropertyValue(data.getValue().getMachine())));
        prop1Col.setCellFactory(TextFieldTableCell.forTableColumn());
        prop1Col.setOnEditCommit(event -> {
            SelectableMachine sm = event.getRowValue();
            updateFirstPropertyValue(sm.getMachine(), event.getNewValue());
        });

        // 特征值2列
        TableColumn<SelectableMachine, String> prop2Col = new TableColumn<>("特征值2");
        prop2Col.setCellValueFactory(data -> new SimpleStringProperty(getSecondPropertyValue(data.getValue().getMachine())));
        prop2Col.setCellFactory(TextFieldTableCell.forTableColumn());
        prop2Col.setOnEditCommit(event -> {
            SelectableMachine sm = event.getRowValue();
            updateSecondPropertyValue(sm.getMachine(), event.getNewValue());
        });

        tableView.getColumns().addAll(selectCol, indexCol, nameCol, prop1Col, prop2Col);
    }

    private String getFirstPropertyValue(AgriculturalMachineryInfo machine) {
        if (machine instanceof Tractor) {
            return "马力：" + ((Tractor) machine).getHorsePower() + "HP";
        } else if (machine instanceof Harvester) {
            return "割台宽度：" + ((Harvester) machine).getCuttingWidth() + "m";
        }
        return "";
    }

    private void updateFirstPropertyValue(AgriculturalMachineryInfo machine, String value) {
        if (machine instanceof Tractor) {
            try {
                double hp = Double.parseDouble(value.replaceAll("[^\\d.]", ""));
                ((Tractor) machine).setHorsePower(hp);
            } catch (NumberFormatException ignored) {}
        } else if (machine instanceof Harvester) {
            try {
                double width = Double.parseDouble(value.replaceAll("[^\\d.]", ""));
                ((Harvester) machine).setCuttingWidth(width);
            } catch (NumberFormatException ignored) {}
        }
    }

    private String getSecondPropertyValue(AgriculturalMachineryInfo machine) {
        if (machine instanceof Tractor) {
            return "最大马力：" + ((Tractor) machine).getMaxPullForce() + "N";
        } else if (machine instanceof Harvester) {
            return "粮仓容量：" + ((Harvester) machine).getGrainTankCapacity() + "kg";
        }
        return "";
    }

    private void updateSecondPropertyValue(AgriculturalMachineryInfo machine, String value) {
        if (machine instanceof Tractor) {
            try {
                double force = Double.parseDouble(value.replaceAll("[^\\d.]", ""));
                ((Tractor) machine).setMaxPullForce(force);
            } catch (NumberFormatException ignored) {}
        } else if (machine instanceof Harvester) {
            try {
                double capacity = Double.parseDouble(value.replaceAll("[^\\d.]", ""));
                ((Harvester) machine).setGrainTankCapacity(capacity);
            } catch (NumberFormatException ignored) {}
        }
    }

    private void addNewMachinery() {
        int newIdNum = nextId.getAndIncrement();
        String newId = String.format("%03d", newIdNum);
        SelectableMachine newMachine = new SelectableMachine(new Tractor(0, 0, "TR", 0));
        selectableMachines.add(newMachine);
        tableView.refresh();
    }

    private void addToOrder() {
        List<AgriculturalMachineryInfo> selectedMachines = selectableMachines.stream()
                .filter(SelectableMachine::isSelected)
                .map(SelectableMachine::getMachine)
                .toList();

        if (selectedMachines.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("提示");
            alert.setHeaderText("未选择农机");
            alert.setContentText("请至少选择一项农机以继续。");
            alert.showAndWait();
            return;
        }

        TextInputDialog dialog = new TextInputDialog("1");
        dialog.setTitle("租赁天数");
        dialog.setHeaderText("请输入租赁天数");
        dialog.setContentText("天数:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(daysStr -> {
            try {
                rentalDays = Integer.parseInt(daysStr);
                if (rentalDays <= 0) throw new NumberFormatException();

                currentOrder = new RentalOrder();
                selectedMachines.forEach(currentOrder::addMachinery);
                currentOrder.setRentalDays(rentalDays);
                double total = currentOrder.calculateTotal();

                orderItems.clear();
                for (int i = 0; i < currentOrder.getMachineryList().size(); i++) {
                    AgriculturalMachineryInfo machine = currentOrder.getMachineryList().get(i);
                    OrderItem item = new OrderItem(
                            i + 1,
                            machine.getMachineryName(),
                            machine.getProperValue(),
                            rentalDays,
                            rentalDays * machine.calculateRent()
                    );
                    orderItems.add(item);
                }

                totalCostLabel.setText(String.format("总费用: ¥%.2f元", total));
            } catch (NumberFormatException ex) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("输入错误");
                errorAlert.setHeaderText("无效的天数");
                errorAlert.setContentText("请输入有效的正整数天数。");
                errorAlert.showAndWait();
            }
        });
    }

    private VBox createLowerPane() {
        VBox lowerPane = new VBox(10);
        Label orderHeader = new Label("订单信息");
        orderListView = new ListView<>(orderItems);
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
        totalCostLabel = new Label("总费用: ¥0.00");
        Button payButton = new Button("支付订单");
        payButton.setDisable(true);
        payButton.setOnAction(e -> generateReceipt());
        lowerPane.getChildren().addAll(orderHeader, orderListView, totalCostLabel, payButton);
        lowerPane.setAlignment(Pos.CENTER_LEFT);
        return lowerPane;
    }

    private void generateReceipt() {
        if (currentOrder.getMachineryList().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            alert.setHeaderText(null);
            alert.setContentText("当前没有订单可支付。");
            alert.showAndWait();
            return;
        }
        String receiptText = currentOrder.generateReceiptText();
        TextArea receiptArea = new TextArea(receiptText);
        receiptArea.setEditable(false);
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("订单收据");
        dialog.getDialogPane().setContent(receiptArea);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.showAndWait();
    }

    static class OrderItem {
        int index;
        String machineryName;
        String property;
        int days;
        double cost;

        public OrderItem(int index, String machineryName, String property, int days, double cost) {
            this.index = index;
            this.machineryName = machineryName;
            this.property = property;
            this.days = days;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return String.format("%d. %s（%s） × %d天 → 租金：%.2f元", index, machineryName, property, days, cost);
        }
    }
}


