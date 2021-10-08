package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    private Course c1 = new Course("Phys 12");
    private Course c2 = new Course("Engl 12");
    private Course c3 = new Course("Chem 12");

    private Student student;
    @BeforeEach
    public void setUp() {
        student = new Student("Rang", "Xiao");
    }

    @Test
    public void changeEmailTest() {
        student.changeEmail("xiaorang@gmail.com");
        assertEquals("xiaorang@gmail.com",student.getEmail());
    }

    @Test
    public void changeNameTest() {
        student.changeName("Xiao","Rang");
        assertEquals("Xiao",student.getFirstName());
        assertEquals("Rang",student.getLastName());
    }

    @Test
    public void changeStudentNumberTest() {
        student.changeStudentNumber("1234");
        assertEquals("1234",student.getStudentNumber());
    }

    @Test
    public void changeStudentAddressTest() {
        Address address = new Address();
        address.changeCity("Vancouver");
        student.changeStudentAddress(address);
        assertEquals(address,student.getAddress());
    }

    @Test
    public void changePhoneNumTest() {
        student.changePhoneNum("1234567");
        assertEquals("1234567",student.getPhoneNum());
    }

    @Test
    public void changeEmergencyContactorTest() {
        EmergencyContactor ec = new EmergencyContactor("rang");
        student.changeEmergencyContactor(ec);
        assertEquals(ec,student.getEmergencyContactor());
    }

    @Test
    public void changeNationalityTest() {
        student.changeNationality("Canada");
        assertEquals("Canada",student.getNationality());
    }

    @Test
    public void addCourseTest() {
        student.addCourse(c1);
        student.addCourse(c2);
        student.addCourse(c3);
        List<Course> courses = new ArrayList<>();
        courses.add(c1);
        courses.add(c2);
        courses.add(c3);
        assertEquals(courses, student.getAllCourses());
    }

    @Test
    public void getPlannedCoursesTest() {
        c1.changeStatus(0);
        c2.changeStatus(1);
        c3.changeStatus(2);
        student.addCourse(c1);
        student.addCourse(c2);
        student.addCourse(c3);
        List<Course> courses = new ArrayList<>();
        courses.add(c2);
        assertEquals(courses,student.getPlannedCourses());
    }

    @Test
    public void getFutureCoursesTest() {
        c1.changeStatus(0);
        c2.changeStatus(1);
        c3.changeStatus(2);
        student.addCourse(c1);
        student.addCourse(c2);
        student.addCourse(c3);
        List<Course> courses = new ArrayList<>();
        courses.add(c3);
        assertEquals(courses,student.getFutureCourse());
    }

    @Test
    public void getFinishedCoursesTest() {
        c1.changeStatus(0);
        c2.changeStatus(1);
        c3.changeStatus(2);
        student.addCourse(c1);
        student.addCourse(c2);
        student.addCourse(c3);
        List<Course> courses = new ArrayList<>();
        courses.add(c1);
        assertEquals(courses,student.getFinishedCourses());
    }
}