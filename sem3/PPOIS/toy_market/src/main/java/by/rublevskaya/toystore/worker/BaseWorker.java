package by.rublevskaya.toystore.worker;

import by.rublevskaya.toystore.client.AbstractClient;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseWorker {
    private List<AbstractClient> clients;
    protected String name;
    protected String position;
    protected String task;
    protected double salary;
    protected int age;
    protected String shift;

    public BaseWorker(String name, String position, String task, double salary, int age, String shift) {
        this.name = name;
        this.position = position;
        this.task = task;
        this.salary = salary;
        this.age = age;
        this.shift = shift;
        clients = new ArrayList<>();
    }

    public abstract void work();

    public void addClient(AbstractClient client) {
        clients.add(client);
    }

    public void removeClient(AbstractClient client) {
        clients.remove(client);
    }

    public void showInfo() {
        System.out.println("Имя: " + name);
        System.out.println("Должность: " + position);
        System.out.println("Задачи: " + task);
        System.out.println("Зарплата: " + salary);
        System.out.println("Возраст: " + age);
        System.out.println("Смена: " + shift);
    }

    public List<AbstractClient> getClients() {
        return new ArrayList<>(clients);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getTask() {
        return task;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
