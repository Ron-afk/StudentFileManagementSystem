package model;

import org.json.JSONObject;
import persistence.Writable;

// represent emergency contactor information
public class EmergencyContactor implements Writable {
    private Address address;
    private String name = "";
    private String phoneNum = "";
    private String relation = "";

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
        EventLog.getInstance().logEvent(new Event("change emergency contactor's name"));
    }

    // MODIFIES: this
    // EFFECTS: change emergency contactor's address
    public void changeAddress(Address address) {
        this.address = address;
        EventLog.getInstance().logEvent(new Event("change emergency contactor's address"));
    }

    // MODIFIES: this
    // EFFECTS: change emergency contactor's  phone number
    public void changePhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
        EventLog.getInstance().logEvent(new Event("change emergency contactor's phone number"));
    }

    // MODIFIES: this
    // EFFECTS: change emergency contactor's relation with student
    public void changeRelation(String relation) {
        this.relation = relation;
        EventLog.getInstance().logEvent(new Event("change emergency contactor's relation with student"));
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name",name);
        json.put("address",address.toJson());
        json.put("phone number",phoneNum);
        json.put("relation",relation);
        return json;
    }
}
