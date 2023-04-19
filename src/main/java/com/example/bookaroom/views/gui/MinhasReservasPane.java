package com.example.bookaroom.views.gui;

import com.example.bookaroom.bookaroom.reserva.Reserva;
import com.example.bookaroom.views.gui.widgets.MessageLabel;
import com.example.bookaroom.views.gui.widgets.ReservaTitledPane;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class MinhasReservasPane extends VBox {
    private final Accordion mainAccordion;

    private final Accordion ativasAccordion;

    private final Accordion inativasAccordion;

    {
        mainAccordion = new Accordion();
        ativasAccordion = new Accordion();
        inativasAccordion = new Accordion();

        mainAccordion.setPrefWidth(200);

        Label title = new Label("Minhas Reservas");
        title.getStyleClass().add("property-name");

        TitledPane ativasGroup = createGroupAccordion(
                new MessageLabel("Ativas", Color.GREEN),
                ativasAccordion
        );

        TitledPane inativasGroup = createGroupAccordion(
                new MessageLabel("Inativas", Color.GRAY),
                inativasAccordion
        );

        mainAccordion.getPanes().addAll(ativasGroup, inativasGroup);

        App.bookARoomApi()
                .getMinhasReservas()
                .forEach(this::addReservaCard);

        getChildren().addAll(
                title,
                mainAccordion
        );
    }

    public TitledPane createGroupAccordion(MessageLabel titleMessage, Accordion accordion) {
        TitledPane titlePane = new TitledPane();

        titlePane.setGraphic(titleMessage);

        titlePane.setContent(accordion);

        return titlePane;
    }

    public void addReservaCard(Reserva reserva) {
        if(reserva.isAtiva())
            ativasAccordion.getPanes().add(new ReservaTitledPane(reserva));
        else
            inativasAccordion.getPanes().add(new ReservaTitledPane(reserva));
    }
}
