package com.example.bookaroom.bookaroom;

import com.example.bookaroom.bookaroom.campus.*;
import com.example.bookaroom.bookaroom.equipamentos.Equipamento;
import com.example.bookaroom.bookaroom.periodo.Horario;
import com.example.bookaroom.bookaroom.periodo.Periodo;
import com.example.bookaroom.bookaroom.reserva.Aula;
import com.example.bookaroom.bookaroom.reserva.Reserva;
import com.example.bookaroom.bookaroom.reserva.Reuniao;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookARoom {
    String sessionCampus;
    String sessionFuncionario;

    public BookARoom(String nomeCampus, String nomeFuncionario) {
        CampusControlador campusControlador = new CampusControlador(nomeCampus);

        if (campusControlador.getCampus() == null) {
            throw new IllegalStateException("Campus não encontrado");
        }

        Funcionario funcionario = campusControlador.getFuncionario(nomeFuncionario);

        if (funcionario == null) {
            throw new IllegalStateException("Funcionario não encontrado nesse Campus");
        }

        sessionCampus = nomeCampus;
        sessionFuncionario = nomeFuncionario;
    }

    private CampusControlador campusControlador() {
        return new CampusControlador(sessionCampus);
    }

    private Funcionario getFuncionario() {
        return campusControlador().getFuncionario(sessionFuncionario);
    }

    public List<Reserva> getMinhasReservas() {
        return campusControlador()
                .buscarReservas()
                .filtrarPor(getFuncionario()).get();
    }

    public List<Semestre> getSemestres() {
        return campusControlador().getCampus().getSemestres();
    }

    public List<Predio> getPredios() {
        return campusControlador().getCampus().getPredios();
    }

    public HashMap<Sala, Boolean> disponibilidadeDeSalas(Periodo periodo) {
        return campusControlador().salasDisponibilidade(periodo);
    }

    public HashMap<Sala, List<Reserva>> reservasPorSala(Periodo periodo) {
        Campus campus = campusControlador().getCampus();

        return campusControlador()
                .buscarReservas(campus)
                .filtrarPor(periodo)
                .groupBy(campus.getSalas());
    }

    public HashMap<Sala, List<Reserva>> reservasPorSala(Semestre semestre, DayOfWeek diaSemana, Horario horario) {
        Campus campus = campusControlador().getCampus();

        return campusControlador()
                .buscarReservas(campus)
                .filtrarPor(semestre, horario)
                .filtrarPor(diaSemana)
                .groupBy(campus.getSalas());
    }

    public List<Equipamento> equipamentosDisponiveis(Periodo periodoUnico) {
        return campusControlador().equipamentosDisponiveis(periodoUnico);
    }

    public Reuniao cadastrarReuniao(Periodo periodo, Sala sala, String assunto) {
        return campusControlador().cadastrarReuniao(
                periodo,
                getFuncionario(),
                sala,
                assunto,
                new ArrayList<>()
                );
    }

    public Aula cadastrarAula(Periodo periodo, Sala sala, String assunto) {
        return campusControlador().cadastrarAula(
                periodo,
                getFuncionario(),
                sala,
                assunto,
                new ArrayList<>()
        );
    }

    public void updateReserva(Reserva reserva) {
//        reservaControlador().updateReserva(reserva);
    }

    public void cadastrarEquipamento() {}

}
