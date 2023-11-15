package com.whatsbehind.thread_.synchronization;

public class ThreadInterference {

    public static void main(String[] args) throws InterruptedException {
        InterferenceThread thread1 = new InterferenceThread();
        InterferenceThread thread2 = new InterferenceThread();
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        int count = InterferenceThread.getCount();
        System.out.println("The value of count in InterferenceThread class is supposed to be 2_000_000, but the real value is: " + count);
    }
}

class InterferenceThread extends Thread {
    static int count = 0;
    int loopCount = 0;

    public static int getCount() {
        return count;
    }

    @Override
    public void run() {
        while (loopCount++ < 1_000_000) {
            count++;
        }
    }
}

/*
 * Executing result:
 * The value of count InterferenceThread class is supposed to be 2_000_000, but the real value is: 1563643
 * */