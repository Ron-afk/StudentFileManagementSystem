package ui;

import model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class StudentFileApplication {
    private List<Student> students;

    // EFFECTS: run the student file system, show the main page
    public StudentFileApplication() {
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: initializes students
    public void init() {
        students = new ArrayList<>();
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
    }

    private void processCommand(String command) {
    }

    public void displayMainPage() {
        System.out.println("\nSelect from:");
        System.out.println("\ts -> Show all student");
        System.out.println("\ta -> Add a new student");
        System.out.println("\tq -> quit");
    }

    public void showStudentList() {
        int i = 1;
        for (Student s : students) {
            System.out.println(i + " -> " + s.getLastName() + "  " + s.getFirstName());
            i++;
        }
    }
}
