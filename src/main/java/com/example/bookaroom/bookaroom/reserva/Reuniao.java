package com.example.bookaroom.bookaroom.reserva;

import com.example.bookaroom.bookaroom.campus.Funcionario;
import com.example.bookaroom.bookaroom.campus.Sala;
import com.example.bookaroom.bookaroom.equipamentos.Equipamento;
import com.example.bookaroom.bookaroom.periodo.Periodo;

import java.util.Collection;
import java.util.List;

public class Reuniao extends Reserva{
    static final int PRIORIDADE = 0;

    public Reuniao(Periodo periodo, Funcionario funcionario, Sala sala, String assunto, List<Equipamento> equipamentos) {
        super(periodo, funcionario, sala, assunto, equipamentos);
    }

    @Override
    public int getPrioridade() {
        return PRIORIDADE;
    }
}
