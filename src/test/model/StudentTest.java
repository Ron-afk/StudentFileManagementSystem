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
    private Course c4 = new Course("CHIN 12");
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
        List<Course> courseListTest = new ArrayList<>();
        List<Course> courseList = new ArrayList<>();
        courseList.add(c1);
        courseList.add(c2);
        courseList.add(c3);
        courseListTest.add(c3);
        assertEquals(courseListTest,student.getPlannedCourses(courseList));
    }

    @Test
    public void getFutureCoursesTest() {
        c1.changeStatus(0);
        c2.changeStatus(1);
        c3.changeStatus(2);
        c4.changeStatus(3);
        student.addCourse(c1);
        student.addCourse(c2);
        student.addCourse(c3);
        student.addCourse(c4);
        List<Course> courseListTest = new ArrayList<>();
        courseListTest.add(c4);
        List<Course> courseList = new ArrayList<>();
        courseList.add(c1);
        courseList.add(c2);
        courseList.add(c3);
        courseList.add(c4);
        assertEquals(courseListTest,student.getFutureCourse(courseList));
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

    @Test
    void changeDateOfBirthTest() {
        student.changeDateOfBirth("1999-01-01");
        assertEquals("1999-01-01",student.getDateOfBirth());
    }

    @Test
    public void getAllCoursesTest() {
        c1.changeStatus(0);
        c2.changeStatus(1);
        c3.changeStatus(2);
        c4.changeStatus(3);
        student.addCourse(c1);
        student.addCourse(c2);
        student.addCourse(c3);
        student.addCourse(c4);

        List<Course> courses = new ArrayList<>();
        courses.add(c1);
        courses.add(c2);
        courses.add(c3);
        courses.add(c4);
        assertEquals(courses,student.getAllCourses());
    }

    @Test
    public void gradeAvgTest() {
        c1.changeStatus(0);
        c2.changeStatus(0);
        c3.changeStatus(1);
        c4.changeStatus(0);

        c1.changeGrade(100);
        c2.changeGrade(75);
        c3.changeGrade(50);
        c4.changeGrade(80);

        student.addCourse(c1);
        student.addCourse(c2);
        student.addCourse(c3);
        student.addCourse(c4);
        assertEquals(85,student.gradeAvg(student.getAllCourses()));
    }

    @Test
    public void getCurrentCoursesTest() {
        c1.changeStatus(0);
        c2.changeStatus(1);
        c3.changeStatus(1);
        c4.changeStatus(3);

        student.addCourse(c1);
        student.addCourse(c2);
        student.addCourse(c3);
        student.addCourse(c4);

        List<Course> courseListTest = new ArrayList<>();
        courseListTest.add(c2);
        courseListTest.add(c3);
        List<Course> courseList= new ArrayList<>();
        courseList.add(c1);
        courseList.add(c2);
        courseList.add(c3);
        courseList.add(c4);
        assertEquals(courseListTest,student.getCurrentCourses(courseList));
    }

    @Test
    public void GenderTest() {
        assertEquals("",student.getGender());
        student.changeGender("Male");
        assertEquals("Male",student.getGender());
    }
}