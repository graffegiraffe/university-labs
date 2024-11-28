package by.rublevskaya.toystore.discountsystem;

import org.junit.Test;

import static org.junit.Assert.*;

public class CouponTest {

    @Test
    public void getDiscount() {
        Coupon fixedCoupon = new Coupon("FIXED01", CouponType.FIXED, 50.0);
        assertEquals(50.0, fixedCoupon.getDiscount(100.0), 0.001);
        assertEquals(30.0, fixedCoupon.getDiscount(30.0), 0.001);

        Coupon percentCoupon = new Coupon("PERCENT01", CouponType.PERCENT, 10.0); // 10% скидка
        assertEquals(10.0, percentCoupon.getDiscount(100.0), 0.001);
        assertEquals(15.0, percentCoupon.getDiscount(150.0), 0.001);
    }

    @Test
    public void getValue() {
        Coupon coupon = new Coupon("VALUE01", CouponType.FIXED, 20.0);
        assertEquals(20.0, coupon.getValue(), 0.001);
    }

    @Test
    public void setValue() {
        Coupon coupon = new Coupon("VALUE01", CouponType.FIXED, 20.0);
        coupon.setValue(30.0);
        assertEquals(30.0, coupon.getValue(), 0.001);
    }

    @Test
    public void getCode() {
        Coupon coupon = new Coupon("CODE01", CouponType.FIXED, 20.0);
        assertEquals("CODE01", coupon.getCode());
    }

    @Test
    public void setCode() {
        Coupon coupon = new Coupon("CODE01", CouponType.FIXED, 20.0);
        coupon.setCode("CODE02");
        assertEquals("CODE02", coupon.getCode());
    }

    @Test
    public void getType() {
        Coupon coupon = new Coupon("TYPE01", CouponType.FIXED, 20.0);
        assertEquals(CouponType.FIXED, coupon.getType());
        coupon.setType(CouponType.PERCENT);
        assertEquals(CouponType.PERCENT, coupon.getType());
    }

    @Test
    public void setType() {
        Coupon coupon = new Coupon("TYPE01", CouponType.PERCENT, 20.0);
        coupon.setType(CouponType.FIXED);
        assertEquals(CouponType.FIXED, coupon.getType());
    }

    @Test
    public void testToString() {
        Coupon coupon = new Coupon("STRING01", CouponType.FIXED, 20.0);
        String expectedString = "Coupon{code='STRING01', type=FIXED, value=20.0}";
        assertEquals(expectedString, coupon.toString());
    }
}