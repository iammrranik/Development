package com.example.database.domain;

public class Student {
    private int id;
    private String name;
    private float cgpa;

    public Student(int id, String name, float cgpa) {
        this.setId(id);
        this.setName(name);
        this.setCgpa(cgpa);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCgpa() {
        return cgpa;
    }

    public void setCgpa(float cgpa) {
        this.cgpa = cgpa;
    }

    @Override
    public String toString() {
        return "Student details: \n" +
                this.getId() + "\n" +
                this.getName() + "\n" +
                this.getCgpa();
    }

}
