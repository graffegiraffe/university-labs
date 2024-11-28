package by.rublevskaya.banksystem.model.card;

import by.rublevskaya.banksystem.utils.Constants;

import java.util.Date;

public class MasterCard extends BaseCard {

    private String region;

    public MasterCard(String cardNumber, int cvv, Date expirationDate, String cardHolderName, String currency, String region) {
        super(cardNumber, cvv, expirationDate, cardHolderName, currency);
        if (region == null || region.isEmpty()) {
            throw new IllegalArgumentException("Region cannot be null or empty");
        }
        this.region = region;
    }

    @Override
    public String toString() {
        return "MasterCard{" +
                "region='" + region + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean checkCardLimitTransfer(double transferAmount) {
        return transferAmount <= Constants.MASTER_CARD_LIMIT;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        if (region == null || region.isEmpty()) {
            throw new IllegalArgumentException("Region cannot be null or empty");
        }
        this.region = region;
    }
}