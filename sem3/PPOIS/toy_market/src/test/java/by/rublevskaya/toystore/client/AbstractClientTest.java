package by.rublevskaya.toystore.client;

import by.rublevskaya.toystore.client.extendclient.PhysicalClient;
import by.rublevskaya.toystore.discountsystem.Coupon;
import by.rublevskaya.toystore.discountsystem.CouponType;
import by.rublevskaya.toystore.mock.SalespersonMock;
import by.rublevskaya.toystore.toy.Toy;
import by.rublevskaya.toystore.mock.ToyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AbstractClientTest {

    private PhysicalClient client;
    private static final double DELTA = 1e-15;

    @Before
    public void setUp() throws Exception {
        client = new PhysicalClient("Test Client");
    }

    @After
    public void tearDown() throws Exception {
        client = null;
    }

    @Test
    public void addPurchase() {
        ToyMock toy = new ToyMock("Test Toy", 100);
        Purchase purchase = new Purchase(toy, 100);
        client.addPurchase(purchase);
        assertEquals(1, client.getPurchases().size());
        assertEquals(purchase, client.getPurchases().get(0));
    }

    @Test
    public void getPurchases() {
        assertTrue(client.getPurchases().isEmpty());
        ToyMock toy = new ToyMock("Test Toy", 100);
        Purchase purchase = new Purchase(toy, 100);
        client.addPurchase(purchase);
        assertEquals(1, client.getPurchases().size());
        assertEquals(purchase, client.getPurchases().get(0));
    }

    @Test
    public void getName() {
        assertEquals("Test Client", client.getName());
    }

    @Test
    public void setName() {
        client.setName("New Name");
        assertEquals("New Name", client.getName());
    }

    @Test
    public void getToys() {
        assertTrue(client.getToys().isEmpty());
        Toy toy = new ToyMock("Test Toy", 100);
        client.setToys(List.of(toy));
        assertEquals(1, client.getToys().size());
        assertEquals(toy, client.getToys().get(0));
    }

    @Test
    public void setToys() {
        Toy toy1 = new ToyMock("Test Toy 1", 100);
        Toy toy2 = new ToyMock("Test Toy 2", 200);
        client.setToys(List.of(toy1, toy2));
        assertEquals(2, client.getToys().size());
        assertTrue(client.getToys().contains(toy1));
        assertTrue(client.getToys().contains(toy2));
    }

    @Test
    public void getBonusPoints() {
        assertEquals(0, client.getBonusPoints(), DELTA);
        client.setBonusPoints(100);
        assertEquals(100, client.getBonusPoints(), DELTA);
    }

    @Test
    public void setBonusPoints() {
        client.setBonusPoints(200);
        assertEquals(200, client.getBonusPoints(), DELTA);
    }

    @Test
    public void getCoupons() {
        assertTrue(client.getCoupons().isEmpty());
    }

    @Test
    public void setCoupons() {
        Coupon coupon1 = new Coupon("DISCOUNT10", CouponType.PERCENT, 10);
        Coupon coupon2 = new Coupon("DISCOUNT20", CouponType.PERCENT, 20);
        client.setCoupons(List.of(coupon1, coupon2));
        assertEquals(2, client.getCoupons().size());
        assertTrue(client.getCoupons().contains(coupon1));
        assertTrue(client.getCoupons().contains(coupon2));
    }

    @Test
    public void buyToy() {
        ToyMock toy = new ToyMock("Test Toy", 100);
        SalespersonMock salesperson = new SalespersonMock(
                "Test Salesperson", "Position", "Task", 50.0, 30, "Shift", 0.05, 20
        );
        client.buyToy(toy, salesperson, 100);
        assertEquals(1, client.getPurchases().size());
        Purchase purchase = client.getPurchases().get(0);
        assertEquals(toy, purchase.getToy());
        assertEquals(100, purchase.getPrice(), DELTA);
    }

    @Test
    public void addBonusPoints() {
        client.addBonusPoints(50);
        assertEquals(50, client.getBonusPoints(), DELTA);
    }

    @Test
    public void useBonusPoints() {
        client.setBonusPoints(100);
        client.useBonusPoints(50);
        assertEquals(50, client.getBonusPoints(), DELTA);
    }

    @Test
    public void addCoupon() {
        Coupon coupon = new Coupon("DISCOUNT10", CouponType.PERCENT, 10);
        client.addCoupon(coupon);
        assertEquals(1, client.getCoupons().size());
        assertEquals(coupon, client.getCoupons().get(0));
    }

    @Test
    public void useCoupon() {
        Coupon coupon = new Coupon("DISCOUNT10", CouponType.PERCENT, 10);
        client.addCoupon(coupon);
        assertEquals(1, client.getCoupons().size());
        assertEquals(coupon, client.getCoupons().get(0));
        client.useCoupon(coupon, 100);
        assertTrue(client.getCoupons().isEmpty());
    }

    @Test
    public void testToString() {
        String expectedString = " Клиент { имя ='Test Client', покупки =[], игрушки =[], бонусные баллы =0.0, купоны =[]}";
        assertEquals(expectedString, client.toString());
    }

    @Test
    public void testExchangeToys_NullOldToys() {
        ToyMock newToy = new ToyMock("New Toy", 100);
        SalespersonMock cashier = new SalespersonMock("Test Salesperson", "Position", "Task", 50.0, 30, "Shift", 0.05, 20);
        client.exchangeToys(null, newToy, cashier);
    }

    @Test
    public void testExchangeToys_EmptyOldToys() {
        ToyMock newToy = new ToyMock("New Toy", 100);
        SalespersonMock cashier = new SalespersonMock("Test Salesperson", "Position", "Task", 50.0, 30, "Shift", 0.05, 20);
        client.exchangeToys(new ArrayList<>(), newToy, cashier);
    }

    @Test
    public void testExchangeToys_NullNewToy() {
        ToyMock oldToy1 = new ToyMock("Old Toy 1", 100);
        ToyMock oldToy2 = new ToyMock("Old Toy 2", 100);
        ToyMock oldToy3 = new ToyMock("Old Toy 3", 100);
        SalespersonMock cashier = new SalespersonMock("Test Salesperson", "Position", "Task", 50.0, 30, "Shift", 0.05, 20);
        client.exchangeToys(new ArrayList<>(Arrays.asList(oldToy1, oldToy2, oldToy3)), null, cashier);
    }

    @Test
    public void testExchangeToys_NullCashier() {
        ToyMock oldToy1 = new ToyMock("Old Toy 1", 100);
        ToyMock oldToy2 = new ToyMock("Old Toy 2", 100);
        ToyMock oldToy3 = new ToyMock("Old Toy 3", 100);
        ToyMock newToy = new ToyMock("New Toy", 100);
        client.exchangeToys(new ArrayList<>(Arrays.asList(oldToy1, oldToy2, oldToy3)), newToy, null);
    }

    @Test
    public void testExchangeToys_NotAllOldToysBelongToClient() {
        ToyMock oldToy1 = new ToyMock("Old Toy 1", 100);
        ToyMock oldToy2 = new ToyMock("Old Toy 2", 100);
        ToyMock oldToy3 = new ToyMock("Old Toy 3", 100);
        ToyMock anotherToy = new ToyMock("Another Toy", 100);
        ToyMock newToy = new ToyMock("New Toy", 100);
        SalespersonMock cashier = new SalespersonMock("Test Salesperson", "Position", "Task", 50.0, 30, "Shift", 0.05, 20);
        client.setToys(new ArrayList<>(Arrays.asList(oldToy1, oldToy2, oldToy3)));
        client.exchangeToys(new ArrayList<>(List.of(anotherToy)), newToy, cashier);
    }

    @Test
    public void testExchangeToys_DifferentToyTypes() {
        ToyMock oldToy1 = new ToyMock("Old Toy 1", 100);
        ToyMock oldToy2 = new ToyMock("Old Toy 2", 100);
        ToyMock oldToy3 = new ToyMock("Old Toy 3", 100);
        ToyMock newToy = new ToyMock("New Toy", 100) {
            public String getClassName() {
                return "DifferentToyClass";
            }
        };
        SalespersonMock cashier = new SalespersonMock("Test Salesperson", "Position", "Task", 50.0, 30, "Shift", 0.05, 20);
        client.setToys(new ArrayList<>(Arrays.asList(oldToy1, oldToy2, oldToy3)));
        client.exchangeToys(new ArrayList<>(Arrays.asList(oldToy1, oldToy2, oldToy3)), newToy, cashier);
    }

    @Test
    public void testExchangeToys_IncorrectToyCount() {
        ToyMock oldToy1 = new ToyMock("Old Toy 1", 100);
        ToyMock oldToy2 = new ToyMock("Old Toy 2", 100);
        ToyMock oldToy3 = new ToyMock("Old Toy 3", 100);
        ToyMock newToy = new ToyMock("New Toy", 100);
        SalespersonMock cashier = new SalespersonMock("Test Salesperson", "Position", "Task", 50.0, 30, "Shift", 0.05, 20);
        client.setToys(new ArrayList<>(Arrays.asList(oldToy1, oldToy2, oldToy3)));
        client.exchangeToys(new ArrayList<>(List.of(oldToy1, oldToy2)), newToy, cashier);
    }

    @Test
    public void testExchangeToys_Success() {
        ToyMock oldToy1 = new ToyMock("Old Toy 1", 100);
        ToyMock oldToy2 = new ToyMock("Old Toy 2", 100);
        ToyMock oldToy3 = new ToyMock("Old Toy 3", 100);
        ToyMock newToy = new ToyMock("New Toy", 100);
        SalespersonMock cashier = new SalespersonMock("Test Salesperson", "Position", "Task", 50.0, 30, "Shift", 0.05, 20);
        client.setToys(new ArrayList<>(Arrays.asList(oldToy1, oldToy2, oldToy3)));
        client.exchangeToys(new ArrayList<>(Arrays.asList(oldToy1, oldToy2, oldToy3)), newToy, cashier);
        assertTrue(client.getToys().contains(newToy));
        assertFalse(client.getToys().contains(oldToy1));
        assertFalse(client.getToys().contains(oldToy2));
        assertFalse(client.getToys().contains(oldToy3));
    }
}