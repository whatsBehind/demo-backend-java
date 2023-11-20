package com.whatsbehind.net_.tcp_.roundtrips.charstream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

import static com.whatsbehind.net_.utility.InputStreamUtil.inputStreamToString;

public class Client_ {
    public static void main(String[] args) throws IOException {

        // Create a new socket and connect to a specific port of the server
        Socket socket = new Socket(InetAddress.getLocalHost(), 8888);

        // Send a message to the server
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        osw.write("Hello Server");
        // Flush data in the buffer to the output stream, else server will receive nothing
        osw.flush();
        // You need to shut down the output stream, otherwise the server will hang up
        // It's a signal let server know that you're done sending data
        // TODO: What's the corresponding TCP packet for this?
        socket.shutdownOutput();

        // Receive a message from the server
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String str = br.readLine();
        System.out.println(str);

        // Close streams and socket
        br.close();
        osw.close();
        socket.close();
    }
}