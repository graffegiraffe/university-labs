package by.rublevskaya.toystore.client.extendclient;

import by.rublevskaya.toystore.client.AbstractClient;
import by.rublevskaya.toystore.client.Purchase;
import by.rublevskaya.toystore.discountsystem.Coupon;
import by.rublevskaya.toystore.toy.Toy;
import by.rublevskaya.toystore.worker.extendworker.Salesperson;

import java.util.ArrayList;
import java.util.List;

public class LegalClient extends AbstractClient {
    private List<Coupon> coupons;

    public LegalClient(String name) {
        super(name);
    }

    @Override
    public void buyToy(Toy toy, Salesperson cashier, double price) {
        if (toy == null || cashier == null) {
            System.out.println("Неверные аргументы: toy или cashier не могут быть null");
        }
        double discount = 0;
        if (coupons != null) {
            for (Coupon coupon : coupons) {
                if (coupon != null) {
                    double couponDiscount = coupon.getDiscount(price - discount);
                    discount += couponDiscount;
                }
            }
        }

        double finalPrice = price - discount;
        if (finalPrice < 0) {
            finalPrice = 0;
        }
        Purchase purchase = new Purchase(toy, finalPrice);
        addPurchase(purchase);
        cashier.processPurchase(this, purchase);
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    public void addCoupon(Coupon coupon) {
        if (coupons == null) {
            coupons = new ArrayList<>();
        }
        coupons.add(coupon);
    }
}