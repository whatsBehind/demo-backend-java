package com.whatsbehind.io_.file_;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static com.whatsbehind.io_.Constant.PLAYGROUND_PATH;
import static com.whatsbehind.utility.Print.print;

public class Directory {
    private final static String NEW_DIR_NAME = "newDir";
    private final static String EXISTED_DIR_NAME = "theDir";
    @Test
    public void makeDir() {
        prepareMakeDir();
        File dir = new File(PLAYGROUND_PATH, NEW_DIR_NAME);
        print("Does [%s] exist? [%s]", NEW_DIR_NAME, dir.exists());
        boolean result = dir.mkdir();
        if (result) {
            print("Dir [%s] was created!", NEW_DIR_NAME);
        }
        print("Does [%s] exist? [%s]", NEW_DIR_NAME, dir.exists());
    }

    @Test
    public void deleteDir() {
        prepareDeleteDir();
        File dir = new File(PLAYGROUND_PATH, EXISTED_DIR_NAME);
        print("Does [%s] exist? [%s]", EXISTED_DIR_NAME, dir.exists());
        boolean result = dir.delete();
        if (result) {
            print("Dir [%s] was deleted!", NEW_DIR_NAME);
        }
        print("Does [%s] exist? [%s]", EXISTED_DIR_NAME, dir.exists());
    }

    private void prepareMakeDir() {
        File dir = new File(PLAYGROUND_PATH, NEW_DIR_NAME);
        if (dir.exists()) {
            dir.delete();
        }
    }

    private void prepareDeleteDir() {
        File dir = new File(PLAYGROUND_PATH, EXISTED_DIR_NAME);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }
}
