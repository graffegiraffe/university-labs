package by.rublevskaya.banksystem.model.client;

import by.rublevskaya.banksystem.model.account.Account;
import by.rublevskaya.banksystem.model.card.BaseCard;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class BaseClientTest {

    private class TestClient extends BaseClient {
        public TestClient(String name, Account[] accounts, BaseCard[] cards) {
            super(name, accounts, cards);
        }
    }

    @Test
    public void getName() {
        BaseClient client = new TestClient("Katusha", null, null);
        assertEquals("Katusha", client.getName());
    }

    @Test
    public void setName() {
        BaseClient client = new TestClient("Katusha", null, null);
        client.setName("Katusha");
        assertEquals("Katusha", client.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNameInvalid() {
        BaseClient client = new TestClient("Katusha", null, null);
        client.setName(null);
    }

    @Test
    public void getAccounts() {
        Account account = new Account("1234567890", 100.0);
        BaseClient client = new TestClient("Katusha", new Account[]{account}, null);
        List<Account> accounts = client.getAccounts();
        assertEquals(1, accounts.size());
        assertEquals(account, accounts.get(0));
    }

    @Test
    public void setAccounts() {
        Account account1 = new Account("1234567890", 100.0);
        Account account2 = new Account("0987654321", 200.0);
        BaseClient client = new TestClient("Katusha", new Account[]{account1}, null);
        client.setAccounts(new Account[]{account2});
        List<Account> accounts = client.getAccounts();
        assertEquals(1, accounts.size());
        assertEquals(account2, accounts.get(0));
    }

    @Test
    public void getCards() {
        BaseCard card = new BaseCard("1234567812345678", 123, new Date(), "Katusha", "USD") {
            @Override
            public boolean checkCardLimitTransfer(double transferAmount) {
                return transferAmount <= 1000;
            }
        };
        BaseClient client = new TestClient("Katusha", null, new BaseCard[]{card});
        List<BaseCard> cards = client.getCards();
        assertEquals(1, cards.size());
        assertEquals(card, cards.get(0));
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
        BaseClient client = new TestClient("Katusha", null, new BaseCard[]{card1});
        client.setCards(new BaseCard[]{card2});
        List<BaseCard> cards = client.getCards();
        assertEquals(1, cards.size());
        assertEquals(card2, cards.get(0));
    }

    @Test
    public void addAccount() {
        Account account1 = new Account("1234567890", 100.0);
        Account account2 = new Account("0987654321", 200.0);
        BaseClient client = new TestClient("Katusha", new Account[]{account1}, null);
        client.addAccount(account2);
        List<Account> accounts = client.getAccounts();
        assertEquals(2, accounts.size());
        assertTrue(accounts.contains(account1));
        assertTrue(accounts.contains(account2));
    }

    @Test
    public void removeAccount() {
        Account account1 = new Account("1234567890", 100.0);
        Account account2 = new Account("0987654321", 200.0);
        BaseClient client = new TestClient("Katusha", new Account[]{account1, account2}, null);
        client.removeAccount(account1);
        List<Account> accounts = client.getAccounts();
        assertEquals(1, accounts.size());
        assertFalse(accounts.contains(account1));
        assertTrue(accounts.contains(account2));
    }

    @Test
    public void addCard() {
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
        BaseClient client = new TestClient("Katusha", null, new BaseCard[]{card1});
        client.addCard(card2);
        List<BaseCard> cards = client.getCards();
        assertEquals(2, cards.size());
        assertTrue(cards.contains(card1));
        assertTrue(cards.contains(card2));
    }

    @Test
    public void removeCard() {
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
        BaseClient client = new TestClient("Katusha", null, new BaseCard[]{card1, card2});
        client.removeCard(card1);
        List<BaseCard> cards = client.getCards();
        assertEquals(1, cards.size());
        assertFalse(cards.contains(card1));
        assertTrue(cards.contains(card2));
    }

    @Test
    public void getTotalBalance() {
        Account account1 = new Account("1234567890", 100.0);
        Account account2 = new Account("0987654321", 200.0);
        BaseClient client = new TestClient("Katusha", new Account[]{account1, account2}, null);
        assertEquals(300.0, client.getTotalBalance(), 0.01);
    }

    @Test
    public void testToString() {
        Account account = new Account("1234567890", 100.0);
        BaseClient client = new TestClient("Katusha", new Account[]{account}, null);
        assertTrue(client.toString().contains("Katusha"));
        assertTrue(client.toString().contains("1234567890"));
    }

    @Test
    public void testEquals() {
        Account account = new Account("1234567890", 100.0);
        BaseClient client1 = new TestClient("Katusha", new Account[]{account}, null);
        BaseClient client2 = new TestClient("Katusha", new Account[]{account}, null);
        assertEquals(client1, client2);
    }

    @Test
    public void testHashCode() {
        Account account = new Account("1234567890", 100.0);
        BaseClient client1 = new TestClient("Katusha", new Account[]{account}, null);
        BaseClient client2 = new TestClient("Katusha", new Account[]{account}, null);
        assertEquals(client1.hashCode(), client2.hashCode());
    }
}