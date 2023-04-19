package com.example.bookaroom.views.terminal;

import com.example.bookaroom.views.gui.App;
import javafx.application.Application;

public class OpenGUI implements Runnable{

    @Override
    public void run() {
        Application.launch(App.class);
    }
}
