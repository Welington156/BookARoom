package com.example.bookaroom.views.widgets;

import com.example.bookaroom.reserva.Reserva;
import javafx.scene.control.TitledPane;

public class ReservaTitledPane extends TitledPane {
    public ReservaTitledPane(Reserva reserva) {
        setGraphic(new ReservaCollapsed(reserva));
        setContent(new ReservaDetalhesPane(reserva));
    }
}
