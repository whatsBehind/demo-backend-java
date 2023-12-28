package com.whatsbehind.netty_.nio.asynchronous_;

import com.whatsbehind.netty_.utility.ByteBufferReader;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
public class FutureFileReader {
    public final static String FILE_PATH = "src/main/java/com/whatsbehind/netty_/nio/resource/data.txt";

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        AsynchronousFileChannel afc = AsynchronousFileChannel.open(Paths.get(FILE_PATH), StandardOpenOption.READ);
        ByteBuffer buffer = ByteBuffer.allocate(16);
        log.debug("Start reading...");
        Future<Integer> future = afc.read(buffer, 0);
        // Blocking method: main thread is blocked here until read operation completes
        future.get();
        buffer.flip();
        ByteBufferReader.readAll(buffer);
        log.debug("Finish reading...");
        Thread.sleep(1000);
    }
}
