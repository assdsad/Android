package com.software.jump.entity;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    public Student(){

    }
    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
