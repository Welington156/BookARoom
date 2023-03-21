package dados.teste;

import com.example.bookaroom.campus.Campus;
import com.example.bookaroom.campus.Equipamento;
import com.example.bookaroom.campus.Predio;
import com.example.bookaroom.campus.Sala;
import com.example.bookaroom.campus.Funcionario;
import com.example.bookaroom.reserva.Reserva;
import com.example.bookaroom.util.Periodo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class DataSource {
    private static final String AMANHA = LocalDate.now().plusDays(1).format(Periodo.FORMATO_DATA);
    private static final List<Campus> campi;
    private static final List<Reserva> reservas;

    static {
        campi = new ArrayList<>();
        reservas = new ArrayList<>();

        dadosIniciais();
    }

    public static List<Reserva> getReservas() {
        return List.copyOf(reservas);
    }

    public static Campus getCampus(String nome) {
        return campi.stream()
                .filter(campus -> campus.getNome().equals(nome))
                .findFirst()
                .orElse(null);
    }


    public static void addReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public static Reserva updateReserva(Reserva reserva) {
        int index = reservas.indexOf(reserva);
        if(index != -1) {
            reservas.remove(index);
            reservas.add(reserva);
            return reserva;
        }
        return null;
    }

    private static void dadosIniciais() {
        // Campus


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
        // Reservas Neila

        // -- Calc 1

        addReserva(new Reserva(
                new Periodo(AMANHA, "08:20", "09:00"),
                neila,
                salasTI.get(0),
                "Calc 1",
                new ArrayList<>()
        ));

        addReserva(new Reserva(
                new Periodo(AMANHA, "15:00", "17:40"),
                neila,
                salasTI.get(0),
                "Calc 1",
                new ArrayList<>()
        ));

        // -- Matematica
        addReserva(new Reserva(
                new Periodo(AMANHA, "09:20", "11:00"),
                neila,
                salasMedio.get(1),
                "Matematica",
                new ArrayList<>()
        ));

        addReserva(new Reserva(
                new Periodo(AMANHA, "11:00", "12:40"),
                neila,
                salasMedio.get(1),
                "Matematica",
                new ArrayList<>()
        ));

        // Reservas Lucio
        addReserva(new Reserva(
                new Periodo(AMANHA, "11:00", "12:40"),
                lucio,
                salasMedio.get(0),
                "Banco de Dados",
                new ArrayList<>()
        ));

        addReserva(new Reserva(
                new Periodo(AMANHA, "15:20", "17:00"),
                lucio,
                salasTI.get(1),
                "Banco de Dados",
                new ArrayList<>()
        ));

        campi.add(new Campus(
                "MOCHELL",
                "Rua 2",
                predios,
                funcionarios,
                equipamentos));
    }

}
