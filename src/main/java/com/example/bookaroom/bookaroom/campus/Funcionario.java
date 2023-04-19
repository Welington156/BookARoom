package com.example.bookaroom.bookaroom.campus;

import com.example.bookaroom.bookaroom.reserva.Reserva;
import com.example.bookaroom.bookaroom.reserva.Reservavel;

import java.io.Serializable;
import java.util.Objects;

public class Funcionario implements Reservavel, Serializable {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Funcionario that)) return false;
        return getNome().equals(that.getNome()) && getCargo().equals(that.getCargo()) && getRamal().equals(that.getRamal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getCargo(), getRamal());
    }
}
