package com.example.bookaroom.bookaroom.campus;


import com.example.bookaroom.bookaroom.equipamentos.Equipamento;
import com.example.bookaroom.bookaroom.reserva.Reserva;
import com.example.bookaroom.bookaroom.reserva.Reuniao;

import java.io.*;
import java.util.*;

public class Campus implements Serializable {
    private final String nome;
    private final String endereco;
    private List<Predio> predios;
    private final List<Funcionario> funcionarios;
    private List<Equipamento> equipamentos;
    private final List<Reuniao> reunioes;
    private final List<Semestre> semestres;

    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public Campus(
            String nome,
            String endereco,
            List<Predio> predios,
            List<Funcionario> funcionarios,
            List<Equipamento> equipamentos,
            List<Reuniao> reunioes,
            List<Semestre> semestres) {
        this.nome = nome;
        this.endereco = endereco;
        setPredios(predios);
        this.funcionarios = funcionarios;
        this.equipamentos = equipamentos;
        this.reunioes = reunioes;
        this.semestres = semestres;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public List<Predio> getPredios() { return predios; }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = List.copyOf(equipamentos);
    }

    public List<Reuniao> getReunioes() {
        return reunioes;
    }

    public List<Semestre> getSemestres() {
        return semestres;
    }

    public List<Sala> getSalas() {
        List<Sala> salas = new ArrayList<>();
        predios.forEach(predio -> salas.addAll(predio.getSalas()));
        return salas;
    }

    protected void setPredios(List<Predio> predios) {
        predios.forEach(predio -> predio.setCampus(this));
        this.predios = predios;
    }

    public List<Reserva> getReservas() {
        List<Reserva> reservas = new ArrayList<>(reunioes);

        semestres.forEach(semestre -> reservas.addAll(semestre.getAulas()));

        return reservas;
    }

    // </editor-fold>

    @Override
    public String toString() {
        return getNome();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Campus campus)) return false;
        return getNome().equals(campus.getNome()) && getEndereco().equals(campus.getEndereco());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getEndereco());
    }


}
