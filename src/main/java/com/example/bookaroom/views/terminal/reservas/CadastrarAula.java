package com.example.bookaroom.views.terminal.reservas;

import com.example.bookaroom.bookaroom.campus.Semestre;
import com.example.bookaroom.bookaroom.periodo.Horario;
import com.example.bookaroom.views.terminal.Terminal;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class CadastrarAula implements Runnable{

    private final HashMap<String, DayOfWeek> dias= new HashMap<>(){{
        put("segunda", DayOfWeek.MONDAY);
        put("terça", DayOfWeek.TUESDAY);
        put("quarta", DayOfWeek.WEDNESDAY);
        put("quinta", DayOfWeek.THURSDAY);
        put("sexta", DayOfWeek.FRIDAY);
        put("sabado", DayOfWeek.SATURDAY);
    }};

    public DayOfWeek readDia(Scanner scanner) {
        String message = "Selecione o dia:\nsegunda | terça | quarta | quinta | sexta | sabado";
        while(true) {
            System.out.println(message);
            String input = scanner.nextLine();
            if(dias.containsKey(input.toLowerCase())) {
                return dias.get(input);
            }
            System.out.println("Selecione um dia válido");
        }
    }

    public Semestre readSemestre(Scanner scanner) {
        String message = "Selecione o semestre:";
        List<Semestre> semestres = Terminal.bookARoomApi().getSemestres();

        HashMap<Integer, Semestre> options = new HashMap<>(){{
            int i = 0;
            for(Semestre semestre : semestres) {
                put(++i, semestre);
                System.out.println(i + " - " + semestre);
            }
        }};

        while(true) {
            System.out.println(message);
            Integer input = Integer.parseInt(scanner.nextLine());
            if(options.containsKey(input)) {
                return options.get(input);
            }
            System.out.println("Selecione um semestre válido");
        }
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        DayOfWeek dia = readDia(scanner);
        LocalTime[] horarios = CadastrarReserva.readHorarios(scanner);

        Horario horario = new Horario(horarios[0], horarios[1]);

        Semestre semestre = readSemestre(scanner);

        // TODO: implementar leitura sala
        CadastrarReserva.readSala(Terminal.bookARoomApi().reservasPorSala(semestre, dia, horario), scanner);

        String assunto = CadastrarReserva.readAssunto(scanner);
        // TODO: implementar cadastro de aula

        scanner.close();
    }
}
