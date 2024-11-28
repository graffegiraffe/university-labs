package by.rublevskaya.banksystem.model.document;

import org.junit.Test;

import static org.junit.Assert.*;

public class CheckTest {

    @Test
    public void getCheckNumber() {
        Check check = new Check("123456", "Payment for services");
        assertEquals("123456", check.getCheckNumber());
    }

    @Test
    public void setCheckNumber() {
        Check check = new Check("123456", "Payment for services");
        check.setCheckNumber("654321");
        assertEquals("654321", check.getCheckNumber());
    }

    @Test
    public void setInvalidCheckNumber() {
        Check check = new Check("123456", "Payment for services");
        try {
            check.setCheckNumber("");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Check number cannot be null or empty", e.getMessage());
        }
    }

    @Test
    public void getTransactionDetails() {
        Check check = new Check("123456", "Payment for services");
        assertEquals("Payment for services", check.getTransactionDetails());
    }

    @Test
    public void setTransactionDetails() {
        Check check = new Check("123456", "Payment for services");
        check.setTransactionDetails("Refund");
        assertEquals("Refund", check.getTransactionDetails());
    }

    @Test
    public void setInvalidTransactionDetails() {
        Check check = new Check("123456", "Payment for services");
        try {
            check.setTransactionDetails("");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Transaction details cannot be null or empty", e.getMessage());
        }
    }

    @Test
    public void printCheckInfo() {
        Check check = new Check("123456", "Payment for services");
        check.printCheckInfo();
    }

    @Test
    public void testToString() {
        Check check = new Check("123456", "Payment for services");
        String expected = "Check{checkNumber='123456', transactionDetails='Payment for services'}";
        assertEquals(expected, check.toString());
    }
}