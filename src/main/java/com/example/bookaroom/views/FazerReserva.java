package com.example.bookaroom.views;

import com.example.bookaroom.util.Periodo;
import com.example.bookaroom.views.widgets.DataField;
import com.example.bookaroom.views.widgets.MessageLabel;
import com.example.bookaroom.views.widgets.PropriedadeItemLabel;
import com.example.bookaroom.views.widgets.TimeField;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.util.Objects;

public class FazerReserva extends GridPane {
    TextField textField;
    DataField dataField;
    TimeField inicio;
    TimeField fim;

    MessageLabel errorMessage = new MessageLabel("", Color.RED);

    {
        textField = new TextField();
        textField.setPrefWidth(400);

        dataField = new DataField();
        inicio = new TimeField();
        fim = new TimeField();

        HBox periodoBox = new HBox(
                new PropriedadeItemLabel("Inicio", inicio),
                new PropriedadeItemLabel("Fim", fim)
        );
        periodoBox.setSpacing(5);

        PropriedadeItemLabel dataFieldItem = new PropriedadeItemLabel("Data", dataField);
        PropriedadeItemLabel periodoPane = new PropriedadeItemLabel("Horario", periodoBox);
        PropriedadeItemLabel assuntoTextArea = new PropriedadeItemLabel("Assunto", textField);

        Label title = new Label("Buscar Salas Disponiveis / Cadastrar Reserva");
        title.getStyleClass().add("property-name");

        Button filtrarSalasBtn = new Button("Filtrar");
//        filtrarSalasBtn.setOnAction(this::addReservaAction);

        Button cadastrarReservaBtn = new Button("Cadastrar");
        cadastrarReservaBtn.setOnAction(this::addReservaAction);

        setPadding(new Insets(0, 10, 0, 0));
        setVgap(10);

        add(title, 0, 0, 3, 1);
        add(errorMessage, 0, 1, 3, 1);
        add(dataFieldItem.getNomeLabel(), 0, 2);
        add(dataFieldItem.getValor(), 1, 2);

        add(periodoPane.getNomeLabel(), 0, 3);
        add(periodoPane.getValor(), 1, 3);

        add(filtrarSalasBtn, 0, 4);

        add(assuntoTextArea.getNomeLabel(), 0, 5);
        add(assuntoTextArea, 1, 5, 1, 4);

        add(new SalasTogglePane(), 0, 6, 3, 10);

        add(cadastrarReservaBtn, 0, 17);
    }

    private void addReservaAction(ActionEvent actionEvent) {
        validarInputs();
    }
    private void validarInputs() {
        if(dataField.getText().equals("")) {
            errorMessage.setText("Data precisa ser definida");
            return;
        }

        Periodo periodo = new Periodo(
                dataField.getText(),
                inicio.getText(),
                fim.getText());

        if(periodo.horaFim.compareTo(periodo.horaInicio) != 1) {
            errorMessage.setText("A reserva precisa ter pelo menos 1 min :)");
            return;
        }

        errorMessage.setText("");
    }
}
