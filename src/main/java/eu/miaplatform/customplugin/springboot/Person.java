package eu.miaplatform.customplugin.springboot;

import java.io.Serializable;

public class Person implements Serializable {

    private String name;
    private String surname;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
