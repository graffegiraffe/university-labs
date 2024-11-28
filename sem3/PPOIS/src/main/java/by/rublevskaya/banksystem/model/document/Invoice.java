package by.rublevskaya.banksystem.model.document;

public class Invoice {
    private String id;
    private double amount;
    private String description;
    private String client;
    private final double TAX_RATE = 0.2;

    public Invoice(String id, double amount, String description, String client) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.client = client;
    }

    public double applyDiscount(double discountPercentage) {
        if (discountPercentage < 0 || discountPercentage > 100) {
            throw new IllegalArgumentException("Discount percentage must be between 0 and 100");
        }
        double discountAmount = amount * (discountPercentage / 100);
        return amount - discountAmount;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", client='" + client + '\'' +
                ", amount with tax=" + getAmountWithTax() +
                '}';
    }

    public double getAmountWithTax() {
        return amount * (1 + TAX_RATE);
    }
}