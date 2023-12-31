package com.whatsbehind.netty_.component.eventloopgroup;

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
        while (true) {
            String input = Scanner_.scanLine("Input: ");
            log.debug("Sending data [{}] to server", input);
            sc.write(StandardCharsets.UTF_8.encode(input));
            log.debug("Sent data [{}] to server", input);
        }
    }
}
