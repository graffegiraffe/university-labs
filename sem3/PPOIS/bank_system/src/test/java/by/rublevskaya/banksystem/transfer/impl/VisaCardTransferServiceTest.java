package by.rublevskaya.banksystem.transfer.impl;

import by.rublevskaya.banksystem.model.card.BaseCard;
import by.rublevskaya.banksystem.model.card.VisaCard;
import by.rublevskaya.banksystem.model.document.Check;
import org.junit.Test;
import java.util.Date;
import static org.junit.Assert.*;

public class VisaCardTransferServiceTest {
    private static final double CASHBACK_PERCENTAGE = 0.01;

    @Test
    public void transferFromCardToCard() {
        Date date = new Date();
        VisaCard sourceCard = new VisaCard("1234567890123456", 123, date, "Katusha", "USD", 1500, 0);
        VisaCard destinationCard = new VisaCard("6543210987654321", 321, date, "Katusha", "USD", 500, 0);
        VisaCardTransferService service = new VisaCardTransferService();
        Check check = service.transferFromCardToCard(sourceCard, destinationCard, 200);
        double expectedCashback = 200 * CASHBACK_PERCENTAGE;
        assertNotNull(check);
        assertEquals("Check Number: 789, Transaction Details: Transfer from card 1234567890123456 to card 6543210987654321 amount: 200.0, cashback: "
                     + expectedCashback, check.getDescription());
        assertEquals(1500 - 200 + expectedCashback, sourceCard.getAmount(), 0.001);
        assertEquals(700, destinationCard.getAmount(), 0.001);
    }

    @Test
    public void transferFromCardToCard_invalidCards() {
        Date date = new Date();
        BaseCard sourceCard = new BaseCard("1234567890123456", 123, date, "Katusha", "USD") {
            @Override
            public boolean checkCardLimitTransfer(double transferAmount) {
                return true;
            }
        };
        BaseCard destinationCard = new VisaCard("6543210987654321", 321, date, "Katusha", "USD", 500, 0);
        VisaCardTransferService service = new VisaCardTransferService();
        try {
            service.transferFromCardToCard(sourceCard, destinationCard, 200);
            fail("Expected IllegalArgumentException for non-Visa source card");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void transferFromCardToAccount() {
        Date date = new Date();
        VisaCard sourceCard = new VisaCard("1234567890123456", 123, date, "Katusha", "USD", 1500, 0);
        String destinationAccountNumber = "1234567890";
        VisaCardTransferService service = new VisaCardTransferService();
        Check check = service.transferFromCardToAccount(sourceCard, destinationAccountNumber, 300);
        double expectedCashback = 300 * CASHBACK_PERCENTAGE;
        assertNotNull(check);
        assertEquals("Check Number: 012, Transaction Details: Transfer from card 1234567890123456 to account "
                    + destinationAccountNumber + " amount: 300.0, cashback: " + expectedCashback, check.getDescription());
        assertEquals(1500 - 300 + expectedCashback, sourceCard.getAmount(), 0.001);
    }

    @Test
    public void transferFromCardToAccount_invalidArguments() {
        Date date = new Date();
        VisaCard sourceCard = new VisaCard("1234567890123456", 123, date, "Katusha", "USD", 1500, 0);
        String destinationAccountNumber = "";
        VisaCardTransferService service = new VisaCardTransferService();
        try {
            service.transferFromCardToAccount(sourceCard, destinationAccountNumber, 300);
            fail("Expected IllegalArgumentException for empty destination account number");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }
}