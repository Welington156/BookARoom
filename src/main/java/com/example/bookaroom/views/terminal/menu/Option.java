package com.example.bookaroom.views.terminal.menu;

public class Option implements Runnable {
    public String name;
    public Runnable runnable;

    public Option(String name, Runnable runnable) {
        this.name = name;
        this.runnable = runnable;
    }

    @Override
    public void run() {
        runnable.run();
    }
}
