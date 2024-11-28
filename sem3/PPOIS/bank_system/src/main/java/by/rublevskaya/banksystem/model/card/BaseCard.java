package by.rublevskaya.banksystem.model.card;

import java.util.Date;

public abstract class BaseCard {
    public String cardNumber;
    public int cvv;
    public Date validDate;
    public String cardHolder;
    public String currency;
    public double amount;

    public BaseCard(String cardNumber, int cvv, Date validDate, String cardHolder, String currency) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.validDate = validDate;
        this.cardHolder = cardHolder;
        this.currency = currency;
    }

    public BaseCard(String cardNumber, int cvv, Date validDate, String cardHolder, String currency, double amount) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.validDate = validDate;
        this.cardHolder = cardHolder;
        this.currency = currency;
        this.amount = amount;
    }

    public abstract boolean checkCardLimitTransfer(double transferAmount);

    public void showBaseInfo() {
        System.out.println(cardNumber + " -> " + amount + " -> " + validDate);
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setAmount(double newAmount) {
        this.amount = newAmount;
    }

    public int getCvv() {
        return cvv;
    }

    public Date getValidDate() {
        return validDate;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public double getAvailableBalance() {
        return amount;
    }
}