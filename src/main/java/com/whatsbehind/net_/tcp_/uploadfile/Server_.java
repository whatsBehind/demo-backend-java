package com.whatsbehind.net_.tcp_.uploadfile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static com.whatsbehind.net_.utility.InputStreamUtil.inputStreamToByteArray;
import static com.whatsbehind.net_.utility.InputStreamUtil.inputStreamToString;

public class Server_ {
    public static void main(String[] args) throws IOException {
        // A server socket waits for requests to come in over the network.
        ServerSocket serverSocket = new ServerSocket(8888);

        // Listens to a connection and accepts it. Returns a new socket
        Socket socket = serverSocket.accept();

        // Read data from input stream
        InputStream is = socket.getInputStream();
        byte[] data = inputStreamToByteArray(is);
        System.out.println("Server received file uploaded from client");

        // Save the file to local disk
        String filePath = "/Users/puyanh/Learning/video.mp4";
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        bos.write(data);
        bos.close();

        // Write data to output stream
        OutputStream os = socket.getOutputStream();
        os.write("Upload succeeded".getBytes());

        // Close streams and socket
        os.close();
        is.close();
        socket.close();
        serverSocket.close();
    }
}

