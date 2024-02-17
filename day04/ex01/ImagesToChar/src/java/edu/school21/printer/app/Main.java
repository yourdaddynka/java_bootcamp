package edu.school21.printer.app;

import edu.school21.printer.logic.Menu;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        if(args.length == 2 && args[0].length() == 2){
            menu.run(args[0],args[1]);
        }
        else
        System.out.println("incorrect imput");
    }
}