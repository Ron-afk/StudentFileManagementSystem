package ui;

import model.Course;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * This class create a new page to represent the detailed student information with their course history
 */
public class StudentInfoUI extends JFrame {
    private Student student;
    private JFrame frame = new JFrame();
    private List<Student> studentList;
    private JScrollPane scrollPane;
    private JPanel detailedStudentInfo;
    private List<Course> courseList;

    // EFFECTS: create a new page to show the detailed student info
    public StudentInfoUI(List<Student> studentList, Student student) {
        this.student = student;
        this.studentList = studentList;
        this.courseList = this.student.getAllCourses();

        frame.setSize(1290, 900);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Detailed Student Info");
        frame.setLayout(new BorderLayout());

        addButton();
        presentInfo();
    }

    // EFFECTS: present detailed student info on the frame
    private void presentInfo() {
        detailedStudentInfo = new JPanel();
        new StudentInfoPanelUI(detailedStudentInfo, student, false);

        scrollPane = new JScrollPane(detailedStudentInfo);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVisible(true);

        frame.add(scrollPane, BorderLayout.CENTER);
        detailedStudentInfo.setVisible(true);
        frame.setVisible(true);

    }

    // EFFECTS: add edit button, add course button, time table button, and refresh button to the frame
    private void addButton() {
        JPanel southPanel = new JPanel();
        JButton editButton = new JButton(new EditAction(student));
        JButton addCourseButton = new JButton(new AddCourseAction());
        JButton timeTableButton = new JButton(new TimeTableAction(Student.getCurrentCourses(courseList)));
        JButton currentAverageButton = new JButton(new AverageAction());
        JButton refreshButton = new JButton(new RefreshAction());
        JPanel northPanel = new JPanel(new BorderLayout());

        northPanel.add(editButton, BorderLayout.EAST);
        northPanel.add(refreshButton, BorderLayout.WEST);

        southPanel.add(timeTableButton, BorderLayout.WEST);
        southPanel.add(currentAverageButton, BorderLayout.CENTER);
        southPanel.add(addCourseButton, BorderLayout.EAST);

        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(southPanel, BorderLayout.SOUTH);
    }

    /**
     * This class provide an action listener for tiem table button
     * Create a new page to show the time table for current term
     */
    private class TimeTableAction extends AbstractAction {
        List<Course> courseList;

        TimeTableAction(List<Course> courseList) {
            super("Time Table");
            this.courseList = courseList;
        }

        // EFFECTS: create a new page to show the time table
        @Override
        public void actionPerformed(ActionEvent e) {
            new TimeTableUI(courseList);
        }
    }

    /**
     * This class provide an action listener for refresh button
     * Refresh current page
     */
    private class RefreshAction extends AbstractAction {

        RefreshAction() {
            super("Refresh");
        }

        // EFFECTS: refresh current page
        @Override
        public void actionPerformed(ActionEvent e) {
//            frame.setVisible(false);
//            detailedStudentInfo.setVisible(false);
//            scrollPane.setVisible(false);
//            presentInfo();
            frame.dispose();
            new StudentInfoUI(studentList, student);
        }
    }

    /**
     * This class provide an action listener for edit button
     * Create a new page to edit selected course
     */
    private class EditAction extends AbstractAction {
        Student student;

        EditAction(Student student) {
            super("edit");
            this.student = student;
        }

        // EFFECTS: create a new page to edit selected course
        @Override
        public void actionPerformed(ActionEvent e) {
            new EditableStudentInfoUI(studentList, student);
            frame.dispose();
        }
    }

    /**
     * This class provides an action listener for add course button
     * Create a new page to add new course to student
     */
    private class AddCourseAction extends AbstractAction {
        AddCourseAction() {
            super("add course");
        }

        // EFFECTS: create a new page to add a new course to student
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame courseFrame = new JFrame();
            new CourseInfoPanelUI(courseFrame, courseList, new Course(""), true);

        }
    }

    /**
     * This class provides an action listener for average grade button
     * Create a new option panel to show the current average grade
     */
    private class AverageAction extends AbstractAction {
        AverageAction() {
            super("get current average");
        }

        // EFFECTS: create a new option panel to show current average grade
        @Override
        public void actionPerformed(ActionEvent e) {
            double avg = student.gradeAvg(student.getFinishedCourses());
            JOptionPane.showMessageDialog(null,
                    "Current average is :" + avg,
                    "Current average",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }
}
