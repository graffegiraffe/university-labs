package by.rublevskaya.toystore.toy.extendtoy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarTest {

    private Car car;

    @Before
    public void setUp() {
        car = new Car("Test Car");
    }

    @After
    public void tearDown() {
        car = null;
    }

    @Test
    public void moveForward() {
        int initialBatteryLevel = car.getBatteryLevel();
        car.moveForward();
        assertTrue(car.getBatteryLevel() < initialBatteryLevel);
        assertFalse(car.getBatteryLevel() > initialBatteryLevel);
    }

    @Test
    public void moveBackward() {
        int initialBatteryLevel = car.getBatteryLevel();
        car.moveBackward();
        assertTrue(car.getBatteryLevel() < initialBatteryLevel);
        assertFalse(car.getBatteryLevel() > initialBatteryLevel);
    }

    @Test
    public void turnRight() {
        int initialBatteryLevel = car.getBatteryLevel();
        car.turnRight();
        assertTrue(car.getBatteryLevel() < initialBatteryLevel);
        assertFalse(car.getBatteryLevel() > initialBatteryLevel);
    }

    @Test
    public void turnLeft() {
        int initialBatteryLevel = car.getBatteryLevel();
        car.turnLeft();
        assertTrue(car.getBatteryLevel() < initialBatteryLevel);
        assertFalse(car.getBatteryLevel() > initialBatteryLevel);
    }

    @Test
    public void charge() {
        car.charge();
        assertEquals(100, car.getBatteryLevel());
    }

    @Test
    public void testToString() {
        String expectedString = "Машинка { имя ='Test Car', уровень заряда=" + car.getBatteryLevel() + ", уровень разряда=" + car.batteryDrain + "}";
        assertEquals(expectedString, car.toString());
    }
}