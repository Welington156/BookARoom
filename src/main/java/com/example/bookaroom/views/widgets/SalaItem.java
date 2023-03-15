package com.example.bookaroom.views.widgets;

import com.example.bookaroom.campus.Sala;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class SalaItem extends HBox {
    public SalaItem(Sala sala, RadioButton radioButton) {
        radioButton.setAlignment(Pos.CENTER_LEFT);
        boolean status = false;

        setAlignment(Pos.BASELINE_LEFT);

        Label salaText = new Label("Sala " + sala.getNumero());
        salaText.getStyleClass().add("property-name-no-color");

        Label statusText = new Label(status ? "Livre" :"Ocupada" );
        statusText.getStyleClass().add("property-name-no-color");
        statusText.setPadding(new Insets(0, 0, 0, 10));
        setPadding(new Insets(0, 0, 0, 10));
        setSpacing(5);

        statusText.setTextFill(status ? Color.LAWNGREEN : Color.RED);

        getChildren().addAll(
                radioButton,
                salaText,
                statusText
        );
    }
}
