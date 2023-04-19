package com.example.bookaroom.views.gui.widgets;

import com.example.bookaroom.views.gui.converters.DateConverter;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;

public class DateField extends DatePicker {
    {
        setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.compareTo(LocalDate.now()) < 0);
            }
        });

        getEditor().setOnMouseClicked(event -> show());
        getEditor().getStyleClass().add("datefield-editor");
        getEditor().setDisable(true);

        setConverter(new DateConverter());
    }

    public String getText() {
        return getEditor().getText();
    }
}
