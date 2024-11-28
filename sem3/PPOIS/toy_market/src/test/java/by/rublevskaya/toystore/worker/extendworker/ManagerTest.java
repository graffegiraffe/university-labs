package by.rublevskaya.toystore.worker.extendworker;

import by.rublevskaya.toystore.discountsystem.Coupon;
import by.rublevskaya.toystore.discountsystem.CouponType;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ManagerTest {
    private Manager manager;
    private Salesperson salesperson;

    @Before
    public void setUp() {
        manager = new Manager("Anna", "Manager", "Manage sales", 5000.0, 35, "day shift");
        salesperson = new Salesperson("John", "Salesperson", "Sell toys", 2000.0, 25, "day shift", 0.1, 100);
    }

    @Test
    public void createCoupon() {
        CouponType type = CouponType.FIXED;
        double value = 50.0;
        manager.createCoupon("DISC50", type, value);
        assertTrue(true);
    }

    @Test
    public void addSalesperson() {
        manager.addSalesperson(salesperson);
        List<Salesperson> salespeople = manager.getSalespeople();
        assertEquals(1, salespeople.size());
        assertEquals("John", salespeople.get(0).getName());
    }

    @Test
    public void distributeCoupon() {
        Coupon coupon = new Coupon("DISC50", CouponType.FIXED, 50.0);
        manager.addSalesperson(salesperson);
        manager.distributeCoupon(coupon, salesperson);
        assertEquals(1, salesperson.getAvailableCoupons().size());
        assertEquals("DISC50", salesperson.getAvailableCoupons().get(0).getCode());
    }

    @Test
    public void fireSalesperson() {
        manager.addSalesperson(salesperson);
        manager.fireSalesperson(salesperson);
        List<Salesperson> salespeople = manager.getSalespeople();
        assertTrue(salespeople.isEmpty());
    }

    @Test
    public void work() {
        manager.work();
        assertTrue(true);
    }

    @Test
    public void getSalespeople() {
        manager.addSalesperson(salesperson);
        List<Salesperson> salespeople = manager.getSalespeople();
        assertEquals(1, salespeople.size());
        assertEquals("John", salespeople.get(0).getName());
    }
}