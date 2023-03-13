package com.example.bookaroom.views.widgets;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class PropriedadeItemLabel extends HBox {
    {
        setAlignment(Pos.BASELINE_LEFT);
    }
    public PropriedadeItemLabel(String nome, String valor) {
        this(nome, new Label(valor));
    }

    public PropriedadeItemLabel(String nome, Node valor) {
        Label nomeLabel = new Label(nome + ": ");
        nomeLabel.getStyleClass().add("property-name");
        valor.getStyleClass().add("property-value");
        getChildren().addAll(nomeLabel, valor);
    }


}
