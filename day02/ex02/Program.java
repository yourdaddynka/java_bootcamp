package ex01;
public class Program {
    public static void main(String[] args) {
        if (args.length == 1) {
            RunMenu work = new RunMenu();
            work.start(args);
        }
    }
}