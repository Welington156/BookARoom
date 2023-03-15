package com.example.bookaroom;


import com.example.bookaroom.campus.CampusControlador;
import com.example.bookaroom.campus.Sala;
import com.example.bookaroom.views.App;
import javafx.application.Application;


public class Main {
    public static void main(String[] args) {


        Application.launch(App.class);
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
        scene.getStylesheets().add(String.valueOf(getClass().getResource("bookaroom.css")));
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