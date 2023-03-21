package com.example.bookaroom.gui.screens;

import com.example.bookaroom.Main;
import com.example.bookaroom.campus.Equipamento;
import com.example.bookaroom.reserva.Reserva;
import com.example.bookaroom.gui.App;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class EditarReservaScreen extends Scene {
    ComboBox<Equipamento> equipamentoComboBox;
    VBox equipamentosList;
    Reserva reserva;
    public EditarReservaScreen(Reserva reserva) {
        super(new GridPane());
        GridPane gridPane = (GridPane) this.getRoot();

        this.reserva = reserva;
        equipamentoComboBox = new ComboBox<>();
        equipamentosList = new VBox();

        ScrollPane equipamentosScrollPane = new ScrollPane();
        equipamentosScrollPane.setContent(equipamentosList);

        Button backBtn = new Button("Voltar");
        Button addEquipamentoBtn = new Button("Add");

        backBtn.setOnAction(this::backAction);
        addEquipamentoBtn.setOnAction(this::addEquipamentoAction);

        getStylesheets().add(String.valueOf(Main.class.getResource("bookaroom.css")));

        List<Equipamento> equipamentos = App.bookARoomApi().equipamentosDisponiveis(reserva.getPeriodo());

        equipamentos.forEach(equipamentoComboBox.getItems()::add);

        updateEquipamentosList();

        gridPane.setPrefWidth(480);
        gridPane.setPrefHeight(360);

        gridPane.add(backBtn, 0, 0);
        gridPane.add(equipamentosScrollPane, 0, 1, 3, 3);
        gridPane.add(equipamentoComboBox, 0, 5, 3, 1);
        gridPane.add(addEquipamentoBtn, 4, 5);
    }

    public void updateEquipamentosList() {
        equipamentosList.getChildren().clear();

        reserva.getEquipamentos()
                .forEach(e -> {
                            equipamentosList.getChildren().add(new Label(e.toString()));
                            equipamentoComboBox.getItems().remove(e);});

        if(equipamentoComboBox.getValue() == null
                && equipamentoComboBox.getItems().size() != 0)
        {
            equipamentoComboBox.setValue(equipamentoComboBox.getItems().get(0));
        }
    }

    public void backAction(ActionEvent actionEvent) {
        App.toNovaReservaScreen();
    }

    public void addEquipamentoAction(ActionEvent action) {
        Equipamento selectedEquipamento = equipamentoComboBox.getValue();
        reserva.getEquipamentos().add(selectedEquipamento);

        App.bookARoomApi().updateReserva(reserva);

        updateEquipamentosList();
    }
}
