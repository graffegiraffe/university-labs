package by.rublevskaya.toystore.toy;

public abstract class Toy implements ToyInterface {
    public String name;
    protected int batteryLevel;

    public Toy(String name, int batteryLevel) {
        this.name = name;
        this.batteryLevel = batteryLevel;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getBatteryLevel() {
        return batteryLevel;
    }

    @Override
    public void use() {
        System.out.println("Используется игрушка " + name);
    }

    public void replaceBattery() {
        System.out.println("Батарейка игрушки " + name + " заменена");
        batteryLevel = 100;
    }
}