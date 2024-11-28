package by.rublevskaya.toystore.discountsystem;

import org.junit.Test;

import static org.junit.Assert.*;

public class CouponTypeTest {

    @Test
    public void values() {
        CouponType[] expectedValues = {CouponType.FIXED, CouponType.PERCENT};
        CouponType[] actualValues = CouponType.values();
        assertArrayEquals(expectedValues, actualValues);
    }

    @Test
    public void valueOf() {
        assertEquals(CouponType.FIXED, CouponType.valueOf("FIXED"));
        assertEquals(CouponType.PERCENT, CouponType.valueOf("PERCENT"));
    }
}