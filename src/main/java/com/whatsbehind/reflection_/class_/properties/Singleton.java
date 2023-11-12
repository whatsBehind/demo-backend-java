package com.whatsbehind.reflection_.class_.properties;

import com.whatsbehind.reflection_.model.Person;

/**
 * Author: puyanh
 * Date: 11/7/23
 * Class is created and loaded once by ClassLoader. There is only one Class object for each class, so Class object is singleton
 */

public class Singleton {

    public static void main(String[] args) throws Exception {

        // Get Class<Person> object in three different ways, and print the hashcode to prove singleton property
        Class personCls1 = Class.forName("com.whatsbehind.reflection_.model.Person");
        Class personCls2 = Person.class;
        Class personCls3 = new Person().getClass();

        System.out.println("personCls1 hashcode: " + personCls1.hashCode());
        System.out.println("personCls2 hashcode: " + personCls2.hashCode());
        System.out.println("personCls3 hashcode: " + personCls3.hashCode());

    }

}
