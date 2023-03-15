package dados.teste;

import com.example.bookaroom.campus.Campus;
import com.example.bookaroom.campus.Equipamento;
import com.example.bookaroom.campus.Predio;
import com.example.bookaroom.campus.Sala;
import com.example.bookaroom.funcionario.Funcionario;
import com.example.bookaroom.reserva.Reserva;
import com.example.bookaroom.reserva.ReservaBuilder;
import com.example.bookaroom.util.Periodo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class DataSource {
    private static final String AMANHA = LocalDate.now().plusDays(1).format(Periodo.FORMATO_DATA);
    private static final Campus campus;
    private static final List<Reserva> reservas;

    static {
        campus = new Campus("MOCHELL", "Rua 2");
        reservas = new ArrayList<>();
        dadosIniciais();
    }

    public static List<Reserva> getReservas() {
        return reservas;
    }

    public static Campus getCampus() {
        return campus;
    }

    public static void addReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    private static void dadosIniciais() {

        // Funcionarios
        Funcionario neila = new Funcionario("Neila", "Professor", "20");
        Funcionario lucio = new Funcionario("Lucio", "Professor", "21");
        Funcionario alberto =  new Funcionario("Alberto", "Professor", "22");
        Funcionario danilo =  new Funcionario("Danilo", "Coordenador", "23");
        List<Funcionario> funcionarios = List.of(neila, lucio, alberto, danilo);

        // Equipamentos
        List<Equipamento> equipamentos = new ArrayList<>();
        equipamentos.add(new Equipamento("Data Show", "if-ds001"));
        equipamentos.add(new Equipamento("Data Show", "if-ds002"));

        // Predios / Salas
        List<Sala> salasTI = List.of(
                new Sala("1", 40),
                new Sala("2", 40),
                new Sala("3", 10)
        );

        List<Sala> salasMedio = List.of(
                new Sala("1", 40),
                new Sala("2", 40),
                new Sala("3", 10)
        );

        List<Sala> salasSecretaria = List.of(
                new Sala("1", 40),
                new Sala("2", 40),
                new Sala("3", 10)
        );

        List<Sala> salasPredio3 = List.of(
                new Sala("1", 40),
                new Sala("2", 40),
                new Sala("3", 10)
        );


        List<Predio> predios = List.of(
                new Predio("TI", salasTI),
                new Predio("Medio", salasMedio),
                new Predio("Secretaria", salasSecretaria),
                new Predio("Predio3", salasPredio3)
        );

        // Reservas
        ReservaBuilder reservaBuilder = new ReservaBuilder();
        // Reservas Neila

        // - Calc 1
        reservaBuilder
                .setAssunto("Calc 1")
                .setFuncionario(neila)
                .setSala(salasTI.get(0));

        reservas.add(reservaBuilder.setPeriodo(AMANHA, "08:20", "09:00").get());
        reservas.add(reservaBuilder.setPeriodo(AMANHA, "15:00", "17:40").get());

        // - Matematica
        reservaBuilder
                .setAssunto("Matematica")
                .setSala(salasMedio.get(1));

        reservas.add(reservaBuilder.setPeriodo(AMANHA, "09:20", "11:00").get());
        reservas.add(reservaBuilder.setPeriodo(AMANHA, "11:00", "12:40").get());

        // Reservas Lucio
        reservaBuilder
                .setFuncionario(lucio)
                .setAssunto("Banco de Dados")
                .setSala(salasMedio.get(0))
                .setPeriodo(AMANHA, "11:00", "12:40");

        reservas.add(reservaBuilder.get());

        reservaBuilder
                .setSala(salasTI.get(1))
                .setPeriodo(AMANHA, "15:20", "17:00");

        reservas.add(reservaBuilder.get());


        campus.setFuncionarios(funcionarios);
        campus.setPredios(predios);
        campus.setEquipamentos(equipamentos);
    }

}
