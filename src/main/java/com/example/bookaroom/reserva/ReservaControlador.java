package com.example.bookaroom.reserva;

import com.example.bookaroom.campus.Sala;
import com.example.bookaroom.funcionario.Funcionario;
import com.example.bookaroom.funcionario.SessionControlador;
import com.example.bookaroom.util.Periodo;
import dados.teste.DataSource;

import java.util.ArrayList;
import java.util.List;


public class ReservaControlador {

    public static List<Reserva> reservasPeriodo(Sala sala, Periodo periodo) {
        List<Reserva> reservas = DataSource.getReservas();

        List<Reserva> output = new ArrayList<>();
        for(Reserva reserva : reservas) {
            if(sala.equals(reserva.getSala())
                    && reserva.getPeriodo().checkOverlap(periodo)) {
                output.add(reserva);
            }
        }

        return output;
    }

    public static Boolean temReservas(Sala sala, Periodo periodo) {
        List<Reserva> reservas = DataSource.getReservas();

        for(Reserva reserva : reservas) {
            if(sala.equals(reserva.getSala())
                    && reserva.getPeriodo().checkOverlap(periodo)
            ) {
                return true;
            }
        }
        return false;
    }

    public static Boolean temReservas(Funcionario funcionario, Periodo periodo) {
        List<Reserva> reservas = DataSource.getReservas();

        for(Reserva reserva : reservas) {
            if(funcionario.equals(reserva.getFuncionario())
                    && reserva.getPeriodo().checkOverlap(periodo)
            ) {
                return true;
            }
        }
        return false;
    }

    public static List<Reserva> reservasFuncionario(Funcionario funcionario) {
        List<Reserva> reservas = DataSource.getReservas();

        List<Reserva> output = new ArrayList<>();

        reservas.forEach(reserva -> {
            if (reserva.getFuncionario().equals(funcionario))
                output.add(reserva);
        });

        return output;
    }

    public static Reserva novaReserva(ReservaBuilder reservaBuilder) {
        Reserva novaReserva = reservaBuilder
                .setFuncionario(SessionControlador.getFuncionario())
                .get();
        DataSource.addReserva(novaReserva);
        return novaReserva;
    }
}
