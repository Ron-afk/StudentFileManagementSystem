package ui;

import model.Course;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class StudentInfoUI extends JFrame {
    private Student student;
    private JFrame frame = new JFrame();
    private List<Student> studentList;
    private JScrollPane scrollPane;
    private JPanel detailedStudentInfo;


    public StudentInfoUI(List<Student> studentList, Student student) {
        this.student = student;
        this.studentList = studentList;

        frame.setSize(1290, 900);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Detailed Student Info");
        frame.setLayout(new BorderLayout());

        addButton();
        presentInfo();
    }

    private void presentInfo() {
        detailedStudentInfo = new JPanel();
        new StudentInfoPanelUI(detailedStudentInfo, student,false);

        scrollPane = new JScrollPane(detailedStudentInfo);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVisible(true);

        frame.add(scrollPane, BorderLayout.CENTER);
        detailedStudentInfo.setVisible(true);
        frame.setVisible(true);

    }

    private void addButton() {
        JPanel southPanel = new JPanel();
        JButton editButton = new JButton(new EditAction(student));
        JButton addCourseButton = new JButton(new AddCourseAction());
        JButton timeTableButton = new JButton("time table");
        JButton currentAverageButton = new JButton(new AverageAction());
        JButton refreshButton = new JButton(new RefreshAction());
        JPanel northPanel = new JPanel(new BorderLayout());

        northPanel.add(editButton,BorderLayout.EAST);
        northPanel.add(refreshButton,BorderLayout.WEST);

        southPanel.add(timeTableButton,BorderLayout.WEST);
        southPanel.add(currentAverageButton,BorderLayout.CENTER);
        southPanel.add(addCourseButton,BorderLayout.EAST);

        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(southPanel, BorderLayout.SOUTH);
    }

    private class RefreshAction extends AbstractAction {

        RefreshAction() {
            super("Refresh");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            detailedStudentInfo.setVisible(false);
            scrollPane.setVisible(false);
            presentInfo();
        }
    }

    private class EditAction extends AbstractAction {
        Student student;

        EditAction(Student student) {
            super("edit");
            this.student = student;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            new EditableStudentInfoUI(studentList, student);
            frame.dispose();
        }
    }

    private class AddCourseAction extends AbstractAction {
        AddCourseAction() {
            super("add course");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame courseFrame = new JFrame();
            new CourseInfoPanelUI(courseFrame,student.getAllCourses(),new Course(""),true);

        }


    }



    private class AverageAction extends AbstractAction {
        AverageAction() {
            super("get current average");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // stub
        }
    }
}
