package com.example.bookaroom.views.screens;

import com.example.bookaroom.funcionario.SessionControlador;
import com.example.bookaroom.views.App;
import com.example.bookaroom.views.widgets.MessageLabel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class LoginScreen extends Scene {
    GridPane gridPane;
    TextField nameField;
    ComboBox<String> campusComboBox;
    MessageLabel errorMessage = new MessageLabel("", Color.RED);

    public LoginScreen() {
        super(new GridPane());

        gridPane = (GridPane) this.getRoot();
        gridPane.setPadding(new Insets(100, 0, 0, 0));
        gridPane.setAlignment(Pos.BASELINE_CENTER);
        gridPane.setPrefWidth(480);
        gridPane.setPrefHeight(360);

        Label nameLabel = new Label("Name:");
        nameField = new TextField();


        Label campusLabel = new Label("Campus:");
        campusComboBox = new ComboBox<>();
        campusComboBox.getItems().add("MOCHELL");
        campusComboBox.setValue("MOCHELL");
        campusComboBox.setDisable(true);

        Button loginButton = new Button("Entrar");
        loginButton.setOnAction(e -> login());

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(campusLabel, 0, 0);
        gridPane.add(campusComboBox, 1, 0, 2, 1);
        gridPane.add(nameLabel, 0, 1);
        gridPane.add(nameField, 1, 1, 2, 1);
        gridPane.add(loginButton, 2, 2);
        gridPane.add(errorMessage, 1, 3, 3, 1);
    }

    private void login() {
        String name = nameField.getText();
        SessionControlador.createSession(name);

        if (SessionControlador.getFuncionario() != null) {
            App.changeScene();
        } else {
            errorMessage.setText("*Funcionario não encontrado");
        }
    }
}
