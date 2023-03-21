package com.example.bookaroom.reserva;

import com.example.bookaroom.campus.Equipamento;
import dados.teste.DataSource;

import java.util.*;
import java.util.stream.Stream;


public class ReservaControlador {
    List<Reserva> reservas;

    // <editor-fold defaultstate="collapsed" desc="Constructor">

    public ReservaControlador() {
        this(DataSource.getReservas());
    }

    private ReservaControlador(List<Reserva> reservas) { this.reservas = List.copyOf(reservas); }

    // </editor-fold>

    public ReservaControlador filtrarPor(Reservavel ...reservaveis) {

        Stream<Reserva> reservasStream = reservas.stream();
        for(Reservavel reservavel : reservaveis) {
            reservasStream = reservasStream.filter(reservavel::contidoEm);
        }

        return new ReservaControlador(reservasStream.toList());
    }

    public boolean hasAny() {
        return !reservas.isEmpty();
    }

    public List<Reserva> get() {
        return List.copyOf(reservas);
    }


    public static Reserva updateReserva(Reserva reserva) {
        return DataSource.updateReserva(reserva);
    }

    public static Reserva cadastrarReserva(Reserva novaReserva) throws IllegalStateException {
        validarCadastro(novaReserva);
        DataSource.addReserva(novaReserva);

        return novaReserva;
    }

    public static void validarCadastro(Reserva reserva) throws IllegalStateException {
        ReservaControlador reservaControlador = new ReservaControlador().filtrarPor(reserva.getPeriodo());

        if(reservaControlador.filtrarPor(reserva.getSala()).hasAny()) {
            throw new IllegalStateException("Sala ja possui reservas nesse periodo");
        }

        if(reservaControlador.filtrarPor(reserva.getFuncionario()).hasAny()){
            throw new IllegalStateException("Funcionario ja possui reservas nesse periodo");
        }

        reserva.getEquipamentos().forEach(equipamento -> {
            if(reservaControlador.filtrarPor(equipamento).hasAny()) {
                throw new IllegalStateException("Equipamento " + equipamento.getPatrimonio() + " ja possui reservas nesse periodo");
            }

        });
    }
}
