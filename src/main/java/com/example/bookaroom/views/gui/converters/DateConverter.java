package com.example.bookaroom.views.gui.converters;

import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConverter extends StringConverter<LocalDate> {
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public String toString(LocalDate date) {
        if (date == null) return "";
        return dateFormatter.format(date);
    }

    @Override
    public LocalDate fromString(String s) {
        if (s == null || s.isEmpty()) return null;
        return LocalDate.parse(s, dateFormatter);
    }
}
