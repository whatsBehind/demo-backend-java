package com.whatsbehind.net_.utility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamUtil {
    public static String inputStreamToString(InputStream is) throws IOException {
        byte[] buf = new byte[1024];
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int len;

        while((len = is.read(buf)) != -1) {
            bos.write(buf, 0, len);
        }

        String result = bos.toString();
        bos.close();
        return result;
    }

    public static byte[] inputStreamToByteArray(InputStream is) throws IOException {
        byte[] buf = new byte[1024];
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int len;

        while((len = is.read(buf)) != -1) {
            bos.write(buf, 0, len);
        }

        byte[] result = bos.toByteArray();
        bos.close();
        return result;
    }
}
