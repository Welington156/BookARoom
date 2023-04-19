package com.example.bookaroom.views.terminal.reservas;

import com.example.bookaroom.bookaroom.campus.Sala;
import com.example.bookaroom.bookaroom.periodo.Periodo;
import com.example.bookaroom.bookaroom.reserva.Reserva;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public abstract class CadastrarReserva {

    public static LocalTime[] readHorarios(Scanner scanner) {
        String message = "Selecione o horário: <HH:mm> <HH:mm>";
        while (true) {
            System.out.println(message);
            try {
                String[] horarios = scanner.nextLine().split(" ");
                return new LocalTime[] {
                        LocalTime.parse(horarios[0], Periodo.FORMATO_HORA),
                        LocalTime.parse(horarios[1], Periodo.FORMATO_HORA)
                };

            } catch (DateTimeParseException e) {
                System.out.println("Input Invalido");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Insira dois periodos");
            }
        }
    }

    public static Sala readSala(HashMap<Sala, List<Reserva>> reservasPorSala, Scanner scanner) {
        HashMap<Integer, Sala> options = new HashMap<>() {{
            int count = 0;
            for(Sala sala: reservasPorSala.keySet()) {
                put(++count, sala);
                System.out.println(count + ". " + sala);
                reservasPorSala.get(sala)
                        .forEach(reserva -> System.out.println("\t" + reserva));
            }
        }};

        while (true) {
            System.out.print("Selecione uma sala: ");
            try {
                int userInput = Integer.parseInt(scanner.nextLine());
                if(options.get(userInput) != null) {
                    return options.get(userInput);
                } else {
                    System.out.println("Opcao invalida, tente novamente");
                }
            } catch (NumberFormatException  e) {
                System.out.println("Input invalido, tente novamente");
            }
        }
    }

    public static String readAssunto(Scanner scanner) {
        String message = "Insira o assunto";
        while(true) {
            System.out.println(message);
            String input = scanner.nextLine();
            if(!input.isEmpty()) {
                return input;
            }
            System.out.println("O Assunto não pode ser vazio");
        }
    }
}
