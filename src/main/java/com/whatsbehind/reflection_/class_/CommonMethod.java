package com.whatsbehind.reflection_.class_;

import com.whatsbehind.reflection_.model.Person;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static com.whatsbehind.reflection_.constant.Delimiter.LINE_DELIMITER;

/**
 * Author: puyanh
 * Date: 11/7/23
 * Demo common methods of Class with examples
 */

public class CommonMethod {

    private final static String PUBLIC_FIELD_NAME_AGE = "age";
    private final static String PRIVATE_FIELD_NAME_NAME = "name";
    private final static String PUBLIC_METHOD_NAME_EAT = "eat";

    public static void main(String[] args) throws Exception {
        Class cls = Person.class;

        // Package name
        String packageName = cls.getPackageName();
        printWithLineDelimiter(String.format("Get package name: [%s]", packageName));

        // Class name
        String fullName = cls.getName();
        printWithLineDelimiter(String.format("Get class full name: [%s]", fullName));

        // Simple name
        String simpleName = cls.getSimpleName();
        printWithLineDelimiter(String.format("Get class simple name: [%s]", simpleName));

        // Create instance
        Person person = (Person) cls.newInstance();
        printWithLineDelimiter(String.format("Create instance: [%s]", person));

        // Get public field
        Field ageField = cls.getField(PUBLIC_FIELD_NAME_AGE);
        printWithLineDelimiter(String.format("Get public field \"age\": [%s]", ageField));

        // Get non-public filed
        Field nameField;
        try {
            nameField = cls.getField(PRIVATE_FIELD_NAME_NAME);
        } catch (NoSuchFieldException e) {
            System.out.println("Unable to get private field \"name\"");
            nameField = cls.getDeclaredField(PRIVATE_FIELD_NAME_NAME);
            printWithLineDelimiter(String.format("Get private field \"name\" using method getDeclaredField: [%s]", nameField));
        }

        // Get all public fields
        Field[] publicFields = cls.getFields();
        System.out.println("Get all public fields");
        for (int i = 0; i < publicFields.length; i++) {
            String printMessage = String.format("Field%s: [%s]", i, publicFields[i]);
            if (i == publicFields.length - 1) {
                printWithLineDelimiter(printMessage);
            } else {
                System.out.println(printMessage);
            }
        }

        // Get all field
        Field[] allFields = cls.getDeclaredFields();
        System.out.println("Get all fields");
        for (int i = 0; i < allFields.length; i++) {
            String printMessage = String.format("Field%s: [%s]", i, allFields[i]);
            if (i == allFields.length - 1) {
                printWithLineDelimiter(printMessage);
            } else {
                System.out.println(printMessage);
            }
        }

        // Get public method
        Method eatMethod = cls.getMethod(PUBLIC_METHOD_NAME_EAT);
        printWithLineDelimiter(String.format("Get public method \"eatMethod\": [%s]", eatMethod));

        // Get public constructor
        Constructor constructor = cls.getConstructor(String.class);
        printWithLineDelimiter(String.format("Get constructor with String as argument: [%s]", constructor));
    }

    private static void printWithLineDelimiter(String str) {
        System.out.println(str);
        System.out.println(LINE_DELIMITER);
    }

}
