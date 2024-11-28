package by.rublevskaya.banksystem.model.account;

public class Account {
    private String accountNumber;
    private double amount;

    public Account(String accountNumber, double amount) {
        if (!isValidAccountNumber(accountNumber)) {
            throw new IllegalArgumentException("Invalid account number");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public boolean isValidAccountNumber(String accountNumber) {
        return accountNumber != null && accountNumber.matches("\\d{10,}");
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountNumber != null ? accountNumber.equals(account.accountNumber) : account.accountNumber == null;
    }

    @Override
    public int hashCode() {
        return accountNumber != null ? accountNumber.hashCode() : 0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        if (!isValidAccountNumber(accountNumber)) {
            throw new IllegalArgumentException("Invalid account number");
        }
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.amount = amount;
    }
}