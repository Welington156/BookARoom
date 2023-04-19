package com.example.bookaroom.views.terminal;

import com.example.bookaroom.views.terminal.menu.Menu;
import com.example.bookaroom.views.terminal.menu.Option;
import com.example.bookaroom.views.terminal.reservas.CadastrarAula;
import com.example.bookaroom.views.terminal.reservas.CadastrarReuniao;
import com.example.bookaroom.views.terminal.reservas.MinhasReservas;

import java.util.Scanner;

public class Login implements Runnable{

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Entre com: '<campus> <funcionario>'");

            try {
                String[] userInput = scanner.nextLine().split(" ");

                Terminal.campus = userInput[0];
                Terminal.funcionario = userInput[1];

                Terminal.bookARoomApi();
                break;

            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Numero de Argumentos Invalido");
            }
        }

        new Menu(
                "Menu Principal",
                new Option("Cadastrar Aula", new CadastrarAula()),
                new Option("Cadastrar Reunião", new CadastrarReuniao()),
                new Option("Minhas Reservas", new MinhasReservas()),
                new Option("Requisitar Equipamento", null)
        ).run();


    }

}
