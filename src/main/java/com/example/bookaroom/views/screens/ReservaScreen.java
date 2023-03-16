package com.example.bookaroom.views.screens;

import com.example.bookaroom.Main;
import com.example.bookaroom.reserva.Reserva;
import com.example.bookaroom.views.FazerReserva;
import com.example.bookaroom.views.MinhasReservas;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class ReservaScreen extends Scene {

    FazerReserva fazerReserva;
    MinhasReservas minhasReservas;

    public ReservaScreen() {
        super(new GridPane());
        GridPane gridPane = (GridPane) this.getRoot();
        getStylesheets().add(String.valueOf(Main.class.getResource("bookaroom.css")));

        minhasReservas = new MinhasReservas();

        fazerReserva = new FazerReserva();
        fazerReserva.setOnReservaCreateEvent(this::onReservaCreate);

        gridPane.setPrefWidth(800);
        gridPane.setPrefHeight(600);
        gridPane.setHgap(30);

        gridPane.add(minhasReservas, 0, 0);
        gridPane.add(fazerReserva, 1, 0);
    }

    public void onReservaCreate(Reserva reserva) {
        minhasReservas.addReservaCard(reserva);
    }
}
