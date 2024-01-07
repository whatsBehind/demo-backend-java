package com.whatsbehind.thread_.commonmethods;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InterruptRunningThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while(true) {
                if (Thread.currentThread().isInterrupted()) {
                                        break;
                }
            }
        }, "t1");
        t1.start();
        Thread.sleep(50);
        log.debug("t1 is interrupted {}", t1.isInterrupted());
        t1.interrupt();
        log.debug("t1 is interrupted {}", t1.isInterrupted());
    }
}
