package com.example.bookaroom.views.widgets;

import com.example.bookaroom.reserva.Reserva;
import javafx.scene.layout.VBox;
import java.time.format.DateTimeFormatter;

public class ReservaDetalhesPane extends VBox {
    public ReservaDetalhesPane(Reserva reserva) {
        String sala = reserva.getSala().getNumero(),
                predio = reserva.getSala().getPredio().getNome(),
                campus = reserva.getSala().getPredio().getCampus().getNome(),
                capacidade = reserva.getSala().getCapacidade().toString(),
                data = reserva.getDataAlocacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                horaInicio = reserva.getHoraInicio().format(DateTimeFormatter.ofPattern("HH:mm")),
                horaFim = reserva.getHoraFim().format(DateTimeFormatter.ofPattern("HH:mm")),
                horario = horaInicio + " - " + horaFim,
                assunto = reserva.getAssunto();

        getChildren().addAll(
                new PropriedadeItemLabel("Campus", campus),
                new PropriedadeItemLabel("Predio", predio),
                new PropriedadeItemLabel("Sala", sala),
                new PropriedadeItemLabel("Capacidade", capacidade),
                new PropriedadeItemLabel("Data", data),
                new PropriedadeItemLabel("Horário", horario),
                new PropriedadeItemLabel("Assunto", assunto)
        );
    }
}
