package model;

// represent emergency contactor information
public class EmergencyContactor {
    private Address address;
    private String name;
    private String phoneNum;
    private String relation;

    // MODIFIES: this
    // EFFECTS: set an emergency contactor with name
    public EmergencyContactor(String name) {
        this.name = name;
        address = new Address();
    }

    // MODIFIES: this
    // EFFECTS: change emergency contactor's name
    public void changeName(String name) {
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: change emergency contactor's address
    public void changeAddress(Address address) {
        this.address = address;
    }

    // MODIFIES: this
    // EFFECTS: change emergency contactor's  phone number
    public void changePhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    // MODIFIES: this
    // EFFECTS: change emergency contactor's relation with student
    public void changeRelation(String relation) {
        this.relation = relation;
    }

    // EFFECTS: get emergency contactor's name
    public String getName() {
        return name;
    }

    //EFFECTS: get emergency contactor's address
    public Address getAddress() {
        return address;
    }

    // EFFECTS: get emergency contactor's phone number
    public String getPhoneNum() {
        return phoneNum;
    }

    // EFFECTS: get emergency contactor's relation with student
    public String getRelation() {
        return relation;
    }

    // EFFECTS: print out emergency contactor's information
    /*
    public void emergencyContactorPrint() {
        System.out.println("Name: " + name);
        System.out.println("Phone Number: " + phoneNum);
        System.out.println("Relation with student: " + relation);
        System.out.print("Address: ");
        address.print();
    }

     */
}
