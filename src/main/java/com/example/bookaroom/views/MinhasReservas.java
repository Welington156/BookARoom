package com.example.bookaroom.views;

import com.example.bookaroom.funcionario.Funcionario;
import com.example.bookaroom.funcionario.SessionControlador;
import com.example.bookaroom.reserva.ReservaControlador;
import com.example.bookaroom.views.widgets.ReservaTitledPane;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MinhasReservas extends VBox {
    private final Accordion reservasAccordion;

    {
        reservasAccordion = new Accordion();
        reservasAccordion.setPrefHeight(400);
        reservasAccordion.setPrefWidth(400);

        Funcionario sessionUser = SessionControlador.getFuncionario();

        ReservaControlador.reservasFuncionario(sessionUser).forEach(
                reserva -> reservasAccordion.getPanes().add(new ReservaTitledPane(reserva))
        );

        getChildren().addAll(
                new Label("Minhas Reservas"),
                reservasAccordion
        );
    }
}
