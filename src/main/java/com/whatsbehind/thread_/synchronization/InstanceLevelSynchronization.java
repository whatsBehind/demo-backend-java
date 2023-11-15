package com.whatsbehind.thread_.synchronization;

public class InstanceLevelSynchronization {
    public static void main(String[] args) throws InterruptedException {
        InstanceLevelSyncThread instanceLevelSyncThread = new InstanceLevelSyncThread();
        Thread thread1 = new Thread(instanceLevelSyncThread);
        Thread thread2 = new Thread(instanceLevelSyncThread);
        Thread thread3 = new Thread(instanceLevelSyncThread);
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
        int count = instanceLevelSyncThread.getCount();
        System.out.println("The value of count in InterferenceThread class is supposed to be 3_000_000, but the real value is: " + count);
    }
}

class InstanceLevelSyncThread implements Runnable {
    int count = 0;

    @Override
    public void run() {
        for (int loopCount = 0; loopCount < 1_000_000; loopCount++) {
            synchronized (this) {
                count++;
            }
        }
    }

    public int getCount() {
        return count;
    }
}

/*
 * Executing results:
 * The value of count in InterferenceThread class is supposed to be 3_000_000, but the real value is: 3000000
 * */
