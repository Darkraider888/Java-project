package com.devhelper.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main.fxml")));
        stage.setScene(new Scene(root, 700, 500));
        stage.setTitle("Developer Helper Desktop App");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}