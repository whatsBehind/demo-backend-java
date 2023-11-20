package com.whatsbehind.utility;

public class Print {
    public static void print(String template, Object... args) {
        System.out.println((String.format(template, args)));
    }
}
