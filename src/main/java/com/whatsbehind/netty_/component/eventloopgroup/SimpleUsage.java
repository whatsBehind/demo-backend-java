package com.whatsbehind.netty_.component.eventloopgroup;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class SimpleUsage {

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup(2);
        group.next().execute(() -> {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.debug("Hello World!");
        });
        log.debug("Hello main!");
        AtomicInteger count = new AtomicInteger(0);


        group.next().scheduleAtFixedRate(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.debug("Hello EventLoop-{}!", count.getAndIncrement());
        }, 0, 1, TimeUnit.SECONDS);
    }
}
