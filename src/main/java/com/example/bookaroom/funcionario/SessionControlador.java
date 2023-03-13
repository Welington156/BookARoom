package com.example.bookaroom.funcionario;

import com.example.bookaroom.campus.CampusControlador;

public class SessionControlador {
    private static Funcionario funcionario = null;

    public static void createSession(String nomeFuncionario) {
        funcionario = CampusControlador.getFuncionario(nomeFuncionario);
    }

    public static Funcionario getFuncionario() {
        return funcionario;
    }
}
