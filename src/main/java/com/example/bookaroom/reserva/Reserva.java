package com.example.bookaroom.reserva;

import com.example.bookaroom.campus.Equipamento;
import com.example.bookaroom.funcionario.Funcionario;
import com.example.bookaroom.campus.Sala;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Reserva {
    private LocalDate dataAlocacao;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private String assunto;
    private Sala sala;
    private Funcionario funcionario;
    private List<Equipamento> equipamentos;

    public boolean ativo() {
        return dataAlocacao.equals(LocalDate.now())
                && horaFim.compareTo(LocalTime.now()) < 1;
    }

    public Reserva() {}

    public Reserva(Reserva reserva) {
        this();
        this.funcionario = reserva.funcionario;
        this.sala = reserva.sala;
        this.assunto = reserva.assunto;
        this.horaInicio = reserva.horaInicio;
        this.horaFim = reserva.horaFim;
        this.dataAlocacao = reserva.dataAlocacao;
        this.equipamentos = reserva.equipamentos;
    }

    // <editor-fold defaultstate="collapsed" desc="Getters/Setters">

    public LocalDate getDataAlocacao() {
        return dataAlocacao;
    }

    public void setDataAlocacao(LocalDate dataAlocacao) {
        this.dataAlocacao = dataAlocacao;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }
    // </editor-fold>
}
