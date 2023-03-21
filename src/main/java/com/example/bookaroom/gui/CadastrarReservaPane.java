package com.example.bookaroom.gui;

import com.example.bookaroom.campus.Sala;
import com.example.bookaroom.reserva.Reserva;
import com.example.bookaroom.util.Periodo;
import com.example.bookaroom.gui.widgets.*;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.time.format.DateTimeParseException;
import java.util.HashMap;

public class CadastrarReservaPane extends GridPane {
    @FunctionalInterface
    public interface OnReservaCreateEvent {
        void onResevaCreate(Reserva reserva);
    }

    OnReservaCreateEvent onReservaCreateEvent;
    TextField assuntoTextField;
    DateField dataField;
    TimeField inicioTimeField;
    TimeField fimTimeField;
    SalasTogglePane salasTogglePane;
    MessageLabel infoMessage = new MessageLabel("", Color.BLACK);

    {
        assuntoTextField = new TextField();
        assuntoTextField.setPrefWidth(400);

        dataField = new DateField();
        inicioTimeField = new TimeField();
        fimTimeField = new TimeField();

        HBox periodoBox = new HBox(
                new PropriedadeItemLabel("Inicio", inicioTimeField),
                new PropriedadeItemLabel("Fim", fimTimeField)
        );
        periodoBox.setSpacing(5);

        PropriedadeItemLabel dataFieldItem = new PropriedadeItemLabel("Data", dataField);
        PropriedadeItemLabel periodoPane = new PropriedadeItemLabel("Horario", periodoBox);
        PropriedadeItemLabel assuntoTextArea = new PropriedadeItemLabel("Assunto", assuntoTextField);

        salasTogglePane = new SalasTogglePane(App.bookARoomApi().getPredios());

        Label title = new Label("Cadastrar Reserva");
        title.getStyleClass().add("property-name");

        Button filtrarSalasBtn = new Button("Filtrar");
        filtrarSalasBtn.setOnAction(this::filtrarSalasAction);

        Button cadastrarReservaBtn = new Button("Cadastrar");
        cadastrarReservaBtn.setOnAction(this::cadastrarReservaAction);

        setPadding(new Insets(0, 10, 0, 0));
        setVgap(10);

        add(title, 0, 0, 3, 1);
        add(infoMessage, 0, 1, 3, 1);
        add(dataFieldItem.getNomeLabel(), 0, 2);
        add(dataFieldItem.getValor(), 1, 2);

        add(periodoPane.getNomeLabel(), 0, 3);
        add(periodoPane.getValor(), 1, 3);

        add(filtrarSalasBtn, 0, 4);

        add(assuntoTextArea.getNomeLabel(), 0, 5);
        add(assuntoTextArea, 1, 5, 1, 4);

        add(salasTogglePane, 0, 6, 3, 10);

        add(cadastrarReservaBtn, 0, 17);
    }


    private void cadastrarReservaAction(ActionEvent actionEvent) {
        hiddenMessage();
        filtrarSalasAction(null);

        String inputsErr = validarReservaInputs();
        if(inputsErr != null) {
            showError(inputsErr);
            return;
        }

        Periodo periodo = new Periodo(dataField.getText(),
                inicioTimeField.getText(),
                fimTimeField.getText());
        Sala sala = salasTogglePane.getSalaSelecionada();
        String assunto = assuntoTextField.getText();

        try {
            Reserva novaReserva = App.bookARoomApi().cadastrarReserva(periodo, sala, assunto);

            showInfo("Sucesso ao cadastrar reserva!");

            if(onReservaCreateEvent != null) {
                onReservaCreateEvent.onResevaCreate(novaReserva);
            }

        } catch (IllegalStateException e) {
            showError(e.getMessage());
        }
    }

    private void filtrarSalasAction(ActionEvent actionEvent) {
        hiddenMessage();
        String validacaoErr = validarPeriodoInput();

        if(validacaoErr != null){
            showError(validacaoErr);
            return;
        }

        HashMap<Sala, Boolean> salasDisponiveis = App.bookARoomApi().ocupacaoSalas(new Periodo(
                dataField.getText(),
                inicioTimeField.getText(),
                fimTimeField.getText()
        ));

        salasTogglePane.filter(salasDisponiveis);
    }

    // Validações de inputs

    private String validarPeriodoInput() {
        try {
            Periodo periodo = new Periodo(
                    dataField.getText(),
                    inicioTimeField.getText(),
                    fimTimeField.getText());

            if(!periodo.horaFim.isAfter(periodo.horaInicio)) {
                return "A reserva precisa ter pelo menos 1 min :)";
            }

        } catch (DateTimeParseException error) {
            return "Data ou horario Invalidos";
        }

        infoMessage.setText("");
        return null;
    }

    public String validarReservaInputs() {
        String err = validarPeriodoInput();
        if(err != null) {
            return err;
        }

        if(salasTogglePane.getSelected() == null) {
            return "Selecione uma Sala";
        }

        if(!salasTogglePane.getSelected().isDisponivel()) {
            return "Selecione uma Sala disponivel";
        }

        if(assuntoTextField.getText().equals("")) {
            return "Especifique o assunto";
        }

        infoMessage.setText("");
        return null;
    }


    // Mostrar Mensagens
    private void showError(String text) {
        infoMessage.setTextFill(Color.RED);
        infoMessage.setText(text);
    }

    private void showInfo(String text) {
        infoMessage.setTextFill(Color.GREEN);
        infoMessage.setText(text);
    }

    private void hiddenMessage() {
        infoMessage.setText("");
    }

    // Eventos
    public void setOnReservaCreateEvent(OnReservaCreateEvent onReservaCreateEvent) {
        this.onReservaCreateEvent = onReservaCreateEvent;
    }
}
