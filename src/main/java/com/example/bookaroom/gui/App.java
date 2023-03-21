package com.example.bookaroom.gui;

import com.example.bookaroom.BookARoom;
import com.example.bookaroom.gui.screens.EditarReservaScreen;
import com.example.bookaroom.gui.screens.LoginScreen;
import com.example.bookaroom.gui.screens.NovaReservaScreen;
import com.example.bookaroom.reserva.Reserva;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    public static Stage stage;

    public static String campus = "";
    public static String funcionario = "";

    public static BookARoom bookARoomApi() {
        return new BookARoom(campus, funcionario);
    }

    @Override
    public void start(Stage firstStage) {
        stage = firstStage;

        firstStage.setTitle("Book a Room");
        firstStage.setScene(new LoginScreen());
        firstStage.show();
    }

    public static void login(String nomeCampus, String nomeFuncionario) {
        campus = nomeCampus;
        funcionario = nomeFuncionario;

        App.bookARoomApi();
        toNovaReservaScreen();
    }

    public static void loggout() {
        campus = "";
        funcionario = "";

        stage.setScene(new LoginScreen());
    }

    public static void toNovaReservaScreen() {
        stage.setScene(new NovaReservaScreen());
    }

    public static void toEditarReservaScreen(Reserva reserva) {
        stage.setScene(new EditarReservaScreen(reserva));
    }
}
