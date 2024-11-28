package by.rublevskaya.banksystem.transfer.impl;

import by.rublevskaya.banksystem.model.card.BaseCard;
import by.rublevskaya.banksystem.model.card.MasterCard;
import by.rublevskaya.banksystem.model.document.Check;
import org.junit.Test;
import java.util.Date;
import static org.junit.Assert.*;

public class MasterCardTransferServiceTest {

    @Test
    public void transferFromCardToCard() {
        Date date = new Date();
        MasterCard sourceCard = new MasterCard("1234567890123456", 123, date, "Katusha", "USD", "BLR");
        MasterCard destinationCard = new MasterCard("6543210987654321", 321, date, "Katusha", "USD", "BLR");

        MasterCardTransferService service = new MasterCardTransferService();
        Check check = service.transferFromCardToCard(sourceCard, destinationCard, 200);

        assertNotNull(check);
        assertEquals("Check Number: 123, Transaction Details: Transfer from card 1234567890123456 to card 6543210987654321 amount: 200.0", check.getDescription());
    }

    @Test(expected = IllegalArgumentException.class)
    public void transferFromCardToCard_invalidSourceCard() {
        Date date = new Date();
        MasterCard destinationCard = new MasterCard("6543210987654321", 321, date, "Katusha", "USD", "BLR");

        BaseCard invalidSourceCard = new BaseCard("1234567890123456", 123, date, "Katusha", "USD") {
            @Override
            public boolean checkCardLimitTransfer(double transferAmount) {
                return true;
            }
        };
        MasterCardTransferService service = new MasterCardTransferService();
        service.transferFromCardToCard(invalidSourceCard, destinationCard, 200);
    }

    @Test(expected = IllegalArgumentException.class)
    public void transferFromCardToCard_invalidDestinationCard() {
        Date date = new Date();
        MasterCard sourceCard = new MasterCard("1234567890123456", 123, date, "Katusha", "USD", "BLR");
        BaseCard invalidDestinationCard = new BaseCard("6543210987654321", 321, date, "Katusha", "USD") {
            @Override
            public boolean checkCardLimitTransfer(double transferAmount) {
                return true;
            }
        };
        MasterCardTransferService service = new MasterCardTransferService();
        service.transferFromCardToCard(sourceCard, invalidDestinationCard, 200);
    }

    @Test
    public void transferFromCardToAccount() {
        Date date = new Date();
        MasterCard sourceCard = new MasterCard("1234567890123456", 123, date, "Katusha", "USD", "BLR");
        String destinationAccountNumber = "1234567890";
        MasterCardTransferService service = new MasterCardTransferService();
        Check check = service.transferFromCardToAccount(sourceCard, destinationAccountNumber, 300);
        assertNotNull(check);
        assertEquals("Check Number: 456, Transaction Details: Transfer from card 1234567890123456 to account 1234567890 amount: 300.0", check.getDescription());
    }

    @Test(expected = IllegalArgumentException.class)
    public void transferFromCardToAccount_invalidSourceCard() {
        Date date = new Date();
        String destinationAccountNumber = "1234567890";
        BaseCard invalidSourceCard = new BaseCard("1234567890123456", 123, date, "Katusha", "USD") {
            @Override
            public boolean checkCardLimitTransfer(double transferAmount) {
                return true;
            }
        };
        MasterCardTransferService service = new MasterCardTransferService();
        service.transferFromCardToAccount(invalidSourceCard, destinationAccountNumber, 300);
    }

    @Test(expected = IllegalArgumentException.class)
    public void transferFromCardToAccount_invalidDestinationAccount() {
        Date date = new Date();
        MasterCard sourceCard = new MasterCard("1234567890123456", 123, date, "Katusha", "USD", "BLR");
        String invalidDestinationAccountNumber = "";
        MasterCardTransferService service = new MasterCardTransferService();
        service.transferFromCardToAccount(sourceCard, invalidDestinationAccountNumber, 300);
    }
}