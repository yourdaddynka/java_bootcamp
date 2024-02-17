package ex02;

public class ThreadAllJob implements Runnable {
    private String myName;
    private int[] myArr;
    private int result;
    private int first;
    private int last;
    private Menu menu;

    public ThreadAllJob(String myName, int[] myArr, int first, int last, Menu menu) {
        this.myName = myName;
        this.myArr = myArr;
        this.first = first;
        this.last = last;
        this.menu = menu;
    }

    @Override
    public void run() {
        for (int i = getFirst(); i < getLast() && i < getMyArr().length; i++) {
            result += myArr[i];
        }
        getMenu().addToTotal(result);
        System.out.println("Thread " + getMyName() + ": from " + getFirst() + " to " + (getLast() - 1) + " sum is " + getResult());
    }

    public String getMyName() {
        return myName;
    }

    public Menu getMenu() {
        return menu;
    }

    public int[] getMyArr() {
        return myArr;
    }

    public int getResult() {
        return result;
    }

    public int getFirst() {
        return first;
    }

    public int getLast() {
        return last;
    }

}
