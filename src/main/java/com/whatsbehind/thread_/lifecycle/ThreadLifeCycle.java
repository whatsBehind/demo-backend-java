package com.whatsbehind.thread_.lifecycle;

public class ThreadLifeCycle {
    public static void main(String[] args) throws InterruptedException {
        LifeCycleThread thread = new LifeCycleThread();
        System.out.println("State of a new Thread: " + thread.getState());
        thread.start();
        Thread.sleep(50);
        System.out.println("LifeCycleThread is sleeping. Thread state: " + thread.getState());
        Thread.sleep(120);
        System.out.println("LifeCycleThread finished. Thread state: " + thread.getState());
    }

}

class LifeCycleThread extends Thread {
    public void run() {
        System.out.println("Thread started. Thread state: " + this.getState());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

/*
 * Executing results:
 * State of a new Thread: NEW
 * Thread started. Thread state: RUNNABLE
 * LifeCycleThread is sleeping. Thread state: TIMED_WAITING
 * LifeCycleThread finished. Thread state: TERMINATED
 * */



