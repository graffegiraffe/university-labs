package by.rublevskaya.banksystem.model.client;

import by.rublevskaya.banksystem.model.account.Account;
import by.rublevskaya.banksystem.model.card.BaseCard;

public class IndividualClient {

    private String name;
    private String passportNumber;
    private Account[] accounts;
    private BaseCard[] cards;

    public IndividualClient(String name, String passportNumber, Account[] accounts, BaseCard[] cards) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (passportNumber == null || passportNumber.isEmpty()) {
            throw new IllegalArgumentException("Passport number cannot be null or empty");
        }
        this.name = name;
        this.passportNumber = passportNumber;
        this.accounts = accounts;
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "IndividualClient{" +
                "name='" + name + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        if (passportNumber == null || passportNumber.isEmpty()) {
            throw new IllegalArgumentException("Passport number cannot be null or empty");
        }
        this.passportNumber = passportNumber;
    }

    public Account[] getAccounts() {
        return accounts;
    }

    public void setAccounts(Account[] accounts) {
        this.accounts = accounts;
    }

    public BaseCard[] getCards() {
        return cards;
    }

    public void setCards(BaseCard[] cards) {
        this.cards = cards;
    }
}