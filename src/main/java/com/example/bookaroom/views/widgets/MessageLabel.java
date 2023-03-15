package com.example.bookaroom.views.widgets;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class MessageLabel extends Label {
    public MessageLabel(String texto, Color color) {
        this.setText(texto);
        this.setTextFill(color);
    }
}
