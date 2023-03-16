package com.example.bookaroom.views.widgets;

import com.example.bookaroom.campus.Sala;
import javafx.beans.Observable;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class SalaItem extends HBox {
    Label statusText;
    boolean disponivel = false;

    public SalaItem(Sala sala, RadioButton radioButton) {

        radioButton.setAlignment(Pos.CENTER_LEFT);

        setAlignment(Pos.BASELINE_LEFT);

        Label salaText = new Label("Sala " + sala.getNumero());
        salaText.getStyleClass().add("property-name-no-color");

        statusText = new Label();
        statusText.getStyleClass().add("property-name-no-color");
        statusText.setPadding(new Insets(0, 0, 0, 10));
        setPadding(new Insets(0, 0, 0, 10));
        setSpacing(5);

        getChildren().addAll(
                radioButton,
                salaText,
                statusText
        );
    }
    private void onStatusChange(Boolean disponivel) {
        this.disponivel = disponivel;
        statusText.setText(disponivel ? "Livre" :"Ocupada");
        statusText.setTextFill(disponivel ? Color.LAWNGREEN : Color.RED);
    }

    public void setDisponibilidade(boolean bool) {
        onStatusChange(bool);
    }

    public boolean isDisponivel() {
        return disponivel;
    }

}
