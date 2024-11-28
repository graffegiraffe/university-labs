package by.rublevskaya.banksystem.model.account;

import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {

    @Test
    public void getAccountNumber() {
        Account account = new Account("1234567890", 100.0);
        assertEquals("1234567890", account.getAccountNumber());
    }

    @Test
    public void setAccountNumber() {
        Account account = new Account("1234567890", 100.0);
        account.setAccountNumber("0987654321");
        assertEquals("0987654321", account.getAccountNumber());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setAccountNumberInvalid() {
        Account account = new Account("1234567890", 100.0);
        account.setAccountNumber("invalid");
    }

    @Test
    public void getAmount() {
        Account account = new Account("1234567890", 100.0);
        assertEquals(100.0, account.getAmount(), 0.01);
    }

    @Test
    public void setAmount() {
        Account account = new Account("1234567890", 100.0);
        account.setAmount(200.0);
        assertEquals(200.0, account.getAmount(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setAmountInvalid() {
        Account account = new Account("1234567890", 100.0);
        account.setAmount(-50.0);
    }

    @Test
    public void testToString() {
        Account account = new Account("1234567890", 100.0);
        String expected = "Account{accountNumber='1234567890', amount=100.0}";
        assertEquals(expected, account.toString());
    }

    @Test
    public void testEquals() {
        Account account1 = new Account("1234567890", 100.0);
        Account account2 = new Account("1234567890", 200.0);
        assertEquals(account1, account2);
    }

    @Test
    public void testHashCode() {
        Account account1 = new Account("1234567890", 100.0);
        Account account2 = new Account("1234567890", 200.0);
        assertEquals(account1.hashCode(), account2.hashCode());
    }

    @Test
    public void isValidAccountNumber() {
        Account account = new Account("1234567890", 100.0);
        assertTrue(account.isValidAccountNumber("1234567890"));
        assertFalse(account.isValidAccountNumber("invalid"));
    }
}