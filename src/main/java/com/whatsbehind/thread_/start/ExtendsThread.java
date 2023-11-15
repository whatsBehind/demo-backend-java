package com.whatsbehind.thread_.start;

public class ExtendsThread {
    public static void main(String[] args) {
        StartThread_1 thread_ = new StartThread_1();
        thread_.start();
    }
}

class StartThread_1 extends Thread {
    public void run() {
        System.out.println("Hello World!");
    }
}