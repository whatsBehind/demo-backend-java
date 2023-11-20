package com.whatsbehind.net_.tcp_.roundtrips.charstream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String str = br.readLine();
        System.out.println(str);

        // Write data to output stream
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw =new OutputStreamWriter(os);
        osw.write("Hello Client");
        osw.flush();

        // Close streams and socket
        osw.close();
        br.close();
        socket.close();
        serverSocket.close();
    }
}
