package com.whatsbehind.netty_.nio.nonblocking;

import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import static com.whatsbehind.netty_.utility.ByteBufferReader.readAll;

@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(16);

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(9999));
        ssc.configureBlocking(false);
        List<SocketChannel> channels = new ArrayList<>();
        while (true) {
            SocketChannel socketChannel = ssc.accept();
            if (socketChannel != null) {
                socketChannel.configureBlocking(false);
                channels.add(socketChannel);
                log.debug("Server connected. {}", socketChannel.getRemoteAddress());
            }
            for (SocketChannel channel : channels) {
                int len = channel.read(buffer);
                if (len != 0) {
                    log.debug("Reading channel {}...", channel.getRemoteAddress());
                    buffer.flip();
                    readAll(buffer);
                    buffer.clear();
                }
            }
        }
    }
}
