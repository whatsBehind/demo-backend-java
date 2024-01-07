package com.whatsbehind.thread_.commonmethods.gracefullyterminate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GracefullyTerminateThread {
    Thread thread;
    public GracefullyTerminateThread() {}

    public void start() {
        thread = new Thread(() -> {
            while(!Thread.currentThread().isInterrupted()) {
                try {
                    log.debug("Hello World");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    log.debug("Interrupt sleep");
                    Thread.currentThread().interrupt();
                }
            }
            log.debug("Thread gracefully terminates");
        });
        thread.start();
    }

    public void gracefullyStop() {
        if (thread != null) {
            thread.interrupt();
        }
    }
}
