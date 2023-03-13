package com.example.bookaroom.campus;

import com.example.bookaroom.funcionario.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class Campus {
    private String nome;
    private String endereco;
    private List<Predio> predios;
    private List<Funcionario> funcionarios;

    private List<Equipamento> equipamentos;

    public Campus(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.predios = new ArrayList<>();
        this.funcionarios = new ArrayList<>();
    }

    // <editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) { this.endereco = endereco; }

    public List<Predio> getPredios() { return predios; }

    public void setPredios(List<Predio> predios) {
        predios.forEach(predio -> predio.setCampus(this));
        this.predios = predios;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    // </editor-fold>
}
