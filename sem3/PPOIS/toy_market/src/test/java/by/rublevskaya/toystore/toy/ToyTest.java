package by.rublevskaya.toystore.toy;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ToyTest {

    private Toy toy;
    private static class TestToy extends Toy {
        public TestToy(String name, int batteryLevel) {
            super(name, batteryLevel);
        }
    }

    @Before
    public void setUp() {
        toy = new TestToy("Test Toy", 50);
    }

    @Test
    public void testGetName() {
        assertEquals("Test Toy", toy.getName());
    }

    @Test
    public void testGetBatteryLevel() {
        assertEquals(50, toy.getBatteryLevel());
    }

    @Test
    public void testUse() {
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));
        toy.use();
        assertEquals("Используется игрушка Test Toy\n", outContent.toString());
    }

    @Test
    public void testReplaceBattery() {
        toy.replaceBattery();
        assertEquals(100, toy.getBatteryLevel());
    }
}