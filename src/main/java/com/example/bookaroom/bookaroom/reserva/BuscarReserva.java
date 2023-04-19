package com.example.bookaroom.bookaroom.reserva;

import java.time.DayOfWeek;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class BuscarReserva {
    Set<Reserva> reservas;

    public BuscarReserva(Collection<Reserva> reservas) { this.reservas = Set.copyOf(reservas); }

    public BuscarReserva filtrarPor(Reservavel ...reservaveis) {

        Stream<Reserva> reservasStream = reservas.stream();
        for(Reservavel reservavel : reservaveis) {
            reservasStream = reservasStream.filter(reservavel::contidoEm);
        }

        return new BuscarReserva(reservasStream.toList());
    }

    public BuscarReserva filtrarPor(DayOfWeek dia) {
        return new BuscarReserva(
                reservas.stream().filter(reserva -> reserva.getDiaSemana().equals(dia)).toList()
        );
    }

    public <T extends Reservavel> HashMap<T, List<Reserva>> groupBy(List<T> t) {
        return new HashMap<>(){{
            t.forEach(t1 -> put(t1, reservas.stream().filter(t1::contidoEm).toList()));
        }};
    }

    public boolean isEmpty() {
        return reservas.isEmpty();
    }

    public boolean hasAny() {
        return !reservas.isEmpty();
    }

    public List<Reserva> get() {
        return List.copyOf(reservas);
    }

}
