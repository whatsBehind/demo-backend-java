package com.whatsbehind.thread_.synchronization;

public class ClassLevelSynchronization {
    public static void main(String[] args) throws InterruptedException {
        ClassLevelSyncThread thread1 = new ClassLevelSyncThread();
        ClassLevelSyncThread thread2 = new ClassLevelSyncThread();
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        int count = ClassLevelSyncThread.getCount();
        System.out.println("The value of count in InterferenceThread class is supposed to be 2_000_000, but the real value is: " + count);
    }
}

class ClassLevelSyncThread extends Thread {
    static int count = 0;
    int loopCount = 0;

    public static int getCount() {
        return count;
    }

    @Override
    public void run() {
        while (loopCount++ < 1_000_000) {
            synchronized (ClassLevelSyncThread.class) {
                count++;
            }
        }
    }
}

/*
 * The value of count in InterferenceThread class is supposed to be 2_000_000, but the real value is: 2000000
 * */