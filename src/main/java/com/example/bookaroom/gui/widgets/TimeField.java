package com.example.bookaroom.gui.widgets;

import javafx.beans.Observable;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import java.util.regex.Pattern;

public class TimeField extends TextField {
    {
        setAlignment(Pos.CENTER);
        textProperty().addListener(this::onTextChange);
        setOnMouseClicked(this::onMouseOrKeyEvent);
        setOnKeyPressed(this::onMouseOrKeyEvent);
        setText("00:00");
        setPrefWidth(50);
        setPrefHeight(20);
    }

    private Boolean checkTimePattern(String str) {
        return Pattern.matches("^([0-1]?[0-9]|2[0-3]):[0-9][0-9]$", str);
    }

    public Boolean isValid() {
        return Pattern.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$", getText());
    }

    private void onMouseOrKeyEvent(Event event) {
        positionCaret(getText().length());
    }

    private void onTextChange(Observable observable, String oldValue, String newValue) {
        if (newValue.length() == 5) return;
        if(Math.abs(newValue.length() - oldValue.length()) != 1) {
            setText(oldValue);
            return;
        }

        String output, str;
        char[] ch = newValue.length() > 5
                ? newValue.substring(1).toCharArray()
                : newValue.toCharArray();

        char temp = ch[1];
        ch[1] = ch[2];
        ch[2] = temp;
        str = new String(ch);

        if(newValue.length() > 5 && checkTimePattern(str)) {
            output = str;
        } else if (checkTimePattern(str) && !oldValue.equals("00:00"))  {
            output = "0" + str;
        } else {
            output = oldValue;
        }

        setText(output);
    }
}
