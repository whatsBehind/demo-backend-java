package com.whatsbehind.thread_.start;

public class InvokesRun {
    public static void main(String[] args) {
        System.out.println("Thread for main method " + Thread.currentThread().getName());
        RunThread runThread = new RunThread();
        StartThread startThread = new StartThread();
        runThread.run();
        startThread.start();
    }
}

class RunThread extends Thread {
    public void run() {
        System.out.println("Thread for RunThread: " + Thread.currentThread().getName());
    }
}

class StartThread extends Thread {
    public void run() {
        System.out.println("Thread for StartThread: " + Thread.currentThread().getName());
    }
}
