package model;

// represent course information
public class Course {
    private String courseName;
    private String teacher;
    private int timeBlock;
    private String finishTime;
    private double grade;
    private int status; // default as finished course

    // REQUIRES: course name cannot be empty string
    // MODIFIES: this
    // EFFECTS: initiate a course with course name
    public Course(String courseName) {
        this.courseName = courseName;
    }

    // MODIFIES: this
    // EFFECTS: change course teacher
    public void changeTeacher(String teacher) {
        this.teacher = teacher;
    }

    // REQUIRES: input must between 1 - 4
    // MODIFIES: this
    // EFFECTS: change time block, 1 is the 1st block in morning, 2 is the 2nd block in morning
    //          3 is the 1st block in afternoon, 4 is the 2nd block in afternoon
    public void changeTimeBlock(int timeBlock) {
        this.timeBlock = timeBlock;
    }

    // REQUIRES: finish time should be in form of yyyy+term, for example "2020W" refers to 2020 winter term
    // MODIFIES: this
    // EFFECTS: change the finish time of the course
    public void changeFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    // REQUIRES: grade must be positive
    // MODIFIES: this
    // EFFECTS: change grade for a finished course
    public void changeGrade(double grade) {
        this.grade = grade;
    }

    // REQUIRES: input int must between 0 - 3
    // MODIFIES: this
    // EFFECTS: change status of the course, 0 means finished, 1 means currently taking, 2 means planned for following
    //          term, 3 means planned for university but will not be taken in following term
    public void changeStatus(int status) {
        this.status = status;
    }

    // EFFECTS: print course detail
    public void print() {
        System.out.println("Course name: " + courseName);
        System.out.print("Status: ");
        if (status == 0) {
            System.out.println("Finished");
            System.out.println("Finish time: " + finishTime);
            System.out.println("Grade: " + grade);
            System.out.println("Teacher: " + teacher);
        } else if (status == 1) {
            System.out.println("Currently taking");
            System.out.println("Time block: " + timeBlock);
            System.out.println("Teacher: " + teacher);
        } else if (status == 2) {
            System.out.println("Planned for next term");
            System.out.println("Time block: " + timeBlock);
            System.out.println("Teacher: " + teacher);
        } else {
            System.out.println("Planned for future");
        }
    }

    // EFFECT: print simplified course info
    public void printSimple() {
        System.out.println("Course name: " + courseName);
        System.out.print("Status: ");
        if (status == 0) {
            System.out.println("Finished");
        } else if (status == 1) {
            System.out.println("Currently taking");
        } else if (status == 2) {
            System.out.println("Planned for next term");
        } else {
            System.out.println("Planned for future");
        }
    }

    // EFFECTS: get status of the course
    public int getStatus() {
        return status;
    }

    // EFFECTS: get course name
    public String getCourseName() {
        return courseName;
    }

    // EFFECTS: get course's teacher
    public String getTeacher() {
        return teacher;
    }

    // EFFECTS: get course's grade
    public double getGrade() {
        return grade;
    }

    // EFFECTS: get course's time block
    public int getTimeBlock() {
        return timeBlock;
    }

    // EFFECTS: get course's finish time
    public String getFinishTime() {
        return finishTime;
    }
}
