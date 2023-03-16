package com.example.bookaroom.campus;

import com.example.bookaroom.funcionario.Funcionario;
import com.example.bookaroom.reserva.Reserva;
import com.example.bookaroom.reserva.ReservaControlador;
import com.example.bookaroom.util.Periodo;
import dados.teste.DataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CampusControlador {

    public static Campus getCampus() {
        return DataSource.getCampus();
    }

    public static List<Sala> getSalas() {
        Campus campus = DataSource.getCampus();

        return new ArrayList<>() {{
           campus.getPredios().forEach(predio -> addAll(predio.getSalas()));
        }};
    }

    public static List<Predio> getPredios() {
        Campus campus = DataSource.getCampus();
        return campus.getPredios();
    }

    public static HashMap<Sala, List<Reserva>> reservaDeSalasPorPeriodo(Periodo periodo) {
        return new HashMap<>(){{
            getSalas().forEach(sala -> put(sala, ReservaControlador.reservasPeriodo(sala, periodo)));
        }};
    }

    public static HashMap<Sala, Boolean> salasDisponiveisPorPeriodo(Periodo periodo) {
        return new HashMap<>() {{
            getSalas().forEach(sala -> put(sala, !ReservaControlador.possuiResevasNoPeriodo(sala, periodo)));
        }};
    }

    public static Funcionario getFuncionario(String nome) {
        Campus campus = DataSource.getCampus();
        Funcionario funcionario = null;

        for(Funcionario f : campus.getFuncionarios()) {
            if(f.getNome().equals(nome)){
                funcionario = f;
            }
        }

        return funcionario;
    }

    public static Boolean funcionarioEstaDisponivel(Funcionario funcionario, Periodo periodo) {
        return !ReservaControlador.possuiResevasNoPeriodo(funcionario, periodo);
    }
}


