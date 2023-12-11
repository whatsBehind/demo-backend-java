package com.whatsbehind.netty_.bytebuffer;

import com.whatsbehind.netty_.utility.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static com.whatsbehind.netty_.bytebuffer.Constant.WRITE_DATA_FILE_PATH;

@Slf4j
public class ReadByteBuffer {
    private ByteBuffer buffer;

    @BeforeEach
    public void setup() {
        buffer = ByteBuffer.allocate(16);
        buffer.put(new byte[]{'a', 'b', 'c', 'd'});
    }

    @Test
    // get() method each time read one byte and increment position pointer
    public void read_get() {
        buffer.flip();
        char data = (char) buffer.get();
        log.debug("Read first byte in buffer " + data);
        ByteBufferUtil.readAll(buffer);
    }

    @Test
    // get(int i) method allows you to read data in any index w/o incrementing position pointer
    public void read_getIndex() {
        buffer.flip();
        char data = (char) buffer.get(1);

        log.debug(String.format("get(int i) to read byte [%s] in index 1, position should not change", data));
        ByteBufferUtil.readAll(buffer);
    }

    @Test
    // Read data from byteBuffer and write them to channel
    public void read_writeToChannel() throws IOException {
        try (FileChannel channel = new FileOutputStream(WRITE_DATA_FILE_PATH).getChannel()) {
            // Need to switch byteBuffer to read mode before writing to channel
            buffer.flip();
            int len = channel.write(buffer);
            log.debug(String.format("Write %s bytes to file", len));
        }
    }

    @Test
    // rewind sets position to 0 regardless its current value
    public void read_rewindBuffer() throws IOException {
        buffer.flip();
        buffer.get(new byte[4]);
        log.debug("Read 4 bytes from byteBuffer");
        ByteBufferUtil.readAll(buffer);

        buffer.rewind();
        log.debug("Rewind byteBuffer, set position to 0");
        ByteBufferUtil.readAll(buffer);
    }

    @Test
    /*
    * Normally mark() and reset() are used together. It's an enhancement of rewind().
    * Position can be reset to any index where it is marked
    * */
    public void read_markAndReset() throws IOException {
        buffer.flip();
        buffer.get();
        buffer.get();
        log.debug("Read two bytes from byteBuffer");
        ByteBufferUtil.readAll(buffer);

        buffer.mark();
        log.debug("Mark byteBuffer at index 2");
        buffer.get();
        buffer.get();
        log.debug("Read two more bytes from byteBuffer");

        buffer.reset();
        log.debug("Reset position to mark(2)");
        ByteBufferUtil.readAll(buffer);
    }

}
