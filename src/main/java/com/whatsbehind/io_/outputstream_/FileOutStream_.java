package com.whatsbehind.io_.outputstream_;

import org.junit.jupiter.api.Test;
import java.io.*;

import static com.whatsbehind.io_.Constant.*;
import static com.whatsbehind.utility.Print.print;

public class FileOutStream_ {
    @Test
    public void writeTxtFile() throws IOException {
        /*
        * Create new file "foo.txt" under playground/outputstream
        * Writer "Hello World!" into the new file
        * */
        File dir = new File(PLAYGROUND_PATH, OUTPUT_STREAM_PATH);
        File file = new File(dir, OUTPUT_STREAM_TEXT_FILE_NAME);

        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream out = new FileOutputStream(file);
        out.write(TEXT_FILE_CONTENT.getBytes());

        readAndVerify();
        out.close();
    }

    private void readAndVerify() throws IOException {
        File dir = new File(PLAYGROUND_PATH, OUTPUT_STREAM_PATH);
        File file = new File(dir, OUTPUT_STREAM_TEXT_FILE_NAME);

        BufferedReader in = new BufferedReader(new FileReader(file));
        String content;
        print("Content from file [%s]", OUTPUT_STREAM_TEXT_FILE_NAME);
        while ((content = in.readLine()) != null) {
            System.out.println(content);
        }
        in.close();
    }
}
