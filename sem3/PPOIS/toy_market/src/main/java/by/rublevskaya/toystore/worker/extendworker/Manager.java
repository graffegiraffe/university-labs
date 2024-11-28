package by.rublevskaya.toystore.worker.extendworker;

import by.rublevskaya.toystore.worker.BaseWorker;
import by.rublevskaya.toystore.discountsystem.Coupon;
import by.rublevskaya.toystore.discountsystem.CouponType;

import java.util.ArrayList;
import java.util.List;

public class Manager extends BaseWorker {
    private List<Salesperson> salespeople;
    public Manager(String name, String position, String task, double salary, int age, String shift) {
        super(name, position, task, salary, age, shift);
        this.salespeople = new ArrayList<>();
    }

    @Override
    public void work() {
        System.out.println(name + " управляет магазином.");
    }

    public void createCoupon(String code, CouponType type, double value) {
        Coupon coupon = new Coupon(code, type, value);
        System.out.println("Создан купон: " + coupon);
    }

    public void addSalesperson(Salesperson salesperson) {
        if (salesperson == null) {
            System.out.println("Salesperson не может быть null");
        }
        this.salespeople.add(salesperson);
    }

    public void distributeCoupon(Coupon coupon, Salesperson salesperson) {
        if (coupon == null || salesperson == null) {
            System.out.println("Coupon и Salesperson не могут быть null");
        }
        salesperson.addCoupon(coupon);
        System.out.println("Купон " + coupon.getCode() + " выдан продавцу " + salesperson.getName());
    }

    public void fireSalesperson(Salesperson salesperson) {
        if (salesperson == null) {
            System.out.println("Salesperson не может быть ноль");
        }
        if (salespeople.remove(salesperson)) {
            System.out.println("Продавец " + salesperson.getName() + " уволен.");
        } else {
            System.out.println("Продавец " + salesperson.getName() + " не найден в списке.");
        }
    }

    public List<Salesperson> getSalespeople() {
        return new ArrayList<>(salespeople);
    }
}