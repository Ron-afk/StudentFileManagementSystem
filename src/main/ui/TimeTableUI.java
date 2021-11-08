package ui;

import model.Course;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/** This class create a new page to show the time table for current term for the student
 *
 */
public class TimeTableUI {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private static final int LABEL_WIDTH = 150;
    private static final int LABEL_HEIGHT = 50;

    private List<Course> courseList;
    private JFrame frame;
    private JPanel panel;

    // EFFECTS: construct a new page to show the time table for current term
    public TimeTableUI(List<Course> courseList) {
        this.courseList = courseList;
        frame = new JFrame();
        frame.setSize(WIDTH,HEIGHT);
        frame.setResizable(true);
        frame.setTitle("Time Table");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        presentInfo();

        frame.setVisible(true);
    }

    // EFFECTS: present the time table on the frame
    private void presentInfo() {
        panel = new JPanel(new GridLayout(courseList.size() + 1,3));

        JLabel nameTag = new JLabel(" Course");
        JLabel teacherTag = new JLabel("|| Teacher ");
        JLabel timeBlockTag = new JLabel("|| Time Block");
        nameTag.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
        teacherTag.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
        timeBlockTag.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
        nameTag.setFont(new Font("JetBrains Mono font", Font.PLAIN,20));
        teacherTag.setFont(new Font("JetBrains Mono font", Font.PLAIN,20));
        timeBlockTag.setFont(new Font("JetBrains Mono font", Font.PLAIN,20));

        panel.add(nameTag);
        panel.add(teacherTag);
        panel.add(timeBlockTag);
        tableConverter();

        frame.add(panel);
    }

    // EFFECTS: convert course info to table format
    private void tableConverter() {

        for (Course c : courseList) {
            String name = c.getCourseName();
            String teacher = c.getTeacher();
            String timeBlock = Integer.toString(c.getTimeBlock());

            JLabel nameLabel = new JLabel(" " + name);
            JLabel teacherLabel = new JLabel("|| " + teacher);
            JLabel timeBlockLabel = new JLabel("|| " + timeBlock);
            nameLabel.setFont(new Font("JetBrains Mono font", Font.PLAIN,20));
            teacherLabel.setFont(new Font("JetBrains Mono font", Font.PLAIN,20));
            timeBlockLabel.setFont(new Font("JetBrains Mono font", Font.PLAIN,20));

            nameLabel.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
            teacherLabel.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
            timeBlockLabel.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));

            panel.add(nameLabel);
            panel.add(teacherLabel);
            panel.add(timeBlockLabel);
        }
    }
}
