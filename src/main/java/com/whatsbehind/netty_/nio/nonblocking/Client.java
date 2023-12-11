package com.whatsbehind.netty_.nio.nonblocking;

import com.whatsbehind.utility.Scanner_;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress(InetAddress.getLocalHost(), 9999));
        while (true) {
            String input = Scanner_.scanLine("Input: ");
            sc.write(StandardCharsets.UTF_8.encode(input));
        }
    }
}
