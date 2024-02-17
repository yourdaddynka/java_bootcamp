package org.example;

public class Menu {
    private static final String flag = "--count=";
    private int count = 0;

    public void run(String args) {
        if (parseFlag(args)) {
            new JThread("Hen",count).start();
            new JThread("Agg",count).start();
            for(int i = 0;i < count;i++){
                System.out.println("\"Human\" = " + "Human");
            }
        }
    }
    private boolean parseFlag(String args) {
        if (args.substring(0, flag.length()).equals(flag)) {
            try {
                int num = Integer.parseInt(args.substring(flag.length()));
                if(num >0) {count = num;}
            } catch (NumberFormatException e) {
                System.err.println("Error: " + args.substring(flag.length()) + " cannot be parsed into an integer.");
                return false;
            }
        }
        return true;
    }
}
