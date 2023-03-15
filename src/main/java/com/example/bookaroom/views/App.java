package com.example.bookaroom.views;

import com.example.bookaroom.Main;
import com.example.bookaroom.campus.CampusControlador;
import com.example.bookaroom.campus.Sala;
import com.example.bookaroom.funcionario.SessionControlador;
import com.example.bookaroom.views.screens.LoginScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class  App extends Application {
    public static Stage stage;

    @Override
    public void start(Stage firstStage) {
        stage = firstStage;
        SessionControlador.createSession("Lucio");

        firstStage.setTitle("Book a Room");
        firstStage.setScene(new LoginScreen());
        firstStage.show();
    }

    public static void changeScene() {
        GridPane root = new GridPane();
        root.setPrefWidth(800);
        root.setPrefHeight(600);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(String.valueOf(Main.class.getResource("bookaroom.css")));

        root.setHgap(30);
        root.add(new MinhasReservas(), 0, 0);
        root.add(new FazerReserva(), 1, 0);

        stage.setScene(scene);
    }
}
