package by.rublevskaya.banksystem.model.document;

import org.junit.Test;
import static org.junit.Assert.*;

public class InvoiceTest {

    @Test
    public void getAmountWithTax() {
        Invoice invoice = new Invoice("INV001", 1000.00, "Test description", "Test client");
        double expected = 1200.00;
        double actual = invoice.getAmountWithTax();
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void applyDiscount() {
        Invoice invoice = new Invoice("INV002", 2000.00, "Test description", "Test client");
        double expected = 1800.00;
        double actual = invoice.applyDiscount(10);
        assertEquals(expected, actual, 0.01);

        try {
            invoice.applyDiscount(-5);
            fail("Discount can't be negative. Exception should have been thrown.");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        try {
            invoice.applyDiscount(105);
            fail("Discount can't be greater than 100%. Exception should have been thrown.");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testToString() {
        Invoice invoice = new Invoice("INV003", 2500.00, "Invoice for software development", "Client XYZ");
        String expected = "Invoice{id='INV003', amount=2500.0, description='Invoice for software development', client='Client XYZ', amount with tax=3000.0}";
        assertEquals(expected, invoice.toString());
    }
}