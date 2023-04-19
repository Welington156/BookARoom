package com.example.bookaroom.dados;

import com.example.bookaroom.bookaroom.equipamentos.Equipamento;
import com.example.bookaroom.bookaroom.periodo.Periodo;
import com.example.bookaroom.bookaroom.campus.*;
import com.example.bookaroom.bookaroom.reserva.Reuniao;

import java.io.*;
import java.nio.channels.FileLock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public final class DataSource {
    static class Dados implements Serializable {
        public List<Campus> campi;

        public Dados(List<Campus> campi) {
            this.campi = campi;
        }

        public Campus getCampus(String nome) {
            return campi.stream()
                    .filter(campus -> campus.getNome().equals(nome))
                    .findFirst()
                    .orElse(null);
        }
    }

    private static final String dadosPath = "dados.dat";
    private static final String lockPath = "dados.lock";
    private static FileOutputStream lockFile = null;
    private static FileLock lock = null;

    private static void lockWriteOperations() throws IOException {
        lockFile = new FileOutputStream(lockPath);
        while(lock == null) {
            lock = lockFile.getChannel().tryLock();
        }
    }

    private static void releaseWriteOperations() throws IOException {
        if(lock != null && lock.isValid()) {
            lock.release();
        }
        if(lockFile != null && lockFile.getChannel().isOpen()) {
            lockFile.close();
        }
    }

    public static Campus getCampus(String nome) {
        try {
            return load().getCampus(nome);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public synchronized static void transaction(String campusNome, Consumer<Campus> func)  {
        try {
            lockWriteOperations();

            Dados dados = load();
            Campus campus = dados.getCampus(campusNome);

            func.accept(campus);

            save(dados);

            releaseWriteOperations();
        }  catch (IllegalStateException e) {
            throw e;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    static synchronized Dados load() throws Exception {
        try {
            FileInputStream fileIn = new FileInputStream(dadosPath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Dados dados = (Dados) in.readObject();
            in.close();
            fileIn.close();
            return dados;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new Exception("Error on read");
        }
    }

    static synchronized void save(Dados dados) {
        try {
            FileOutputStream fileOut = new FileOutputStream(dadosPath, false);
            FileLock lock = fileOut.getChannel().tryLock();

            while (lock == null) {
                lock = fileOut.getChannel().tryLock();
            }

            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(dados);

            lock.release();
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void dadosIniciais() {

        String AMANHA = LocalDate.now().plusDays(1).format(Periodo.FORMATO_DATA);
        Dados dados = new Dados(new ArrayList<>());
        // Funcionarios
        Funcionario neila = new Funcionario("Neila", "Professor", "20");
        Funcionario lucio = new Funcionario("Lucio", "Professor", "21");
        Funcionario alberto =  new Funcionario("Alberto", "Professor", "22");
        Funcionario danilo =  new Funcionario("Danilo", "Coordenador", "23");
        List<Funcionario> funcionarios = List.of(neila, lucio, alberto, danilo);

        // Equipamentos
        List<Equipamento> equipamentos = new ArrayList<>();
//        equipamentos.add(new Equipamento("Data Show", "if-ds001"));
//        equipamentos.add(new Equipamento("Data Show", "if-ds002"));

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

        List<Reuniao> reservas = new ArrayList<>();

        reservas.add(new Reuniao(
                new Periodo(AMANHA, "08:20", "09:00"),
                neila,
                salasTI.get(0),
                "Calc 1",
                new ArrayList<>()
        ));

        reservas.add(new Reuniao(
                new Periodo(AMANHA, "15:00", "17:40"),
                neila,
                salasTI.get(0),
                "Calc 1",
                new ArrayList<>()
        ));

        // -- Matematica
        reservas.add(new Reuniao(
                new Periodo(AMANHA, "09:20", "11:00"),
                neila,
                salasMedio.get(1),
                "Matematica",
                new ArrayList<>()
        ));

        reservas.add(new Reuniao(
                new Periodo(AMANHA, "11:00", "12:40"),
                neila,
                salasMedio.get(1),
                "Matematica",
                new ArrayList<>()
        ));

        // Reservas Lucio
        reservas.add(new Reuniao(
                new Periodo(AMANHA, "11:00", "12:40"),
                lucio,
                salasMedio.get(0),
                "Banco de Dados",
                new ArrayList<>()
        ));

        reservas.add(new Reuniao(
                new Periodo(AMANHA, "15:20", "17:00"),
                lucio,
                salasTI.get(1),
                "Banco de Dados",
                new ArrayList<>()
        ));

        Semestre semestre = new Semestre(LocalDate.of(2023, 2, 10), LocalDate.of(2023, 7, 10), List.of());

        dados.campi.add(new Campus(
                "MOCHELL",
                "Rua 2",
                predios,
                funcionarios,
                equipamentos,
                reservas,
                List.of(semestre)
                ));

        save(dados);
    }

}
