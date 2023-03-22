package com.example.bookaroom.campus;

import com.example.bookaroom.reserva.Reservavel;
import com.example.bookaroom.reserva.Reserva;

public class Sala implements Reservavel {
    private Predio predio;
    private final String numero;
    private final Integer capacidade;

    // <editor-fold defaultstate="collapsed" desc="ItemReservavel">
    @Override
    public boolean contidoEm(Reserva reserva) {
        return reserva.getSala().equals(this);
    }
    // </editor-fold>

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

    public Integer getCapacidade() {
        return capacidade;
    }

    public Predio getPredio() {
        return predio;
    }

    protected void setPredio(Predio predio) {
        this.predio = predio;
    }

    // </editor-fold>


    @Override
    public String toString() {
        return this.getPredio().toString() + " - " + this.numero;
    }
}
