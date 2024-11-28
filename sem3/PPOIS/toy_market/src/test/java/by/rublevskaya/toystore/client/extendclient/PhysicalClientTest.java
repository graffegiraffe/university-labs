package by.rublevskaya.toystore.client.extendclient;

import by.rublevskaya.toystore.client.Purchase;
import by.rublevskaya.toystore.discountsystem.Coupon;
import by.rublevskaya.toystore.discountsystem.CouponType;
import by.rublevskaya.toystore.mock.ToyMock;
import by.rublevskaya.toystore.mock.SalespersonMock;
import org.junit.Test;

import static org.junit.Assert.*;

public class PhysicalClientTest {

    @Test
    public void getCoupon() {
        PhysicalClient physicalClient = new PhysicalClient("Test Client");
        Coupon coupon = new Coupon("DISCOUNT10", CouponType.PERCENT, 10);
        physicalClient.setCoupon(coupon);
        Coupon retrievedCoupon = physicalClient.getCoupon();
        assertNotNull(retrievedCoupon);
        assertEquals("DISCOUNT10", retrievedCoupon.getCode());
    }

    @Test
    public void setCoupon() {
        PhysicalClient physicalClient = new PhysicalClient("Test Client");
        Coupon coupon = new Coupon("DISCOUNT10", CouponType.PERCENT, 10);
        physicalClient.setCoupon(coupon);
        Coupon retrievedCoupon = physicalClient.getCoupon();
        assertNotNull(retrievedCoupon);
        assertEquals("DISCOUNT10", retrievedCoupon.getCode());
    }

    @Test
    public void buyToy() {
        PhysicalClient physicalClient = new PhysicalClient("Test Client");
        ToyMock toy = new ToyMock("Test Toy", 100);
        SalespersonMock salesperson = new SalespersonMock(
                "Test Salesperson", "Position", "Task", 50.0, 30, "Shift", 0.05, 20
        );
        double price = 100.0;
        Coupon coupon = new Coupon("DISCOUNT10", CouponType.PERCENT, 10);
        physicalClient.setCoupon(coupon);
        physicalClient.buyToy(toy, salesperson, price);
        assertEquals(1, physicalClient.getPurchases().size());
        double expectedFinalPrice = price - coupon.getDiscount(price);
        Purchase purchase = physicalClient.getPurchases().get(0);
        assertEquals(expectedFinalPrice, purchase.getPrice(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void buyToy_withNullToy_throwsException() {
        PhysicalClient physicalClient = new PhysicalClient("Test Client");
        SalespersonMock salesperson = new SalespersonMock(
                "Test Salesperson", "Position", "Task", 50.0, 30, "Shift", 0.05, 20
        );
        physicalClient.buyToy(null, salesperson, 100.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void buyToy_withNullSalesperson_throwsException() {
        PhysicalClient physicalClient = new PhysicalClient("Test Client");
        ToyMock toy = new ToyMock("Test Toy", 100);
        physicalClient.buyToy(toy, null, 100.0);
    }
}