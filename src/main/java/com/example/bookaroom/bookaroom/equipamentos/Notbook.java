package com.example.bookaroom.bookaroom.equipamentos;

public class Notbook extends Equipamento{
    String especificacoes;

    public Notbook(String nome, String especificacoes) {
        super(nome);
        this.especificacoes = especificacoes;
    }
}
