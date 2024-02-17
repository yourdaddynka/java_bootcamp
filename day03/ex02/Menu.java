package ex02;

public class Menu {
    private static final String flag = "--arraySize=";
    private static final String threadsFlag = "--threadsCount=";
    private int countSize;
    private int countThreads;
    private int[] array;
    private int totalSum;

    public void run(String args1, String args2) {
        if (parseFlag(args1) && parseThreads(args2)) {
            randomArr();
            createThreads();
        }
    }

    private void createThreads() {
        int sizExceptLast = (int) Math.ceil(countSize * 1.0 / countThreads);
        Thread[] threads = new Thread[countThreads];
        for (int i = 0; i < countThreads; i++) {
            if (i == countThreads - 1) {
                threads[i] = new Thread(new ThreadAllJob(Integer.toString(i + 1), array, sizExceptLast * i, array.length, this));
            } else {
                threads[i] = new Thread(new ThreadAllJob(Integer.toString(i + 1), array, sizExceptLast * i, (sizExceptLast * i) + sizExceptLast, this));
            }
            threads[i].start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Total sum: " + getTotalSum());
    }

    public synchronized void addToTotal(int value) {
        totalSum += value;
    }

    public int getTotalSum() {
        return totalSum;
    }

    private void randomArr() {
        array = new int[countSize];
        for (int i = 0; i < countSize; i++) {
            double random = Math.random() * 1000;
            array[i] = (int) random;
        }
    }

    private boolean parseThreads(String args) {
        if (args.substring(0, threadsFlag.length()).equals(threadsFlag)) {
            try {
                int num = Integer.parseInt(args.substring(threadsFlag.length()));
                if (num > 0) {
                    countThreads = num;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: " + args.substring(flag.length()) + " cannot be parsed into an integer.");
                return false;
            }
        }
        return true;
    }

    private boolean parseFlag(String args) {
        if (args.substring(0, flag.length()).equals(flag)) {
            try {
                int num = Integer.parseInt(args.substring(flag.length()));
                if (num > 0) {
                    countSize = num;
                }
            } catch (NumberFormatException e) {
                System.err.println("Error: " + args.substring(flag.length()) + " cannot be parsed into an integer.");
                return false;
            }
        }
        return true;
    }
}
