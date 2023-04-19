package com.example.bookaroom.bookaroom.campus;

import com.example.bookaroom.bookaroom.periodo.Periodo;
import com.example.bookaroom.bookaroom.reserva.Aula;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class Semestre extends Periodo {
    protected String id = null;

    public static final DateTimeFormatter FORMATO_MES_ANO = DateTimeFormatter.ofPattern("MM/yyyy");

    List<Aula> aulas;

    public Semestre(LocalDate Inicio, LocalDate Fim, List<Aula> aulas) {
        super(LocalDateTime.of(Inicio, LocalTime.MIN),
                LocalDateTime.of(Fim, LocalTime.MAX));
        this.aulas = aulas;
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Semestre semestre)) return false;
        return aulas.equals(semestre.aulas) && inicio.equals(semestre.inicio) && fim.equals(semestre.fim);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, inicio, fim);
    }

    @Override
    public String toString() {
        return id + " " + inicio.toLocalDate().format(FORMATO_MES_ANO) + " " + fim.toLocalDate().format(FORMATO_MES_ANO);
    }
}
