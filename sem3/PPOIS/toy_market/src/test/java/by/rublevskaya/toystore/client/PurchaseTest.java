package by.rublevskaya.toystore.client;

import by.rublevskaya.toystore.toy.Toy;
import by.rublevskaya.toystore.mock.ToyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class PurchaseTest {

    private Purchase purchase;
    private Toy toy;

    @Before
    public void setUp() {
        toy = new ToyMock("Test Toy", 100);
        purchase = new Purchase(toy, 100.0);
    }

    @After
    public void tearDown() {
        purchase = null;
        toy = null;
    }

    @Test
    public void getToy() {
        assertEquals("Test Toy", purchase.getToy().getName());
    }

    @Test
    public void getPrice() {
        assertEquals(100.0, purchase.getPrice(), 0.001);
    }

    @Test
    public void setPrice() {
        purchase.setPrice(150.0);
        assertEquals(150.0, purchase.getPrice(), 0.001);
    }

    @Test
    public void getPurchaseDateTime() {
        LocalDateTime now = LocalDateTime.now();
        assertTrue(purchase.getPurchaseDateTime().isBefore(now.plusSeconds(1)));
        assertTrue(purchase.getPurchaseDateTime().isAfter(now.minusSeconds(1)));
    }

    @Test
    public void testToString() {
        String expectedString = "Purchase{toy=Test Toy, price=100.0, purchaseDateTime=" + purchase.getPurchaseDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "}";
        assertEquals(expectedString, purchase.toString());
    }
}