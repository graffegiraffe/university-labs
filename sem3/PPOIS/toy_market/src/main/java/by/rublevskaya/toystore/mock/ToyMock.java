package by.rublevskaya.toystore.mock;

import by.rublevskaya.toystore.toy.Toy;

public class ToyMock extends Toy {
    private String name;

    public ToyMock(String name, int batteryLevel) {
        super(name, batteryLevel);
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public double getPrice() {
        return 10.1;
    }
}

