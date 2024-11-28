package by.rublevskaya.toystore.worker.extendworker;

import by.rublevskaya.toystore.client.AbstractClient;
import by.rublevskaya.toystore.client.Purchase;
import by.rublevskaya.toystore.discountsystem.Coupon;
import by.rublevskaya.toystore.toy.Toy;
import by.rublevskaya.toystore.worker.BaseWorker;

import java.util.ArrayList;
import java.util.List;

public class Salesperson extends BaseWorker {
    private double commissionRate;
    private double salesAmount;
    private List<Coupon> availableCoupons;
    private int toysInStock;

    public Salesperson(String name, String position, String task, double salary, int age, String shift, double commissionRate, int toysInStock) {
        super(name, position, task, salary, age, shift);
        this.commissionRate = commissionRate;
        this.salesAmount = 0;
        this.availableCoupons = new ArrayList<>();
        this.toysInStock = toysInStock;
    }

    @Override
    public void work() {
        System.out.println(name + " работает над продажами.");
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Ставка комиссии: " + commissionRate);
        System.out.println("Сумма продаж: " + salesAmount);
        System.out.println("Общая зарплата с комиссиями: " + calculateTotalSalary());
        System.out.println("Доступные купоны: " + availableCoupons);
    }

    public double calculateTotalSalary() {
        return salary + calculateCommission();
    }

    public double calculateCommission() {
        return commissionRate * salesAmount;
    }

    public void serveClient(AbstractClient client) {
        addClient(client);
        System.out.println("Продавец обслуживает клиента: " + client.getName());
    }

    public void finishServingClient(AbstractClient client) {
        removeClient(client);
        System.out.println("Продавец закончил обслуживать клиента: " + client.getName());
    }

    public void listClients() {
        System.out.println("Список клиентов:");
        for (AbstractClient client : getClients()) {
            System.out.println(client.getName());
        }
    }

    public Purchase processPurchase(AbstractClient client, Purchase purchase) {
        if (client == null || purchase == null) {
            System.out.println("Client и Purchase не могут быть ноль");
        }

        if (purchase.getToy() == null) {
            System.out.println("toy не может быть null");
        }

        if (toysInStock <= 0) {
            System.err.println("Ошибка покупки: Игрушка закончилась на складе.");
            return null;
        }

        Toy toy = purchase.getToy();
        toysInStock--;

        double finalPrice = calculateFinalPrice(purchase, client);
        if (finalPrice < 0) {
            finalPrice = 0;
        }

        purchase.setPrice(finalPrice);
        client.addBonusPoints(finalPrice * 0.1);
        addSales(finalPrice);
        client.getToys().add(toy);
        System.out.println(client.getName() + " купил игрушку " + toy.getName() + " за " + finalPrice + " у продавца " + getName());
        return purchase;
    }

    private double calculateFinalPrice(Purchase purchase, AbstractClient client) {
        double price = purchase.getPrice();
        if (availableCoupons != null) {
            for (Coupon coupon : availableCoupons) {
                price -= coupon.getDiscount(price);
            }
        }
        return price;
    }

    public void addSales(double amount) {
        this.salesAmount += amount;
    }

    public void addCoupon(Coupon coupon) {
        if (coupon == null) {
            System.out.println("Coupon не может быть null");
        }
        this.availableCoupons.add(coupon);
    }

    public void removeCoupon(Coupon coupon) {
        this.availableCoupons.remove(coupon);
    }

    public List<Coupon> getAvailableCoupons() {
        return new ArrayList<>(availableCoupons);
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(double commissionRate) {
        this.commissionRate = commissionRate;
    }

    public double getSalesAmount() {
        return salesAmount;
    }

    public int getToysInStock() {
        return toysInStock;
    }

    public void setToysInStock(int toysInStock) {
        this.toysInStock = toysInStock;
    }

    public Object getShift() {
        return shift;
    }

    public Object getSalary() {
        return salary;
    }
}