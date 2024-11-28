package by.rublevskaya.toystore.programmanager;

import by.rublevskaya.toystore.client.AbstractClient;
import by.rublevskaya.toystore.client.extendclient.LegalClient;
import by.rublevskaya.toystore.client.extendclient.PhysicalClient;
import by.rublevskaya.toystore.discountsystem.Coupon;
import by.rublevskaya.toystore.discountsystem.CouponType;
import by.rublevskaya.toystore.toy.Toy;
import by.rublevskaya.toystore.toy.extendtoy.Car;
import by.rublevskaya.toystore.toy.extendtoy.CubeRubic;
import by.rublevskaya.toystore.toy.extendtoy.Doll;
import by.rublevskaya.toystore.toy.extendtoy.FifteenPuzzle;
import by.rublevskaya.toystore.worker.extendworker.Bookkeeper;
import by.rublevskaya.toystore.worker.extendworker.Manager;
import by.rublevskaya.toystore.worker.extendworker.Salesperson;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToyStoreManager {
    protected static final Scanner scanner = new Scanner(System.in);
    protected static final List<AbstractClient> clients = new ArrayList<>();
    protected static final List<Salesperson> salespeople = new ArrayList<>();
    protected static final List<Toy> toys = new ArrayList<>();
    private static Manager manager;
    private static Bookkeeper bookkeeper;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public void initializeData() {
        manager = new Manager("Пикта Игнат", "Менеджер", "Управление магазином", 1500, 18, "Дневная");
        salespeople.add(new Salesperson("Кадиков Павел", "Продавец", "Продажа игрушек", 1000, 18, "Дневная", 0.05, 10));
        salespeople.add(new Salesperson("Банкевич Яна", "Продавец", "Продажа игрушек", 1000, 18, "Вечерняя", 0.05, 5));
        clients.add(new PhysicalClient("Римонт Елисей"));
        clients.add(new LegalClient("ОАО \"Цветочки\""));
        toys.add(new Car("Гоночная машина"));
        toys.add(new Doll("Кукла"));
        toys.add(new CubeRubic("Кубик Рубика"));
        toys.add(new FifteenPuzzle("Пятнашки", 4));
        bookkeeper = new Bookkeeper("Сергиевич Дарья", "Бухгалтер", "Ведение отчетности", 1200, 30, "Дневная");
    }

    public void printMenu() {
        System.out.println("Меню:");
        System.out.println("1. Купить игрушку");
        System.out.println("2. Обменять игрушки");
        System.out.println("3. Использовать бонусные баллы");
        System.out.println("4. Применить купон");
        System.out.println("5. Создать отчет по продажам");
        System.out.println("6. Управление сотрудниками");
        System.out.println("7. Управление купонами");
        System.out.println("8. Играть с игрушками");
        System.out.println("9. Обслуживание клиентов");
        System.out.println("10. Выход из программы");
        System.out.print("Выберите пункт меню: ");
    }

    public void buyToy() {
        printClients();
        System.out.print("Выберите клиента: ");
        int clientIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (clientIndex < 0 || clientIndex >= clients.size()) {
            System.out.println("Неверный индекс клиента.");
            return;
        }

        printSalespeople();
        System.out.print("Выберите продавца: ");
        int salespersonIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (salespersonIndex < 0 || salespersonIndex >= salespeople.size()) {
            System.out.println("Неверный индекс продавца.");
            return;
        }

        printToys();
        System.out.print("Выберите игрушку: ");
        int toyIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (toyIndex < 0 || toyIndex >= toys.size()) {
            System.out.println("Неверный индекс игрушки.");
            return;
        }

        System.out.print("Введите цену: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        AbstractClient client = clients.get(clientIndex);
        Salesperson salesperson = salespeople.get(salespersonIndex);
        Toy toy = toys.get(toyIndex);

        if (salesperson.getToysInStock() > 0) {
            client.buyToy(toy, salesperson, price);
            toys.remove(toyIndex);
        } else {
            System.out.println("Игрушки нет в наличии у продавца " + salesperson.getName());
        }
    }

    public void exchangeToys() {
        printClients();
        System.out.print("Выберите клиента: ");
        int clientIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (clientIndex < 0 || clientIndex >= clients.size()) {
            System.out.println("Неверный индекс клиента.");
            return;
        }

        AbstractClient client = clients.get(clientIndex);
        System.out.print("Введите количество игрушек для обмена: ");
        int numToysToExchange = scanner.nextInt();
        scanner.nextLine();

        if (numToysToExchange <= 0 || numToysToExchange > client.getToys().size()) {
            System.out.println("Неверное количество игрушек для обмена.");
            return;
        }

        List<Toy> oldToys = new ArrayList<>();
        for (int i = 0; i < numToysToExchange; i++) {
            System.out.println("Игрушки клиента: " + client.getToys());
            System.out.print("Выберите игрушку для обмена (номер в списке): ");
            int toyIndex = scanner.nextInt() - 1;
            scanner.nextLine();

            if (toyIndex < 0 || toyIndex >= client.getToys().size()) {
                System.out.println("Неверный индекс игрушки.");
                return;
            }
            oldToys.add(client.getToys().get(toyIndex));
        }

        printToys();
        System.out.print("Выберите новую игрушку: ");
        int newToyIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (newToyIndex < 0 || newToyIndex >= toys.size()) {
            System.out.println("Неверный индекс новой игрушки.");
            return;
        }

        printSalespeople();
        System.out.print("Выберите продавца: ");
        int salespersonIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (salespersonIndex < 0 || salespersonIndex >= salespeople.size()) {
            System.out.println("Неверный индекс продавца.");
            return;
        }

        Toy newToy = toys.get(newToyIndex);
        Salesperson salesperson = salespeople.get(salespersonIndex);
        client.exchangeToys(oldToys, newToy, salesperson);
        toys.remove(newToyIndex);
    }

    public void useBonusPoints() {
        printClients();
        System.out.print("Выберите клиента: ");
        int clientIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (clientIndex < 0 || clientIndex >= clients.size()) {
            System.out.println("Неверный индекс клиента.");
            return;
        }

        AbstractClient client = clients.get(clientIndex);
        System.out.print("Введите количество бонусных баллов для использования: ");
        double pointsToUse = scanner.nextDouble();
        client.useBonusPoints(pointsToUse);
    }

    public void applyCoupon() {
        printClients();
        System.out.print("Выберите клиента: ");
        int clientIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (clientIndex < 0 || clientIndex >= clients.size()) {
            System.out.println("Неверный индекс клиента.");
            return;
        }

        AbstractClient client = clients.get(clientIndex);
        System.out.print("Введите код купона: ");
        String couponCode = scanner.nextLine();
        System.out.print("Введите тип купона (FIXED или PERCENT): ");
        String couponType = scanner.nextLine().toUpperCase();
        System.out.print("Введите значение купона: ");
        double couponValue = scanner.nextDouble();

        if (couponType.equals("FIXED") || couponType.equals("PERCENT")) {
            Coupon coupon = new Coupon(couponCode, CouponType.valueOf(couponType), couponValue);
            if (client instanceof PhysicalClient) {
                ((PhysicalClient) client).setCoupon(coupon);
            } else if (client instanceof LegalClient) {
                ((LegalClient) client).addCoupon(coupon);
            }
            System.out.println("Купон успешно применен");
        } else {
            System.out.println("Неверный тип купона");
        }
    }

    public void generateSalesReport() {
        System.out.print("Введите начальную дату отчета (yyyy-MM-dd HH:mm:ss): ");
        String startDateString = scanner.nextLine();
        System.out.print("Введите конечную дату отчета (yyyy-MM-dd HH:mm:ss): ");
        String endDateString = scanner.nextLine();
        try {
            LocalDateTime startDate = LocalDateTime.parse(startDateString, formatter);
            LocalDateTime endDate = LocalDateTime.parse(endDateString, formatter);
            bookkeeper.generateSalesReport(clients, startDate, endDate);
        } catch (Exception e) {
            System.out.println("Неверный формат даты. Используйте формат yyyy-MM-dd HH:mm:ss");
        }
    }

    public void manageEmployees() {
        System.out.println("1. Нанять продавца");
        System.out.println("2. Уволить продавца");
        System.out.println("3. Показать информацию о продавцах");
        System.out.print("Выберите действие: ");
        int action = scanner.nextInt();
        scanner.nextLine();

        switch (action) {
            case 1:
                hireSalesperson();
                break;
            case 2:
                fireSalesperson();
                break;
            case 3:
                showSalespeopleInfo();
                break;
            default:
                System.out.println("Неверный выбор. Попробуйте еще раз.");
        }
    }

    public void hireSalesperson() {
        System.out.print("Введите имя продавца: ");
        String name = scanner.nextLine();
        System.out.print("Введите должность продавца: ");
        String position = scanner.nextLine();
        System.out.print("Введите описание задачи: ");
        String task = scanner.nextLine();
        System.out.print("Введите зарплату: ");
        double salary = Double.parseDouble(scanner.nextLine());
        System.out.print("Введите возраст: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Введите смену: ");
        String shift = scanner.nextLine();
        System.out.print("Введите комиссию: ");
        double commissionRate = Double.parseDouble(scanner.nextLine());
        System.out.print("Введите количество игрушек на складе: ");
        int toysInStock = Integer.parseInt(scanner.nextLine());

        Salesperson salesperson = new Salesperson(name, position, task, salary, age, shift, commissionRate, toysInStock);
        salespeople.add(salesperson);
        System.out.println("Продавец " + name + " нанят.");
    }

    public void fireSalesperson() {
        System.out.print("Введите имя продавца для увольнения: ");
        String name = scanner.nextLine();

        Salesperson salespersonToRemove = null;
        for (Salesperson salesperson : salespeople) {
            if (salesperson.getName().equals(name)) {
                salespersonToRemove = salesperson;
                break;
            }
        }

        if (salespersonToRemove != null) {
            salespeople.remove(salespersonToRemove);
            System.out.println("Продавец " + name + " уволен.");
        } else {
            System.out.println("Продавец с именем " + name + " не найден.");
        }
    }

    public void showSalespeopleInfo() {
        System.out.println("Список продавцов:");
        for (Salesperson salesperson : salespeople) {
            System.out.printf("Имя: %s, Должность: %s, Описание: %s, Зарплата: %.2f, Возраст: %d, Смена: %s, Комиссия: %.2f, Количество игрушек на складе: %d%n",
                    salesperson.getName(), salesperson.getPosition(), salesperson.getTask(), salesperson.getSalary(),
                    salesperson.getAge(), salesperson.getShift(), salesperson.getCommissionRate(), salesperson.getToysInStock());
        }
    }

    public void manageCoupons() {
        System.out.println("1. Создать купон");
        System.out.println("2. Распределить купон");
        System.out.print("Выберите действие: ");
        int action = scanner.nextInt();
        scanner.nextLine();

        switch (action) {
            case 1:
                createCoupon();
                break;
            case 2:
                distributeCoupon();
                break;
            default:
                System.out.println("Неверный выбор. Попробуйте еще раз.");
        }
    }

    public void createCoupon() {
        System.out.print("Введите код купона: ");
        String code = scanner.nextLine();

        System.out.print("Введите тип купона (FIXED или PERCENT): ");
        String typeString = scanner.nextLine().toUpperCase();

        if (!(typeString.equals("FIXED") || typeString.equals("PERCENT"))) {
            System.out.println("Неверный тип купона.");
            return;
        }

        CouponType type = CouponType.valueOf(typeString);
        System.out.print("Введите значение купона: ");
        double value = scanner.nextDouble();
        scanner.nextLine();
        manager.createCoupon(code, type, value);
    }

    public void distributeCoupon() {
        printSalespeople();
        System.out.print("Выберите продавца для выдачи купона: ");
        int salespersonIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (salespersonIndex < 0 || salespersonIndex >= salespeople.size()) {
            System.out.println("Неверный индекс продавца.");
            return;
        }

        System.out.print("Введите код купона: ");
        String code = scanner.nextLine();
        System.out.print("Введите тип купона (FIXED или PERCENT): ");
        String typeString = scanner.nextLine().toUpperCase();

        if (!(typeString.equals("FIXED") || typeString.equals("PERCENT"))) {
            System.out.println("Неверный тип купона.");
            return;
        }

        CouponType type = CouponType.valueOf(typeString);
        System.out.print("Введите значение купона: ");
        double value = scanner.nextDouble();
        scanner.nextLine();
        Coupon coupon = new Coupon(code, type, value);
        Salesperson salesperson = salespeople.get(salespersonIndex);
        manager.distributeCoupon(coupon, salesperson);
    }

    public void playWithToys() {
        System.out.println("Выберите игрушку для игры:");
        for (int i = 0; i < toys.size(); i++) {
            System.out.println((i + 1) + ". " + toys.get(i).getName());
        }

        int choiceIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (choiceIndex >= 0 && choiceIndex < toys.size()) {
            Toy toy = toys.get(choiceIndex);
            playWithToy(toy);
        } else {
            System.out.println("Неверный выбор");
        }
    }

    public void playWithToy(Toy toy) {
        if (toy instanceof Car) {
            playWithCar((Car) toy);
        } else if (toy instanceof Doll) {
            playWithDoll((Doll) toy);
        } else if (toy instanceof CubeRubic) {
            playWithCubeRubic((CubeRubic) toy);
        } else if (toy instanceof FifteenPuzzle) {
            playWithFifteenPuzzle((FifteenPuzzle) toy);
        } else {
            toy.use();
        }
    }

    private void playWithCar(Car car) {
        car.moveForward();
        car.moveBackward();
        car.turnLeft();
        car.turnRight();
        car.charge();
    }

    private void playWithDoll(Doll doll) {
        doll.turnOn();
        doll.use();
        doll.turnOff();
    }

    private void playWithCubeRubic(CubeRubic cube) {
        cube.playCubic();
    }

    private void playWithFifteenPuzzle(FifteenPuzzle puzzle) {
        System.out.println("Вы играете в пятнашки!");
        puzzle.printBoard();
        while (!puzzle.decisionMade()) {
            System.out.print("Введите номер плитки для перемещения (0 для выхода): ");
            int tile = scanner.nextInt();
            scanner.nextLine();
            if (tile == 0) {
                break;
            }
            if (!puzzle.moveSpecificTile(tile)) {
                System.out.println("Невозможно переместить плитку " + tile);
            }
            puzzle.printBoard();
        }
        if (puzzle.decisionMade()) {
            System.out.println("Поздравляю, вы выиграли!");
        }
    }

    public void customerService() {
        AbstractClient client1 = new PhysicalClient("Казаченко Вадим");
        AbstractClient client2 = new PhysicalClient("Курило Максим");
        Salesperson salesperson = new Salesperson ("Сотников Артем", "Старший продавец","Продавать ирушки",1800.8, 18,"Дневная",7.7,98);
        salesperson.serveClient(client1);
        salesperson.serveClient(client2);
        salesperson.listClients();
        salesperson.finishServingClient(client1);
        salesperson.listClients();
    }

    public void printClients() {
        System.out.println("Список клиентов:");
        for (AbstractClient client : clients) {
            System.out.printf("Имя: %s%n", client.getName());
        }
    }

    public void printSalespeople() {
        System.out.println("Список продавцов:");
        for (Salesperson salesperson : salespeople) {
            System.out.printf("Имя: %s%n", salesperson.getName());
        }
    }

    public void printToys() {
        System.out.println("Список игрушек:");
        for (Toy toy : toys) {
            System.out.println(toy);
        }
    }
}