package by.rublevskaya.banksystem.model.card;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Date;

public class BaseCardTest {

    private class TestBaseCard extends BaseCard {
        public TestBaseCard(String cardNumber, int cvv, Date validDate, String cardHolder, String currency) {
            super(cardNumber, cvv, validDate, cardHolder, currency);
        }

        @Override
        public boolean checkCardLimitTransfer(double transferAmount) {
            return transferAmount <= 1000;
        }
    }

    @Test
    public void checkCardLimitTransfer() {
        BaseCard card = new TestBaseCard("1234567812345678", 123, new Date(), "Katusha", "USD");
        assertTrue(card.checkCardLimitTransfer(500));
        assertFalse(card.checkCardLimitTransfer(1500));
    }

    @Test
    public void showBaseInfo() {
        BaseCard card = new TestBaseCard("1234567812345678", 123, new Date(), "Katusha", "USD");
        card.amount = 1500.0;
        card.showBaseInfo();
        assertNotNull(card);
    }

    @Test
    public void getCardNumber() {
        BaseCard card = new TestBaseCard("1234567812345678", 123, new Date(), "Katusha", "USD");
        assertEquals("1234567812345678", card.getCardNumber());
    }
}