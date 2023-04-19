package com.example.bookaroom.views.gui.widgets;

import com.example.bookaroom.bookaroom.reserva.Reserva;
import com.example.bookaroom.views.gui.App;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

import java.time.format.DateTimeFormatter;

public class ReservaTitledPane extends TitledPane {
    Reserva reserva;
    public ReservaTitledPane(Reserva reserva) {
        this.reserva = reserva;
        setGraphic(itemCollapsed(reserva));
        setContent(itemExpanded(reserva));
    }

    private VBox itemCollapsed(Reserva reserva) {
        VBox collapsedVbox = new VBox();

        String data = reserva.getDataAlocacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                horaInicio = reserva.getHoraInicio().format(DateTimeFormatter.ofPattern("HH:mm"));
        collapsedVbox.getStyleClass().add("reserva-collapsed");
        collapsedVbox.getChildren().addAll(
                new PropriedadeItemLabel("Sala", reserva.getSala().getNumero()),
                new PropriedadeItemLabel("Data", data + " - " + horaInicio)
        );

        return collapsedVbox;
    }

    private VBox itemExpanded(Reserva reserva) {
        VBox expandedVbox = new VBox();
        Button addEquipamentosBtn = new Button("Editar");
        addEquipamentosBtn.setOnAction(this::addEquipamentosAction);

        String sala = reserva.getSala().getNumero(),
                predio = reserva.getSala().getPredio().getNome(),
                campus = reserva.getSala().getPredio().getCampus().getNome(),
                capacidade = reserva.getSala().getCapacidade().toString(),
                data = reserva.getDataAlocacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                horaInicio = reserva.getHoraInicio().format(DateTimeFormatter.ofPattern("HH:mm")),
                horaFim = reserva.getHoraFim().format(DateTimeFormatter.ofPattern("HH:mm")),
                horario = horaInicio + " - " + horaFim,
                assunto = reserva.getAssunto();

        expandedVbox.getChildren().addAll(
                new PropriedadeItemLabel("Campus", campus),
                new PropriedadeItemLabel("Predio", predio),
                new PropriedadeItemLabel("Sala", sala),
                new PropriedadeItemLabel("Capacidade", capacidade),
                new PropriedadeItemLabel("Data", data),
                new PropriedadeItemLabel("Horário", horario),
                new PropriedadeItemLabel("Assunto", assunto),
                addEquipamentosBtn
        );

        return expandedVbox;
    }

    private void addEquipamentosAction(ActionEvent actionEvent) {
        App.toEditarReservaScreen(reserva);
    }
}
