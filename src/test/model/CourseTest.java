package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseTest {
    Course c;

    @BeforeEach
    public void setUp() {
        c = new Course("");
    }

    @Test
    public void changeCourseNameTest() {
        assertEquals("",c.getCourseName());
        c.changeCourseName("Phys12");
        assertEquals("Phys12",c.getCourseName());
    }

    @Test
    public void changeTeacherTest() {
        assertEquals(null,c.getTeacher());
        c.changeTeacher("Fan");
        assertEquals("Fan",c.getTeacher());
    }

    @Test
    public void changeTimeBlockTest() {
        assertEquals(0,c.getTimeBlock());
        c.changeTimeBlock(2);
        assertEquals(2,c.getTimeBlock());
    }

    @Test
    public void changeFinishTimeTest() {
        assertEquals(null,c.getFinishTime());
        c.changeFinishTime("2020W1");
        assertEquals("2020W1",c.getFinishTime());
    }

    @Test
    public void changeGradeTest() {
        assertEquals(0,c.getGrade());
        c.changeGrade(99.5);
        assertEquals(99.5, c.getGrade());
    }

    @Test
    public void changeStatusTest() {
        assertEquals(0,c.getStatus());
        c.changeStatus(2);
        assertEquals(2,c.getStatus());
    }
}
