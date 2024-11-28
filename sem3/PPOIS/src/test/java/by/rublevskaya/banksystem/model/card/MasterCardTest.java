package by.rublevskaya.banksystem.model.card;

import by.rublevskaya.banksystem.utils.Constants;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Date;

public class MasterCardTest {

    @Test
    public void getRegion() {
        MasterCard card = new MasterCard("1234567812345678", 123, new Date(), "Katusha", "USD", "Europe");
        assertEquals("Europe", card.getRegion());
    }

    @Test
    public void setRegion() {
        MasterCard card = new MasterCard("1234567812345678", 123, new Date(), "Katusha", "USD", "Europe");
        card.setRegion("Asia");
        assertEquals("Asia", card.getRegion());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setRegionInvalid() {
        MasterCard card = new MasterCard("1234567812345678", 123, new Date(), "Katusha", "USD", "Europe");
        card.setRegion(null);
    }

    @Test
    public void testToString() {
        MasterCard card = new MasterCard("1234567812345678", 123, new Date(), "Katusha", "USD", "Europe");
        String expected = "MasterCard{region='Europe'} " + card;
        assertTrue(card.toString().contains("region='Europe'"));
    }

    @Test
    public void checkCardLimitTransfer() {
        MasterCard card = new MasterCard("1234567812345678", 123, new Date(), "Katusha", "USD", "Europe");
        assertTrue(card.checkCardLimitTransfer(Constants.MASTER_CARD_LIMIT - 1));
        assertFalse(card.checkCardLimitTransfer(Constants.MASTER_CARD_LIMIT + 1));
    }
}