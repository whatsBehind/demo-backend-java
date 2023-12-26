package com.whatsbehind.netty_.nio.asynchronous_;

import com.whatsbehind.netty_.utility.ByteBufferReader;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Slf4j
public class CallBackFileReader {
    public final static String FILE_PATH = "src/main/java/com/whatsbehind/netty_/nio/resource/data.txt";

    public static void main(String[] args) throws IOException, InterruptedException {
        AsynchronousFileChannel afc = AsynchronousFileChannel.open(Paths.get(FILE_PATH), StandardOpenOption.READ);
        ByteBuffer buffer = ByteBuffer.allocate(16);
        log.debug("Start reading...");
        afc.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer integer, ByteBuffer buffer) {
                buffer.flip();
                ByteBufferReader.readAll(buffer);
            }

            @Override
            public void failed(Throwable throwable, ByteBuffer buffer) {
                throwable.printStackTrace();
            }
        });
        log.debug("Finish reading...");
        Thread.sleep(1000);
    }
}
