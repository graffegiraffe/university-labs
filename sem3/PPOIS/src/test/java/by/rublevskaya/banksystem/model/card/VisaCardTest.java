package by.rublevskaya.banksystem.model.card;

import by.rublevskaya.banksystem.utils.Constants;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Date;

public class VisaCardTest {

    @Test
    public void getCashback() {
        VisaCard card = new VisaCard("1234567812345678", 123, new Date(), "Katusha", "USD", 1000.0, 5.0);
        assertEquals(5.0, card.getCashback(), 0.01);
    }

    @Test
    public void setCashback() {
        VisaCard card = new VisaCard("1234567812345678", 123, new Date(), "Katusha", "USD", 1000.0, 5.0);
        card.setCashback(10.0);
        assertEquals(10.0, card.getCashback(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCashbackInvalid() {
        VisaCard card = new VisaCard("1234567812345678", 123, new Date(), "Katusha", "USD", 1000.0, 5.0);
        card.setCashback(-1.0);
    }

    @Test
    public void testToString() {
        VisaCard card = new VisaCard("1234567812345678", 123, new Date(), "Katusha", "USD", 1000.0, 5.0);
        assertTrue(card.toString().contains("cashback=5.0"));
    }

    @Test
    public void checkCardLimitTransfer() {
        VisaCard card = new VisaCard("1234567812345678", 123, new Date(), "Katusha", "USD", 1000.0, 5.0);
        assertTrue(card.checkCardLimitTransfer(Constants.VISA_CARD_LIMIT - 1));
        assertFalse(card.checkCardLimitTransfer(Constants.VISA_CARD_LIMIT + 1));
    }
}