package com.whatsbehind.netty_.nio.multiplethreads;

import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;

@Slf4j
public class Server {

    public static void main(String[] args) throws IOException {
        Thread.currentThread().setName("boss");

        // Create a ServerSocketChannel
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(9999));

        // Create a boss selector to listen to ACCEPT event from the ServerSocketChannel
        Selector boss = Selector.open();
        SelectionKey bossKey = ssc.register(boss, 0, null);
        bossKey.interestOps(SelectionKey.OP_ACCEPT);
        log.debug("Boss starts working...");

        Worker[] workers = new Worker[]{
                new Worker("worker-0"),
                new Worker("worker-1")
        };
        int count = 0;

        while (true) {
            boss.select();
            Iterator<SelectionKey> keyIterator = boss.selectedKeys().iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                keyIterator.remove();
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel sc = server.accept();
                    log.debug("Accept client [{}] connection", sc.getRemoteAddress());

                    workers[count++ % 2].register(sc);
                }
            }
        }
    }
}
