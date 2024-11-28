package by.rublevskaya.toystore.programmanager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ToyStoreManagerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void printMenu() {
        ToyStoreManager manager = new ToyStoreManager();
        manager.printMenu();
        String output = outContent.toString();
        assertFalse(output.isEmpty());
    }

    @Test
    public void printClients() {
        ToyStoreManager manager = new ToyStoreManager();
        manager.printClients();
        String output = outContent.toString();
        assertFalse(output.isEmpty());
    }

    @Test
    public void printSalespeople() {
        ToyStoreManager manager = new ToyStoreManager();
        manager.printSalespeople();
        String output = outContent.toString();
        assertFalse(output.isEmpty());
    }

    @Test
    public void printToys() {
        ToyStoreManager manager = new ToyStoreManager();
        manager.printToys();
        String output = outContent.toString();
        assertFalse(output.isEmpty());
    }

    @Test
    public void customerService() {
        ToyStoreManager manager = new ToyStoreManager();
        manager.customerService();
        String output = outContent.toString();
        assertTrue(output.contains("Казаченко Вадим"));
        assertTrue(output.contains("Курило Максим"));
        assertTrue(output.contains("Список клиентов:"));
    }
}