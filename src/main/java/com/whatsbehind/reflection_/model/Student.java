package com.whatsbehind.reflection_.model;

/**
 * Author: puyanh
 * Date: 11/7/23
 */
public class Student extends Person {
    String school;
    protected double grade;


    public Student(String name, double age, String school, double grade) {
        super(name, age);
        this.school = school;
        this.grade = grade;
    }

    public void goToSchool() {
        System.out.println("Student.goToSchool");
    }
}
