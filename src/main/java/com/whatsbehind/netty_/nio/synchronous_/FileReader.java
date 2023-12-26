package com.whatsbehind.netty_.nio.synchronous_;

import com.whatsbehind.netty_.utility.ByteBufferReader;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileReader {
    public final static String FILE_PATH = "src/main/java/com/whatsbehind/netty_/nio/resource/data.txt";

    public static void main(String[] args) throws IOException {
        FileChannel fc = FileChannel.open(Paths.get(FILE_PATH), StandardOpenOption.READ);
        ByteBuffer buffer = ByteBuffer.allocate(16);
        fc.read(buffer);
        buffer.flip();
        ByteBufferReader.readAll(buffer);
    }
}
