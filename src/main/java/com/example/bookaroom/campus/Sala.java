package com.example.bookaroom.campus;

import com.example.bookaroom.campus.Predio;
import com.example.bookaroom.reserva.Reserva;

import java.util.List;

public class Sala {
    private Predio predio;
    private String numero;
    private Integer capacidade;

    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public Sala(String numero, Integer capacidade) {
        this.numero = numero;
        this.capacidade = capacidade;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public Predio getPredio() {
        return predio;
    }

    public void setPredio(Predio predio) {
        this.predio = predio;
    }

    // </editor-fold>
}
