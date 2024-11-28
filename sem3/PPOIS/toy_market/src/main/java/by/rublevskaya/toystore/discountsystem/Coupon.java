package by.rublevskaya.toystore.discountsystem;
import by.rublevskaya.toystore.client.AbstractClient;

public class Coupon {
    private String code;
    private CouponType type;
    private double value;
    private AbstractClient client;

    public Coupon(String code, CouponType type, double value) {
        this.code = code;
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "code='" + code + '\'' +
                ", type=" + type +
                ", value=" + value +
                '}';
    }

    public double getDiscount(double price) {
        if (type == CouponType.FIXED) {
            return Math.min(value, price);
        } else if (type == CouponType.PERCENT) {
            return price * value / 100;
        } else {
            return 0;
        }
    }

    public void setType(CouponType type) {
        this.type = type;
    }

    public CouponType getType() {
        return type;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}