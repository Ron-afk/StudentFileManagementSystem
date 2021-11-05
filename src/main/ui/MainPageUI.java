package ui;

import model.Student;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        mainPage.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
            System.out.println("info saved");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads
    // student list from file
    private void loadInfo() {
        try {
            studentList = jsonReader.read();
            System.out.println("info loaded");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
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

    private class SaveAction extends AbstractAction {

        SaveAction() {
            super("Save");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            saveStudents();
        }
    }

    private class AddNewStudentAction extends AbstractAction {

        AddNewStudentAction() {
            super("Add New Student");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            new AddNewStudentUI(studentList);
        }
    }

    private class RefreshAction extends AbstractAction {
        RefreshAction() {
            super("refresh");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            presentInfo();
        }
    }

    // EFFECTS: present brief student info on the main page
    private void presentInfo() {

        panel  = new JPanel(new GridLayout(studentList.size() + 1, 8));
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
        System.out.println("info presented"); // stub
    }

    private void setHeader(JPanel panel) {
        JLabel lastName = new JLabel("Last Name");
        JLabel firstName = new JLabel("|| First Name");
        JLabel personalEducationNumber = new JLabel("|| PEN");
        JLabel dateOfBirth = new JLabel("|| DOB");
        JLabel phoneNum = new JLabel("|| Phone Number");
        JLabel email = new JLabel("|| Email");
        JLabel empty1 = new JLabel("||");
        JLabel empty2 = new JLabel("||");


        panel.add(lastName);
        panel.add(firstName);
        panel.add(personalEducationNumber);
        panel.add(dateOfBirth);
        panel.add(phoneNum);
        panel.add(email);
        panel.add(empty1);
        panel.add(empty2);
    }

    private void infoConvertToCol(Student s, JPanel panel) {
        JLabel lastName = new JLabel(s.getLastName());
        JLabel firstName = new JLabel("|| " + s.getFirstName());
        JLabel personalEducationNumber = new JLabel("|| " + s.getStudentNumber());
        JLabel dateOfBirth = new JLabel("|| " + s.getDateOfBirth());
        JLabel phoneNum = new JLabel("|| " + s.getPhoneNum());
        JLabel email = new JLabel("|| " + s.getEmail());

        JButton viewButton = new JButton("View");
        JButton deleteButton = new JButton("Delete");

        panel.add(lastName);
        panel.add(firstName);
        panel.add(personalEducationNumber);
        panel.add(dateOfBirth);
        panel.add(phoneNum);
        panel.add(email);

        panel.add(viewButton);
        panel.add(deleteButton);
    }
}
