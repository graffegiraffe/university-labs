package by.rublevskaya.toystore.toy.extendtoy;

import by.rublevskaya.toystore.toy.Toy;
import java.util.Random;

public class Car extends Toy {
    int batteryDrain;

    public Car(String name) {
        super(name, 100);
        Random random = new Random();
        this.batteryDrain = random.nextInt(25) + 1;
    }

    @Override
    public String toString() {
        return  "Машинка {" +
                " имя ='" + name + '\'' +
                ", уровень заряда=" + batteryLevel +
                ", уровень разряда=" + batteryDrain +
                '}';
    }

    public void moveForward() {
        if (batteryLevel >= batteryDrain) {
            System.out.println("Машинка " + name + " едет вперед");
            batteryLevel -= batteryDrain;
        } else {
            System.out.println("Недостаточно заряда для движения у машинки " + name);
        }
    }

    public void moveBackward() {
        if (batteryLevel >= batteryDrain) {
            System.out.println("Машинка " + name + " едет назад");
            batteryLevel -= batteryDrain;
        } else {
            System.out.println("Недостаточно заряда для движения у машинки " + name);
        }
    }

    public void turnRight() {
        if (batteryLevel >= batteryDrain) {
            System.out.println("Машинка " + name + " поворачивает направо");
            batteryLevel -= batteryDrain;
        } else {
            System.out.println("Недостаточно заряда для движения у машинки " + name);
        }
    }

    public void turnLeft() {
        if (batteryLevel >= batteryDrain) {
            System.out.println("Машинка " + name + " поворачивает налево");
            batteryLevel -= batteryDrain;
        } else {
            System.out.println("Недостаточно заряда для движения у машинки " + name);
        }
    }

    public void charge() {
        System.out.println("Машинка " + name + " заряжается...");
        batteryLevel = 100;
    }
}