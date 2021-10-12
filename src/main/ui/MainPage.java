package ui;

import exception.InvalidInputException;
import model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class MainPage {
    private Scanner input;
    private List<Student> students;

    // EFFECTS: run the student file system
    public MainPage() {
        runApp();
    }

    // EFFECTS: run the program, start with showing the main page
    private void runApp() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            mainPageCommandList();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: initiate scanner and student list
    private void init() {
        students = new ArrayList<>();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: display the command list for main page
    private void mainPageCommandList() {
        System.out.println("\nSelect from:");
        System.out.println("\ts -> Show all students");
        System.out.println("\ta -> Add a new student");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: process based on the user input command
    private void processCommand(String command) {
        switch (command) {
            case "s":
                new StudentListPage(students);
                break;
            case "a":
                addNewStudent();
                break;
            default:
                System.out.println("Invalid input...");
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
}
