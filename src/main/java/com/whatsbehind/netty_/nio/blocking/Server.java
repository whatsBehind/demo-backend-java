package com.whatsbehind.netty_.nio.blocking;

import com.whatsbehind.netty_.utility.ByteBufferReader;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(32);

        // Create server
        ServerSocketChannel ssc = ServerSocketChannel.open();

        // Server listens to port 9999 at local host
        ssc.bind(new InetSocketAddress(9999));
        log.debug("Create server listening to port 9999");

        List<SocketChannel> socketChannels = new ArrayList<>();
        while (true) {
            // Accept: build connection with client
            log.debug("Waiting for client connection...");
            SocketChannel socketChannel = ssc.accept();
            log.debug("Build connection with client " + socketChannel.getRemoteAddress());
            socketChannels.add(socketChannel);

            for (SocketChannel channel : socketChannels) {
                log.debug("Start to read channel from client " + channel.getRemoteAddress());
                channel.read(buffer);
                buffer.flip();
                ByteBufferReader.readAll(buffer);
                buffer.clear();
                log.debug("Complete reading data");
            }
        }

    }
}
