package ui;

import model.Student;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** This class represent the main page ui, it can save and load to/from previous data file
 *  The ui will show brief student info and have options to add new student, view detailed student
 *   info or delete a student from the current student list
 */
public class MainPageUI implements ActionListener {
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 900;
    private ImageIcon initIcon = new ImageIcon("./img/initIcon.JPG");

    private String title = "Student File Management System";
    private JFrame mainPage;
    private JMenuItem saveItem;
    private JMenuItem loadItem;
    private JMenuItem refreshItem;
    private JButton addNewButton;
    private JButton saveButton;
    private List<Student> studentList;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private JPanel panel;
    private JScrollPane infoPane;
    private static final String JSON_STORE = "./data/studentFile.json";

    // EFFECTS: create a main page frame
    public MainPageUI() {
        studentList = new ArrayList<>();
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);

        mainPage = new JFrame();
        mainPage.setSize(WIDTH, HEIGHT);
        mainPage.setTitle(title);
        mainPage.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        mainPage.addWindowListener(new WindowCloseOption());
        mainPage.setIconImage(initIcon.getImage());
        mainPage.setLayout(new BorderLayout());

        addMenuBar();
        addButton();
        if (panel != null) {
            panel.setVisible(false);
            infoPane.setVisible(false);
        }
        presentInfo();
    }

    // EFFECTS: add a menu bar to the main page
    private void addMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        saveItem = new JMenuItem("save");
        loadItem = new JMenuItem("load");
        refreshItem = new JMenuItem(new RefreshAction());

        saveItem.addActionListener(this);
        loadItem.addActionListener(this);

        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
        fileMenu.add(refreshItem);

        menuBar.add(fileMenu);

        mainPage.setJMenuBar(menuBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveItem) {
            saveStudents();
        } else if (e.getSource() == loadItem) {
            loadInfo();
            if (panel != null) {
                panel.setVisible(false);
                infoPane.setVisible(false);
            }
            presentInfo();
        }
    }

    // EFFECTS: save students information to file
    private void saveStudents() {
        try {
            jsonWriter.open();
            jsonWriter.write(studentList);
            jsonWriter.close();
//            System.out.println("info saved");
        } catch (FileNotFoundException e) {
//            System.out.println("Unable to read from file: " + JSON_STORE);
            JOptionPane.showMessageDialog(null, "Unable to read from file: " + JSON_STORE, "Error", 0);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads student list from file
    private void loadInfo() {
        try {
            studentList = jsonReader.read();
//            System.out.println("info loaded");
        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_STORE);
            JOptionPane.showMessageDialog(null, "Unable to read from file: " + JSON_STORE, "Error", 0);

        }
    }

    // EFFECTS: add button
    private void addButton() {
        addNewButton = new JButton(new AddNewStudentAction());
        addNewButton.setSize(150, 25);

        saveButton = new JButton(new SaveAction());
        saveButton.setSize(100, 25);

        mainPage.add(addNewButton, BorderLayout.NORTH);
        mainPage.add(saveButton, BorderLayout.SOUTH);
    }

    /** This class provides action listener for save button
     *
     */
    private class SaveAction extends AbstractAction {

        SaveAction() {
            super("Save");
        }

        // MODIFIES: this
        // EFFECTS: save current student information to the JSON file
        @Override
        public void actionPerformed(ActionEvent e) {
            saveStudents();
        }
    }

    /** This class provide action listener for add new student button
     *  Add a new student to current student list
     */
    private class AddNewStudentAction extends AbstractAction {

        AddNewStudentAction() {
            super("Add New Student");
        }

        // EFFECTS: create a new page for adding a new student
        @Override
        public void actionPerformed(ActionEvent e) {
            new EditableStudentInfoUI(studentList, new Student("", ""));
        }
    }

    /** This class provides action listener for refresh button
     *  Refresh the current page
     */
    private class RefreshAction extends AbstractAction {
        RefreshAction() {
            super("refresh");
        }

        // EFFECTS: refresh the current page
        @Override
        public void actionPerformed(ActionEvent e) {
            if (panel != null) {
                panel.setVisible(false);
                infoPane.setVisible(false);
            }
            presentInfo();
        }
    }

    // EFFECTS: present brief student info on the main page
    private void presentInfo() {

        panel = new JPanel(new GridLayout(studentList.size() + 1, 9));

        setHeader(panel);

        for (Student s : studentList) {
            infoConvertToCol(s, panel);
        }

        infoPane = new JScrollPane(panel);

        mainPage.add(infoPane, BorderLayout.CENTER);

        infoPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        infoPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.setVisible(true);
        mainPage.setVisible(true);
    }

    //EFFECTS: set header for information to be presented
    private void setHeader(JPanel panel) {
        JLabel lastName = new JLabel("Last Name");
        JLabel firstName = new JLabel("|| First Name");
        JLabel personalEducationNumber = new JLabel("|| PEN");
        JLabel dateOfBirth = new JLabel("|| DOB");
        JLabel phoneNum = new JLabel("|| Phone Number");
        JLabel email = new JLabel("|| Email");
        JLabel gender = new JLabel("|| Gender");
        JLabel empty1 = new JLabel("||");
        JLabel empty2 = new JLabel("||");


        panel.add(lastName);
        panel.add(firstName);
        panel.add(gender);
        panel.add(personalEducationNumber);
        panel.add(dateOfBirth);
        panel.add(phoneNum);
        panel.add(email);
        panel.add(empty1);
        panel.add(empty2);
    }

    // EFFECTS: convert detailed info into rows as label
    private void infoConvertToCol(Student s, JPanel panel) {
        JLabel lastName = new JLabel(s.getLastName());
        JLabel firstName = new JLabel("|| " + s.getFirstName());
        JLabel personalEducationNumber = new JLabel("|| " + s.getStudentNumber());
        JLabel dateOfBirth = new JLabel("|| " + s.getDateOfBirth());
        JLabel phoneNum = new JLabel("|| " + s.getPhoneNum());
        JLabel email = new JLabel("|| " + s.getEmail());
        JLabel gender = new JLabel("|| " + s.getGender());

        JButton viewButton = new JButton(new View(s));
        JButton deleteButton = new JButton(new Delete(s));

        panel.add(lastName);
        panel.add(firstName);
        panel.add(gender);
        panel.add(personalEducationNumber);
        panel.add(dateOfBirth);
        panel.add(phoneNum);
        panel.add(email);

        panel.add(viewButton);
        panel.add(deleteButton);
    }

    /** This class provides action listener for the view button
     *  Create a new page with detailed student information
     */
    private class View extends AbstractAction {
        Student student;

        View(Student student) {
            super("view");
            this.student = student;
        }

        // EFFECTS: create a new page with detailed student information
        @Override
        public void actionPerformed(ActionEvent e) {
            new StudentInfoUI(studentList, student);
        }
    }

    /** This class provides action listener for delete button
     *  Delete a student from current student list
     */
    private class Delete extends AbstractAction {
        Student student;

        Delete(Student student) {
            super("delete");
            this.student = student;
        }

        // EFFECTS: delete a student from current student list with an option panel for confirmation
        @Override
        public void actionPerformed(ActionEvent e) {

            int i = JOptionPane.showConfirmDialog(null,
                    "Sure to delete?",
                    "Delete",
                    JOptionPane.YES_NO_OPTION);
            if (i == 0) {
                studentList.remove(student);
//                saveStudents();
            }
            panel.setVisible(false);
            infoPane.setVisible(false);
            presentInfo();
        }
    }

    /** This class provide a window close behavior with choice of save, save and close, close without save, and cnacel
     *
     */
    private class WindowCloseOption extends WindowAdapter {

        // EFFECTS: provide an option panel with choice of save, save and close, close without save, or cancel
        //          when closing the window
        @Override
        public void windowClosing(WindowEvent e) {
            String[] options = {"save", "save and close", "close without save", "cancel"};
            int i = JOptionPane.showOptionDialog(null,
                    "Save and close",
                    "Close?",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            if (i == 0) {
                saveStudents();
            } else if (i == 1) {
                saveStudents();
                System.exit(0);
            } else if (i == 2) {
                System.exit(0);
            }
        }
    }
}
