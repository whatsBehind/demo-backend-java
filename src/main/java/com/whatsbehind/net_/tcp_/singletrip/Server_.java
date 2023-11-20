package com.whatsbehind.net_.tcp_.singletrip;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static com.whatsbehind.net_.utility.InputStreamUtil.inputStreamToString;

public class Server_ {
    public static void main(String[] args) throws IOException {
        // A server socket waits for requests to come in over the network.
        ServerSocket serverSocket = new ServerSocket(8888);

        // Listens to a connection and accepts it. Returns a new socket
        Socket socket = serverSocket.accept();

        // Read data from input stream, convert it to string and print it
        InputStream is = socket.getInputStream();
        String str = inputStreamToString(is);
        System.out.println(str);

        // Close stream and socket
        is.close();
        socket.close();
        serverSocket.close();
    }
}
