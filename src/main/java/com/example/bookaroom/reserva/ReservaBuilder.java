package com.example.bookaroom.reserva;

import com.example.bookaroom.campus.Equipamento;
import com.example.bookaroom.campus.Sala;
import com.example.bookaroom.funcionario.Funcionario;
import com.example.bookaroom.util.Periodo;

import java.util.List;

public class ReservaBuilder {
    private final Reserva reserva;

    public ReservaBuilder() {
        this(new Reserva());
    }

    public ReservaBuilder(Reserva reserva) {
        this.reserva = new Reserva(reserva);
    }

    public ReservaBuilder setSala(Sala sala) {
        this.reserva.setSala(sala);
        return this;
    }

    public ReservaBuilder setPeriodo (Periodo periodo) {
        this.reserva.setDataAlocacao(periodo.data);
        this.reserva.setHoraInicio(periodo.horaInicio);
        this.reserva.setHoraFim(periodo.horaFim);
        return this;
    }

    public ReservaBuilder setPeriodo(String data, String horaInicio, String horaFim) {
        setPeriodo(new Periodo(data, horaInicio, horaFim));
        return this;
    }

    public ReservaBuilder setAssunto(String assunto) {
        this.reserva.setAssunto(assunto);
        return this;
    }

    public ReservaBuilder setFuncionario(Funcionario funcionario) {
        this.reserva.setFuncionario(funcionario);
        return this;
    }

    public ReservaBuilder setEquipamentos(List<Equipamento> equipamentos) {
        this.reserva.setEquipamentos(equipamentos);
        return this;
    }

    public Reserva get() {
        return new Reserva(this.reserva);
    }

}
