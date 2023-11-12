package com.whatsbehind.reflection_.model;

/**
 * Author: puyanh
 * Date: 11/7/23
 */
public class Person {

    private String name;
    private String sex;
    public double age;
    private String height;

    public Person(String name, double age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name) {
        this.name = name;
    }

    public Person() {
    }

    public void eat() {
        System.out.println("Person.eat");
    }

    private void run() {
        System.out.println("Person.run");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", height='" + height + '\'' +
                '}';
    }
}
