package ui;

import model.Address;
import model.Course;
import model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentFileApplication {
    private List<Student> students;
    private Scanner input;

    // EFFECTS: run the student file system, show the main page
    public StudentFileApplication() {
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: initializes students
    public void init() {
        students = new ArrayList<>();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: run the application
    public void runApp() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMainPage();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nSee you next time!");
    }

    // EFFECTS: process the input command
    private void processCommand(String command) {
        if (command.equals("s")) {
            showStudentList();
        } else if (command.equals("a")) {
            addNewStudent();
        } else {
            System.out.println("Invalid input");
        }
    }

    // MODIFIES: this
    // EFFECTS: add new student to students list
    private void addNewStudent() {
        System.out.println("Input Last Name: ");
        String lastName = input.next();
        System.out.println("Input First Name: ");
        String firstName = input.next();
        Student newStudent = new Student(firstName, lastName);
        students.add(newStudent);
    }

    // EFFECTS: display the main menu with command options
    public void displayMainPage() {
        System.out.println("\nSelect from:");
        System.out.println("\ts -> Show all students");
        System.out.println("\ta -> Add a new student");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: show all students in list
    public void showStudentList() {
        int i = 1;
        System.out.println("\nInput a number to view detailed student's information.");
        for (Student s : students) {
            System.out.println("\t" + i + " -> " + s.getLastName() + "  " + s.getFirstName());
            i++;
        }
        selectStudent();
    }

    // REQUIRES: user input must be a number
    // EFFECTS: select a student to show his/her detailed info
    private void selectStudent() {
        System.out.println("Only number is accepted");
        System.out.println("Any number not corresponding to a student will return to main menu");
        String selection = input.next();
        int i = Integer.valueOf(selection);
        if (i > students.size() || i <= 0) {
            System.out.println("Return to main menu");
        } else {
            Student student = students.get(i - 1);
            student.printInfo();
            commandChoice(student);
        }
    }

    // EFFECTS: show all command options to operate on the student
    private void commandChoice(Student student) {
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
        System.out.println("\tany other command will return to the main page");
        commandProcessForStudentDetail(student);
    }

    // EFFECTS: process the command which operate on student's info
    private void commandProcessForStudentDetail(Student student) {
        String command = input.next();
        if (command.equals("en")) {
            editStudentName(student);
        } else if (command.equals("ad")) {
            editAddress(student);
        } else if (command.equals("pn")) {
            editPhoneNum(student);
        } else if (command.equals("na")) {
            editNationality(student);
        } else if (command.equals("em")) {
            editEmail(student);
        } else if (command.equals("sn")) {
            editStudentNumber(student);
        } else if (command.equals("ec")) {
            editEmergencyContactor(student);
        } else if (command.equals("ci")) {
            courseDetailCommand(student);
        } else if (command.equals("ac")) {
            addCourse(student);
        } else {
            System.out.println("Return to student List");
            showStudentList();
        }
    }


    // MODIFIES: student
    // EFFECTS: add a new course to student course list
    private void addCourse(Student student) {
        Course course = initiateCourse();
        student.addCourse(course);
        returnToCurrentStudent(student);
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

    private void courseDetailCommand(Student student) {
        //stub
    }

    private void editEmergencyContactor(Student student) {
        //stub
    }

    // MODIFIES: student
    // EFFECTS: edit student number
    private void editStudentNumber(Student student) {
        System.out.println("Input student number: ");
        String studentId = input.next();
        student.changeStudentNumber(studentId);
        returnToCurrentStudent(student);
    }

    // EFFECTS: print current student info and show command list
    private void returnToCurrentStudent(Student student) {
        student.printInfo();
        commandChoice(student);
    }

    // MODIFIES: student
    // EFFECTS: change student's email address
    private void editEmail(Student student) {
        System.out.println("Input student's email address: ");
        String email = input.next();
        if (email.matches("^(.+)@(.+)$")) {
            student.changeEmail(email);
            returnToCurrentStudent(student);
        } else {
            System.out.println("Invalid email address");
            editEmail(student);
        }
    }

    // MODIFIES: student
    // EFFECTS: change student's nationality
    private void editNationality(Student student) {
        System.out.println("Input student's nationality: ");
        String nationality = input.next();
        student.changeNationality(nationality);
        returnToCurrentStudent(student);
    }

    // MODIFIES: student
    // EFFECTS: change student's phone number
    private void editPhoneNum(Student student) {
        System.out.println("Input student's phone number");
        String phoneNum = input.next();
        student.changePhoneNum(phoneNum);
        returnToCurrentStudent(student);
    }

    private void editAddress(Student student) {
        // stub
    }

    // MODIFIES: student
    // EFFECTS: change student's name
    private void editStudentName(Student student) {
        System.out.println("Input last name: ");
        String lastName = input.next();
        System.out.println("Input first name: ");
        String firstName = input.next();
        student.changeName(firstName,lastName);
        returnToCurrentStudent(student);
    }

}
