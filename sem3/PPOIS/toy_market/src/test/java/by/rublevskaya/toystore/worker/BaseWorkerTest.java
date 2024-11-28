package by.rublevskaya.toystore.worker;

import by.rublevskaya.toystore.client.AbstractClient;
import by.rublevskaya.toystore.client.Purchase;
import by.rublevskaya.toystore.discountsystem.Coupon;
import by.rublevskaya.toystore.toy.Toy;
import by.rublevskaya.toystore.worker.extendworker.Salesperson;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BaseWorkerTest {

    private BaseWorker worker;
    private AbstractClient client;

    @Before
    public void setUp() {
        worker = new BaseWorker("Alice", "Worker", "General tasks", 1500.0, 30, "day shift") {
            @Override
            public void work() {
                System.out.println("Работаем!");
            }
        };

        client = new AbstractClient("Client1") {
            @Override
            public List<Purchase> getPurchases() {
                return null;
            }

            @Override
            public List<Coupon> getCoupons() {
                return null;
            }

            @Override
            public void buyToy(Toy toy, Salesperson cashier, double price) {
            }
        };
    }

    @Test
    public void showInfo() {
        worker.showInfo();
        assertTrue(true);
    }

    @Test
    public void work() {
        worker.work();
        assertTrue(true);
    }

    @Test
    public void addClient() {
        worker.addClient(client);
        assertEquals(1, worker.getClients().size());
        assertEquals(client, worker.getClients().get(0));
    }

    @Test
    public void removeClient() {
        worker.addClient(client);
        worker.removeClient(client);
        assertEquals(0, worker.getClients().size());
    }

    @Test
    public void getClients() {
        worker.addClient(client);
        List<AbstractClient> clients = worker.getClients();
        assertEquals(1, clients.size());
        assertEquals(client, clients.get(0));
    }

    @Test
    public void getName() {
        assertEquals("Alice", worker.getName());
    }

    @Test
    public void setName() {
        worker.setName("Bob");
        assertEquals("Bob", worker.getName());
    }

    @Test
    public void getAge() {
        assertEquals(30, worker.getAge());
    }

    @Test
    public void getTask() {
        assertEquals("General tasks", worker.getTask());
    }

    @Test
    public void getPosition() {
        assertEquals("Worker", worker.getPosition());
    }

    @Test
    public void setPosition() {
        worker.setPosition("Manager");
        assertEquals("Manager", worker.getPosition());
    }
}