package com.example.bookaroom.bookaroom.campus;

import com.example.bookaroom.bookaroom.equipamentos.Equipamento;
import com.example.bookaroom.bookaroom.periodo.Periodo;
import com.example.bookaroom.bookaroom.reserva.*;
import com.example.bookaroom.dados.DataSource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;

public class CampusControlador {
    String campusNome;

    public CampusControlador(String campusNome) {
        this.campusNome = campusNome;
    }

    public BuscarReserva buscarReservas() {
        return buscarReservas(getCampus());
    }

    public BuscarReserva buscarReservas(Campus campus) {
        return new BuscarReserva(campus.getReservas());
    }

    public Campus getCampus() {
        return DataSource.getCampus(campusNome);
    }

    public void updateCampus(Consumer<Campus> updateFunc) {
        DataSource.transaction(campusNome, updateFunc);
    }

    public HashMap<Sala, Boolean> salasDisponibilidade(Periodo periodoUnico) {
        BuscarReserva buscarReserva = buscarReservas().filtrarPor(periodoUnico);

        return new HashMap<>() {{
            getCampus().getSalas().forEach(
                    sala -> put(sala, buscarReserva.filtrarPor(sala).isEmpty()));
        }};
    }

    public List<Equipamento> equipamentosDisponiveis(Periodo periodoUnico) {
        BuscarReserva buscarReserva = buscarReservas().filtrarPor(periodoUnico);

        return new ArrayList<>() {{
            getCampus().getEquipamentos().forEach(equipamento -> {
                if(buscarReserva.filtrarPor(equipamento).isEmpty())
                    add(equipamento);
            });
        }};
    }

    public HashMap<Sala, List<Reserva>> reservasPorSala(Periodo periodoUnico) {
        Campus campus = getCampus();
        return buscarReservas(campus).filtrarPor(periodoUnico).groupBy(campus.getSalas());
    }

    public Funcionario getFuncionario(String nome) {
        for(Funcionario funcionario : getCampus().getFuncionarios()) {
            if(funcionario.getNome().equals(nome)){
                return funcionario;
            }
        }

        return null;
    }

    public Reuniao cadastrarReuniao(Periodo periodo, Funcionario funcionario, Sala sala, String assunto, ArrayList<Equipamento> equipamentos) throws IllegalStateException {
        Reuniao novaReserva = new Reuniao(periodo, funcionario, sala, assunto, equipamentos);

        updateCampus(campus -> {
            validarCadastroDeReserva(campus, novaReserva);
            campus.getReunioes().add(novaReserva);
        });

        return novaReserva;
    }

    public Aula cadastrarAula(Periodo periodo, Funcionario sessionFuncionario, Sala sala, String assunto, ArrayList<Equipamento> equipamentos) {
        Aula novaReserva = new Aula(periodo, sessionFuncionario, sala, assunto, equipamentos, LocalDate.now());

//        validarCadastro(novaReserva);
//        DataSource.addReserva(novaReserva);

        return novaReserva;
    }

    public Semestre cadastrarSemestre(Semestre novoSemestre) {
        novoSemestre.id = getCampus().getNome() + "_" + novoSemestre.inicio.format(Semestre.FORMATO_MES_ANO);
        updateCampus(campus -> campus.getSemestres().add(novoSemestre));
        return novoSemestre;
    }

    private void validarCadastroDeReserva(Campus campus, Reserva reserva) throws IllegalStateException {
        BuscarReserva buscarReserva = buscarReservas(campus).filtrarPor(reserva.getPeriodo());

        if(buscarReserva.filtrarPor(reserva.getFuncionario()).hasAny()){
            throw new IllegalStateException("Funcionario ja possui reservas nesse periodo");
        }

        List<Reserva> reservasSala = buscarReserva.filtrarPor(reserva.getSala()).get();

        reservasSala.forEach(reservaSala -> {
            if(reservaSala.getPrioridade() > reserva.getPrioridade()){
                throw new IllegalStateException("Sala ja possui reservas com maior prioridade");
            }
        });

        reserva.getEquipamentos().forEach(equipamento -> {
            if(buscarReserva.filtrarPor(equipamento).hasAny()){
                throw new IllegalStateException("Equipamento ja possui reservas nesse periodo");
            }});
    }

}
