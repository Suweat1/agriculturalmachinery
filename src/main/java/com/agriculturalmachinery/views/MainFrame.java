package com.agriculturalmachinery.views;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainFrame extends Application {

    @Override
    public void start(Stage primaryStage) {
        MachineService service = new MachineService();
        TractorTableView tableView = new TractorTableView(service);
        OrderPanel orderPanel = new OrderPanel(service);
        ControlPanel controlPanel = new ControlPanel(tableView, orderPanel, service);

        VBox root = new VBox(10);
        root.setPrefSize(1500, 900);
        root.getChildren().addAll(tableView, controlPanel, orderPanel);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("农机系统课程设计[HENAU-BigData24-12]");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}