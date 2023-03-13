package com.example.bookaroom.campus;

import java.util.List;

public class Predio {
    private String nome;
    private Campus campus;
    private List<Sala> salas;

    public Predio(String nome, List<Sala> salas) {
        this.nome = nome;
        setSalas(salas);
    }

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public void setSalas(List<Sala> salas) {
        salas.forEach(sala -> sala.setPredio(this));
        this.salas = salas;
    }
    //    </editor-fold>
}
