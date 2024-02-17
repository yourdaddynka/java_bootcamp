package ex03;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static final String flag = "--threadsCount=";
    private static final String fileName = "files_urls.txt";
    private int countThreads;
    List<String> urls;

    public void run(String args1) {
        if (parseThreads(args1) && parseFile()) {
            createThreads();
        }
    }

    public int getCountThreads() {
        return countThreads;
    }

    private void createThreads() {
        Thread[] threads = new Thread[countThreads];
        for (int i = 0; i < countThreads; i++) {
            Downloader temp = new Downloader(Integer.toString(i + 1), urls, i, this);
            threads[i] = new Thread(temp);
            threads[i].start();
        }
        for (int i = 0; i < countThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private boolean parseFile() {
        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            Scanner in = new Scanner(fileInputStream);
            urls = new ArrayList<>();
            while (in.hasNextLine()) {
                String line = in.nextLine();
                urls.add(line);
            }
            in.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
            return false;
        }
        return true;
    }

    private boolean parseThreads(String args) {
        if (args.substring(0, flag.length()).equals(flag)) {
            try {
                int num = Integer.parseInt(args.substring(flag.length()));
                if (num > 0) {
                    countThreads = num;
                }
            } catch (NumberFormatException e) {
                System.err.println("Error: " + args.substring(flag.length()) + " cannot be parsed into an integer.");
                return false;
            }
        }
        return true;
    }
}
