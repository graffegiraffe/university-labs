package by.rublevskaya.toystore.toy.extendtoy;

import by.rublevskaya.toystore.toy.Toy;

public class Doll extends Toy {
    private boolean isOn;

    public Doll(String name) {
        super(name, 100);
        this.isOn = false;
    }

    @Override
    public void use() {
        if (isOn) {
            System.out.println("Играем с куклой " + name);
        } else {
            System.out.println("Кукла " + name + " выключена. Включите ее, чтобы играть.");
        }
    }

    @Override
    public String toString() {
        return "Кукла {" +
                " имя ='" + name + '\'' +
                ", уровень заряда =" + batteryLevel +
                ", включена ли =" + isOn +
                '}';
    }

    public void turnOn() {
        if (!isOn) {
            System.out.println("Кукла " + name + " включена");
            isOn = true;
        } else {
            System.out.println("Кукла " + name + " уже включена");
        }
    }

    public void turnOff() {
        if (isOn) {
            System.out.println("Кукла " + name + " выключена");
            isOn = false;
        } else {
            System.out.println("Кукла " + name + " уже выключена");
        }
    }

    public boolean isOn() {
        return isOn;
    }
}
