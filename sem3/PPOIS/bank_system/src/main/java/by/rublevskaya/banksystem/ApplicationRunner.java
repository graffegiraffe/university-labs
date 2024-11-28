package by.rublevskaya.banksystem;

import by.rublevskaya.banksystem.fabric.ParserFabric;
import by.rublevskaya.banksystem.model.account.Account;
import by.rublevskaya.banksystem.model.card.BaseCard;
import by.rublevskaya.banksystem.model.card.MasterCard;
import by.rublevskaya.banksystem.model.card.VisaCard;
import by.rublevskaya.banksystem.model.client.IndividualClient;
import by.rublevskaya.banksystem.model.document.Check;
import by.rublevskaya.banksystem.model.document.Invoice;
import by.rublevskaya.banksystem.transfer.CardTransferService;
import by.rublevskaya.banksystem.transfer.impl.MasterCardTransferService;
import by.rublevskaya.banksystem.transfer.impl.VisaCardTransferService;
import by.rublevskaya.banksystem.documentparser.IParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ApplicationRunner {
    public static void main(String[] args) {
        List<Account> accountsList = new ArrayList<>();
        List<BaseCard> cardsList = new ArrayList<>();
        List<Check> checks = new ArrayList<>();

        try {
            accountsList.add(new Account("1234567890", 200.0));
            accountsList.add(new Account("0987654321", 500.0));
            accountsList.add(new Account("5555555555", 1000.0));
            accountsList.add(new Account("4444444444", 0.0));
            try {
                accountsList.add(new Account("111111", -100.0));
            } catch (IllegalArgumentException e) {
                System.err.println("Account creation error: " + e.getMessage());
            }
            accountsList.add(new Account("9999999999", 1500.0));

            try {
                accountsList.get(0).setAccountNumber("1122334455");
                accountsList.get(0).setAmount(300.0);

                accountsList.get(1).setAccountNumber("8888888888");
                accountsList.get(1).setAmount(450.0);
            } catch (IllegalArgumentException e) {
                System.err.println("Error setting values: " + e.getMessage());
            }

            Account account4 = new Account("1122334455", 200.0);
            System.out.println("Accounts are equal: " + accountsList.get(0).equals(account4));
            System.out.println("HashCode of Account 0: " + accountsList.get(0).hashCode());
            System.out.println("HashCode of Account 4: " + account4.hashCode());

            for (Account account : accountsList) {
                if (!account.isValidAccountNumber(account.getAccountNumber())) {
                    System.err.println("Invalid account number: " + account.getAccountNumber());
                }
            }

            try {
                cardsList.add(new MasterCard("1111222233334444", 123, new Date(), "Katusha", "USD", "USA"));
                cardsList.add(new VisaCard("5555666677778888", 321, new Date(), "Katusha", "USD", 5.0, 1.0));
                cardsList.add(new VisaCard("9999888877776666", 987, new Date(), "Andrew", "EUR", 3.5, 0.5));
            } catch (IllegalArgumentException e) {
                System.err.println("Error creating card: " + e.getMessage());
            }

            MasterCard masterCard = (MasterCard) cardsList.get(0);
            System.out.println("Region of MasterCard: " + masterCard.getRegion());
            try {
                masterCard.setRegion("Europe");
                System.out.println("Updated Region of MasterCard: " + masterCard.getRegion());
            } catch (IllegalArgumentException e) {
                System.err.println("Error setting region: " + e.getMessage());
            }

            System.out.println("MasterCard details: " + masterCard);

            double transferAmount = 300.0;
            if (masterCard.checkCardLimitTransfer(transferAmount)) {
                System.out.println("MasterCard: Transfer amount is within the limit.");
            } else {
                System.err.println("MasterCard: Transfer amount exceeds the limit.");
            }

            VisaCard visaCard = (VisaCard) cardsList.get(1);
            System.out.println("Cashback of VisaCard: " + visaCard.getCashback());
            try {
                visaCard.setCashback(2.0);
                System.out.println("Updated Cashback of VisaCard: " + visaCard.getCashback());
            } catch (IllegalArgumentException e) {
                System.err.println("Cashback setting error: " + e.getMessage());
            }

            System.out.println("VisaCard details: " + visaCard);

            if (visaCard.checkCardLimitTransfer(transferAmount)) {
                System.out.println("VisaCard: Transfer amount is within the limit.");
            } else {
                System.err.println("VisaCard: Transfer amount exceeds the limit.");
            }

            IndividualClient client = new IndividualClient(
                    "Katusha",
                    "1234AB5678",
                    accountsList.toArray(new Account[0]),
                    cardsList.toArray(new BaseCard[0])
            );

            try {
                client.setName("Katusha");
                System.out.println("Updated client name: " + client.getName());
            } catch (IllegalArgumentException e) {
                System.err.println("Error setting name: " + e.getMessage());
            }

            try {
                client.setPassportNumber("AB1234567");
                System.out.println("Updated client passport number: " + client.getPassportNumber());
            } catch (IllegalArgumentException e) {
                System.err.println("Error setting passport number: " + e.getMessage());
            }

            Account[] newAccounts = {new Account("222233334444", 700.0), new Account("555566667777", 800.0)};
            client.setAccounts(newAccounts);
            System.out.println("Updated accounts of client: " + Arrays.toString(client.getAccounts()));
            BaseCard[] newCards = {new VisaCard("7777888899990000", 456, new Date(), "Katusha", "GBP", 1.5, 1.0)};
            client.setCards(newCards);
            System.out.println("Updated cards of client: " + Arrays.toString(client.getCards()));
            System.out.println("Client information: " + client);

            CardTransferService masterCardService = new MasterCardTransferService();
            CardTransferService visaCardService = new VisaCardTransferService();

            try {
                if (cardsList.get(0) instanceof MasterCard && cardsList.get(0).getAvailableBalance() >= 50.0) {
                    checks.add(masterCardService.transferFromCardToCard((MasterCard) cardsList.get(0), (MasterCard) cardsList.get(0), 50.0));
                }

                if (cardsList.get(1) instanceof VisaCard && cardsList.get(1).getAvailableBalance() >= 100.0 && cardsList.get(2) instanceof VisaCard) {
                    checks.add(visaCardService.transferFromCardToCard((VisaCard) cardsList.get(1), (VisaCard) cardsList.get(2), 100.0));
                }
            } catch (IllegalArgumentException e) {
                System.err.println("Card to card transfer error: " + e.getMessage());
            }

            try {
                if (cardsList.get(0).getAvailableBalance() >= 200.0) {
                    checks.add(masterCardService.transferFromCardToAccount((MasterCard) cardsList.get(0), accountsList.get(2).getAccountNumber(), 200.0));
                }

                if (cardsList.get(1).getAvailableBalance() >= 150.0) {
                    checks.add(visaCardService.transferFromCardToAccount((VisaCard) cardsList.get(1), accountsList.get(1).getAccountNumber(), 150.0));
                }
            } catch (IllegalArgumentException e) {
                System.err.println("Error transferring from card to account: " + e.getMessage());
            }

            String pdfPath = "path/to/pdf/document.pdf";
            String docxPath = "path/to/docx/document.docx";
            String txtPath = "path/to/txt/document.txt";

            try {
                IParser pdfParser = ParserFabric.create("pdf");
                if (pdfParser != null) {
                    pdfParser.parseFile(pdfPath);
                }
                IParser docxParser = ParserFabric.create("docx");
                if (docxParser != null) {
                    docxParser.parseFile(docxPath);
                }
                IParser txtParser = ParserFabric.create("txt");
                if (txtParser != null) {
                    txtParser.parseFile(txtPath);
                }
            } catch (IllegalArgumentException e) {
                System.err.println("Parser creation error: " + e.getMessage());
            }

            try {
                if (checks.size() > 0) {
                    Check firstCheck = checks.get(0);
                    firstCheck.setCheckNumber("101");
                    firstCheck.setTransactionDetails("Transfer from MasterCard to VisaCard");
                    System.out.println("First Check: " + firstCheck);
                }

                if (checks.size() > 1) {
                    Check secondCheck = checks.get(1);
                    secondCheck.setCheckNumber("102");
                    secondCheck.setTransactionDetails("Transfer from VisaCard to Account");
                    System.out.println("Second Check: " + secondCheck);
                }
            } catch (IllegalArgumentException e) {
                System.err.println("Error setting check values: " + e.getMessage());
            }

            for (Check check : checks) {
                System.out.println(check);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Invoice invoice1 = new Invoice("INV001", 1500.00, "Payment for consulting services", "Client A");
        Invoice invoice2 = new Invoice("INV002", 2200.00, "Payment for design services", "Client B");
        System.out.println(invoice1);
        System.out.println("Amount with 10% discount for Invoice 1: " + invoice1.applyDiscount(10));
        System.out.println(invoice2);
        System.out.println("Amount with 15% discount for Invoice 2: " + invoice2.applyDiscount(15));
    }
}