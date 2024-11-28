package by.rublevskaya.toystore.client;

import by.rublevskaya.toystore.toy.Toy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Purchase {
    private final Toy toy;
    private double price;
    private final LocalDateTime purchaseDateTime;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public String toString() {
        String formattedDateTime = purchaseDateTime.format(formatter);
        return "Purchase{" +
                "toy=" + toy.getName() +
                ", price=" + price +
                ", purchaseDateTime=" + formattedDateTime +
                '}';
    }

    public Purchase(Toy toy, double price) {
        if (toy == null) {
            System.out.println("Toy не может быть ноль");
        }
        if (price < 0) {
            System.out.println("Цена не может быть отрицательной");
        }
        this.toy = toy;
        this.price = price;
        this.purchaseDateTime = LocalDateTime.now();
    }

    public Purchase setPrice(double price) {
        if (price < 0) {
            System.out.println("Цена не может быть отрицательной");
        }
        this.price = price;
        return this;
    }

    public LocalDateTime getPurchaseDateTime() {
        return purchaseDateTime;
    }

    public Toy getToy() {
        return toy;
    }

    public double getPrice() {
        return price;
    }
}