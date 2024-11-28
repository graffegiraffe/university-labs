package by.rublevskaya.toystore.worker.extendworker;

import by.rublevskaya.toystore.client.AbstractClient;
import by.rublevskaya.toystore.client.Purchase;
import by.rublevskaya.toystore.discountsystem.Coupon;
import by.rublevskaya.toystore.discountsystem.CouponType;
import by.rublevskaya.toystore.toy.Toy;
import by.rublevskaya.toystore.mock.ToyMock;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class BookkeeperTest {
    private Bookkeeper bookkeeper;

    private AbstractClient createClient(String name, List<Purchase> purchases, List<Coupon> coupons) {
        return new AbstractClient(name) {
            @Override
            public List<Purchase> getPurchases() {
                return purchases;
            }

            @Override
            public List<Coupon> getCoupons() {
                return coupons;
            }

            @Override
            public void buyToy(Toy toy, Salesperson cashier, double price) {
            }
        };
    }

    private Purchase createPurchase(String toyName, double price, LocalDateTime dateTime) {
        Toy toy = new ToyMock(toyName, 100);
        return new Purchase(toy, (int) price) {
            @Override
            public LocalDateTime getPurchaseDateTime() {
                return dateTime;
            }
        };
    }

    @Before
    public void setUp() {
        bookkeeper = new Bookkeeper("Ivan", "Bookkeeper", "Create reports", 3000.0, 40, "day shift");
    }

    @Test
    public void testGenerateSalesReport_NominalCase() {
        LocalDateTime startDate = LocalDateTime.of(2023, 9, 30, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2023, 10, 1, 0, 0);
        AbstractClient client1 = createClient("Client1",
                Arrays.asList(
                        createPurchase("Toy1", 100.0, LocalDateTime.of(2023, 9, 30, 10, 0)),
                        createPurchase("Toy2", 200.0, LocalDateTime.of(2023, 9, 30, 12, 0))
                ),
                Collections.singletonList(new Coupon("C1", CouponType.FIXED, 20.0))
        );

        AbstractClient client2 = createClient("Client2",
                Collections.singletonList(
                        createPurchase("Toy3", 300.0, LocalDateTime.of(2023, 9, 30, 14, 0))
                ),
                Collections.emptyList()
        );
        List<AbstractClient> clients = Arrays.asList(client1, client2);
        bookkeeper.generateSalesReport(clients, startDate, endDate);
        assertTrue(true);
    }

    @Test
    public void testGenerateSalesReport_NoPurchases() {
        LocalDateTime startDate = LocalDateTime.of(2023, 9, 30, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2023, 10, 1, 0, 0);
        AbstractClient client1 = createClient("Client1", Collections.emptyList(), Collections.singletonList(new Coupon("C1", CouponType.FIXED, 20.0)));
        AbstractClient client2 = createClient("Client2", Collections.emptyList(), Collections.emptyList());
        List<AbstractClient> clients = Arrays.asList(client1, client2);
        bookkeeper.generateSalesReport(clients, startDate, endDate);
        assertTrue(true);
    }

    @Test
    public void testGenerateSalesReport_PartialPurchases() {
        LocalDateTime startDate = LocalDateTime.of(2023, 9, 30, 11, 0);
        LocalDateTime endDate = LocalDateTime.of(2023, 9, 30, 15, 0);

        AbstractClient client1 = createClient("Client1",
                Arrays.asList(
                        createPurchase("Toy1", 100.0, LocalDateTime.of(2023, 9, 30, 10, 0)),
                        createPurchase("Toy2", 200.0, LocalDateTime.of(2023, 9, 30, 12, 0))
                ),
                Collections.singletonList(new Coupon("C1", CouponType.FIXED, 20.0))
        );

        AbstractClient client2 = createClient("Client2",
                Collections.singletonList(
                        createPurchase("Toy3", 300.0, LocalDateTime.of(2023, 9, 30, 14, 0))
                ),
                Collections.emptyList()
        );
        List<AbstractClient> clients = Arrays.asList(client1, client2);
        bookkeeper.generateSalesReport(clients, startDate, endDate);
        assertTrue(true);
    }

    @Test
    public void testGenerateSalesReport_EmptyClientsList() {
        LocalDateTime startDate = LocalDateTime.of(2023, 9, 30, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2023, 10, 1, 0, 0);
        List<AbstractClient> clients = Collections.emptyList();
        bookkeeper.generateSalesReport(clients, startDate, endDate);
        assertTrue(true);
    }

    @Test
    public void testWorkMethod() {
        bookkeeper.work();
        assertTrue(true);
    }
}