package com.whatsbehind.thread_.lifecycle;

public class BlockedState {
    public static void main(String[] args) throws InterruptedException {
        BlockedStateThread thread1 = new BlockedStateThread();
        BlockedStateThread thread2 = new BlockedStateThread();
        thread1.setName("Thread1");
        thread2.setName("Thread2");
        thread1.start();
        thread2.start();
        Thread.sleep(50);
        System.out.println("Thread1 state: " + thread1.getState());
        System.out.println("Thread2 state: " + thread2.getState());
    }
}

class BlockedStateThread extends Thread {
    static int count = 0;

    @Override
    public void run() {
        synchronized (BlockedStateThread.class) {
            System.out.println("Which thread has the lock now: " + Thread.currentThread().getName());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            count++;
        }
    }
}

/*
 * Executing results:
 * Which thread has the lock now: Thread1
 * Thread1 state: TIMED_WAITING
 * Thread2 state: BLOCKED
 * Which thread has the lock now: Thread2
 * */