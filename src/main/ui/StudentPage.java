package ui;

import model.Address;
import model.Course;
import model.EmergencyContactor;
import model.Student;

import java.util.List;
import java.util.Scanner;

public class StudentPage {
    private Student student;
    private Scanner input;
    private PrintInfo printInfo = new PrintInfo();

    // MODIFIES: this
    // EFFECTS: initiate a new student page
    public StudentPage(Student student) {
        this.student = student;

        init();
        runStudentPage();
    }


    // MODIFIES: this
    // EFFECTS: initiate scanner
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: run the student info page
    private void runStudentPage() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            studentPrintInfo();
            commandChoice();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

    }

    // EFFECTS: print student's info
    public void studentPrintInfo() {
        System.out.println("First Name: " + student.getFirstName());
        System.out.println("Last Name: " + student.getLastName());
        System.out.println("PEN: " + student.getStudentNumber());
        System.out.println("DOB" + student.getDateOfBirth());
        System.out.println("Address: ");
        printInfo.addressPrint(student.getAddress());
        System.out.println("Phone Number: " + student.getPhoneNum());
        System.out.println("Email Address: " + student.getEmail());
        System.out.println("Emergency Contactor: ");
        printInfo.emergencyContactorPrint(student.getEmergencyContactor());
        System.out.println("Course History: ");
        for (Course c : student.getAllCourses()) {
            printInfo.coursePrintSimple(c);
        }
    }

    // EFFECTS: show all command options to operate on the student
    private void commandChoice() {
        System.out.println("\nSelection from following to edit student info:");
        System.out.println("\ten -> edit name");
        System.out.println("\tad -> edit address");
        System.out.println("\tpn -> edit phone number");
        System.out.println("\tna -> edit nationality");
        System.out.println("\tem -> edit email address");
        System.out.println("\tsn -> edit student number");
        System.out.println("\tec -> edit emergency contactor info");
        System.out.println("\tci -> view detailed course info");
        System.out.println("\tac -> add course to this student");
        System.out.println("\tdb -> edit date of birth");
        System.out.println("\tq -> quit to student list page");
    }

    // EFFECTS: process the command which operate on student's info
    private void processCommand(String command) {
        if (command.equals("en")) {
            editStudentName();
        } else if (command.equals("ad")) {
            editStudentAddress();
        } else if (command.equals("pn")) {
            editPhoneNum();
        } else if (command.equals("na")) {
            editNationality();
        } else if (command.equals("em")) {
            editEmail();
        } else if (command.equals("sn")) {
            editStudentNumber();
        } else if (command.equals("ec")) {
            editEmergencyContactor();
        } else if (command.equals("ci")) {
            courseList();
        } else if (command.equals("ac")) {
            addCourse();
        } else if (command.equals("db")) {
            editDateOfBirth();
        } else {
            System.out.println("Invalid input...");
        }
    }

    private void editDateOfBirth() {
        System.out.println("Input date of birth in format YYYY-MM-DD: ");
        String dateOfBirth = input.next();
        student.changeDateOfBirth(dateOfBirth);
    }


    // MODIFIES: this
    // EFFECTS: change student's name
    private void editStudentName() {
        System.out.println("Input last name: ");
        String lastName = input.next();
        System.out.println("Input first name: ");
        String firstName = input.next();
        student.changeName(firstName, lastName);
    }

    // MODIFIES: this
    // EFFECTS: add a new course to student course list
    private void addCourse() {
        Course course = initiateCourse();
        student.addCourse(course);

    }

    // EFFECTS: create a course
    private Course initiateCourse() {
        System.out.println("Input course name: ");
        String name = input.next();
        Course c = new Course(name);
        System.out.println("\nInput course status: ");
        System.out.println("\t0 -> finished");
        System.out.println("\t1 -> currently taking");
        System.out.println("\t2 -> planned for next term");
        System.out.println("\t3 -> planned for future");
        int status = Integer.valueOf(input.next());
        c.changeStatus(status);
        return c;
    }

    // MODIFIES: this
    // EFFECTS: change student address information
    private void editStudentAddress() {
        Address address = createNewAddress();
        student.changeStudentAddress(address);

    }

    // EFFECTS: create a new address and return it
    private Address createNewAddress() {
        Address address = new Address();
        System.out.println("Input street address: ");
        String streetAddress = input.next();
        address.changeStreetAddress(streetAddress);
        System.out.println("Input unit number (if applicable) or space: ");
        String unitNum = input.next();
        address.changeUnitNum(unitNum);
        System.out.println("Input city: ");
        String city = input.next();
        address.changeCity(city);
        System.out.println("Input provence: ");
        String provence = input.next();
        address.changeProvence(provence);
        System.out.println("Input postal code");
        String postalCode = input.next();
        address.changePostalCode(postalCode);
        return address;
    }

    // MODIFIES: this
    // EFFECTS: change student's email address
    private void editEmail() {
        System.out.println("Input student's email address: ");
        String email = input.next();
        if (email.matches("^(.+)@(.+)$")) {
            student.changeEmail(email);
        } else {
            System.out.println("Invalid email address");
            editEmail();
        }
    }

    // MODIFIES: this
    // EFFECTS: change student's nationality
    private void editNationality() {
        System.out.println("Input student's nationality: ");
        String nationality = input.next();
        student.changeNationality(nationality);
    }

    // MODIFIES: this
    // EFFECTS: change student's phone number
    private void editPhoneNum() {
        System.out.println("Input student's phone number");
        String phoneNum = input.next();
        student.changePhoneNum(phoneNum);
    }

    // MODIFIES: student
    // EFFECTS: edit student number
    private void editStudentNumber() {
        System.out.println("Input student number: ");
        String studentId = input.next();
        student.changeStudentNumber(studentId);
    }

    // MODIFIES: this
    // EFFECTS: edit emergency contactor's information
    private void editEmergencyContactor() {
        System.out.println("Input emergency contactor's name: ");
        String name = input.next();
        EmergencyContactor ec = new EmergencyContactor(name);
        System.out.println("Input phone number: ");
        String phoneNum = input.next();
        ec.changePhoneNum(phoneNum);
        System.out.println("Input relation with student: ");
        String relation = input.next();
        Address address = createNewAddress();
        ec.changeAddress(address);
        student.changeEmergencyContactor(ec);
    }

    // EFFECTS: print course detail and command option
    private void courseList() {
        List<Course> courses = student.getAllCourses();
        new CourseListPage(courses);
    }
}
