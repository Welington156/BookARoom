package com.example.bookaroom.views.terminal;

import com.example.bookaroom.bookaroom.BookARoom;
import com.example.bookaroom.views.terminal.menu.Menu;
import com.example.bookaroom.views.terminal.menu.Option;

public class Terminal {
    static String funcionario;
    static String campus;

    public Terminal() {
        Menu menu = new Menu(
                "BookARoom",
                new Option("Login", new Login()),
                new Option("Open Gui", new OpenGUI())
        );

        menu.run();

    }

    public static BookARoom bookARoomApi() {return new BookARoom(campus, funcionario);}

}
