package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.print.attribute.HashPrintServiceAttributeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressTest {
    Address address;

    @BeforeEach
    public void setUp() {
        address = new Address();
    }

    @Test
    public void changePostalCodeTest() {
        assertEquals(null,address.getPostalCode());
        address.changePostalCode("VVVVVV");
        assertEquals("VVVVVV",address.getPostalCode());
    }

    @Test
    public void changeCityTest() {
        assertEquals(null,address.getCity());
        address.changeCity("Vancouver");
        assertEquals("Vancouver",address.getCity());
    }

    @Test
    public void changeProvenceTest() {
        assertEquals(null,address.getProvence());
        address.changeProvence("BC");
        assertEquals("BC", address.getProvence());
    }

    @Test
    public void changeUnitNumTest() {
        assertEquals(null,address.getUnitNum());
        address.changeUnitNum("2001");
        assertEquals("2001",address.getUnitNum());
    }

    @Test
    public void changeStreetAddressTest() {
        assertEquals(null,address.getStreetAddress());
        address.changeStreetAddress("433-Marine Dr");
        assertEquals("433-Marine Dr",address.getStreetAddress());
    }
}
