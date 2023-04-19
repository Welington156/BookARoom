package com.example.bookaroom.views.gui.widgets;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class MessageLabel extends Label {
    public MessageLabel(String texto, Color color) {
        setText(texto);
        setTextFill(color);
        getStyleClass().add("info-message");
    }
}
