package com.example.bookaroom.campus;

import com.example.bookaroom.reserva.Reservavel;
import com.example.bookaroom.reserva.Reserva;

public class Equipamento implements Reservavel {
    private final String nome;
    private final String patrimonio;

    // <editor-fold defaultstate="collapsed" desc="ItemReservavel">
    @Override
    public boolean contidoEm(Reserva reserva) {
        return reserva.getEquipamentos().contains(this);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public Equipamento(String nome, String patrimonio) {
        this.nome = nome;
        this.patrimonio = patrimonio;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getNome() {
        return nome;
    }

    public String getPatrimonio() {
        return patrimonio;
    }

    // </editor-fold>


    @Override
    public String toString() {
        return getNome() + " - " + getPatrimonio();
    }
}
