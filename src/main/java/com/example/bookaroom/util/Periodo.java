package com.example.bookaroom.util;

import com.example.bookaroom.reserva.Reserva;
import com.example.bookaroom.reserva.Reservavel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Periodo implements Reservavel, Comparable<Periodo> {
    public static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public LocalDate data;
    public LocalTime horaInicio;
    public LocalTime horaFim;

    public Periodo(LocalDate data, LocalTime horaInicio, LocalTime horaFim) {
        this.data = data;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
    }

    public Periodo(String data, String horaInicio, String horaFim) {
        this(LocalDate.parse(data, FORMATO_DATA),
                LocalTime.parse(horaInicio, FORMATO_HORA),
                LocalTime.parse(horaFim, FORMATO_HORA)
        );
    }

    public boolean checkOverlap(Periodo periodo) {
        return periodo.data.equals(data)
                && periodo.horaInicio.isBefore(horaFim)
                && periodo.horaFim.isAfter(horaInicio);
    }

    @Override
    public boolean contidoEm(Reserva reserva) {
        return this.checkOverlap(reserva.getPeriodo());
    }

    @Override
    public int compareTo(Periodo periodo) {
        int dataCompare = this.data.compareTo(periodo.data);
        if(dataCompare != 0) {
            return dataCompare;
        }
        return this.horaInicio.compareTo(periodo.horaInicio);
    }
}
