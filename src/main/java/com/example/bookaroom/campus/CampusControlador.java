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

    public static List<Sala> getSalas() {
        Campus campus = DataSource.getCampus();

        return new ArrayList<>() {{
           campus.getPredios().forEach(predio -> addAll(predio.getSalas()));
        }};
    }

    public static HashMap<Sala, List<Reserva>> salasPorPeriodo(Periodo periodo) {
        return new HashMap<>(){{
            getSalas().forEach(sala -> put(sala, ReservaControlador.reservasPeriodo(sala, periodo)));
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
}
