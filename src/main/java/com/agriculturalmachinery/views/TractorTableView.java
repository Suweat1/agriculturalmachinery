package com.agriculturalmachinery.views;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import javafx.geometry.Pos;
import java.util.Optional;

public class TractorTableView extends VBox {
    private final TableView<SelectableMachine> tableView;
    private final MachineService service;
    private ComboBox<TypeMachine> machineTypeComboBox = new ComboBox<>();

    public ComboBox<TypeMachine> getMachineTypeComboBox() {
        return machineTypeComboBox;
    }

    public TractorTableView(MachineService service) {
        this.service = service;
        this.tableView = new TableView<>();
        tableView.setItems(service.getSelectableMachines()); // ✅ 直接绑定服务层数据
        tableView.setEditable(true);
        setupTableColumns();
        setupLayout();
    }

    public TableView<SelectableMachine> getTableView() {
        return tableView;
    }

    private void setupTableColumns() {
        // 复选框列
        TableColumn<SelectableMachine, Boolean> selectCol = new TableColumn<>("选择");
        selectCol.setCellFactory(col -> new CheckBoxTableCell<>());
        selectCol.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());
        tableView.getColumns().add(selectCol);

        // 订单序号列
        TableColumn<SelectableMachine, String> indexCol = new TableColumn<>("订单序号");
        indexCol.setCellValueFactory(data -> new SimpleStringProperty(
                String.format("%02d", service.getSelectableMachines().indexOf(data.getValue()) + 1)
        ));
        tableView.getColumns().add(indexCol);

        // 特征值1列
        TableColumn<SelectableMachine, String> prop1Col = new TableColumn<>("特征值1");
        prop1Col.setCellValueFactory(data -> data.getValue().firstPropertyProperty());
        prop1Col.setCellFactory(TextFieldTableCell.forTableColumn());
        prop1Col.setOnEditCommit(event -> event.getRowValue().updateFirstProperty(event.getNewValue()));
        tableView.getColumns().add(prop1Col);

        // 特征值2列
        TableColumn<SelectableMachine, String> prop2Col = new TableColumn<>("特征值2");
        prop2Col.setCellValueFactory(data -> data.getValue().secondPropertyProperty());
        prop2Col.setCellFactory(TextFieldTableCell.forTableColumn());
        prop2Col.setOnEditCommit(event -> event.getRowValue().updateSecondProperty(event.getNewValue()));
        tableView.getColumns().add(prop2Col);
    }

    private void setupLayout() {
        // 类型选择框
        machineTypeComboBox.getItems().addAll(TypeMachine.values());
        machineTypeComboBox.setValue(TypeMachine.TRACTOR);
        machineTypeComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(TypeMachine type) {
                return type != null ? type.getDisplayName() : "";
            }

            @Override
            public TypeMachine fromString(String string) {
                return TypeMachine.fromDisplayName(string);
            }
        });

        // 表头和操作栏
        HBox header = new HBox(new Label("租赁信息："));
        header.setAlignment(Pos.CENTER_LEFT);

        HBox operationBox = new HBox(10, new Label("选择农机类型："), machineTypeComboBox);
        operationBox.setAlignment(Pos.CENTER_LEFT);

        GridPane gridPane = new GridPane();
        gridPane.addColumn(0, tableView);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // 设置列约束：表格列自动扩展
        ColumnConstraints col0 = new ColumnConstraints();
        col0.setHgrow(Priority.ALWAYS);
        gridPane.getColumnConstraints().add(col0);

        // 设置行约束：表格自动扩展
        RowConstraints row0 = new RowConstraints();
        row0.setVgrow(Priority.ALWAYS);
        RowConstraints row1 = new RowConstraints();
        row1.setVgrow(Priority.NEVER);
        gridPane.getRowConstraints().addAll(row0, row1);

        this.getChildren().addAll(header, operationBox, gridPane);
    }
}