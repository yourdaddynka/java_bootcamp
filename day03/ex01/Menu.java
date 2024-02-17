package ex01;

public class Menu {
    private static final String flag = "--count=";
    private int count;
    private ParseThread parseThread;

    public void run(String args) {
        if (parseFlag(args)) {
            parseThread = new ParseThread();
            new Thread(new Variative(count,parseThread,"Hen")).start();
            new Thread(new Variative(count,parseThread,"Agg")).start();
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
