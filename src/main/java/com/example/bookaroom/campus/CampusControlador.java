package com.example.bookaroom.campus;

import com.example.bookaroom.reserva.Reserva;
import com.example.bookaroom.reserva.ReservaControlador;
import com.example.bookaroom.util.Periodo;

import java.util.*;

public class CampusControlador {
    Campus campus;

    public CampusControlador(Campus campus) {
        this.campus = campus;
    }

    public Campus getCampus() {
        return this.campus;
    }

    public List<Predio> getPredios() {
        return campus.getPredios();
    }

    public List<Sala> getSalas() {
        return new ArrayList<>() {{
           campus.getPredios().forEach(predio -> addAll(predio.getSalas()));
        }};
    }

    public HashMap<Sala, Boolean> salasDisponibilidade(Periodo periodo) {
        ReservaControlador reservaControlador = new ReservaControlador().filtrarPor(periodo);

        return new HashMap<>() {{
            getSalas().forEach(
                    sala -> put(sala, !reservaControlador.filtrarPor(sala).hasAny()));
        }};
    }

    public HashMap<Sala, List<Reserva>> reservasPorSala(Periodo periodo) {
        ReservaControlador reservaControlador = new ReservaControlador().filtrarPor(periodo);

        return new HashMap<>() {{
            getSalas().forEach(
                    sala -> put(sala, reservaControlador.filtrarPor(sala).get()));
        }};
    }

    public List<Equipamento> equipamentosDisponiveis(Periodo periodo) {
        ReservaControlador reservaControlador = new ReservaControlador().filtrarPor(periodo);

        return new ArrayList<>() {{
            getEquipamentos().forEach(equipamento -> {
                if(!reservaControlador.filtrarPor(equipamento).hasAny())
                    add(equipamento);
            });
        }};
    }

    public Funcionario getFuncionario(String nome) {
        for(Funcionario funcionario : campus.getFuncionarios()) {
            if(funcionario.getNome().equals(nome)){
                return funcionario;
            }
        }

        return null;
    }

    public List<Equipamento> getEquipamentos() {
        return campus.getEquipamentos();
    }
}


