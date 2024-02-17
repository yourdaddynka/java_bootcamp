package ex01;

public class ParseThread {
    private enum type {AGG, HEN}

    private type temp;

    public ParseThread() {
        temp = type.AGG;
    }

    public void setTemp(type temp) {
        this.temp = temp;
    }

    public synchronized void printHen() {
        if (!temp.equals(type.HEN)) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Agg");
        setTemp(type.AGG);
        notify();


    }

    public synchronized void printAgg() {
        if (!temp.equals(type.AGG)) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Hen");
        setTemp(type.HEN);
        notify();
    }
}
