package model;

// represent address
public class Address {
    private String postalCode;
    private String city;
    private String provence;
    private String unitNum;
    private String streetAddress;

    // EFFECTS: initiate an address
    public Address() { }

    // MODIFIES: this
    // EFFECTS: change address postal code
    public void changePostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    // MODIFIES: this
    // EFFECTS: change address city
    public void changeCity(String city) {
        this.city = city;
    }

    // MODIFIES: this
    // EFFECTS: change provence
    public void changeProvence(String provence) {
        this.provence = provence;
    }

    // MODIFIES: this
    // EFFECTS: change unitNum (if applicable)
    public void changeUnitNum(String unitNum) {
        this.unitNum = unitNum;
    }

    // MODIFIES: this
    // EFFECTS: change street address
    public void changeStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    // EFFECTS: print out address
    public void print() {
        System.out.println(unitNum + " " + streetAddress);
        System.out.println(city + "  " + provence);
        System.out.println(postalCode);
    }
}
