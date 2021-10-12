package ui;

import model.Course;
import model.Student;

import java.util.List;
import java.util.Scanner;

public class CourseListPage {
    private Scanner input;
    private List<Course> courseList;
    private PrintInfo printInfo = new PrintInfo();
    private final int maxLoad = 4;

    // MODIFIES: this
    // EFFECTS: initiate course list page
    public CourseListPage(List<Course> courseList) {
        this.courseList = courseList;

        init();
        runCourseListPage();
    }

    // MODIFIES: this
    // EFFECTS: initiate scanner
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: run the course page
    private void runCourseListPage() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            int i = 0;
            for (Course c : courseList) {
                System.out.println((i + 1) + " --> ");
                printInfo.coursePrintSimple(c);
                i++;
            }
            courseListCommand();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
    }

    // EFFECTS: print course list command option
    private void courseListCommand() {
        System.out.println("\nInput a number to view course detail, or choose from following command:");
        System.out.println("\ta -> add course");
        System.out.println("\tavg -> get current average");
        System.out.println("\tsch -> get current term schedule");
        System.out.println("\tplc -> get all future planned courses");
        System.out.println("\tq -> back to student's page");
    }

    // MODIFIES: courses, student
    // EFFECTS: view and edit course for student
    private void processCommand(String command) {
        try {
            int i = Integer.valueOf(command);
            Course c = courseList.get(i - 1);
            new CoursePage(c);
        } catch (Exception e) {
            if (command.equals("a")) {
                courseList.add(initiateCourse());
            } else if (command.equals("avg")) {
                System.out.println("Current Average: " + Student.gradeAvg(courseList));
            } else if (command.equals("sch")) {
                printSchedule();
            } else if (command.equals("plc")) {
                printFutureCourse();
            } else {
                System.out.println("Invalid input...");
            }
        }
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

    //EFFECTS: pint all currently taking courses with time block
    private void printSchedule() {
        List<Course> currentCourses = Student.getCurrentCourses(courseList);
        if (currentCourses.size() > maxLoad) {
            System.out.println("Over loaded");
        } else if (currentCourses.size() == 0) {
            System.out.println("No course for current term");
        } else {
            for (Course c : currentCourses) {
                System.out.println("Course Name: " + c.getCourseName() + "Time Block: " + c.getTimeBlock());
            }
        }
    }

    // EFFECTS: print all future courses
    private void printFutureCourse() {
        List<Course> futureCourse = Student.getFutureCourse(courseList);
        if (futureCourse.size() == 0) {
            System.out.println("No future planned course");
        } else {
            for (Course c : futureCourse) {
                printInfo.coursePrint(c);
            }
        }
    }
}
