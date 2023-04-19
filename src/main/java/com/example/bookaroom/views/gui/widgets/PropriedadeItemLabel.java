package com.example.bookaroom.views.gui.widgets;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class PropriedadeItemLabel extends HBox {
    Label nomeLabel;
    Node valor;

    {
        setAlignment(Pos.BASELINE_LEFT);
    }
    public PropriedadeItemLabel(String nome, String valor) {
        this(nome, new Label(valor));
    }

    public PropriedadeItemLabel(String nome, Node valor) {
        this.valor = valor;
        nomeLabel = new Label(nome + ": ");
        nomeLabel.getStyleClass().add("property-name");
        valor.getStyleClass().add("property-value");
        nomeLabel.setAlignment(Pos.CENTER);
        getChildren().addAll(nomeLabel, valor);
    }

    public Label getNomeLabel() {
        return nomeLabel;
    }

    public Node getValor() {
        return valor;
    }
}
