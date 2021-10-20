package persistence;

import model.Student;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/nonSuchFile.json");
        try {
            List<Student> studentList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderGeneralStudentList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralStudentList.json");
        try {
            List<Student> studentList = reader.read();
            assertEquals(1,studentList.size());
            Student s = studentList.get(0);
            assertEquals("aaa",s.getFirstName());
            assertEquals("bbb",s.getLastName());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
