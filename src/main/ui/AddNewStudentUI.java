package ui;

import model.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddNewStudentUI extends JFrame implements ActionListener {

    private List<Student> studentList;

    public AddNewStudentUI(List<Student> studentList) {
        this.studentList = studentList;
        System.out.println("new page created");
        // stub
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // stub
    }
}
