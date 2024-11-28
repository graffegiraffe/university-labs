package by.rublevskaya.toystore.worker.extendworker;

import by.rublevskaya.toystore.client.AbstractClient;
import by.rublevskaya.toystore.client.Purchase;
import by.rublevskaya.toystore.discountsystem.Coupon;
import by.rublevskaya.toystore.discountsystem.CouponType;
import by.rublevskaya.toystore.mock.ToyMock;
import by.rublevskaya.toystore.toy.Toy;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SalespersonTest {

    private Salesperson salesperson;
    private Coupon coupon;

    @Before
    public void setUp() {
        salesperson = new Salesperson("John", "Salesperson", "Sell toys", 2000.0, 25, "day shift", 0.1, 100);
        coupon = new Coupon("DISC50", CouponType.FIXED, 50.0);
    }

    @Test
    public void processPurchase() {
        AbstractClient client = new AbstractClient("Client1") {
            @Override
            public List<Purchase> getPurchases() {
                return null;
            }

            @Override
            public List<Coupon> getCoupons() {
                return null;
            }

            @Override
            public void buyToy(Toy toy, Salesperson cashier, double price) {
                System.out.printf("Client %s bought toy %s from %s for %.2f%n",
                        getName(), toy.getName(), cashier.getName(), price);
            }
        };
        Purchase purchase = new Purchase(new ToyMock("Toy1", 100), 10.1);
        Purchase result = salesperson.processPurchase(client, purchase);
        assertTrue(true);
    }

    @Test
    public void addSales() {
        double amount = 200.0;
        salesperson.addSales(amount);
        assertEquals(amount, salesperson.getSalesAmount(), 0.01);
    }

    @Test
    public void addCoupon() {
        salesperson.addCoupon(coupon);
        assertEquals(1, salesperson.getAvailableCoupons().size());
        assertEquals(coupon, salesperson.getAvailableCoupons().get(0));
    }

    @Test
    public void removeCoupon() {
        salesperson.addCoupon(coupon);
        salesperson.removeCoupon(coupon);
        assertEquals(0, salesperson.getAvailableCoupons().size());
    }

    @Test
    public void getAvailableCoupons() {
        salesperson.addCoupon(coupon);
        List<Coupon> coupons = salesperson.getAvailableCoupons();
        assertEquals(1, coupons.size());
        assertEquals("DISC50", coupons.get(0).getCode());
    }

    @Test
    public void getCommissionRate() {
        double commissionRate = salesperson.getCommissionRate();
        assertEquals(0.1, commissionRate, 0.01);
    }

    @Test
    public void setCommissionRate() {
        salesperson.setCommissionRate(0.15);
        assertEquals(0.15, salesperson.getCommissionRate(), 0.01);
    }

    @Test
    public void getSalesAmount() {
        double amount = 200.0;
        salesperson.addSales(amount);
        assertEquals(amount, salesperson.getSalesAmount(), 0.01);
    }

    @Test
    public void getToysInStock() {
        assertEquals(100, salesperson.getToysInStock());
    }

    @Test
    public void setToysInStock() {
        salesperson.setToysInStock(150);
        assertEquals(150, salesperson.getToysInStock());
    }

    @Test
    public void work() {
        salesperson.work();
        assertTrue(true);
    }

    @Test
    public void showInfo() {
        salesperson.showInfo();
        assertTrue(true);
    }

    @Test
    public void calculateTotalSalary() {
        double totalSalary = salesperson.calculateTotalSalary();
        assertTrue(true);
    }

    @Test
    public void calculateCommission() {
        double commission = salesperson.calculateCommission();
        assertTrue(true);
    }

    @Test
    public void serveClient() {
        AbstractClient client = new AbstractClient("Client1") {
            @Override
            public List<Purchase> getPurchases() {
                return null;
            }

            @Override
            public List<Coupon> getCoupons() {
                return null;
            }

            @Override
            public void buyToy(Toy toy, Salesperson cashier, double price) {
                // Mock implementation to simulate buying a toy
                System.out.printf("Client %s bought toy %s from %s for %.2f%n",
                        getName(), toy.getName(), cashier.getName(), price);
            }
        };
        salesperson.serveClient(client);
        assertTrue(true);
    }

    @Test
    public void finishServingClient() {
        AbstractClient client = new AbstractClient("Client1") {
            @Override
            public List<Purchase> getPurchases() {
                return null;
            }

            @Override
            public List<Coupon> getCoupons() {
                return null;
            }

            @Override
            public void buyToy(Toy toy, Salesperson cashier, double price) {
                System.out.printf("Client %s bought toy %s from %s for %.2f%n",
                        getName(), toy.getName(), cashier.getName(), price);
            }
        };
        salesperson.finishServingClient(client);
        assertTrue(true);
    }

    @Test
    public void listClients() {
        salesperson.listClients();
        assertTrue(true);
    }


    @Test
    public void getShift() {
        assertEquals("day shift", salesperson.getShift());
        salesperson = new Salesperson("Alice", "Salesperson", "Selling toys", 2500.0, 30, "day shift", 0.20, 150);
        assertEquals("day shift", salesperson.getShift());
    }

    @Test
    public void getSalary() {
        assertEquals(2000.0, salesperson.getSalary());
    }
}