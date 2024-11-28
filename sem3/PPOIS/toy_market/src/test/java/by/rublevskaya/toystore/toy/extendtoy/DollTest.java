package by.rublevskaya.toystore.toy.extendtoy;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DollTest {

    private Doll doll;

    @Before
    public void setUp() {
        doll = new Doll("Test Doll");
    }

    @Test
    public void testTurnOn() {
        assertFalse(doll.isOn());
        doll.turnOn();
        assertTrue(doll.isOn());
        doll.turnOn();
        assertTrue(doll.isOn());
    }

    @Test
    public void testTurnOff() {
        assertFalse(doll.isOn());
        doll.turnOn();
        assertTrue(doll.isOn());
        doll.turnOff();
        assertFalse(doll.isOn());
        doll.turnOff();
        assertFalse(doll.isOn());
    }

    @Test
    public void testUse() {
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));
        doll.use();
        String expectedOutputOff = "Кукла Test Doll выключена. Включите ее, чтобы играть.\n";
        String actualOutput = outContent.toString();
        assertEquals(expectedOutputOff, actualOutput);
        outContent.reset();
        doll.turnOn();
        doll.use();
        String expectedOutputOn = "Кукла Test Doll включена\nИграем с куклой Test Doll\n";
        actualOutput = outContent.toString();
        assertEquals(expectedOutputOn, actualOutput);
    }

    @Test
    public void testIsOn() {
        assertFalse(doll.isOn());
        doll.turnOn();
        assertTrue(doll.isOn());
        doll.turnOff();
        assertFalse(doll.isOn());
    }

    @Test
    public void testToString() {
        String expectedInitialStr = "Кукла { имя ='Test Doll', уровень заряда =100, включена ли =false}";
        String dollStr = doll.toString();
        assertNotNull(dollStr);
        assertEquals(expectedInitialStr, dollStr);
        doll.turnOn();
        String expectedTurnedOnStr = "Кукла { имя ='Test Doll', уровень заряда =100, включена ли =true}";
        dollStr = doll.toString();
        assertEquals(expectedTurnedOnStr, dollStr);
    }
}