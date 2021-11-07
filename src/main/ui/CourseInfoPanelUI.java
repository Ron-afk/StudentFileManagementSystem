package ui;

import model.Course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class CourseInfoPanelUI extends JPanel {
    private final int tagWidth = 200;
    private final int tagHeight = 25;

    private JFrame frame;
    private JPanel panel = new JPanel();
    private Course course;
    boolean editable;
    private JTextArea courseNameInput;
    private JTextArea teacherInput;
    private JTextArea finishTimeInput;
    private JTextArea gradeInput;
    private String[] statusComBox = {"finished","currently taking","planned for next term", "planned for future"};
    private Integer[] timeBlockComBox = {1,2,3,4};
    private JComboBox statusInput;
    private JComboBox timeBlockInput;
    private String courseName;
    private String teacher;
    private String finishTime;
    private int timeBlock;
    private int status;
    private double grade;
    private List<Course> courseList;


    public CourseInfoPanelUI(JFrame frame,List<Course> courseList,Course course, boolean editable) {
        this.course = course;
        this.frame = frame;
        this.editable = editable;
        this.courseList = courseList;
        init();
        if (editable) {
            addButton();
        }

        courseInfoConverter();
        frame.setVisible(true);
    }

    private void init() {

        frame.setSize(750,750);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowCloseOption());
        frame.setTitle("Edit Course");
        frame.setLayout(new BorderLayout());
    }

    private void courseInfoConverter() {

        Box box = Box.createVerticalBox();

        box.add(courseNameConverter());
        box.add(Box.createRigidArea(new Dimension(0,5)));

        box.add(teacherConverter());
        box.add(Box.createRigidArea(new Dimension(0,5)));

        box.add(statusConverter());
        box.add(Box.createRigidArea(new Dimension(0,5)));

        box.add(timeBlockConverter());
        box.add(Box.createRigidArea(new Dimension(0,5)));

        box.add(finishTimeConverter());
        box.add(Box.createRigidArea(new Dimension(0,5)));

        box.add(gradeConverter());

        box.add(Box.createHorizontalStrut(700));
        box.add(Box.createVerticalStrut(700));

        panel.add(box,BorderLayout.CENTER);
        frame.add(panel,BorderLayout.CENTER);
    }

    private Box courseNameConverter() {
        JLabel tag = new JLabel("Course Name");
        tag.setPreferredSize(new Dimension(tagWidth,tagHeight));

        courseNameInput = new JTextArea(course.getCourseName());
        courseNameInput.setEditable(editable);
        Box box = Box.createHorizontalBox();
        box.add(tag);
        box.add(courseNameInput);
        return box;
    }

    private Box teacherConverter() {
        JLabel tag = new JLabel("Teacher");
        tag.setPreferredSize(new Dimension(tagWidth,tagHeight));
        teacherInput = new JTextArea(course.getTeacher());
        teacherInput.setEditable(editable);
        Box box = Box.createHorizontalBox();
        box.add(tag);
        box.add(teacherInput);
        return box;
    }

    private Box statusConverter() {
        JLabel tag = new JLabel("status");
        tag.setPreferredSize(new Dimension(tagWidth,tagHeight));
        statusInput = new JComboBox(statusComBox);
        statusInput.setSelectedIndex(course.getStatus());
        statusInput.setEditable(editable);
        Box box = Box.createHorizontalBox();
        box.add(tag);
        box.add(statusInput);
        return box;
    }

    private Box timeBlockConverter() {
        JLabel tag = new JLabel("Time Block");
        tag.setPreferredSize(new Dimension(tagWidth,tagHeight));
        timeBlockInput = new JComboBox(timeBlockComBox);
        timeBlockInput.setSelectedIndex(course.getTimeBlock() - 1);
        timeBlockInput.setEditable(editable);
        Box box = Box.createHorizontalBox();
        box.add(tag);
        box.add(timeBlockInput);
        return box;
    }

    private Box finishTimeConverter() {
        JLabel tag = new JLabel("Finish Time");
        tag.setPreferredSize(new Dimension(tagWidth,tagHeight));
        finishTimeInput = new JTextArea(course.getFinishTime());
        finishTimeInput.setEditable(editable);
        Box box = Box.createHorizontalBox();
        box.add(tag);
        box.add(finishTimeInput);
        return box;
    }

    private Box gradeConverter() {
        JLabel tag = new JLabel("Grade");
        tag.setPreferredSize(new Dimension(tagWidth,tagHeight));
        gradeInput = new JTextArea(Double.toString(course.getGrade()));
        gradeInput.setEditable(editable);
        Box box = Box.createHorizontalBox();
        box.add(tag);
        box.add(gradeInput);
        return box;
    }

    private void getInput() {
        courseName = courseNameInput.getText();
        teacher = teacherInput.getText();
        finishTime = finishTimeInput.getText();
        timeBlock = timeBlockInput.getSelectedIndex() + 1;
        status = statusInput.getSelectedIndex();
        grade = Double.parseDouble(gradeInput.getText());
    }

    private void update() {
        getInput();
        course.changeCourseName(courseName);
        course.changeTeacher(teacher);
        course.changeTimeBlock(timeBlock);
        course.changeFinishTime(finishTime);
        course.changeGrade(grade);
        course.changeStatus(status);
    }


    private class WindowCloseOption extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            String[] options = {"save","save and close", "close"};
            int i = JOptionPane.showOptionDialog(null,
                    "Save and close",
                    "Close?",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            if (i == 0) {
                new SaveAction();
            } else if (i == 0) {
                new SaveAction();
                frame.dispose();
            } else {
                frame.dispose();
            }
        }
    }

    private void addButton() {
        JButton saveButton = new JButton(new SaveAction());
        frame.add(saveButton,BorderLayout.SOUTH);
    }

    private class SaveAction extends AbstractAction {

        SaveAction() {
            super("save");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!courseList.contains(course)) {
                courseList.add(course);
            }
            update();
            int i = JOptionPane.showConfirmDialog(null,
                    "Close current window?",
                    "Close notice",
                    JOptionPane.YES_NO_OPTION);
            if (i == 0) {
                frame.dispose();
            }
        }
    }
}
