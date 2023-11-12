package com.whatsbehind.reflection_.class_;

import com.whatsbehind.reflection_.model.Person;

/**
 * Author: puyanh
 * Date: 11/7/23
 * Demo different ways to acquire Class object
 */

public class AcquireClass {

    public final static String PERSON_CLASS_FULL_NAME = "com.whatsbehind.reflection_.model.Person";

    public static void main(String[] args) throws Exception {
        // 1. Class.forName()
        Class cls1 = Class.forName(PERSON_CLASS_FULL_NAME);
        System.out.println(String.format("Acquire Class object of Person using static method Class.forName: [%s]", cls1));

        // 2. Person.class
        Class cls2 = Person.class;
        System.out.println(String.format("Acquire Class object of Person from Person's class field: [%s]", cls2));

        // 3. Object.getClass()
        Person person = new Person();
        Class cls3 = person.getClass();
        System.out.println(String.format("Acquire Class object of Person from method Object.getClass: [%s]", cls3));

        // 4. By ClassLoader
        ClassLoader classLoader = Person.class.getClassLoader();
        Class cls4 = classLoader.loadClass(PERSON_CLASS_FULL_NAME);
        System.out.println(String.format("Acquire Class object of Person by ClassLoader: [%s]", cls4));

        // 5. class of primitive type
        Class intCls = int.class;
        System.out.println(String.format("Acquire Class object of primitive type int: [%s]", intCls));

        // 6.
        Class integerCls = Integer.TYPE;
        System.out.println(String.format("Acquire Class object of wrapper class by Integer.TYPE: [%s]", integerCls));
    }

}
