package com.example.bookaroom.views.terminal.reservas;

import com.example.bookaroom.bookaroom.reserva.Reserva;
import com.example.bookaroom.views.terminal.Terminal;

import java.util.List;

public class MinhasReservas implements Runnable {
    @Override
    public void run() {
         List<Reserva> reservas = Terminal.bookARoomApi().getMinhasReservas();
         reservas.forEach(System.out::println);
    }
}
