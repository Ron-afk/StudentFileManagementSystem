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

    private void processCommand(String command) {
        if (command.equals("s")) {
            showStudentList();
        } else if (command.equals("a")) {
            addNewStudent();
        } else {
            System.out.println("Invalid input");
        }
    }

    private void addNewStudent() {
        System.out.println("Input Last Name: ");
        String lastName = input.next();
        System.out.println("Input First Name: ");
        String firstName = input.next();
        Student newStudent = new Student(firstName,lastName);
        students.add(newStudent);
    }

    public void displayMainPage() {
        System.out.println("\nSelect from:");
        System.out.println("\ts -> Show all students");
        System.out.println("\ta -> Add a new student");
        System.out.println("\tq -> quit");
    }

    public void showStudentList() {
        int i = 1;
        System.out.println("\nInput a number to view detailed student's information.");
        for (Student s : students) {
            System.out.println("\t" + i + " -> " + s.getLastName() + "  " + s.getFirstName());
            i++;
        }
        selectStudent();
    }

    private void selectStudent() {
        String selection = input.next();
        try {
            int i = Integer.valueOf(selection);
            if (i > students.size() || i < 0) {
                System.out.println("Unexpected input number");
            } else {
                Student student = students.get(i - 1);
                student.printInfo();
                commondChoice();
                commandProcessForStudentDetail(student);
            }
        } catch (Exception e) {
            System.out.println("Please input a number!");
            showStudentList();
        }
        
    }

    private void commondChoice() {
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
    }

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
            System.out.println("Unexpected command");
        }
    }

    private void addCourse(Student student) {
    }

    private void courseDetailCommand(Student student) {
    }

    private void editEmergencyContactor(Student student) {
    }

    private void editStudentNumber(Student student) {
    }

    private void editEmail(Student student) {
    }

    private void editNationality(Student student) {
    }

    private void editPhoneNum(Student student) {
    }

    private void editAddress(Student student) {
    }

    private void editStudentName(Student student) {
    }



}
