package org.example;

public class JThread extends Thread {
    private final int count;
    private final String names;

    public JThread(String name, int count) {
        this.names = name;
        this.count = count;
    }
    @Override
    public void run(){
        for (int i = 0; i < count;i++){
            System.out.println("names = " + names);
        }
    }
}
