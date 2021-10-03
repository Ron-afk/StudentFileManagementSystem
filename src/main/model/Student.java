package model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private int studentNumber;
    private List<Course> courses;

    public Student() {
        courses = new ArrayList<>();
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeStudentNumber(int id) {
        this.studentNumber = id;
    }

    public void addCourse(Course c) {
        courses.add(c);
    }
}
