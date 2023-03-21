package com.example.bookaroom.campus;

import com.example.bookaroom.reserva.Reservavel;
import com.example.bookaroom.reserva.Reserva;

public class Funcionario implements Reservavel {
    private final String nome;
    private final String cargo;
    private final String ramal;

    public Funcionario(String nome, String cargo, String ramal) {
        this.nome = nome;
        this.cargo = cargo;
        this.ramal = ramal;
    }

    // <editor-fold defaultstate="collapsed" desc="ItemReservavel">
    @Override
    public boolean contidoEm(Reserva reserva) {
        return reserva.getFuncionario().equals(this);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getNome() {
        return nome;
    }

    public String getCargo() {
        return cargo;
    }

    public String getRamal() {
        return ramal;
    }


    // </editor-fold>
}
