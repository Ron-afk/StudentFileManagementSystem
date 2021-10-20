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
            System.out.println("\nInput a number to view detailed student's information ");
            showStudentList();
            System.out.println("\nPress d to delete a student");
            System.out.println("\nPress q to back to main page");
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                try {
                    new StudentPage(selectStudent(command));
                } catch (Exception e) {
                    if (command.equals("d")) {
                        removeStudent();
                    } else {
                        System.out.println("Invalid input...");
                    }
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
        for (Student s : students) {
            System.out.println("\t" + i + " -> " + s.getLastName() + "  " + s.getFirstName());
            i++;
        }
    }

    // EFFECTS: select a student to show his/her detailed info
    private Student selectStudent(String command) throws Exception {
        try {
            int i = Integer.valueOf(command);
            Student student = students.get(i - 1);
            return student;
        } catch (Exception e) {
            throw new Exception();
        }
    }

    // MODIFIES: this
    // EFFECTS: show student list and choose which to delete
    private void removeStudent() {
        showStudentList();
        System.out.println("Input a number to choose the student you want to delete");
        String command = input.next();
        try {
            remove(selectStudent(command));
        } catch (Exception e) {
            System.out.println("Invalid input...");
        }
    }

    // MODIFIES: this
    // EFFECTS: remove student from student list
    private void remove(Student student) {
        students.remove(student);
    }
}
