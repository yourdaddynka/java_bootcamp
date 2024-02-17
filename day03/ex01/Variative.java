package ex01;

public class Variative implements Runnable {
    private int count;
    private ParseThread flag;
    private String type;

    public Variative(int count, ParseThread flag, String type) {
        this.count = count;
        this.flag = flag;
        this.type = type;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            if (type.equals("Agg")) flag.printAgg();
            else if (type.equals("Hen")) flag.printHen();
        }
    }
}
