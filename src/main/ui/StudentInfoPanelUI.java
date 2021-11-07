package ui;

import model.Address;
import model.Course;
import model.EmergencyContactor;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StudentInfoPanelUI {
    private final int tagWidth = 200;
    private final int tagHeight = 25;

    private JPanel panel;
    private JScrollPane scrollPane;
    private Student student;
    private String studentFirstName;
    private String studentLastName;
    private String studentNumber;
    private String gender;
    private String nationality;
    private String studentEmail;
    private String studentPhoneNum;
    private String dateOfBirth;
    private String studentUnitNum;
    private String studentStreetAddress;
    private String studentCity;
    private String studentProvince;
    private String studentPostalCode;
    private String ecName;
    private String ecPhoneNum;
    private String relation;
    private String ecUnitNum;
    private String ecStreetAddress;
    private String ecProvince;
    private String ecCity;
    private String ecPostalCode;
    private JTextArea studentLastNameInput;
    private JTextArea studentFirstNameInput;
    private JTextArea genderInput;
    private JTextArea dateOfBirthInput;
    private JTextArea studentNumInput;
    private JTextArea studentPhoneNumInput;
    private JTextArea studentEmailInput;
    private JTextArea nationalityInput;
    private JTextArea studentProvinceInput;
    private JTextArea studentPostalCodeInput;
    private JTextArea studentCityInput;
    private JTextArea studentStreetInput;
    private JTextArea studentUnitNumInput;
    private JTextArea ecNameInput;
    private JTextArea ecRelationInput;
    private JTextArea ecPhoneNumInput;
    private JTextArea ecUnitNumInput;
    private JTextArea ecStreetInput;
    private JTextArea ecCityInput;
    private JTextArea ecProvinceInput;
    private JTextArea ecPostalCodeInput;
    private boolean editable = true;

    public StudentInfoPanelUI(JPanel panel, Student student, boolean editable) {

        this.student = student;
        this.panel = panel;
        this.editable = editable;


        Box box = Box.createVerticalBox();
        box.add(studentConverter(student));
        box.add(studentAddressConverter(student.getAddress()));
        box.add(emergencyContactorConverter(student.getEmergencyContactor()));
        box.add(emergencyContactorAddressConverter(student.getEmergencyContactor().getAddress()));
        box.add(new JLabel("Course History"));
        box.add(courseConverter(student.getAllCourses()));
        box.add(Box.createHorizontalStrut(1200));
        box.add(Box.createVerticalStrut(850));
        panel.add(box);

        scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    private Box studentConverter(Student student) {
        Box boxV = Box.createVerticalBox();
        boxV.add(studentLastNameConverter());
        boxV.add(Box.createRigidArea(new Dimension(0, 5)));
        boxV.add(studentFirstNameConverter());
        boxV.add(Box.createRigidArea(new Dimension(0, 5)));
        boxV.add(studentGenderConverter());
        boxV.add(Box.createRigidArea(new Dimension(0, 5)));
        boxV.add(studentDateOfBirthConvertor());
        boxV.add(Box.createRigidArea(new Dimension(0, 5)));
        boxV.add(studentNumberConvertor());
        boxV.add(Box.createRigidArea(new Dimension(0, 5)));
        boxV.add(studentPhoneNumConverter());
        boxV.add(Box.createRigidArea(new Dimension(0, 5)));
        boxV.add(studentEmailConvertor());
        boxV.add(Box.createRigidArea(new Dimension(0, 5)));
        boxV.add(nationalityConvertor());
        boxV.add(Box.createRigidArea(new Dimension(0, 5)));

        return boxV;
    }

    private Box studentFirstNameConverter() {
        JLabel tag = new JLabel("First Name");
        tag.setPreferredSize(new Dimension(tagWidth, tagHeight));
        studentFirstNameInput = new JTextArea(student.getFirstName());
        studentFirstNameInput.setEditable(editable);
        Box box = Box.createHorizontalBox();
        box.add(tag);
        box.add(studentFirstNameInput);
        return box;
    }

    private Box studentGenderConverter() {
        JLabel tag = new JLabel("Gender");
        tag.setPreferredSize(new Dimension(tagWidth, tagHeight));
        genderInput = new JTextArea(student.getGender());
        genderInput.setEditable(editable);
        Box box = Box.createHorizontalBox();
        box.add(tag);
        box.add(genderInput);
        return box;
    }

    private Box studentDateOfBirthConvertor() {
        JLabel tag = new JLabel("DOB");
        tag.setPreferredSize(new Dimension(tagWidth, tagHeight));

        dateOfBirthInput = new JTextArea(student.getDateOfBirth());
        dateOfBirthInput.setEditable(editable);
        Box box = Box.createHorizontalBox();
        box.add(tag);
        box.add(dateOfBirthInput);
        return box;
    }

    private Box studentNumberConvertor() {
        JLabel tag = new JLabel("PEN");
        tag.setPreferredSize(new Dimension(tagWidth, tagHeight));

        studentNumInput = new JTextArea(student.getStudentNumber());
        studentNumInput.setEditable(editable);
        Box box = Box.createHorizontalBox();
        box.add(tag);
        box.add(studentNumInput);
        return box;
    }

    private Box studentPhoneNumConverter() {
        JLabel tag = new JLabel("Phone Number");
        tag.setPreferredSize(new Dimension(tagWidth, tagHeight));

        studentPhoneNumInput = new JTextArea(student.getPhoneNum());
        studentPhoneNumInput.setEditable(editable);
        Box box = Box.createHorizontalBox();
        box.add(tag);
        box.add(studentPhoneNumInput);
        return box;
    }

    private Box studentEmailConvertor() {
        JLabel tag = new JLabel("Email");
        tag.setPreferredSize(new Dimension(tagWidth, tagHeight));

        studentEmailInput = new JTextArea(student.getEmail());
        studentEmailInput.setEditable(editable);
        Box box = Box.createHorizontalBox();
        box.add(tag);
        box.add(studentEmailInput);
        return box;
    }

    private Box nationalityConvertor() {
        JLabel tag = new JLabel("Nationality");
        tag.setPreferredSize(new Dimension(tagWidth, tagHeight));

        nationalityInput = new JTextArea(student.getNationality());
        nationalityInput.setEditable(editable);
        Box box = Box.createHorizontalBox();
        box.add(tag);
        box.add(nationalityInput);
        return box;
    }

    private Box studentLastNameConverter() {
        JLabel tag = new JLabel("Last Name");
        tag.setPreferredSize(new Dimension(tagWidth, tagHeight));

        studentLastNameInput = new JTextArea(student.getLastName());
        studentLastNameInput.setEditable(editable);
        Box box = Box.createHorizontalBox();
        box.add(tag);
        box.add(studentLastNameInput);
        return box;
    }

    private Box emergencyContactorConverter(EmergencyContactor ec) {
        Box box = Box.createVerticalBox();
        box.add(new JLabel("Emergency Contactor"));
        box.add(Box.createRigidArea(new Dimension(0, 5)));
        box.add(emergencyContactorNameConvertor(ec));
        box.add(Box.createRigidArea(new Dimension(0, 5)));
        box.add(emergencyContactorPhoneNumConverter(ec));
        box.add(Box.createRigidArea(new Dimension(0, 5)));
        box.add(emergencyContactorRelationConverter(ec));
        box.add(Box.createRigidArea(new Dimension(0, 5)));
        return box;
    }

    private Box emergencyContactorNameConvertor(EmergencyContactor ec) {
        JLabel tag = new JLabel("Name");
        tag.setPreferredSize(new Dimension(tagWidth, tagHeight));

        ecNameInput = new JTextArea(ec.getName());
        ecNameInput.setEditable(editable);
        Box box = Box.createHorizontalBox();
        box.add(tag);
        box.add(ecNameInput);
        return box;
    }

    private Box emergencyContactorPhoneNumConverter(EmergencyContactor ec) {
        JLabel tag = new JLabel("Phone Number");
        tag.setPreferredSize(new Dimension(tagWidth, tagHeight));

        ecPhoneNumInput = new JTextArea(ec.getPhoneNum());
        ecPhoneNumInput.setEditable(editable);
        Box box = Box.createHorizontalBox();
        box.add(tag);
        box.add(ecPhoneNumInput);
        return box;
    }

    private Box emergencyContactorRelationConverter(EmergencyContactor ec) {
        JLabel tag = new JLabel("Relation with student");
        tag.setPreferredSize(new Dimension(tagWidth, tagHeight));

        ecRelationInput = new JTextArea(ec.getRelation());
        ecRelationInput.setEditable(editable);

        Box box = Box.createHorizontalBox();
        box.add(tag);
        box.add(ecRelationInput);
        return box;
    }

    private Box studentAddressConverter(Address ad) {
        Box box = Box.createVerticalBox();

        box.add(new JLabel("Address"));
        box.add(Box.createRigidArea(new Dimension(0, 5)));
        box.add(studentUnitConverter(ad));
        box.add(Box.createRigidArea(new Dimension(0, 5)));
        box.add(studentStreetConverter(ad));
        box.add(Box.createRigidArea(new Dimension(0, 5)));
        box.add(studentCityConverter(ad));
        box.add(Box.createRigidArea(new Dimension(0, 5)));
        box.add(studentProvinceConverter(ad));
        box.add(Box.createRigidArea(new Dimension(0, 5)));
        box.add(studentPostalCodeConverter(ad));
        box.add(Box.createRigidArea(new Dimension(0, 5)));

        return box;
    }

    private Box studentUnitConverter(Address ad) {
        JLabel tag = new JLabel("Unit Number");
        tag.setPreferredSize(new Dimension(tagWidth, tagHeight));

        studentUnitNumInput = new JTextArea(ad.getUnitNum());
        studentUnitNumInput.setEditable(editable);
        Box box = Box.createHorizontalBox();
        box.add(tag);
        box.add(studentUnitNumInput);
        return box;
    }

    private Box studentStreetConverter(Address ad) {
        JLabel tag = new JLabel("Street Address");
        tag.setPreferredSize(new Dimension(tagWidth, tagHeight));

        studentStreetInput = new JTextArea(ad.getStreetAddress());
        studentStreetInput.setEditable(editable);
        Box box = Box.createHorizontalBox();
        box.add(tag);
        box.add(studentStreetInput);
        return box;
    }

    private Box studentCityConverter(Address ad) {
        JLabel tag = new JLabel("City");
        tag.setPreferredSize(new Dimension(tagWidth, tagHeight));

        studentCityInput = new JTextArea(ad.getCity());
        studentCityInput.setEditable(editable);
        Box box = Box.createHorizontalBox();
        box.add(tag);
        box.add(studentCityInput);
        return box;
    }

    private Box studentPostalCodeConverter(Address ad) {
        JLabel tag = new JLabel("Postal Code");
        tag.setPreferredSize(new Dimension(tagWidth, tagHeight));

        studentPostalCodeInput = new JTextArea(ad.getPostalCode());
        studentPostalCodeInput.setEditable(editable);
        Box box = Box.createHorizontalBox();
        box.add(tag);
        box.add(studentPostalCodeInput);
        return box;
    }

    private Box studentProvinceConverter(Address ad) {
        JLabel tag = new JLabel("Province");
        tag.setPreferredSize(new Dimension(tagWidth, tagHeight));

        studentProvinceInput = new JTextArea(ad.getProvince());
        studentProvinceInput.setEditable(editable);
        Box box = Box.createHorizontalBox();
        box.add(tag);
        box.add(studentProvinceInput);
        return box;
    }

    private Box emergencyContactorAddressConverter(Address ad) {
        Box box = Box.createVerticalBox();

        box.add(new JLabel("Address"));
        box.add(Box.createRigidArea(new Dimension(0, 5)));
        box.add(ectUnitConverter(ad));
        box.add(Box.createRigidArea(new Dimension(0, 5)));
        box.add(ecStreetConverter(ad));
        box.add(Box.createRigidArea(new Dimension(0, 5)));
        box.add(ecCityConverter(ad));
        box.add(Box.createRigidArea(new Dimension(0, 5)));
        box.add(ecProvinceConverter(ad));
        box.add(Box.createRigidArea(new Dimension(0, 5)));
        box.add(ecPostalCodeConverter(ad));
        box.add(Box.createRigidArea(new Dimension(0, 5)));

        return box;
    }

    private Box ectUnitConverter(Address ad) {
        JLabel tag = new JLabel("Unit Number");
        tag.setPreferredSize(new Dimension(tagWidth, tagHeight));

        ecUnitNumInput = new JTextArea(ad.getUnitNum());
        ecUnitNumInput.setEditable(editable);
        Box box = Box.createHorizontalBox();
        box.add(tag);
        box.add(ecUnitNumInput);
        return box;
    }

    private Box ecStreetConverter(Address ad) {
        JLabel tag = new JLabel("Street Address");
        tag.setPreferredSize(new Dimension(tagWidth, tagHeight));

        ecStreetInput = new JTextArea(ad.getStreetAddress());
        ecStreetInput.setEditable(editable);
        Box box = Box.createHorizontalBox();
        box.add(tag);
        box.add(ecStreetInput);
        return box;
    }

    private Box ecCityConverter(Address ad) {
        JLabel tag = new JLabel("City");
        tag.setPreferredSize(new Dimension(tagWidth, tagHeight));

        ecCityInput = new JTextArea(ad.getCity());
        ecCityInput.setEditable(editable);
        Box box = Box.createHorizontalBox();
        box.add(tag);
        box.add(ecCityInput);
        return box;
    }

    private Box ecProvinceConverter(Address ad) {
        JLabel tag = new JLabel("Province");
        tag.setPreferredSize(new Dimension(tagWidth, tagHeight));

        ecProvinceInput = new JTextArea(ad.getProvince());
        ecProvinceInput.setEditable(editable);
        Box box = Box.createHorizontalBox();
        box.add(tag);
        box.add(ecProvinceInput);
        return box;
    }

    private Box courseConverter(List<Course> courses) {
        Box boxV = Box.createVerticalBox();
        for (Course c : courses) {
            boxV.add(Box.createRigidArea(new Dimension(0, 5)));
            boxV.add(new JLabel(c.getCourseName()));
            boxV.add(Box.createRigidArea(new Dimension(0, 5)));
            boxV.add(infoBoxConverter("Teacher", c.getTeacher()));
            boxV.add(Box.createRigidArea(new Dimension(0, 5)));
            boxV.add(infoBoxConverter("Time Block", Integer.toString(c.getTimeBlock())));
            boxV.add(Box.createRigidArea(new Dimension(0, 5)));
            boxV.add(infoBoxConverter("Grade", Double.toString(c.getGrade())));
            boxV.add(Box.createRigidArea(new Dimension(0, 5)));
            boxV.add(infoBoxConverter("Status", courseStatusConvertor(c.getStatus())));
            boxV.add(Box.createRigidArea(new Dimension(0, 5)));
            boxV.add(infoBoxConverter("Finish Time", c.getFinishTime()));
        }
        return boxV;
    }

    private Box infoBoxConverter(String fieldName, String content) {
        Box boxH = Box.createHorizontalBox();
        JLabel tag = new JLabel(fieldName);
        tag.setPreferredSize(new Dimension(100, 25));
        JTextArea textArea = new JTextArea(content);
        textArea.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        textArea.setEditable(editable);
        boxH.add(tag);
        boxH.add(Box.createRigidArea(new Dimension(50, 10)));
        boxH.add(Box.createHorizontalGlue());
        boxH.add(textArea);
        return boxH;
    }

    private String courseStatusConvertor(int i) {
        if (i == 0) {
            return "Finished";
        } else if (i == 1) {
            return "Currently taking";
        } else if (i == 2) {
            return "Planned for next term";
        } else {
            return "Planned for future";
        }
    }

    private Box ecPostalCodeConverter(Address ad) {
        JLabel tag = new JLabel("Postal Code");
        tag.setPreferredSize(new Dimension(tagWidth, tagHeight));

        ecPostalCodeInput = new JTextArea(ad.getProvince());
        ecPostalCodeInput.setEditable(editable);
        Box box = Box.createHorizontalBox();
        box.add(tag);
        box.add(ecPostalCodeInput);
        return box;
    }

    public void getInput() {
        studentFirstName = studentFirstNameInput.getText();
        studentLastName = studentLastNameInput.getText();
        studentNumber = studentNumInput.getText();
        gender = genderInput.getText();
        nationality = nationalityInput.getText();
        studentEmail = studentEmailInput.getText();
        studentPhoneNum = studentPhoneNumInput.getText();
        dateOfBirth = dateOfBirthInput.getText();
        studentUnitNum = studentUnitNumInput.getText();
        studentStreetAddress = studentStreetInput.getText();
        studentCity = studentCityInput.getText();
        studentProvince = studentProvinceInput.getText();
        studentPostalCode = studentPostalCodeInput.getText();
        ecName = ecNameInput.getText();
        ecPhoneNum = ecPhoneNumInput.getText();
        relation = ecRelationInput.getText();
        ecUnitNum = ecUnitNumInput.getText();
        ecStreetAddress = ecStreetInput.getText();
        ecProvince = ecProvinceInput.getText();
        ecCity = ecCityInput.getText();
        ecPostalCode = ecPostalCodeInput.getText();
    }

    public void update() {

        getInput();

        student.changeName(studentFirstName, studentLastName);
        student.changeGender(gender);
        student.changeStudentNumber(studentNumber);
        student.changeDateOfBirth(dateOfBirth);
        student.changeNationality(nationality);
        student.changePhoneNum(studentPhoneNum);
        student.changeEmail(studentEmail);

        student.getAddress().changePostalCode(studentPostalCode);
        student.getAddress().changeCity(studentCity);
        student.getAddress().changeUnitNum(studentUnitNum);
        student.getAddress().changeProvince(studentProvince);
        student.getAddress().changeStreetAddress(studentStreetAddress);

        student.getEmergencyContactor().changeName(ecName);
        student.getEmergencyContactor().changeRelation(relation);
        student.getEmergencyContactor().changePhoneNum(ecPhoneNum);

        student.getEmergencyContactor().getAddress().changeStreetAddress(ecStreetAddress);
        student.getEmergencyContactor().getAddress().changeProvince(ecProvince);
        student.getEmergencyContactor().getAddress().changeCity(ecCity);
        student.getEmergencyContactor().getAddress().changeUnitNum(ecUnitNum);
        student.getEmergencyContactor().getAddress().changePostalCode(ecPostalCode);
    }
}
