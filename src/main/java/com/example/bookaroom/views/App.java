package com.example.bookaroom.views;

import com.example.bookaroom.funcionario.SessionControlador;
import com.example.bookaroom.views.screens.LoginScreen;
import com.example.bookaroom.views.screens.ReservaScreen;
import javafx.application.Application;
import javafx.stage.Stage;

public class  App extends Application {
    public static Stage stage;

    @Override
    public void start(Stage firstStage) {
        stage = firstStage;

        firstStage.setTitle("Book a Room");
        firstStage.setScene(new LoginScreen());
        firstStage.show();
    }

    public static void loadReservaScreen() {
        stage.setScene(new ReservaScreen());
    }
}
