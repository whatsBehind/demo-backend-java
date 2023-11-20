package com.whatsbehind.net_.tcp_.uploadfile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import static com.whatsbehind.net_.utility.InputStreamUtil.inputStreamToByteArray;
import static com.whatsbehind.net_.utility.InputStreamUtil.inputStreamToString;

public class Client_ {
    public static void main(String[] args) throws IOException {

        // Create a new socket and connect to a specific port of the server
        Socket socket = new Socket(InetAddress.getLocalHost(), 8888);

        // Read file from disk
        String filePath = "/Users/puyanh/Desktop/entryPoint.cy.ts.mp4";
        FileInputStream bis = new FileInputStream(filePath);
        byte[] data = inputStreamToByteArray(bis);
        System.out.println("Client reads a file from disk");

        // Send a message to the server
        OutputStream os = socket.getOutputStream();
        os.write(data);
        // You need to shut down the output stream, otherwise the server will hang up
        // It's a signal let server know that you're done sending data
        // TODO: What's the corresponding TCP packet for this?
        socket.shutdownOutput();
        System.out.println("Client uploads a file to server");

        // Receive a message from the server
        InputStream is = socket.getInputStream();
        String str = inputStreamToString(is);
        System.out.println(str);

        // Close streams and socket
        is.close();
        os.close();
        socket.close();
    }
}