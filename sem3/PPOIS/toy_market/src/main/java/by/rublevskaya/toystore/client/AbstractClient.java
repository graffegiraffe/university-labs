package by.rublevskaya.toystore.client;

import by.rublevskaya.toystore.discountsystem.Coupon;
import by.rublevskaya.toystore.toy.Toy;
import by.rublevskaya.toystore.worker.extendworker.Salesperson;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractClient {
    protected String name;
    protected List<Toy> toys;
    protected double bonusPoints;
    protected List<Coupon> coupons;
    private List<Purchase> purchases;

    public AbstractClient(String name) {
        this.name = name;
        this.toys = new ArrayList<>();
        this.bonusPoints = 0;
        this.coupons = new ArrayList<>();
        this.purchases = new ArrayList<>();
    }

    @Override
    public String toString() {
        return " Клиент {" +
                " имя ='" + name + '\'' +
                ", покупки =" + purchases +
                ", игрушки =" + toys +
                ", бонусные баллы =" + bonusPoints +
                ", купоны =" + coupons +
                '}';
    }

    public void addPurchase(Purchase purchase) {
        this.purchases.add(purchase);
    }

    public abstract void buyToy(Toy toy, Salesperson cashier, double price);

    public void exchangeToys(List<Toy> oldToys, Toy newToy, Salesperson cashier) {
        if (oldToys == null || oldToys.isEmpty() || newToy == null || cashier == null) {
            System.out.println("Ошибка обмена: неверные данные.");
            return;
        }

        if (!toys.containsAll(oldToys)) {
            System.out.println("Ошибка обмена: у клиента нет указанных игрушек.");
            return;
        }

        if (!oldToys.get(0).getClass().equals(newToy.getClass())) {
            System.out.println("Ошибка обмена: можно обменять только игрушки одного типа.");
            return;
        }

        if (oldToys.size() != 3) {
            System.out.println("Ошибка обмена: необходимо предоставить 3 старые игрушки для обмена.");
            return;
        }
        toys.removeAll(oldToys);
        toys.add(newToy);
        System.out.println(name + " обменял игрушки на " + newToy.getName());
    }

    public void addBonusPoints(double points) {
        this.bonusPoints += points;
        System.out.println(name + ", начислено бонусных баллов: " + points);
    }

    public void useBonusPoints(double points) {
        if (bonusPoints >= points) {
            bonusPoints -= points;
            System.out.println(name + ", списано бонусных баллов: " + points +
                    ". Остаток: " + bonusPoints);
        } else {
            System.out.println("Недостаточно бонусных баллов у клиента " + name);
        }
    }

    public void addCoupon(Coupon coupon) {
        coupons.add(coupon);
        System.out.println(name + ", добавлен купон: " + coupon);
    }

    public void useCoupon(Coupon coupon, double price) {
        if (coupons.contains(coupon)) {
            double discount = coupon.getDiscount(price);
            System.out.println(name + ", применен купон на скидку " + discount);
            coupons.remove(coupon);
        } else {
            System.out.println("Купон не найден у клиента " + name);
        }
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    public List<Toy> getToys() {
        return toys;
    }

    public void setToys(List<Toy> toys) {
        this.toys = toys;
    }

    public double getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(double bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}