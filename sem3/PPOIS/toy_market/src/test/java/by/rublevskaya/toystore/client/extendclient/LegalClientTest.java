package by.rublevskaya.toystore.client.extendclient;

import by.rublevskaya.toystore.client.Purchase;
import by.rublevskaya.toystore.discountsystem.Coupon;
import by.rublevskaya.toystore.discountsystem.CouponType;
import by.rublevskaya.toystore.mock.ToyMock;
import by.rublevskaya.toystore.mock.SalespersonMock;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LegalClientTest {

    @Test
    public void buyToy() {
        LegalClient legalClient = new LegalClient("Test Client");
        ToyMock toy = new ToyMock("Test Toy", 100);
        SalespersonMock salesperson = new SalespersonMock(
                "Test Salesperson", "Position", "Task", 50.0, 30, "Shift", 0.05, 20
        );
        double price = 100.0;
        Coupon coupon1 = new Coupon("DISCOUNT10", CouponType.PERCENT, 10);
        Coupon coupon2 = new Coupon("DISCOUNT5", CouponType.PERCENT, 5);
        legalClient.setCoupons(List.of(coupon1, coupon2));
        legalClient.buyToy(toy, salesperson, price);
        List<Purchase> purchases = legalClient.getPurchases();
        assertEquals(1, purchases.size());
        double discount1 = coupon1.getDiscount(price);
        double discount2 = coupon2.getDiscount(price - discount1);
        double expectedFinalPrice = price - discount1 - discount2;
        System.out.println("Total discount from coupons: " + discount1 + " + " + discount2);
        System.out.println("Expected price: " + expectedFinalPrice);
        Purchase purchase = purchases.get(0);
        assertEquals(expectedFinalPrice, purchase.getPrice(), 0.01);
    }

    @Test
    public void getCoupons() {
        LegalClient legalClient = new LegalClient("Test Client");
        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon("DISCOUNT10", CouponType.PERCENT, 10));
        legalClient.setCoupons(coupons);
        List<Coupon> retrievedCoupons = legalClient.getCoupons();
        assertNotNull(retrievedCoupons);
        assertEquals(1, retrievedCoupons.size());
        assertEquals("DISCOUNT10", retrievedCoupons.get(0).getCode());
    }

    @Test
    public void setCoupons() {
        LegalClient legalClient = new LegalClient("Test Client");
        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon("DISCOUNT10", CouponType.PERCENT, 10));
        legalClient.setCoupons(coupons);
        List<Coupon> retrievedCoupons = legalClient.getCoupons();
        assertNotNull(retrievedCoupons);
        assertEquals(1, retrievedCoupons.size());
        assertEquals("DISCOUNT10", retrievedCoupons.get(0).getCode());
        legalClient.setCoupons(new ArrayList<>());
        assertTrue(legalClient.getCoupons().isEmpty());
    }

    @Test
    public void addCoupon() {
        LegalClient legalClient = new LegalClient("Test Client");
        Coupon newCoupon = new Coupon("DISCOUNT20", CouponType.PERCENT, 20);
        legalClient.addCoupon(newCoupon);
        List<Coupon> retrievedCoupons = legalClient.getCoupons();
        assertNotNull(retrievedCoupons);
        assertEquals(1, retrievedCoupons.size());
        assertEquals("DISCOUNT20", retrievedCoupons.get(0).getCode());
    }
}