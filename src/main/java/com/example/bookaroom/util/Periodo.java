package com.example.bookaroom.util;

import com.example.bookaroom.reserva.Reserva;
import com.example.bookaroom.reserva.Reservavel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Periodo implements Reservavel, Comparable<Periodo> {
    public static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public LocalDate dataInicio;
    public LocalDate dataFim;
    public LocalTime horaInicio;
    public LocalTime horaFim;

    public Periodo(LocalDate dataInicio, LocalDate dataFim, LocalTime horaInicio, LocalTime horaFim) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
    }

    public Periodo(LocalDate dataInicio, LocalTime horaInicio, LocalTime horaFim) {
        this.dataInicio = dataInicio;
        this.dataFim = dataInicio;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
    }

    public Periodo(String dataInicio, String horaInicio, String horaFim) {
        this(LocalDate.parse(dataInicio, FORMATO_DATA),
                LocalTime.parse(horaInicio, FORMATO_HORA),
                LocalTime.parse(horaFim, FORMATO_HORA)
        );
    }

    public Periodo(String dataInicio, String dataFim, String horaInicio, String horaFim) {
        this(LocalDate.parse(dataInicio, FORMATO_DATA),
                LocalDate.parse(dataFim, FORMATO_DATA),
                LocalTime.parse(horaInicio, FORMATO_HORA),
                LocalTime.parse(horaFim, FORMATO_HORA)
        );
    }

    public LocalDateTime getInicio() {
        return LocalDateTime.of(dataInicio, horaInicio);
    }

    public LocalDateTime getFim() {
        return LocalDateTime.of(dataFim, horaFim);
    }

    public boolean checkOverlap(Periodo periodo) {
        return periodo.getInicio().isBefore(this.getFim())
                && periodo.getFim().isAfter(this.getInicio());
    }

    @Override
    public boolean contidoEm(Reserva reserva) {
        return this.checkOverlap(reserva.getPeriodo());
    }

    @Override
    public int compareTo(Periodo periodo) {
        int dataCompare = this.dataInicio.compareTo(periodo.dataInicio);
        int dataFCompare = this.dataFim.compareTo(periodo.dataFim);

        if(dataCompare != 0) {
            return dataCompare;
        }

        if(dataFCompare != 0) {
            return dataFCompare;
        }

        return this.horaInicio.compareTo(periodo.horaInicio);
    }
}
