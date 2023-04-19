package com.example.bookaroom.bookaroom.reserva;

import com.example.bookaroom.bookaroom.campus.Funcionario;
import com.example.bookaroom.bookaroom.campus.Sala;
import com.example.bookaroom.bookaroom.equipamentos.Equipamento;
import com.example.bookaroom.bookaroom.periodo.Periodo;

import java.time.LocalDate;
import java.util.List;

public class Aula extends ReservaRecorrente {
    static final int PRIORIDADE = 1;
    static final int DIAS_ENTRE_RECORRENCIAS = 7;

    public Aula(Periodo periodo, Funcionario funcionario, Sala sala, String assunto, List<Equipamento> equipamentos, LocalDate dataExpiracao) {
        super(periodo, funcionario, sala, assunto, equipamentos, dataExpiracao);
    }

    @Override
    public int getPrioridade() {
        return PRIORIDADE;
    }

    @Override
    public int diasEntreRecorrencias() {
        return DIAS_ENTRE_RECORRENCIAS;
    }
}
