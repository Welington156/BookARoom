package com.example.bookaroom.views;

import com.example.bookaroom.campus.CampusControlador;
import com.example.bookaroom.campus.Sala;
import com.example.bookaroom.funcionario.Funcionario;
import com.example.bookaroom.funcionario.SessionControlador;
import com.example.bookaroom.reserva.Reserva;
import com.example.bookaroom.reserva.ReservaBuilder;
import com.example.bookaroom.reserva.ReservaControlador;
import com.example.bookaroom.util.Periodo;
import com.example.bookaroom.views.widgets.DateField;
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

import java.time.format.DateTimeParseException;
import java.util.HashMap;

public class FazerReserva extends GridPane {
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

        salasTogglePane = new SalasTogglePane(CampusControlador.getPredios());

        Label title = new Label("Buscar Salas Disponiveis / Cadastrar Reserva");
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
        filtrarSalasAction(null);

        String inputsErr = validarReservaInputs();
        if(inputsErr != null) {
            showError(inputsErr);
            return;
        }

        String funcionarioErr = validarFuncionario();
        if(funcionarioErr != null){
            showError(funcionarioErr);
            return;
        }

        Periodo periodo = new Periodo(dataField.getText(),
                inicioTimeField.getText(),
                fimTimeField.getText());

        ReservaBuilder reservaBuilder = new ReservaBuilder();
        reservaBuilder.setPeriodo(periodo);
        reservaBuilder.setSala(salasTogglePane.getSalaSelecionada());
        reservaBuilder.setAssunto(assuntoTextField.getText());

        Reserva novaReserva = ReservaControlador.novaReserva(reservaBuilder);

        showInfo("Sucesso ao cadastrar reserva!");

        if(onReservaCreateEvent != null) {
            onReservaCreateEvent.onResevaCreate(novaReserva);
        }
    }

    private void filtrarSalasAction(ActionEvent actionEvent) {
        String validacaoErr = validarPeriodoInput();

        if(validacaoErr != null){
            showError(validacaoErr);
            return;
        }

        HashMap<Sala, Boolean> salasDisponiveis = CampusControlador.salasDisponiveisPorPeriodo(new Periodo(
                dataField.getText(),
                inicioTimeField.getText(),
                fimTimeField.getText()
        ));

        salasTogglePane.filter(salasDisponiveis);
    }

    // Validações
    private String validarFuncionario() {
        Funcionario funcionario = SessionControlador.getFuncionario();

        Periodo periodo = new Periodo(dataField.getText(),
                inicioTimeField.getText(),
                fimTimeField.getText());

        if(funcionario == null) {
            return "Você Precisa estar logado.";
        }

        if(!CampusControlador.funcionarioEstaDisponivel(funcionario, periodo)){
            return "Você já possui reserva(s) nesse invervalo de tempo.";
        }

        return null;
    }

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

    // Eventos
    public void setOnReservaCreateEvent(OnReservaCreateEvent onReservaCreateEvent) {
        this.onReservaCreateEvent = onReservaCreateEvent;
    }
}
