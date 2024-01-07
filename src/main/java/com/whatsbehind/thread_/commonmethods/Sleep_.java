package com.whatsbehind.thread_.commonmethods;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Sleep_ {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.debug("Interrupted");
                log.debug("t1 state: {}", Thread.currentThread().getState());
            }
        });
        t1.setName("t1");
        t1.start();

        Thread.sleep(50);
        log.debug("t1 state: {}", t1.getState());
        log.debug("Interrupting thread t1");
        t1.interrupt();
    }
}
