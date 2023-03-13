package com.example.bookaroom.views;

import com.example.bookaroom.views.widgets.DataField;
import com.example.bookaroom.views.widgets.PeriodoPane;
import com.example.bookaroom.views.widgets.PropriedadeItemLabel;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FazerReserva extends VBox {
    {
        DataField dataField = new DataField();
        PeriodoPane periodoPane = new PeriodoPane();
        TextArea assuntoTextArea = new TextArea();
        HBox periodoHbox = new HBox(
                new PropriedadeItemLabel("Data", dataField),
                periodoPane);
        periodoHbox.setSpacing(10);

        Button button = new Button("Reservar");
        ToolBar toolBar = new ToolBar(button);

        getChildren().addAll(
                periodoHbox,
                assuntoTextArea,
                toolBar
        );
    }

}
