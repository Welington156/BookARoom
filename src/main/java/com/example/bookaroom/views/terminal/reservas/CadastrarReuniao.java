package com.example.bookaroom.views.terminal.reservas;

import com.example.bookaroom.bookaroom.campus.Sala;
import com.example.bookaroom.bookaroom.periodo.Periodo;
import com.example.bookaroom.bookaroom.reserva.Reserva;
import com.example.bookaroom.views.terminal.Terminal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class CadastrarReuniao implements Runnable {

    public LocalDate readData(Scanner scanner) {
        String message = "Selecione a data:\n<dd/MM/yyyy>";

        while(true) {
            System.out.println(message);
            try {
                String input = scanner.nextLine();
                return LocalDate.parse(input, Periodo.FORMATO_DATA);
            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido");
            }
        }
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        LocalTime[] horarios = CadastrarReserva.readHorarios(scanner);
        LocalDate data = readData(scanner);

        Periodo periodo = new Periodo(data, horarios[0], horarios[1]);

        HashMap<Sala, List<Reserva>> reservasPorSala = Terminal.bookARoomApi().reservasPorSala(periodo);

        Sala sala = CadastrarReserva.readSala(reservasPorSala, scanner);

        String assunto = CadastrarReserva.readAssunto(scanner);

        try {
            Terminal.bookARoomApi().cadastrarReuniao(periodo, sala, assunto);
            System.out.println("Reunião cadastrada com sucesso!");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado");
        }

        scanner.close();
    }
}
