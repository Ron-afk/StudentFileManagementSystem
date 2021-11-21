package model;

import org.json.JSONObject;
import persistence.Writable;

// represent address
public class Address implements Writable {
    private String postalCode = "";
    private String city = "";
    private String province = "";
    private String unitNum = "";
    private String streetAddress = "";

    // EFFECTS: initiate an address
    public Address() { }

    // MODIFIES: this
    // EFFECTS: change address postal code
    public void changePostalCode(String postalCode) {
        this.postalCode = postalCode;
        EventLog.getInstance().logEvent(new Event("change address postal code"));
    }

    // EFFECTS: get the postal code
    public String getPostalCode() {
        return postalCode;
    }

    // MODIFIES: this
    // EFFECTS: change address city
    public void changeCity(String city) {
        this.city = city;
        EventLog.getInstance().logEvent(new Event("change address city"));
    }

    // EFFECTS: get city
    public String getCity() {
        return city;
    }

    // MODIFIES: this
    // EFFECTS: change provence
    public void changeProvince(String provence) {
        this.province = provence;
        EventLog.getInstance().logEvent(new Event("change address province"));
    }

    // EFFECTS: get provence
    public String getProvince() {
        return province;
    }

    // MODIFIES: this
    // EFFECTS: change unitNum (if applicable)
    public void changeUnitNum(String unitNum) {
        this.unitNum = unitNum;
        EventLog.getInstance().logEvent(new Event("change address unit number"));
    }

    // EFFECTS: get unit number
    public String getUnitNum() {
        return unitNum;
    }

    // MODIFIES: this
    // EFFECTS: change street address
    public void changeStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
        EventLog.getInstance().logEvent(new Event("change address street address"));
    }

    // EFFECTS: get street address
    public String getStreetAddress() {
        return streetAddress;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("unit number",unitNum);
        json.put("street address", streetAddress);
        json.put("city", city);
        json.put("provence", province);
        json.put("postal code",postalCode);
        return json;
    }
}
