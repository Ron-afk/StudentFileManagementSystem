package ui;

import model.Student;

import java.util.List;
import java.util.Scanner;

public class StudentListPage {
    private Scanner input;
    private List<Student> students;

    // MODIFIES: this
    // EFFECTS: initiate student list page
    public StudentListPage(List<Student> students) {
        this.students = students;

        init();
        runStudentListPage();
    }

    private void runStudentListPage() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            showStudentList();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                try {
                    selectStudent(command);
                } catch (Exception e) {
                    System.out.println("Invalid input... "
                            + "Please reselect number to view, or press q to back to main page");
                }
            }
        }
    }


    // MODIFIES: this
    // EFFECTS: initiate scanner
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }


    // EFFECTS: show all students in list
    public void showStudentList() {
        int i = 1;
        System.out.println("\nInput a number to view detailed student's information "
                + "or press q to back to the main page.");
        for (Student s : students) {
            System.out.println("\t" + i + " -> " + s.getLastName() + "  " + s.getFirstName());
            i++;
        }
    }

    // EFFECTS: select a student to show his/her detailed info
    private void selectStudent(String command) throws Exception {
        try {
            int i = Integer.valueOf(command);
            Student student = students.get(i - 1);
            new StudentPage(student);
        } catch (Exception e) {
            throw new Exception();
        }
    }


}
