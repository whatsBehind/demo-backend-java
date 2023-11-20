package com.whatsbehind.io_.inputstream_;

import org.junit.jupiter.api.Test;

import java.io.*;

import static com.whatsbehind.io_.Constant.*;
import static com.whatsbehind.utility.Print.print;

public class FileInputStream_ {

    @Test
    public void readOneByte() throws IOException {
        prepareReadByte();
        File dir = new File(PLAYGROUND_PATH, INPUT_STREAM_PATH);
        File file = new File(dir, INPUT_STREAM_TEXT_FILE_NAME);

        FileInputStream input = new FileInputStream(file);
        int result;
        print("Read single byte from file [%s]", INPUT_STREAM_TEXT_FILE_NAME);
        while ((result = input.read()) != -1) {
            System.out.print((char) result);
        }

        input.close();
    }

    @Test
    public void readByteArray() throws IOException {
        prepareReadByte();
        File dir = new File(PLAYGROUND_PATH, INPUT_STREAM_PATH);
        File file = new File(dir, INPUT_STREAM_TEXT_FILE_NAME);

        FileInputStream input = new FileInputStream(file);

        byte[] results = new byte[8];
        int len;
        print("Read date into array from file [%s]", INPUT_STREAM_TEXT_FILE_NAME);
        while((len = input.read(results)) != -1) {
            System.out.print(new String(results, 0, len));
        }

        input.close();
    }

    private void prepareReadByte() throws IOException {
        File dir = new File(PLAYGROUND_PATH, INPUT_STREAM_PATH);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File file = new File(dir, INPUT_STREAM_TEXT_FILE_NAME);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter writer = new FileWriter(file);
        writer.write("Hello World");
        writer.close();
    }
}

