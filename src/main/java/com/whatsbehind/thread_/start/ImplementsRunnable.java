package com.whatsbehind.thread_.start;

public class ImplementsRunnable {
    public static void main(String[] args) {
        Thread thread = new Thread(new StartThread_2());
        thread.start();
    }
}

class StartThread_2 implements Runnable {
    public void run() {
        System.out.println("Hello World!");
    }
}
