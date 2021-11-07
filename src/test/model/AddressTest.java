package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressTest {
    Address address;

    @BeforeEach
    public void setUp() {
        address = new Address();
    }

    @Test
    public void changePostalCodeTest() {
        assertEquals("",address.getPostalCode());
        address.changePostalCode("VVVVVV");
        assertEquals("VVVVVV",address.getPostalCode());
    }

    @Test
    public void changeCityTest() {
        assertEquals("",address.getCity());
        address.changeCity("Vancouver");
        assertEquals("Vancouver",address.getCity());
    }

    @Test
    public void changeProvenceTest() {
        assertEquals("",address.getProvince());
        address.changeProvince("BC");
        assertEquals("BC", address.getProvince());
    }

    @Test
    public void changeUnitNumTest() {
        assertEquals("",address.getUnitNum());
        address.changeUnitNum("2001");
        assertEquals("2001",address.getUnitNum());
    }

    @Test
    public void changeStreetAddressTest() {
        assertEquals("",address.getStreetAddress());
        address.changeStreetAddress("433-Marine Dr");
        assertEquals("433-Marine Dr",address.getStreetAddress());
    }
}
