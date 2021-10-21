package persistence;

import model.Course;
import model.Student;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            List<Student> studentList = new ArrayList<>();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testWriteEmptyStudentList() {
        try {
            List<Student> studentList = new ArrayList<>();
            JsonWriter writer = new JsonWriter("./data/testReaderEmptyStudentList.json");
            writer.open();
            writer.write(studentList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderEmptyStudentList.json");
            studentList = reader.read();
            assertEquals(0,studentList.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralStudentList() {
        try{
            List<Student> studentList = new ArrayList<>();
            Student s = new Student("aaa","bbb");
            s.addCourse(new Course("phys12"));
            studentList.add(s);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralStudentList.json");

            writer.open();
            writer.write(studentList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralStudentList.json");
            studentList = reader.read();
            assertEquals(1,studentList.size());
            s = studentList.get(0);
            assertEquals("aaa",s.getFirstName());
            assertEquals("bbb",s.getLastName());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
