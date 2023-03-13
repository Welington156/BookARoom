package com.example.bookaroom.reserva;

import com.example.bookaroom.campus.Equipamento;
import com.example.bookaroom.campus.Sala;
import com.example.bookaroom.funcionario.Funcionario;
import com.example.bookaroom.util.Periodo;
import dados.teste.DataSource;

import java.util.ArrayList;
import java.util.List;


public class ReservaControlador {

    public static List<Reserva> getReservas() {
        return DataSource.getReservas();
    }

    public static List<Reserva> reservasPeriodo(Sala sala, Periodo periodo) {
        List<Reserva> reservas = DataSource.getReservas();

        List<Reserva> output = new ArrayList<>();
        for(Reserva reserva : reservas) {
            if(sala.equals(reserva.getSala())
                    && reserva.getDataAlocacao().equals(periodo.data)
                    && reserva.getHoraInicio().compareTo(periodo.horaInicio) > 0
                    && reserva.getHoraInicio().compareTo(periodo.horaFim) < 0) {
                output.add(reserva);
            }
        }

        return output;
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

    public static void novaReserva(ReservaBuilder reservaBuilder) {
        DataSource.addReserva(reservaBuilder.get());
    }

}
