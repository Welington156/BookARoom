package com.example.bookaroom.bookaroom.equipamentos;

import com.example.bookaroom.bookaroom.campus.Campus;
import com.example.bookaroom.dados.DataSource;

import java.util.ArrayList;
import java.util.List;

public class EquipamentoControlador {
    Campus campus;
    public EquipamentoControlador(Campus campus) {
        this.campus = campus;
    }

    public String patrimonio(String campusNome, int number) {
        return campusNome + "-equip-" + number;
    }

    public Equipamento cadastrarEquipamento(Equipamento equipamento) {
//        DataSource.transaction(this.campus, campus -> {
//            List<Equipamento> equipamentos = new ArrayList<>(campus.getEquipamentos());
//
//            equipamento.patrimonio = patrimonio(campus.getNome(), equipamentos.size());
//            equipamentos.add(equipamento);
//
//            campus.setEquipamentos(equipamentos);
//        });

        return equipamento;
    }
}
