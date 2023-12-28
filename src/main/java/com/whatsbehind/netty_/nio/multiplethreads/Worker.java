package com.whatsbehind.netty_.nio.multiplethreads;

import com.whatsbehind.netty_.utility.ByteBufferReader;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

@Getter
@Setter
@Slf4j
public class Worker implements Runnable {
    private Thread thread;
    private Selector selector;
    private String name;
    private boolean start;

    public Worker(String name) {
        this.name = name;
    }

    public void register(SocketChannel sc) throws IOException {
        if (!start) {
            start = true;
            selector = Selector.open();
            thread = new Thread(this, this.name);
            thread.start();
            log.debug("Start selector and thread in {}", name);
        }
        sc.configureBlocking(false);
        sc.register(selector, SelectionKey.OP_READ, null);
        log.debug("Register client [{}] with {}", sc.getRemoteAddress(), name);
    }

    @Override
    public void run() {
        while (true) {
            try {
                log.debug("{} is listening...", name);
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isReadable()) {
                        SocketChannel sc = (SocketChannel) key.channel();
                        log.debug("Reading message from client [{}]", sc.getRemoteAddress());
                        ByteBuffer buffer = ByteBuffer.allocate(16);

                        sc.read(buffer);
                        buffer.flip();
                        ByteBufferReader.readAll("Message: ", buffer);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
