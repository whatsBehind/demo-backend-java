package com.whatsbehind.netty_.nio.nonblocking;

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

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(9999));

        ssc.configureBlocking(false);

        List<SocketChannel> channels = new ArrayList<>();
        while (true) {
            // log.debug("Server connecting");
            SocketChannel sc = ssc.accept();
            if (sc != null) {
                log.debug("Connect to client " + sc.getRemoteAddress());
                sc.configureBlocking(false);
                channels.add(sc);
            }
            for (SocketChannel channel : channels) {
                // log.debug("Server reading");
                int read = channel.read(buffer);
                if (read != 0) {
                    buffer.flip();
                    ByteBufferReader.readAll(buffer);
                    buffer.clear();
                    log.debug("Read data from client " + channel.getRemoteAddress());
                }
            }
        }
    }
}
