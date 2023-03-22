package com.example.bookaroom.campus;

import java.util.List;

public class Campus {
    private final String nome;
    private final String endereco;
    private List<Predio> predios;
    private final List<Funcionario> funcionarios;
    private final List<Equipamento> equipamentos;

    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public Campus(String nome, String endereco, List<Predio> predios, List<Funcionario> funcionarios, List<Equipamento> equipamentos) {
        this.nome = nome;
        this.endereco = endereco;
        setPredios(predios);
        this.funcionarios = funcionarios;
        this.equipamentos = equipamentos;
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

    protected void setPredios(List<Predio> predios) {
        predios.forEach(predio -> predio.setCampus(this));
        this.predios = predios;
    }

    // </editor-fold>


    @Override
    public String toString() {
        return getNome();
    }
}
