package com.example.bookaroom.reserva;

import com.example.bookaroom.campus.Equipamento;
import com.example.bookaroom.campus.Funcionario;
import com.example.bookaroom.campus.Sala;
import com.example.bookaroom.util.Periodo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Reserva implements Comparable<Reserva> {
    private LocalDate dataAlocacao;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private String assunto;
    private Sala sala;
    private Funcionario funcionario;
    private List<Equipamento> equipamentos;


    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Reserva other)) {
            return false;
        }

        return getPeriodo().equals(other.getPeriodo())
                && getFuncionario().equals(other.funcionario)
                && getSala().equals(other.sala);
    }

    @Override
    public int compareTo(Reserva o) {
        return getPeriodo().compareTo(o.getPeriodo());
    }

    // <editor-fold defaultstate="collapsed" desc="Constructor">

    public Reserva(Periodo periodo, Funcionario funcionario, Sala sala, String assunto, ArrayList<Equipamento> equipamentos) {
        this.funcionario = funcionario;
        this.sala = sala;
        this.assunto = assunto;
        this.horaInicio = periodo.horaInicio;
        this.horaFim = periodo.horaFim;
        this.dataAlocacao = periodo.data;
        this.equipamentos = equipamentos;
    }

    public Reserva() {}

    //  </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters/Setters">

    public Periodo getPeriodo() {
        return new Periodo(getDataAlocacao(), getHoraInicio(), getHoraFim());
    }

    public LocalDate getDataAlocacao() {
        return dataAlocacao;
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

    // </editor-fold>
}
