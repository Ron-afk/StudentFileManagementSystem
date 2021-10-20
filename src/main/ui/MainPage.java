package ui;

import model.Student;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class MainPage {
    private static final String JSON_STORE = "./data/studentFile.json";
    private Scanner input;
    private List<Student> students;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private Boolean saved = false;

    // EFFECTS: run the student file system
    public MainPage() throws FileNotFoundException {
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
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

            if (command.equals("q") && saved) {
                keepGoing = false;
            } else if (command.equals("q") && !saved) {
                System.out.println("\nYou have unsaved information, do you want to quit without save?");
                System.out.println("\tq -> quit without save");
                System.out.println("\ts -> save and quit");
                keepGoing = saveOrQuit();
            } else {
                processCommand(command);
            }
        }
    }

    // EFFECTS: terminate application with save information or not based on input
    private boolean saveOrQuit() {
        String command = input.next();
        command.toLowerCase();
        if (command.equals("q")) {
            return false;
        } else if (command.equals("s")) {
            saveStudents();
            return false;
        } else {
            System.out.println("Unexpected input... Return to main menu...");
            return true;
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
        System.out.println("\td -> Display all students");
        System.out.println("\ta -> Add a new student");
        System.out.println("\ts -> Save students information to file");
        System.out.println("\tl -> Load students information from file");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: process based on the user input command
    private void processCommand(String command) {
        switch (command) {
            case "d":
                new StudentListPage(students);
                saved = false;
                break;
            case "a":
                addNewStudent();
                saved = false;
                break;
            case "s":
                saveStudents();
                saved = true;
                break;
            case "l":
                loadInfo();
                saved = false;
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

    // EFFECTS: save students information to file
    private void saveStudents() {
        try {
            jsonWriter.open();
            jsonWriter.write(students);
            jsonWriter.close();
            System.out.println("Saved students information to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads student list from file
    private void loadInfo() {
        try {
            students = jsonReader.read();
            System.out.println("Loaded student list from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }

    }
}
