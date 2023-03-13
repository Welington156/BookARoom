package com.example.bookaroom.views.widgets;

import com.example.bookaroom.views.converters.DateConverter;
import javafx.scene.control.DatePicker;

public class DataField extends DatePicker {
    {
        getEditor().setOnMouseClicked(event -> show());
        getEditor().getStyleClass().add("datefield-editor");
        getEditor().setDisable(true);

        setConverter(new DateConverter());
    }
}
