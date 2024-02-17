package ex02;

public class Program {
    public static void main(String[] args) {
        if (args.length == 2) {
            Menu menu = new Menu();
            menu.run(args[0], args[1]);
        } else System.out.println("incorrect param");
    }
}