
package com.agriculturalmachinery.views;

import javafx.scene.*;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.controlsfx.control.*;




public class Test extends Application {
    @Override
    public void start(Stage primaryStage) {
        // 创建主界面控制器
        MainFrame mainFrame = new MainFrame();

        // 设置窗口标题和场景
        primaryStage.setTitle("农机系统课程设计[HENAU-BigData24-12]");
        primaryStage.setScene(new Scene(mainFrame.getRoot()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

