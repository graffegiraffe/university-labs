package by.rublevskaya.banksystem.model.client;

import by.rublevskaya.banksystem.model.account.Account;
import by.rublevskaya.banksystem.model.card.BaseCard;

public class LegalClient extends BaseClient {
    private String legalClientNumber;

    public LegalClient(String name, String legalClientNumber, Account[] accounts, BaseCard[] cards) {
        super(name, accounts, cards);
        if (legalClientNumber == null || legalClientNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Legal client number cannot be null or empty");
        }
        this.legalClientNumber = legalClientNumber;
    }

    @Override
    public String toString() {
        return "LegalClient{" +
                "name='" + getName() + '\'' +
                ", accounts=" + getAccounts() +
                ", cards=" + getCards() +
                ", legalClientNumber='" + legalClientNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        LegalClient that = (LegalClient) o;

        return legalClientNumber != null ? legalClientNumber.equals(that.legalClientNumber) : that.legalClientNumber == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (legalClientNumber != null ? legalClientNumber.hashCode() : 0);
        return result;
    }

    public String getLegalClientNumber() {
        return legalClientNumber;
    }

    public void setLegalClientNumber(String legalClientNumber) {
        if (legalClientNumber == null || legalClientNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Legal client number cannot be null or empty");
        }
        this.legalClientNumber = legalClientNumber;
    }
}