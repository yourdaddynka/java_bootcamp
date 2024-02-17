package edu.school21.printer.app;

import edu.school21.printer.logic.Menu;

public class Main {
    public static void main(String[] args) {
        if (args.length == 2) {
            Menu menu = new Menu();
            menu.run(args);
        } else
            System.out.println("incorrect input");
    }
}