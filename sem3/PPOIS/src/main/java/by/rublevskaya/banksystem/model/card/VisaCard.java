package by.rublevskaya.banksystem.model.card;

import by.rublevskaya.banksystem.utils.Constants;

import java.util.Date;

public class VisaCard extends BaseCard {
    private double cashback;

    public VisaCard(String cardNumber, int cvv, Date expirationDate, String cardHolderName, String currency, double amount, double cashback) {
        super(cardNumber, cvv, expirationDate, cardHolderName, currency, amount);
        if (cashback < 0) {
            throw new IllegalArgumentException("Cashback cannot be negative");
        }
        this.cashback = cashback;
    }

    @Override
    public boolean checkCardLimitTransfer(double transferAmount) {
        return transferAmount <= Constants.VISA_CARD_LIMIT;
    }

    @Override
    public String toString() {
        return "VisaCard{" +
                "cashback=" + cashback +
                "} " + super.toString();
    }

    public double getCashback() {
        return cashback;
    }

    public void setCashback(double cashback) {
        if (cashback < 0) {
            throw new IllegalArgumentException("Cashback cannot be negative");
        }
        this.cashback = cashback;
    }
}