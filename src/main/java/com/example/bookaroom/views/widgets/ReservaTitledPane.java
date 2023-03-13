package com.example.bookaroom.views.widgets;

import com.example.bookaroom.reserva.Reserva;
import javafx.scene.control.TitledPane;
import java.time.format.DateTimeFormatter;

public class ReservaTitledPane extends TitledPane {
    public ReservaTitledPane(Reserva reserva) {
        String data = reserva.getDataAlocacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                horaInicio = reserva.getHoraInicio().format(DateTimeFormatter.ofPattern("HH:mm"));

        setText(data + " - " + horaInicio);
        setContent(new ReservaDetalhesPane(reserva));
    }
}
