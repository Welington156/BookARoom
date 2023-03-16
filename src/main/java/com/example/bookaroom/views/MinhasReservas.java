package com.example.bookaroom.views;

import com.example.bookaroom.funcionario.Funcionario;
import com.example.bookaroom.funcionario.SessionControlador;
import com.example.bookaroom.reserva.Reserva;
import com.example.bookaroom.reserva.ReservaControlador;
import com.example.bookaroom.views.widgets.ReservaTitledPane;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class MinhasReservas extends VBox {
    private final Accordion reservasAccordion;

    {
        reservasAccordion = new Accordion();
        reservasAccordion.setPrefWidth(200);

        Label title = new Label("Minhas Reservas");
        title.getStyleClass().add("property-name");

        Funcionario sessionUser = SessionControlador.getFuncionario();

        ReservaControlador.reservasFuncionario(sessionUser).forEach(this::addReservaCard);

        getChildren().addAll(
                title,
                reservasAccordion
        );
    }

    public void addReservaCard(Reserva reserva) {
        reservasAccordion.getPanes().add(new ReservaTitledPane(reserva));
    }
}
