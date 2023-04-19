package com.example.bookaroom.views.terminal.menu;

import java.util.HashMap;
import java.util.Scanner;

public class Menu implements Runnable {
    private final String title;
    private final HashMap<Integer, Option> options;

    public Menu(String title, Option...options) {
        this.options = new HashMap<>();
        this.title = title;

        int i = 0;
        for(Option option : options) {
            this.options.put(++i, option);
        }
    }

    private void printOptions() {
        options.forEach((key, option) -> System.out.println(key + ". " + option.name));
    }

    private void waitInput() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                int userInput = Integer.parseInt(scanner.nextLine());
                if(options.get(userInput) != null) {
                    options.get(userInput).run();
                    break;
                } else {
                    System.out.println("Opcao invalida, tente novamente");
                    printOptions();
                }
            } catch (NumberFormatException  e) {
                System.out.println("Input invalido, tente novamente");
                printOptions();
            }
        }
        scanner.close();
    }

    @Override
    public void run() {
        System.out.println(title);
        printOptions();
        waitInput();
    }
}
