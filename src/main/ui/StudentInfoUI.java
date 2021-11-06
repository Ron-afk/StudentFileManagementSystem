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
    private JScrollPane scrollPane;

    public StudentInfoUI(List<Student> studentList, Student student) {
        this.student = student;
        this.studentList = studentList;

        frame.setSize(1290, 900);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Detailed Student Info");
        frame.setLayout(new BorderLayout());

        addButton();
        addScrollPane();

        frame.setVisible(true);
    }

    private void addButton() {
        JButton editButton = new JButton(new EditAction(student));
        JButton timeTableButton = new JButton("time table");

        frame.add(editButton,BorderLayout.NORTH);
        frame.add(timeTableButton,BorderLayout.SOUTH);
    }

    private void addScrollPane() {
        scrollPane = new JScrollPane();

        presentInfo();

        scrollPane.setVisible(true);
        frame.add(scrollPane, BorderLayout.CENTER);
    }

    private void presentInfo() {
//
    }

    private class EditAction extends AbstractAction {
        Student student;

        EditAction(Student student) {
            super("edit");
            this.student = student;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            new EditableStudentInfoUI(studentList,student);
            frame.dispose();
        }
    }
}
