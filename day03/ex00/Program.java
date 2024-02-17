package org.example;

public class Program {
    public static void main(String[] args) {
        if(args.length ==1){
            Menu menu = new Menu();
            menu.run(args[0]);
        }
        else System.out.println("incorrect param");
    }
}