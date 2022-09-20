package com.example.xml_processing_exercise.productshop.entities.users;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


public class ExportUserWithSoldCountDTO {


    private String firstName;
    private String lastName;
    private int age;


    public ExportUserWithSoldCountDTO() {}

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
