package com.whatsbehind.netty_.nio.fragmentation_assembly;

import com.whatsbehind.utility.Print;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {
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
                    ByteBuffer buffer = ByteBuffer.allocate(32);
                    SelectionKey scKey = sc.register(selector, 0, buffer);
                    scKey.interestOps(SelectionKey.OP_READ);
                    log.debug("Register SocketChannel with selector");
                } else if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    log.debug("Read from client {}", channel.getRemoteAddress());
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    log.debug("Buffer size: {}", buffer.capacity());
                    int len = channel.read(buffer);
                    if (len == -1) {
                        log.debug("Client {} is closed", channel.getRemoteAddress());
                        key.cancel();
                    } else {
                        List<String> messages = getFullMessages(buffer);
                        if (messages.isEmpty()) {
                            ByteBuffer doubleSizedBuffer = ByteBuffer.allocate(buffer.capacity() * 2);
                            buffer.flip();
                            doubleSizedBuffer.put(buffer);
                            key.attach(doubleSizedBuffer);
                        } else {
                            for (String message : messages) {
                                log.debug(message);
                            }
                        }
                    }
                }
                keyIterator.remove();
            }
        }

    }

    public static List<String> getFullMessages(ByteBuffer buffer) {
        buffer.flip();
        List<String> messages = new ArrayList<>();
        for (int i = 0; i < buffer.limit(); i++) {
            if (buffer.get(i) == '\n') {
                int len = i + 1 - buffer.position();
                ByteBuffer fullMessageBuffer = ByteBuffer.allocate(len);
                for (int j = 0; j < len - 1; j++) {
                    fullMessageBuffer.put(buffer.get());
                }
                buffer.get();
                fullMessageBuffer.flip();
                messages.add(Charset.defaultCharset().decode(fullMessageBuffer).toString());
            }
        }
        buffer.compact();
        return messages;
    }
}
