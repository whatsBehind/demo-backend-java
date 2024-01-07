package com.whatsbehind.thread_.start;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    log.debug("running");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        new Thread(runnable).start();
    }
}
