package ui;

import model.Course;
import model.Student;

import java.util.Scanner;

public class CoursePage {
    private Course course;
    private Scanner input;

    // MODIFIES: this
    // EFFECTS: initiate a new course page
    public CoursePage(Course course) {
        this.course = course;

        init();
        runCoursePage();
    }

    // MODIFIES: this
    // EFFECTS: initiate scanner
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    private void runCoursePage() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            commandList();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
    }

    // EFFECTS: show command option to operate on course
    private void commandList() {
        System.out.println("\nChoose a command: ");
        System.out.println("\tn -> edit course name");
        System.out.println("\tg -> edit course grade");
        System.out.println("\ts -> edit course status");
        System.out.println("\tf -> edit course finishTime");
        System.out.println("\tt -> edit course time block");
        System.out.println("\tp -> edit course teacher");
        System.out.println("\tq -> back to course list");
    }

    // EFFECTS: process course command
    private void processCommand(String command) {
        if (command.equals("n")) {
            editCourseName();
        } else if (command.equals("g")) {
            editCourseGrade();
        } else if (command.equals("s")) {
            editCourseStatus();
        } else if (command.equals("f")) {
            editCourseFinishTime();
        } else if (command.equals("t")) {
            editCourseTimeBlock();
        } else if (command.equals("p")) {
            editCourseTeacher();
        } else {
            System.out.println("Invalid input...");
        }
    }

    // MODIFIES: this
    // EFFECTS: change course teacher's name
    private void editCourseTeacher() {
        System.out.println("Input teacher's name: ");
        String teacher = input.next();
        course.changeTeacher(teacher);
    }

    // MODIFIES: this
    // EFFECTS: change course time block
    private void editCourseTimeBlock() {
        System.out.println("Input 1 - 4 for time block");
        int timeBlock = Integer.valueOf(input.next());
        course.changeTimeBlock(timeBlock);
    }

    // MODIFIES: this
    // EFFECTS: change course finish time
    private void editCourseFinishTime() {
        System.out.println("Input finish time: ");
        String finishTime = input.next();
        course.changeFinishTime(finishTime);
    }

    // MODIFIES: this
    // EFFECTS: change course status
    private void editCourseStatus() {
        System.out.println("\nInput course status: ");
        System.out.println("\t0 -> finished");
        System.out.println("\t1 -> currently taking");
        System.out.println("\t2 -> planned for next term");
        System.out.println("\t3 -> planned for future");
        int status = Integer.valueOf(input.next());
        course.changeStatus(status);
    }

    // MODIFIES: this
    // EFFECTS: change course grade
    private void editCourseGrade() {
        System.out.println("Input course grade: ");
        String grade = input.next();
        Double num = Double.valueOf(grade);
        course.changeGrade(num);
    }

    // MODIFIES: this
    // EFFECTS: change course name
    private void editCourseName() {
        System.out.println("Input course name: ");
        String courseName = input.next();
        course.changeCourseName(courseName);
    }
}
