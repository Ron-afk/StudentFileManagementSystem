package ui;

import model.EmergencyContactor;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

// This class creates a modifiable student information page for user to edit current
// student information or add a new student to the student list
public class EditableStudentInfoUI extends JFrame {

    private List<Student> studentList;
    private JFrame frame = new JFrame();
    private Student student;
    private final String defaultStudentNum = "Input student number";
    private final String defaultGender = "Input gender";
    private final String defaultNationality = "Input nationality";
    private final String defaultEmail = "Input email";
    private final String defaultDOB = "Input date of birth";
    private final String defaultPhoneNUm = "Input phone number";
    private JPanel infoPanel;

    // EFFECTS: construct a new page for editing student information
    public EditableStudentInfoUI(List<Student> studentList, Student student) {
        this.studentList = studentList;
        this.student = student;
//        System.out.println("new page created");

        frame.setSize(1290, 900);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowCloseOption());
        frame.setTitle("Edit student");
        frame.setLayout(new BorderLayout());

        addButton();
        presentInfo();

        frame.setVisible(true);
        // stub
    }

    private class WindowCloseOption extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            String[] options = {"save", "save and close", "cancel"};
            int i = JOptionPane.showOptionDialog(null,
                    "Save and close",
                    "Close?",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            if (i == 0) {
                studentList.add(student);
            } else if (i == 1) {
                studentList.add(student);
                frame.dispose();
            }
        }
    }

    // EFFECTS: add save button to the page
    private void addButton() {
        JButton saveButton = new JButton(new SaveAction());
        frame.add(saveButton, BorderLayout.SOUTH);
    }

    /**
     * This is a private class to add action when click the save button on this page
     * it will ask if user want to exit the page after save or continue to work on this page
     */
    private class SaveAction extends AbstractAction {
        // EFFECTS: add label to the button
        SaveAction() {
            super("save");
        }

        // MODIFIES: this
        // EFFECTS: add student to the student list
        //          and ask if the user want to exit the page or continue work on this page
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!studentList.contains(student)) {
                studentList.add(student);
            }
            int i = JOptionPane.showConfirmDialog(null,
                    "Close current window?",
                    "Close notice",
                    JOptionPane.YES_NO_OPTION);
            if (i == 0) {
                frame.dispose();
            }
        }
    }

    private void presentInfo() {

    }

}
