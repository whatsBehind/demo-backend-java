package com.whatsbehind.reflection_.class_;

import static com.whatsbehind.reflection_.constant.Delimiter.LINE_DELIMITER;

/**
 * Author: puyanh
 * Date: 11/9/23
 * Demo the sequence of executing static variables and static block
 */

public class InitiateStatic {

    public static void main(String[] args) {
        new InitiateStaticSample();
    }

    public static class InitiateStaticSample {

        static String DECLARED_BEFORE_STATIC_BLOCK = "String declared before static block - before static block";

        static {
            System.out.println("Entering static block");
            System.out.println(DECLARED_BEFORE_STATIC_BLOCK);
            DECLARED_BEFORE_STATIC_BLOCK = "String declared before static block - inside static block";
            System.out.println(DECLARED_BEFORE_STATIC_BLOCK);
            System.out.println("Exiting static block");
            System.out.println(LINE_DELIMITER);
        }

        static String DECLARED_AFTER_STATIC_BLOCK = "String declared after static block - after static block";

        InitiateStaticSample() {
            System.out.println("Entering constructor");
            System.out.println(DECLARED_BEFORE_STATIC_BLOCK);
            System.out.println(DECLARED_AFTER_STATIC_BLOCK);
            System.out.println("Exiting constructor");
        }
    }

}


