package com.example.bookaroom.gui.screens;

import com.example.bookaroom.Main;
import com.example.bookaroom.reserva.Reserva;
import com.example.bookaroom.gui.App;
import com.example.bookaroom.gui.CadastrarReservaPane;
import com.example.bookaroom.gui.MinhasReservas;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class NovaReservaScreen extends Scene {
    CadastrarReservaPane fazerReserva;
    MinhasReservas minhasReservas;

    public NovaReservaScreen() {
        super(new GridPane());
        GridPane gridPane = (GridPane) this.getRoot();
        getStylesheets().add(String.valueOf(Main.class.getResource("bookaroom.css")));

        minhasReservas = new MinhasReservas();

        fazerReserva = new CadastrarReservaPane();
        fazerReserva.setOnReservaCreateEvent(this::onReservaCreate);

        Button loggoutBtn = new Button("Sair");

        loggoutBtn.setOnAction(this::loggoutAction);

        gridPane.setPrefWidth(800);
        gridPane.setPrefHeight(600);
        gridPane.setHgap(30);


        gridPane.add(loggoutBtn, 0, 0);
        gridPane.add(minhasReservas, 0, 1);
        gridPane.add(fazerReserva, 1, 1);
    }

    public void loggoutAction(ActionEvent actionEvent) {
        App.loggout();
    }

    public void onReservaCreate(Reserva reserva) {
        minhasReservas.addReservaCard(reserva);
    }
}
