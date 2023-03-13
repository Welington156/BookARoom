package com.example.bookaroom.views.widgets;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class PeriodoPane extends HBox {
    {
        TimeField inicio = new TimeField();
        TimeField fim = new TimeField();
        setAlignment(Pos.BOTTOM_CENTER);
        getChildren().addAll(
                new PropriedadeItemLabel("Inicio", inicio),
                new PropriedadeItemLabel("Fim", fim)
        );
        setSpacing(5);
    }
}
