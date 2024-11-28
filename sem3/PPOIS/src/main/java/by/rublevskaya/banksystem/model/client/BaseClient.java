package by.rublevskaya.banksystem.model.client;

import by.rublevskaya.banksystem.model.account.Account;
import by.rublevskaya.banksystem.model.card.BaseCard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BaseClient {
    private String name;
    private List<Account> accounts;
    private List<BaseCard> cards;

    public BaseClient(String name, Account[] accounts, BaseCard[] cards) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
        this.accounts = accounts != null ? new ArrayList<>(Arrays.asList(accounts)) : new ArrayList<>();
        this.cards = cards != null ? new ArrayList<>(Arrays.asList(cards)) : new ArrayList<>();
    }

    public void addAccount(Account account) {
        if (account != null) {
            accounts.add(account);
        }
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    public void addCard(BaseCard card) {
        if (card != null) {
            cards.add(card);
        }
    }

    public void removeCard(BaseCard card) {
        cards.remove(card);
    }

    @Override
    public String toString() {
        return "BaseClient{" +
                "name='" + name + '\'' +
                ", accounts=" + accounts +
                ", cards=" + cards +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseClient that = (BaseClient) o;

        if (!name.equals(that.name)) return false;
        if (!accounts.equals(that.accounts)) return false;
        return cards.equals(that.cards);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + accounts.hashCode();
        result = 31 * result + cards.hashCode();
        return result;
    }

    public String getName() {
        return name;
    }

    public double getTotalBalance() {
        return accounts.stream()
                .mapToDouble(Account::getAmount)
                .sum();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public List<Account> getAccounts() {
        return new ArrayList<>(accounts);
    }

    public void setAccounts(Account[] accounts) {
        this.accounts = accounts != null ? new ArrayList<>(Arrays.asList(accounts)) : new ArrayList<>();
    }

    public List<BaseCard> getCards() {
        return new ArrayList<>(cards);
    }

    public void setCards(BaseCard[] cards) {
        this.cards = cards != null ? new ArrayList<>(Arrays.asList(cards)) : new ArrayList<>();
    }
}