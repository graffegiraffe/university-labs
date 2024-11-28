package by.rublevskaya.toystore.mock;

import org.junit.Test;

import static org.junit.Assert.*;

public class ToyMockTest {

    @Test
    public void getName() {
        ToyMock toy = new ToyMock("Test Toy", 100);
        assertEquals("Test Toy", toy.getName());
    }

    @Test
    public void getPrice() {
        ToyMock toy = new ToyMock("Test Toy", 100);
        assertEquals(10.1, toy.getPrice(), 0.01);
    }
}