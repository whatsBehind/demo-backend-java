package com.whatsbehind.netty_.nio.selector_;

import com.whatsbehind.netty_.utility.ByteBufferReader;
import com.whatsbehind.utility.Print;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;


@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(32);
        Selector selector = Selector.open();
        log.debug("Create a selector");

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(9999));
        ssc.configureBlocking(false);
        ssc.register(selector, SelectionKey.OP_ACCEPT, null);
        log.debug("Register ServerSocketChannel with selector");

        while (true) {
            Print.printDelimiter();
            log.debug("Listen to events from selection keys");
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            log.debug("Selected {} key(s)", selectionKeys.size());
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while(keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = channel.accept();
                    sc.configureBlocking(false);
                    log.debug("Connected to client {}", sc.getRemoteAddress());
                    SelectionKey scKey = sc.register(selector, 0, null);
                    scKey.interestOps(SelectionKey.OP_READ);
                    log.debug("Register SocketChannel with selector");
                } else if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    log.debug("Read from client {}", channel.getRemoteAddress());
                    int len = channel.read(buffer);
                    if (len == -1) {
                        log.debug("Client {} is closed", channel.getRemoteAddress());
                        key.cancel();
                    } else {
                        buffer.flip();
                        ByteBufferReader.readAll(buffer);
                        buffer.clear();
                    }
                }
                keyIterator.remove();
            }
        }
    }
}
