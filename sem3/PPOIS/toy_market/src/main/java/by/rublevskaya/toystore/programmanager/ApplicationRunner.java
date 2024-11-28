package by.rublevskaya.toystore.programmanager;

public class ApplicationRunner {
    private static final ToyStoreManager toyStoreManager = new ToyStoreManager();

    public static void main(String[] args) {
        toyStoreManager.initializeData();

        while (true) {
            toyStoreManager.printMenu();
            int choice = ToyStoreManager.scanner.nextInt();
            ToyStoreManager.scanner.nextLine();

            switch (choice) {
                case 1:
                    toyStoreManager.buyToy();
                    break;
                case 2:
                    toyStoreManager.exchangeToys();
                    break;
                case 3:
                    toyStoreManager.useBonusPoints();
                    break;
                case 4:
                    toyStoreManager.applyCoupon();
                    break;
                case 5:
                    toyStoreManager.generateSalesReport();
                    break;
                case 6:
                    toyStoreManager.manageEmployees();
                    break;
                case 7:
                    toyStoreManager.manageCoupons();
                    break;
                case 8:
                    toyStoreManager.playWithToys();
                    break;
                case 9:
                    toyStoreManager.customerService();
                    break;
                case 10:
                    System.out.println("Выход из программы.");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте еще раз.");
            }
        }
    }
}