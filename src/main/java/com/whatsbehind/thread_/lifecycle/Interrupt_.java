package com.whatsbehind.thread_.lifecycle;

public class Interrupt_ {

    public static void main(String[] args) throws InterruptedException {
        InterruptThread thread = new InterruptThread();
        thread.start();
        Thread.sleep(10);
        System.out.println("InterruptThread state: " + thread.getState());
        thread.interrupt();
        Thread.sleep(10);
        System.out.println("InterruptThread was interrupted. Thread state: " + thread.getState());
    }
}

class InterruptThread extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("InterruptThread is sleep");
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("InterruptThread was interrupted");
        }
    }
}

/*
 * Executing results:
 * InterruptThread is sleep
 * InterruptThread state: TIMED_WAITING
 * InterruptThread was interrupted
 * InterruptThread was interrupted. Thread state: TERMINATED
 * */