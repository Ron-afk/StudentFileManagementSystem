package model;

import java.util.ArrayList;
import java.util.List;

// Represent student info including basic information and course information
public class Student {
    private String firstName;
    private String lastName;
    private String studentNumber;
    private Address address;
    private List<Course> courses;
    private String nationality;
    private String email;
    private String phoneNum;
    private EmergencyContactor emergencyContactor;

    // REQUIRES: last name and first name can not be empty string
    // MODIFIES: this
    // EFFECTS: set student first name and last name, initiate student's course with empty list
    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        courses = new ArrayList<>();
        address = new Address();
        emergencyContactor = new EmergencyContactor("");
    }

    // REQUIRES: last name and first name can not be empty string
    // MODIFIES: this
    // EFFECTS: change student's name on record
    public void changeName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // MODIFIES: this
    // EFFECTS: change student's PEN
    public void changeStudentNumber(String id) {
        this.studentNumber = id;
    }

    // MODIFIES: this
    // EFFECTS: change student's address on record
    public void changeStudentAddress(Address address) {
        this.address = address;
    }

    // MODIFIES: this
    // EFFECTS: change student's email address, return warning if the email address is invalid
    public void changeEmail(String email) {
        this.email = email;
    }

    // MODIFIES: this
    // EFFECTS: change student's phone number
    public void changePhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    // MODIFIES: this
    // EFFECTS: change emergency contactor's information
    public void changeEmergencyContactor(EmergencyContactor ec) {
        this.emergencyContactor = ec;
    }

    // MODIFIES: this
    // EFFECTS: change student's nationality
    public void changeNationality(String nationality) {
        this.nationality = nationality;
    }

    // MODIFIES: this
    // EFFECTS: add a course to student's course list
    public void addCourse(Course c) {
        courses.add(c);
    }

    // EFFECTS: get student's first name
    public String getFirstName() {
        return firstName;
    }

    // EFFECTS: get student's last name
    public String getLastName() {
        return lastName;
    }

    // EFFECTS: get student's PEN
    public String getStudentNumber() {
        return studentNumber;
    }

    // EFFECTS: get student's address
    public Address getAddress() {
        return address;
    }

    // EFFECTS: get student's nationality
    public String getNationality() {
        return nationality;
    }

    // EFFECTS: get student's email address
    public String getEmail() {
        return email;
    }

    //EFFECTS: get student's phone number
    public String getPhoneNum() {
        return phoneNum;
    }

    // EFFECTS: get student's emergency contactor information
    public EmergencyContactor getEmergencyContactor() {
        return emergencyContactor;
    }

    // EFFECTS: calculate grade average for the student
    public double gradeAvg(List<Course> courses) {
        int count = 0;
        double sum = 0;
        for (Course c : courses) {
            int status = c.getStatus();
            if (status == 0) {
                sum += c.getGrade();
                count++;
            }
        }
        return sum / count;
    }

    // EFFECTS: get all courses of the student
    public List<Course> getAllCourses() {
        return courses;
    }

    // EFFECTS：get all finished courses of the student
    public List<Course> getFinishedCourses() {
        List<Course> finishedCourses = new ArrayList<>();
        for (Course c : courses) {
            if (c.getStatus() == 0) {
                finishedCourses.add(c);
            }
        }
        return finishedCourses;
    }

    // EFFECTS：get all planned courses for next term
    public List<Course> getPlannedCourses() {
        List<Course> plannedCourses = new ArrayList<>();
        for (Course c : courses) {
            if (c.getStatus() == 2) {
                plannedCourses.add(c);
            }
        }
        return plannedCourses;
    }

    // EFFECTS: get all current courses
    public List<Course> getCurrentCourses() {
        List<Course> currentCourses = new ArrayList<>();
        for (Course c : courses) {
            if (c.getStatus() == 1) {
                currentCourses.add(c);
            }
        }
        return currentCourses;
    }

    // EFFECTS: get all planned courses for future
    public List<Course> getFutureCourse() {
        List<Course> futureCourse = new ArrayList<>();
        for (Course c : courses) {
            if (c.getStatus() == 3) {
                futureCourse.add(c);
            }
        }
        return futureCourse;
    }

    public void printInfo() {
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("PEN: " + studentNumber);
        System.out.println("Address: ");
        address.print();
        System.out.println("Phone Number: " + phoneNum);
        System.out.println("Email Address: " + email);
        System.out.println("Emergency Contactor: ");
        emergencyContactor.print();
        System.out.println("Course History: ");
        for (Course c : courses) {
            c.printSimple();
        }
    }
}
