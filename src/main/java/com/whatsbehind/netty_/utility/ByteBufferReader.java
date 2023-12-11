package com.whatsbehind.netty_.utility;

import lombok.extern.slf4j.Slf4j;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

@Slf4j
public class ByteBufferReader {
    public static void readAll(ByteBuffer buffer) {
        String data = StandardCharsets.UTF_8.decode(buffer).toString();
        log.debug(data);
    }
}
