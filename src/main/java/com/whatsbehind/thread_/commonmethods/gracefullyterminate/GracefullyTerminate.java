package com.whatsbehind.thread_.commonmethods.gracefullyterminate;

public class GracefullyTerminate {
    public static void main(String[] args) throws InterruptedException {
        GracefullyTerminateThread thread = new GracefullyTerminateThread();
        thread.start();

        Thread.sleep(3000);
        thread.gracefullyStop();
    }
}
