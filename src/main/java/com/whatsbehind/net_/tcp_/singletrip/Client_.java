package com.whatsbehind.net_.tcp_.singletrip;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client_ {
    public static void main(String[] args) throws IOException {

        // Create a new socket and connect to a specific port of the server
        Socket socket = new Socket(InetAddress.getLocalHost(), 8888);

        // Send a message to the server
        OutputStream os = socket.getOutputStream();
        os.write("Hello Server".getBytes());

        // Close stream and socket
        os.close();
        socket.close();
    }
}