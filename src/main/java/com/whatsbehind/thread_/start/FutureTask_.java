package com.whatsbehind.thread_.start;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j
public class FutureTask_ {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(() -> {
            log.debug("running");
            Thread.sleep(1000);
            return 100;
        });
        Thread t = new Thread(task, "Thread_Get_Int");
        t.start();
        log.debug("In main thread");
        Integer result = task.get();
        log.debug("{}", result);
    }
}
