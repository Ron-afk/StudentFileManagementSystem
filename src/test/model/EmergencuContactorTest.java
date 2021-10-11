package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmergencuContactorTest {
    EmergencyContactor ec;

    @BeforeEach
    public void setUp() {
        ec = new EmergencyContactor("");
    }

    @Test
    public void changeNameTest() {
        assertEquals("",ec.getName());
        ec.changeName("rang");
        assertEquals("rang",ec.getName());
    }

    @Test
    public void changeAddressTest() {
        Address address = new Address();
        ec.changeAddress(address);
        assertEquals(address,ec.getAddress());
    }

    @Test
    public void changeRelationTest() {
        assertEquals(null,ec.getRelation());
        ec.changeRelation("Mother");
        assertEquals("Mother",ec.getRelation());
    }

    @Test
    public void changePhoneNumTest() {
        assertEquals(null,ec.getPhoneNum());
        ec.changePhoneNum("123456");
        assertEquals("123456",ec.getPhoneNum());
    }
}
