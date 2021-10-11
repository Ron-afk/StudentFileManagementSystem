package ui;

import model.Address;
import model.Course;
import model.EmergencyContactor;
import model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static sun.util.locale.LocaleUtils.isAlphaNumericString;


public class StudentFileApplication {
    private final int maxLoad = 4;

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
        System.out.println("Any number not corresponding to a student will return to previous page");
        String selection = input.next();
        int i = Integer.valueOf(selection);
        if (i > students.size() || i <= 0) {
            System.out.println("Return to previous page");
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
        System.out.println("\tany other command will return to the student list");
        commandProcessForStudentDetail(student);
    }

    // EFFECTS: process the command which operate on student's info
    private void commandProcessForStudentDetail(Student student) {
        String command = input.next();
        if (command.equals("en")) {
            editStudentName(student);
        } else if (command.equals("ad")) {
            editStudentAddress(student);
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
            coursesDetail(student);
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

    // EFFECTS: print course list command option
    private void courseListCommand() {
        System.out.println("\ta -> add course");
        System.out.println("\tavg -> get current average");
        System.out.println("\tsch -> get current term schedule");
        System.out.println("\tplc -> get all future planned courses");
    }

    // EFFECTS: print course detail and command option
    private void coursesDetail(Student student) {
        List<Course> courses = student.getAllCourses();
        int i = 0;
        for (Course c : courses) {
            System.out.println((i + 1) + " --> ");
            c.printSimple();
            i++;
        }
        System.out.println("\nInput a number to visit a course or following command: ");
        courseListCommand();
        courseListCommandProcess(courses,student);
    }

    // MODIFIES: courses, student
    // EFFECTS: view and edit course for student
    private void courseListCommandProcess(List<Course> courses, Student student) {
        String command = input.next();
        try {
            int i = Integer.valueOf(command);
            Course c = courses.get(i - 1);
            displayCourse(c,student);
        } catch (Exception e) {
            if (command.equals("a")) {
                addCourse(student);
            } else if (command.equals("avg")) {
                System.out.println("Current Average: " + student.gradeAvg(courses));
            } else if (command.equals("sch")) {
                printSchedule(student);
            } else if (command.equals("plc")) {
                printFutureCourse(student);
            }
        } finally {
            returnToCurrentStudent(student);
        }
    }

    // EFFECTS: print all future courses
    private void printFutureCourse(Student student) {
        List<Course> futureCourse  = student.getFutureCourse();
        if (futureCourse.size() == 0) {
            System.out.println("No future planned course");
        } else {
            for (Course c : futureCourse) {
                c.print();
            }
        }
    }

    // EFFECTS: print schedule for current term
    private void printSchedule(Student student) {
        List<Course> currentCourses = student.getCurrentCourses();
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

    // MODIFIES: courses
    // EFFECTS: show course and edit course info based on input
    private void displayCourse(Course c,Student student) {
        c.print();
        courseCommand();
        courseCommandProcess(c,student);
    }

    // EFFECTS: show command option to operate on course
    private void courseCommand() {
        System.out.println("\nChoose a command: ");
        System.out.println("\tn -> edit course name");
        System.out.println("\tg -> edit course grade");
        System.out.println("\ts -> edit course status");
        System.out.println("\tf -> edit course finishTime");
        System.out.println("\tt -> edit course time block");
        System.out.println("\tp -> edit course teacher");
        System.out.println("Other command will back to course list");
    }

    // EFFECTS: process course command
    private void courseCommandProcess(Course c,Student student) {
        String command = input.next();
        if (command.equals("n")) {
            editCourseName(c,student);
        } else if (command.equals("g")) {
            editCourseGrade(c,student);
        } else if (command.equals("s")) {
            editCourseStatus(c,student);
        } else if (command.equals("f")) {
            editCourseFinishTime(c,student);
        } else if (command.equals("t")) {
            editCourseTimeBlock(c,student);
        } else if (command.equals("p")) {
            editCourseTeacher(c,student);
        } else {
            coursesDetail(student);
        }
    }

    // MODIFIES: course, student
    // EFFECTS: change course teacher's name
    private void editCourseTeacher(Course c, Student student) {
        System.out.println("Input teacher's name: ");
        String teacher = input.next();
        c.changeTeacher(teacher);
        displayCourse(c,student);
    }

    // MODIFIES: course, student
    // EFFECTS: change course time block
    private void editCourseTimeBlock(Course c, Student student) {
        System.out.println("Input 1 - 4 for time block");
        int timeBlock = Integer.valueOf(input.next());
        c.changeTimeBlock(timeBlock);
        displayCourse(c,student);
    }

    // MODIFIES: course, student
    // EFFECTS: change course finish time
    private void editCourseFinishTime(Course c, Student student) {
        System.out.println("Input finish time: ");
        String finishTime = input.next();
        c.changeFinishTime(finishTime);
        displayCourse(c,student);
    }

    // MODIFIES: course, student
    // EFFECTS: change course status
    private void editCourseStatus(Course c, Student student) {
        System.out.println("\nInput course status: ");
        System.out.println("\t0 -> finished");
        System.out.println("\t1 -> currently taking");
        System.out.println("\t2 -> planned for next term");
        System.out.println("\t3 -> planned for future");
        int status = Integer.valueOf(input.next());
        c.changeStatus(status);
        displayCourse(c,student);
    }

    // MODIFIES: course
    // EFFECTS: change course grade
    private void editCourseGrade(Course c,Student student) {
        System.out.println("Input course grade: ");
        String grade = input.next();
        Double num = Double.valueOf(grade);
        c.changeGrade(num);
        displayCourse(c,student);
    }

    // MODIFIES: course
    // EFFECTS: change course name
    private void editCourseName(Course c,Student s) {
        System.out.println("Input course name: ");
        String courseName = input.next();
        c.changeCourseName(courseName);
        displayCourse(c,s);
    }


    // MODIFIES: this
    // EFFECTS: edit emergency contactor's information
    private void editEmergencyContactor(Student student) {
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
        returnToCurrentStudent(student);
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

    // MODIFIES: student
    // EFFECTS: change student address information
    private void editStudentAddress(Student student) {
        Address address = createNewAddress();
        student.changeStudentAddress(address);
        returnToCurrentStudent(student);
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
