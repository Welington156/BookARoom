package com.example.bookaroom.reserva;

import com.example.bookaroom.campus.Equipamento;
import com.example.bookaroom.funcionario.Funcionario;
import com.example.bookaroom.campus.Sala;
import com.example.bookaroom.util.Periodo;

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
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc="Constructor">

    public Reserva() {}

    public Reserva(Reserva reserva) {
        this.funcionario = reserva.funcionario;
        this.sala = reserva.sala;
        this.assunto = reserva.assunto;
        this.horaInicio = reserva.horaInicio;
        this.horaFim = reserva.horaFim;
        this.dataAlocacao = reserva.dataAlocacao;
        this.equipamentos = reserva.equipamentos;
    }

    //  </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters/Setters">

    public Periodo getPeriodo() {
        return new Periodo(getDataAlocacao(), getHoraInicio(), getHoraFim());
    }

    public LocalDate getDataAlocacao() {
        return dataAlocacao;
    }

    protected void setDataAlocacao(LocalDate dataAlocacao) {
        this.dataAlocacao = dataAlocacao;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    protected void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    protected void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    public String getAssunto() {
        return assunto;
    }

    protected void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public Sala getSala() {
        return sala;
    }

    protected void setSala(Sala sala) {
        this.sala = sala;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    protected void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    protected void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    protected void addEquipamento(Equipamento equipamento) { this.equipamentos.add(equipamento); }
    // </editor-fold>
}
