package com.example.bookaroom.views.widgets;

import com.example.bookaroom.reserva.Reserva;
import javafx.scene.layout.VBox;

import java.time.format.DateTimeFormatter;

public class ReservaCollapsed extends VBox {
    public ReservaCollapsed(Reserva reserva) {
        String data = reserva.getDataAlocacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                horaInicio = reserva.getHoraInicio().format(DateTimeFormatter.ofPattern("HH:mm"));
        getStyleClass().add("reserva-collapsed");
        getChildren().addAll(
                new PropriedadeItemLabel("Sala", reserva.getSala().getNumero()),
                new PropriedadeItemLabel("Data", data + " - " + horaInicio)
        );
    }
}
