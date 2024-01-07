package com.whatsbehind.thread_.commonmethods;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Join_ {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(50);
                log.debug("t1 terminates");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t1.setName("t1");

        Thread t2 = new Thread(() -> {
            try {
                log.debug("t2 is waiting for t1 terminating");
                t1.join();
                log.debug("t2 terminates");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t2.setName("t2");

        t1.start();
        t2.start();
        Thread.sleep(20);
        log.debug("t2 state: {}", t2.getState());
    }
}
