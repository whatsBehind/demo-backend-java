package com.whatsbehind.netty_.bytebuffer;

import com.whatsbehind.netty_.utility.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;

@Slf4j
public class ByteBufferAndString {
    private ByteBuffer buffer;

    @BeforeEach
    public void setup() {
        buffer = ByteBuffer.allocate(16);
    }

    @Test
    public void fromString_getBytes() {
        buffer.put("Hello World".getBytes());
        ByteBufferUtil.readAll(buffer);
    }

    @Test
    public void fromString_charset() {
        buffer = StandardCharsets.UTF_8.encode("Hello World");
        ByteBufferUtil.readAll(buffer);
    }

    @Test
    public void fromString_wrap() {
        buffer = ByteBuffer.wrap("Hello World".getBytes());
        ByteBufferUtil.readAll(buffer);
    }

    @Test
    public void toString_charset() {
        buffer = StandardCharsets.UTF_8.encode("Hello World");
        String str = StandardCharsets.UTF_8.decode(buffer).toString();
        log.debug("Decode byteBuffer: " + str);
    }
}
