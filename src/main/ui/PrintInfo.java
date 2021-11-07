package ui;

import model.Address;
import model.Course;
import model.EmergencyContactor;

public class PrintInfo {

    public PrintInfo() {}

    // EFFECTS: print out address
    public void addressPrint(Address address) {
        System.out.println(address.getUnitNum() + " " + address.getStreetAddress());
        System.out.println(address.getCity() + "  " + address.getProvince());
        System.out.println(address.getPostalCode());
    }

    // EFFECTS: print emergency contactor information
    public void emergencyContactorPrint(EmergencyContactor ec) {
        System.out.println("Name: " + ec.getName());
        System.out.println("Phone Number: " + ec.getPhoneNum());
        System.out.println("Relation with student: " + ec.getRelation());
        System.out.print("Address: ");
        addressPrint(ec.getAddress());
    }

    // EFFECT: print simplified course info
    public void coursePrintSimple(Course c) {
        System.out.println("Course name: " + c.getCourseName());
        System.out.print("Status: ");
        int status = c.getStatus();
        if (status == 0) {
            System.out.println("Finished");
        } else if (status == 1) {
            System.out.println("Currently taking");
        } else if (status == 2) {
            System.out.println("Planned for next term");
        } else {
            System.out.println("Planned for future");
        }
    }

    // EFFECTS: print course detail
    public void coursePrint(Course c) {
        System.out.println("Course name: " + c.getCourseName());
        System.out.print("Status: ");
        int status = c.getStatus();
        if (status == 0) {
            System.out.println("Finished");
            System.out.println("Finish time: " + c.getFinishTime());
            System.out.println("Grade: " + c.getGrade());
            System.out.println("Teacher: " + c.getTeacher());
        } else if (status == 1) {
            System.out.println("Currently taking");
            System.out.println("Time block: " + c.getTimeBlock());
            System.out.println("Teacher: " + c.getTeacher());
        } else if (status == 2) {
            System.out.println("Planned for next term");
            System.out.println("Time block: " + c.getTimeBlock());
            System.out.println("Teacher: " + c.getTeacher());
        } else {
            System.out.println("Planned for future");
        }
    }
}
