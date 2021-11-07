package persistence;

import model.Address;
import model.Course;
import model.EmergencyContactor;
import model.Student;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // MODIFIES: this
    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads students list from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public List<Student> read() throws IOException {
        String jsonData = readFile(source);
        JSONArray jsonArray = new JSONArray(jsonData);
        return parseStudentList(jsonArray);
    }

    // EFFECTS: read source file as string and return it
    // citation: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses student list from JSON array and return it
    // citation: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    private List<Student> parseStudentList(JSONArray jsonArray) {
        List<Student> studentList = new ArrayList<>();
        for (Object json : jsonArray) {
            JSONObject nextStudent = (JSONObject) json;
            addStudent(studentList,nextStudent);
        }
        return studentList;
    }


    // MODIFIES: studentList
    // EFFECTS: parses student from JSON object and adds it to student list
    public void addStudent(List<Student> studentList, JSONObject json) {
        String firstName = json.getString("First Name");
        String lastName = json.getString("Last Name");
        Student student = new Student(firstName,lastName);

        String email = json.getString("email");
        student.changeEmail(email);

        String nationality = json.getString("nationality");
        student.changeNationality(nationality);

        String phoneNum = json.getString("phone number");
        student.changePhoneNum(phoneNum);

        String studentNum = json.getString("student number");
        student.changeStudentNumber(studentNum);

        Address address = getAddress(json.getJSONObject("address"));
        student.changeStudentAddress(address);

        String dateOfBirth = json.getString("date of birth");
        student.changeDateOfBirth(dateOfBirth);

        String gender = json.getString("gender");
        student.changeGender(gender);


        EmergencyContactor ec = getEmergencyContactor(json.getJSONObject("emergency contactor"));
        student.changeEmergencyContactor(ec);

        addCourseList(student,json);

        studentList.add(student);
    }

    // EFFECTS: parse address from JSON object and return it
    private Address getAddress(JSONObject json) {
        Address address = new Address();

        String unitNum = json.getString("unit number");
        address.changeUnitNum(unitNum);

        String streetAddress = json.getString("street address");
        address.changeStreetAddress(streetAddress);

        String city = json.getString("city");
        address.changeCity(city);

        String provence = json.getString("provence");
        address.changeProvince(provence);

        String postalCode = json.getString("postal code");
        address.changePostalCode(postalCode);

        return address;
    }

    // EFFECTS: parse emergency contactor from JSON and return it
    private EmergencyContactor getEmergencyContactor(JSONObject json) {
        String name = json.getString("name");
        EmergencyContactor ec = new EmergencyContactor(name);

        String phoneNum = json.getString("phone number");
        ec.changePhoneNum(phoneNum);

        String relation = json.getString("relation");
        ec.changeRelation(relation);

        Address address = getAddress(json.getJSONObject("address"));
        ec.changeAddress(address);

        return ec;
    }

    // MODIFIES: student
    // EFFECTS: parse course list from json and return it
    private void addCourseList(Student student,JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("course list");
        for (Object json : jsonArray) {
            JSONObject nextCourse = (JSONObject) json;
            addCourse(student,nextCourse);
        }
    }

    // MODIFIES: student
    // EFFECTS: parse course from json and add it to student
    private void addCourse(Student student, JSONObject nextCourse) {
        String courseName = nextCourse.getString("course name");
        Course course = new Course(courseName);

        int status = nextCourse.getInt("status");
        course.changeStatus(status);

        String finishTime = nextCourse.getString("finish time");
        course.changeFinishTime(finishTime);

        double grade = nextCourse.getDouble("grade");
        course.changeGrade(grade);

        String teacher = nextCourse.getString("teacher");
        course.changeTeacher(teacher);

        int timeBlock = nextCourse.getInt("time block");
        course.changeTimeBlock(timeBlock);

        student.addCourse(course);
    }
}
