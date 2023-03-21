package com.example.bookaroom;

import com.example.bookaroom.campus.*;
import com.example.bookaroom.reserva.Reserva;
import com.example.bookaroom.reserva.ReservaControlador;
import com.example.bookaroom.util.Periodo;
import dados.teste.DataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookARoom {
    Campus sessionCampus;
    Funcionario sessionFuncionario;

    public BookARoom(String nomeCampus, String nomeFuncionario) {
        sessionCampus = DataSource.getCampus(nomeCampus);

        if(sessionCampus == null) {
            throw new IllegalStateException("Campus não encontrado");
        }

        sessionFuncionario = new CampusControlador(sessionCampus).getFuncionario(nomeFuncionario);

        if(sessionFuncionario == null) {
            throw new IllegalStateException("Funcionario não encontrado nesse Campus");
        }
    }

    public List<Reserva> getMinhasReservas() {
        return new ReservaControlador().filtrarPor(sessionFuncionario).get();
    }

    public List<Predio> getPredios() {
        return new CampusControlador(sessionCampus).getPredios();
    }

    public Reserva cadastrarReserva(Periodo periodo, Sala sala, String assunto) {
        Reserva novaReserva = new Reserva(
                periodo,
                sessionFuncionario,
                sala,
                assunto,
                new ArrayList<>()
        );

        return ReservaControlador.cadastrarReserva(novaReserva);
    }

    public void updateReserva(Reserva reserva) {
        ReservaControlador.updateReserva(reserva);
    }

    public HashMap<Sala, Boolean> ocupacaoSalas(Periodo periodo) {
        return new CampusControlador(sessionCampus).salasDisponibilidade(periodo);
    }

    public List<Equipamento> equipamentosDisponiveis(Periodo periodo) {
        return new CampusControlador(sessionCampus).equipamentosDisponiveis(periodo);
    }
}
