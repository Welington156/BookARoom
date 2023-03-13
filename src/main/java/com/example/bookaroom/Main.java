package com.example.bookaroom;

import com.example.bookaroom.campus.CampusControlador;
import com.example.bookaroom.funcionario.SessionControlador;
import com.example.bookaroom.reserva.Reserva;
import com.example.bookaroom.reserva.ReservaBuilder;
import com.example.bookaroom.reserva.ReservaControlador;
import com.example.bookaroom.views.FazerReserva;
import com.example.bookaroom.views.MinhasReservas;
import com.example.bookaroom.views.widgets.ReservaTitledPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        SessionControlador.createSession("Lucio");

        ReservaBuilder reservaBuilder = new ReservaBuilder();

        reservaBuilder
                .setFuncionario(SessionControlador.getFuncionario())
                .setAssunto("Eng. Software")
                .setSala(CampusControlador.getSalas().get(0))
                .setPeriodo("14/03/2023", "11:00", "12:40");

        ReservaControlador.novaReserva(reservaBuilder);

        BorderPane root = new BorderPane();
        root.setPrefWidth(800);
        root.setPrefHeight(600);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(String.valueOf(getClass().getResource("accordion.css")));

        Accordion accordion = new Accordion();
        List<Reserva> reservas = ReservaControlador.getReservas();
        reservas.forEach(reserva -> accordion.getPanes().add(new ReservaTitledPane(reserva)));

        root.setLeft(accordion);
        root.setBottom(new FazerReserva());
        root.setRight(new MinhasReservas());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}


/*
* // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("accordion.fxml"));
        // Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Campus campus = new Campus("MOC", "asdasdas");

        Predio predio = new Predio("Predio 1");
        predio.setCampus(campus);

        Predio predio2 = new Predio("Predio 2");
        predio2.setCampus(campus);

        Predio predio3 = new Predio("Predio 3");
        predio3.setCampus(campus);

        Sala sala = new Sala();
        sala.setNumero("08");
        sala.setCapacidade(12);
        sala.setPredio(predio);

        Sala sala2 = new Sala();
        sala2.setNumero("02");
        sala2.setCapacidade(12);
        sala2.setPredio(predio2);

        Sala sala3 = new Sala();
        sala3.setNumero("03");
        sala3.setCapacidade(12);
        sala3.setPredio(predio2);

        Sala sala4 =  new Sala();
        sala4.setNumero("04");
        sala4.setCapacidade(12);
        sala4.setPredio(predio3);

        Reserva reserva = new Reserva();
        reserva.setSala(sala);
        reserva.setAssunto("Um assunto qualquer");
        reserva.setDataAlocacao(LocalDate.now());
        reserva.setHoraFim(LocalTime.now());
        reserva.setHoraInicio(LocalTime.now());

        BorderPane root = new BorderPane();
        root.setPrefWidth(800);
        root.setPrefHeight(600);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(String.valueOf(getClass().getResource("accordion.css")));
        ReservaTitledPane rtp = new ReservaTitledPane(reserva);

//        HBox titledPaneHeader = new HBox(new CheckBox(), new Label("Title"));
//        titledPaneHeader.setSpacing(10);
//
//        TitledPane titledPane = new TitledPane();
//        titledPane.setGraphic(titledPaneHeader);
//        titledPane.setContent(new Label("Content"));

        Accordion accordion = new Accordion(
                rtp,
                new ReservaTitledPane(reserva),
                new ReservaTitledPane(reserva),
                new ReservaTitledPane(reserva)
        );

        accordion.setPrefHeight(400);
        accordion.setPrefWidth(400);

        root.setLeft(accordion);
        root.setRight(new FazerReserva());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
* */