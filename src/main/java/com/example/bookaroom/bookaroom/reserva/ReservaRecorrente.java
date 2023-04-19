package com.example.bookaroom.bookaroom.reserva;

import com.example.bookaroom.bookaroom.campus.Funcionario;
import com.example.bookaroom.bookaroom.campus.Sala;
import com.example.bookaroom.bookaroom.equipamentos.Equipamento;
import com.example.bookaroom.bookaroom.periodo.Periodo;

import java.time.LocalDate;
import java.util.List;

public abstract class ReservaRecorrente extends Reserva {

    LocalDate dataExpiracao;

    protected ReservaRecorrente(Periodo periodo, Funcionario funcionario, Sala sala, String assunto, List<Equipamento> equipamentos, LocalDate dataExpiracao) {
        super(periodo, funcionario, sala, assunto, equipamentos);
        this.dataExpiracao = dataExpiracao;
    }

    public LocalDate getDataExpiracao() {
        return dataExpiracao;
    }

    public Periodo proximaOcorrenciaApartirDe(LocalDate data) {
        LocalDate dataIter = this.getDataAlocacao();
        while(dataIter.isBefore(data)) {
            if(dataIter.isAfter(dataExpiracao)) return null;
            dataIter = dataIter.plusDays(diasEntreRecorrencias());
        }
        return new Periodo(dataIter, getHoraInicio(), getHoraFim());
    }

    @Override
    public boolean hasOverlap(Periodo p) {
        return proximaOcorrenciaApartirDe(p.inicio.toLocalDate()).overlaps(p);
    }

    abstract public int diasEntreRecorrencias();
}
