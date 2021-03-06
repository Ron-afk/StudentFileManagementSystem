package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represent student info including basic information and course information
public class Student implements Writable {
    private String firstName = "";
    private String lastName = "";
    private String studentNumber = "";
    private Address address;
    private String gender = "";
    private List<Course> courses;
    private String nationality = "";
    private String email = "";
    private String phoneNum = "";
    private EmergencyContactor emergencyContactor;
    private String dateOfBirth = "";

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

        EventLog.getInstance().logEvent(new Event("Student name changed to  " + firstName + " " + lastName));
    }

    // MODIFIES: this
    // EFFECTS: change student's PEN
    public void changeStudentNumber(String id) {
        this.studentNumber = id;
        EventLog.getInstance().logEvent(new Event(firstName + " " + lastName + "'s student number changed"));
    }

    // MODIFIES: this
    // EFFECTS: change student's address on record
    public void changeStudentAddress(Address address) {
        this.address = address;
        EventLog.getInstance().logEvent(new Event(firstName + " " + lastName + "'s address changed"));
    }

    // MODIFIES: this
    // EFFECTS: change student's email address, return warning if the email address is invalid
    public void changeEmail(String email) {
        this.email = email;
        EventLog.getInstance().logEvent(new Event(firstName + " " + lastName + "'s email address changed"));
    }

    // MODIFIES: this
    // EFFECTS: change student's phone number
    public void changePhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
        EventLog.getInstance().logEvent(new Event(firstName + " " + lastName + "'s phone number changed"));
    }

    // MODIFIES: this
    // EFFECTS: change emergency contactor's information
    public void changeEmergencyContactor(EmergencyContactor ec) {
        this.emergencyContactor = ec;
        EventLog.getInstance().logEvent(new Event(firstName + " "
                + lastName + "'s emergency contactor changed"));
    }

    // MODIFIES: this
    // EFFECTS: change student's nationality
    public void changeNationality(String nationality) {
        this.nationality = nationality;
        EventLog.getInstance().logEvent(new Event(firstName + " " + lastName + "'s nationality changed"));
    }

    // MODIFIES: this
    // EFFECTS: change student's date of birth
    public void changeDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        EventLog.getInstance().logEvent(new Event(firstName + " " + lastName + "'s DOB changed"));
    }

    // MODIFIES: this
    // EFFECTS: change gender "M" for male, "F" for female
    public void changeGender(String gender) {
        this.gender = gender;
        EventLog.getInstance().logEvent(new Event(firstName + " " + lastName + "'s gender changed"));
    }

    // MODIFIES: this
    // EFFECTS: add a course to student's course list
    public void addCourse(Course c) {
        courses.add(c);
        EventLog.getInstance().logEvent(new Event("Add a new course to "
                + firstName + " " + lastName + "'s course history"));
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

    // EFFECTS: get student's phone number
    public String getPhoneNum() {
        return phoneNum;
    }

    // EFFECTS: get student's date of birth
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    // EFFECTS: get student's gender
    public String getGender() {
        return gender;
    }

    // EFFECTS: get student's emergency contactor information
    public EmergencyContactor getEmergencyContactor() {
        return emergencyContactor;
    }

    // EFFECTS: calculate grade average for the student
    public static double gradeAvg(List<Course> courses) {
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

    // EFFECTS???get all finished courses of the student
    public List<Course> getFinishedCourses() {
        List<Course> finishedCourses = new ArrayList<>();
        for (Course c : courses) {
            if (c.getStatus() == 0) {
                finishedCourses.add(c);
            }
        }
        return finishedCourses;
    }

    // EFFECTS???get all planned courses for next term
    public static List<Course> getPlannedCourses(List<Course> courses) {
        List<Course> plannedCourses = new ArrayList<>();
        for (Course c : courses) {
            if (c.getStatus() == 2) {
                plannedCourses.add(c);
            }
        }
        return plannedCourses;
    }

    // EFFECTS: get all current courses
    public static List<Course> getCurrentCourses(List<Course> courses) {
        List<Course> currentCourses = new ArrayList<>();
        for (Course c : courses) {
            if (c.getStatus() == 1) {
                currentCourses.add(c);
            }
        }
        return currentCourses;
    }

    // EFFECTS: get all planned courses for future
    public static List<Course> getFutureCourse(List<Course> courses) {
        List<Course> futureCourse = new ArrayList<>();
        for (Course c : courses) {
            if (c.getStatus() == 3) {
                futureCourse.add(c);
            }
        }
        return futureCourse;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("First Name", firstName);
        json.put("Last Name",lastName);
        json.put("email",email);
        json.put("nationality",nationality);
        json.put("phone number",phoneNum);
        json.put("student number",studentNumber);
        json.put("address", address.toJson());
        json.put("emergency contactor",emergencyContactor.toJson());
        json.put("course list",coursesToJson());
        json.put("date of birth", dateOfBirth);
        json.put("gender",gender);
        return json;
    }

    // EFFECTS: return courses in this student wo a JSON array
    public JSONArray coursesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Course c: courses) {
            jsonArray.put(c.toJson());
        }
        return jsonArray;
    }
}
