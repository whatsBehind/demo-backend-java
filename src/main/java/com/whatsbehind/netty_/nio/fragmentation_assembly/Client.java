package com.whatsbehind.netty_.nio.fragmentation_assembly;

import com.whatsbehind.utility.Scanner_;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

@Slf4j
public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        log.debug("Connecting to server");
        sc.connect(new InetSocketAddress(InetAddress.getLocalHost(), 9999));
        log.debug("Connected with server");
        int count = 0;
        String message = "";
        while (true) {
            count++;
            String input = Scanner_.scanLine("Input: ") + "\n";
            message += input;
            if (count % 3 == 0) {
                log.debug("Sending data [{}] to server", message);
                sc.write(StandardCharsets.UTF_8.encode(message));
                log.debug("Sent data [{}] to server", message);
                message = "";
            }
        }
    }
}
