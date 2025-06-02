package com.agriculturalmachinery.views;

import javafx.scene.*;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.controlsfx.control.*;




public class MainFrame extends Application {
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button("Click me!");
        btn.setOnAction(e -> System.out.println("Button clicked"));

        Scene scene = new Scene(new StackPane(btn), 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello JavaFX");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

