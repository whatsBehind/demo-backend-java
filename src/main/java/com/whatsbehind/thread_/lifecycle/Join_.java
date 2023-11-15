package com.whatsbehind.thread_.lifecycle;

public class Join_ {
    public static void main(String[] args) throws InterruptedException {
        OuterThread outerThread = new OuterThread();
        outerThread.setName("OuterThread");
        outerThread.start();
        Thread.sleep(50);
        Thread innerThread = outerThread.getInnerThread();
        System.out.println("InnerThread state: " + innerThread.getState());
        System.out.println("OuterThread state: " + outerThread.getState());
    }

}

class OuterThread extends Thread {
    Thread innerThread = new Thread(new InnerThread(), "InnerThread");

    public void run() {

        innerThread.start();
        try {
            innerThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Thread getInnerThread() {
        return innerThread;
    }

    class InnerThread implements Runnable {
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

/*
 * Executing results
 * InnerThread state: TIMED_WAITING
 * OuterThread state: WAITING
 * */
