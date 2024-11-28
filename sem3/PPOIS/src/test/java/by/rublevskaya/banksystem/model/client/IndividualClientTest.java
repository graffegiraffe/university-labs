package by.rublevskaya.banksystem.model.client;

import by.rublevskaya.banksystem.model.account.Account;
import by.rublevskaya.banksystem.model.card.BaseCard;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class IndividualClientTest {

    @Test
    public void getName() {
        IndividualClient client = new IndividualClient("Katusha", "AA1234567", null, null);
        assertEquals("Katusha", client.getName());
    }

    @Test
    public void setName() {
        IndividualClient client = new IndividualClient("Katusha", "AA1234567", null, null);
        client.setName("Katusha");
        assertEquals("Katusha", client.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNameInvalid() {
        IndividualClient client = new IndividualClient("Katusha", "AA1234567", null, null);
        client.setName(null);
    }

    @Test
    public void getPassportNumber() {
        IndividualClient client = new IndividualClient("Katusha", "AA1234567", null, null);
        assertEquals("AA1234567", client.getPassportNumber());
    }

    @Test
    public void setPassportNumber() {
        IndividualClient client = new IndividualClient("Katusha", "AA1234567", null, null);
        client.setPassportNumber("BB7654321");
        assertEquals("BB7654321", client.getPassportNumber());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setPassportNumberInvalid() {
        IndividualClient client = new IndividualClient("Katusha", "AA1234567", null, null);
        client.setPassportNumber(null);
    }

    @Test
    public void getAccounts() {
        Account account = new Account("1234567890", 100.0);
        IndividualClient client = new IndividualClient("Katusha", "AA1234567", new Account[]{account}, null);
        Account[] accounts = client.getAccounts();
        assertEquals(1, accounts.length);
        assertEquals(account, accounts[0]);
    }

    @Test
    public void setAccounts() {
        Account account1 = new Account("1234567890", 100.0);
        Account account2 = new Account("0987654321", 200.0);
        IndividualClient client = new IndividualClient("Katusha", "AA1234567", new Account[]{account1}, null);
        client.setAccounts(new Account[]{account2});
        Account[] accounts = client.getAccounts();
        assertEquals(1, accounts.length);
        assertEquals(account2, accounts[0]);
    }

    @Test
    public void getCards() {
        BaseCard card = new BaseCard("1234567812345678", 123, new Date(), "Katusha", "USD") {
            @Override
            public boolean checkCardLimitTransfer(double transferAmount) {
                return transferAmount <= 1000;
            }
        };
        IndividualClient client = new IndividualClient("Katusha", "AA1234567", null, new BaseCard[]{card});
        BaseCard[] cards = client.getCards();
        assertEquals(1, cards.length);
        assertEquals(card, cards[0]);
    }

    @Test
    public void setCards() {
        BaseCard card1 = new BaseCard("1234567812345678", 123, new Date(), "Katusha", "USD") {
            @Override
            public boolean checkCardLimitTransfer(double transferAmount) {
                return transferAmount <= 1000;
            }
        };
        BaseCard card2 = new BaseCard("8765432187654321", 321, new Date(), "Katusha", "EUR") {
            @Override
            public boolean checkCardLimitTransfer(double transferAmount) {
                return transferAmount <= 1000;
            }
        };
        IndividualClient client = new IndividualClient("Katusha", "AA1234567", null, new BaseCard[]{card1});
        client.setCards(new BaseCard[]{card2});
        BaseCard[] cards = client.getCards();
        assertEquals(1, cards.length);
        assertEquals(card2, cards[0]);
    }

    @Test
    public void testToString() {
        IndividualClient client = new IndividualClient("Katusha", "AA1234567", null, null);
        assertTrue(client.toString().contains("Katusha"));
        assertTrue(client.toString().contains("AA1234567"));
    }
}