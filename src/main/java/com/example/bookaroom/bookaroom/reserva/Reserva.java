package com.example.bookaroom.bookaroom.reserva;

import com.example.bookaroom.bookaroom.equipamentos.Equipamento;
import com.example.bookaroom.bookaroom.campus.Funcionario;
import com.example.bookaroom.bookaroom.periodo.Horario;
import com.example.bookaroom.bookaroom.periodo.Periodo;
import com.example.bookaroom.bookaroom.campus.Sala;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;


public abstract class Reserva implements Serializable {
    private final LocalDate dataAlocacao;
    private final LocalTime horaInicio;
    private final LocalTime horaFim;
    private final String assunto;
    private final Sala sala;
    private final Funcionario funcionario;
    private final List<Equipamento> equipamentos;
    protected Boolean ativa;

    abstract public int getPrioridade();

    public boolean hasOverlap(Periodo p) {
        return getPeriodo().overlaps(p);
    }

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    protected Reserva(Periodo periodo, Funcionario funcionario, Sala sala, String assunto, List<Equipamento> equipamentos) {
        this.funcionario = funcionario;
        this.sala = sala;
        this.assunto = assunto;
        this.horaInicio = periodo.inicio.toLocalTime();
        this.horaFim = periodo.fim.toLocalTime();
        this.dataAlocacao = periodo.inicio.toLocalDate();
        this.equipamentos = equipamentos;
        this.ativa = false;
    }
    //  </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters/Setters">

    public Periodo getPeriodo() {
        return new Periodo(
                LocalDateTime.of(dataAlocacao, horaInicio),
                LocalDateTime.of(dataAlocacao, horaFim)
        );
    }

    public LocalDate getDataAlocacao() {
        return dataAlocacao;
    }

    public Horario getHorario() {
        return new Horario(horaInicio, horaFim);
    }

    public DayOfWeek getDiaSemana() {
        return dataAlocacao.getDayOfWeek();
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public String getAssunto() {
        return assunto;
    }

    public Sala getSala() {
        return sala;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public boolean isAtiva() {
        return ativa;
    }


    // </editor-fold>

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reserva reserva)) return false;
        return getPeriodo().equals(reserva.getPeriodo()) && getSala().equals(reserva.getSala()) && getFuncionario().equals(reserva.getFuncionario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPeriodo(), getSala(), getFuncionario(), getPrioridade());
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "assunto='" + assunto + '\'' +
                ", prioridade=" + getPrioridade() +
                ", sala=" + sala +
                ", funcionario=" + funcionario +
                ", ativa=" + ativa +
                ", periodo: "  + getDataAlocacao()  + " "  + getHorario() +
                '}';
    }
}
