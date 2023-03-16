package com.example.bookaroom.views.screens;

import com.example.bookaroom.Main;
import com.example.bookaroom.funcionario.SessionControlador;
import com.example.bookaroom.views.App;
import com.example.bookaroom.views.widgets.MessageLabel;
import com.example.bookaroom.views.widgets.PropriedadeItemLabel;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
        getStylesheets().add(String.valueOf(Main.class.getResource("bookaroom.css")));

        gridPane.setPadding(new Insets(100, 0, 0, 0));
        gridPane.setAlignment(Pos.BASELINE_CENTER);
        gridPane.setPrefWidth(480);
        gridPane.setPrefHeight(360);

        nameField = new TextField();

        campusComboBox = new ComboBox<>();
        campusComboBox.getItems().add("MOCHELL");
        campusComboBox.setValue("MOCHELL");
        campusComboBox.setDisable(true);

        Button loginButton = new Button("Entrar");
        loginButton.setOnAction(this::entrarAction);

        gridPane.setVgap(10);
        gridPane.add(new PropriedadeItemLabel("Campus", campusComboBox), 0, 0);
        gridPane.add(new PropriedadeItemLabel("Nome", nameField), 0, 1);
        gridPane.add(errorMessage, 0, 2);
        gridPane.add(loginButton, 0, 3);
    }

    private void entrarAction(ActionEvent e) {
        String name = nameField.getText();
        SessionControlador.createSession(name);

        if (SessionControlador.getFuncionario() != null) {
            App.loadReservaScreen();
        } else {
            errorMessage.setText("Funcionario não encontrado");
        }
    }
}
