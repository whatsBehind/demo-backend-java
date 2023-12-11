package com.whatsbehind.netty_.bytebuffer;

import com.whatsbehind.netty_.utility.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static com.whatsbehind.netty_.bytebuffer.Constant.READ_DATA_FILE_PATH;

@Slf4j
public class WriteByteBuffer {
    private ByteBuffer buffer;

    @BeforeEach
    public void setup() {
        buffer = ByteBuffer.allocate(16);
    }

    @Test
    public void writeSingleByte() {
        buffer.put((byte) 'a');
        ByteBufferUtil.readAll(buffer);
    }

    @Test
    public void writeByteArray() {
        buffer.put(new byte[]{'a', 'b', 'c'});
        ByteBufferUtil.readAll(buffer);
    }

    @Test
    public void write_clearBuffer() {
        buffer.put(new byte[]{'a', 'b', 'c', 'd'});
        buffer.flip();
        char data = (char) buffer.get();
        log.debug("Read first byte in buffer " + data);
        buffer.clear();
        ByteBufferUtil.readAll(buffer);
    }

    @Test
    public void write_compactBuffer() {
        buffer.put(new byte[]{'a', 'b', 'c'});
        buffer.flip();
        char data = (char) buffer.get();
        log.debug("Read first byte in buffer " + data);
        buffer.compact();
        ByteBufferUtil.readAll(buffer);
    }

    @Test
    public void write_readFromChannel() throws IOException {
        try (FileChannel channel = new FileInputStream(READ_DATA_FILE_PATH).getChannel()) {
            channel.read(buffer);
            ByteBufferUtil.readAll(buffer);
        }
    }
}
