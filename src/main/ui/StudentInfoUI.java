package ui;

import model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class StudentInfoUI extends JFrame {
    private Student student;
    private JFrame frame = new JFrame();
    private List<Student> studentList;


    public StudentInfoUI(List<Student> studentList, Student student) {
        this.student = student;
        this.studentList = studentList;

        frame.setSize(1290, 900);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Detailed Student Info");
        frame.setLayout(new BorderLayout());

        addButton();
        JPanel detailedStudentInfo = new JPanel();
        new StudentInfoPanelUI(detailedStudentInfo, student,false);

        frame.add(detailedStudentInfo, BorderLayout.CENTER);
        detailedStudentInfo.setVisible(true);
        frame.setVisible(true);
    }

    private void addButton() {
        JPanel southPanel = new JPanel();
        JButton editButton = new JButton(new EditAction(student));
        JButton addCourseButton = new JButton(new AddCourseAction());
        JButton timeTableButton = new JButton("time table");
        JButton currentAverageButton = new JButton(new AverageAction());

        southPanel.add(timeTableButton,BorderLayout.WEST);
        southPanel.add(currentAverageButton,BorderLayout.CENTER);
        southPanel.add(addCourseButton,BorderLayout.EAST);

        frame.add(editButton, BorderLayout.NORTH);
        frame.add(southPanel, BorderLayout.SOUTH);
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
            // stub
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
