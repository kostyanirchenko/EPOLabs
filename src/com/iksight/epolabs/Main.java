package com.iksight.epolabs;/**
 * Created by DMITRY on 07.03.2017.
 */

import com.iksight.epolabs.views.lab1.Lab1Controller;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {

    @FXML
    private Button btnLab1;
    @FXML
    private Button btnLab2;
    @FXML
    private Button btnLab3;
    @FXML
    private Button btnLab4;
    @FXML
    private Button btnLab5;
    @FXML
    private Button btnLab6;
    @FXML
    private BorderPane rootLayout;

    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Лабораторные работы по Экономике ПО");
        this.primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("images/icon.png")));

        initRootLayout();

        showDevInfo();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("MainForm.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
          e.printStackTrace();
        }
    }

    public void handleButtons(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            switch (((Button) event.getSource()).getId()) {
                case "btnLab1":
                    loader.setLocation(Main.class.getResource("views/lab1/Lab1.fxml"));
                    AnchorPane lab1 = (AnchorPane) loader.load();
                    rootLayout.setCenter(lab1);
                    break;
                case "btnLab2":
                    showDevInfo();
                    break;
                case "btnLab3":

                    break;
                case "btnLab4":

                    break;
                case "btnLab5":

                    break;
                case "btnLab6":

                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDevInfo() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/devinfo/DevInfo.fxml"));
            AnchorPane devInfo = (AnchorPane) loader.load();

            rootLayout.setCenter(devInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actionButtonClick() {

    }
}
