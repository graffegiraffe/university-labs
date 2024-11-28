package by.rublevskaya.toystore.worker.extendworker;

import by.rublevskaya.toystore.worker.BaseWorker;
import by.rublevskaya.toystore.client.AbstractClient;
import by.rublevskaya.toystore.client.Purchase;
import by.rublevskaya.toystore.discountsystem.Coupon;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class Bookkeeper extends BaseWorker {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public Bookkeeper(String name, String position, String task, double salary, int age, String shift) {
        super(name, position, task, salary, age, shift);
    }

    @Override
    public void work() {
        System.out.println(name + " работает над отчетами.");
    }

    public void generateSalesReport(List<AbstractClient> clients, LocalDateTime startDate, LocalDateTime endDate) {
        double totalEarned = 0;
        System.out.println("Отчет о продажах с " + startDate.format(formatter) + " по " + endDate.format(formatter));

        for (AbstractClient client : clients) {
            List<Purchase> purchases = client.getPurchases().stream()
                    .filter(purchase -> purchase.getPurchaseDateTime().isAfter(startDate) && purchase.getPurchaseDateTime().isBefore(endDate))
                    .collect(Collectors.toList());
            if (!purchases.isEmpty()) {
                System.out.println("\nКлиент: " + client.getName());
                for (Purchase purchase : purchases) {
                    String formattedDateTime = purchase.getPurchaseDateTime().format(formatter);
                    double price = purchase.getPrice();
                    totalEarned += price;
                    List<Coupon> coupons = client.getCoupons();
                    String usedCoupons = coupons != null && !coupons.isEmpty() ? coupons.toString() : "Купоны не использовались";
                    System.out.println("  Покупка: " + purchase.getToy().getName() + ", Цена: " + price + ", Дата и время: " + formattedDateTime +
                            ", Купоны: " + usedCoupons);
                }
            }
        }
        System.out.println("\nОбщая сумма заработанных денег: " + totalEarned);
    }
}