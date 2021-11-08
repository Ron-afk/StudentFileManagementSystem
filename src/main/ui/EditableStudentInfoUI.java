package ui;

import model.Course;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

// This class creates a modifiable student information page for user to edit current
// student information or add a new student to the student list
public class EditableStudentInfoUI extends JFrame {

    private List<Student> studentList;
    private JFrame frame = new JFrame();
    private Student student;
    private StudentInfoPanelUI editableStudentInfoPanelUI;

    // EFFECTS: construct a new page for editing student information
    public EditableStudentInfoUI(List<Student> studentList, Student student) {
        this.studentList = studentList;
        this.student = student;

        frame.setSize(1290, 900);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowCloseOption());
        frame.setTitle("Edit student");
        frame.setLayout(new BorderLayout());

        addButton();

        JPanel detailedStudentInfo = new JPanel();
        editableStudentInfoPanelUI = new StudentInfoPanelUI(detailedStudentInfo, student,true);

        frame.add(detailedStudentInfo, BorderLayout.CENTER);
        detailedStudentInfo.setVisible(true);

        frame.setVisible(true);
    }

    /** This class provide a window listener for window close
     *
     */
    public class WindowCloseOption extends WindowAdapter {

        // EFFECTS: an option panel will show up to choose behavior
        //          between save, save and close, and close without save
        @Override
        public void windowClosing(WindowEvent e) {
            String[] options = {"save", "save and close", "close"};
            int i = JOptionPane.showOptionDialog(null,
                    "Save and close",
                    "Close?",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            if (i == 0) {
                editableStudentInfoPanelUI.update();

                if (!studentList.contains(student)) {
                    studentList.add(student);
                }
            } else if (i == 1) {
                editableStudentInfoPanelUI.update();
                if (!studentList.contains(student)) {
                    studentList.add(student);
                }
                frame.dispose();
            } else {
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
            editableStudentInfoPanelUI.update();
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

//    private class AddCourseAction extends AbstractAction {
//        AddCourseAction() {
//            super("add course");
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            JFrame courseFrame = new JFrame();
//
//            new CourseInfoPanelUI(courseFrame,student.getAllCourses(),new Course(""),true);
//
//        }
//    }

}
