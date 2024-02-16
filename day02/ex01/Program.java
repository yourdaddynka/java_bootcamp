package ex01;
public class Program {
    public static void main(String[] args) {
        if (args.length == 2) {
            RunMenu work = new RunMenu();
            work.start(args[0],args[1]);
        }
    }
}