package by.rublevskaya.banksystem.model.client;

import by.rublevskaya.banksystem.model.account.Account;
import by.rublevskaya.banksystem.model.card.BaseCard;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class LegalClientTest {

    @Test(expected = IllegalArgumentException.class)
    public void setLegalClientNumberInvalid() {
        LegalClient client = new LegalClient("Company XYZ", "12345", null, null);
        client.setLegalClientNumber("");
    }

    @Test
    public void setLegalClientNumber() {
        LegalClient client = new LegalClient("Company XYZ", "12345", null, null);
        client.setLegalClientNumber("67890");
        assertEquals("67890", client.getLegalClientNumber());
    }

    @Test
    public void testToString() {
        Account account = new Account("1234567890", 1000.0);
        BaseCard card = new BaseCard("1234567812345678", 123, new Date(), "John Doe", "USD") {
            @Override
            public boolean checkCardLimitTransfer(double transferAmount) {
                return transferAmount <= 1000;
            }

            @Override
            public String toString() {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                return "BaseCard{" +
                        "cardNumber='" + getCardNumber() + '\'' +
                        ", cvv=" + getCvv() +
                        ", validDate=" + sdf.format(getValidDate()) +
                        ", cardHolder='" + getCardHolder() + '\'' +
                        ", currency='" + getCurrency() + '\'' +
                        '}';
            }
        };
        LegalClient client = new LegalClient("Company XYZ", "12345", new Account[]{account}, new BaseCard[]{card});
        String expected = "LegalClient{name='Company XYZ', accounts=[Account{accountNumber='1234567890', amount=1000.0}], cards=[BaseCard{cardNumber='1234567812345678', cvv=123, validDate="
                + new SimpleDateFormat("yyyy-MM-dd").format(card.getValidDate()) + ", cardHolder='John Doe', currency='USD'}], legalClientNumber='12345'}";
        assertEquals(expected, client.toString());
    }

    @Test
    public void testEquals() {
        Account account = new Account("1234567890", 1000.0);
        BaseCard card = new BaseCard("1234567812345678", 123, new Date(), "John Doe", "USD") {
            @Override
            public boolean checkCardLimitTransfer(double transferAmount) {
                return transferAmount <= 1000;
            }
        };
        LegalClient client1 = new LegalClient("Company XYZ", "12345", new Account[]{account}, new BaseCard[]{card});
        LegalClient client2 = new LegalClient("Company XYZ", "12345", new Account[]{account}, new BaseCard[]{card});
        assertEquals(client1, client2);
    }

    @Test
    public void testHashCode() {
        Account account = new Account("1234567890", 1000.0);
        BaseCard card = new BaseCard("1234567812345678", 123, new Date(), "John Doe", "USD") {
            @Override
            public boolean checkCardLimitTransfer(double transferAmount) {
                return transferAmount <= 1000;
            }
        };
        LegalClient client1 = new LegalClient("Company XYZ", "12345", new Account[]{account}, new BaseCard[]{card});
        LegalClient client2 = new LegalClient("Company XYZ", "12345", new Account[]{account}, new BaseCard[]{card});
        assertEquals(client1.hashCode(), client2.hashCode());
    }
}