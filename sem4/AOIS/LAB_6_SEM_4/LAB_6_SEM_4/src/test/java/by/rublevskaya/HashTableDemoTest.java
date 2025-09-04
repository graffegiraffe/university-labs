package by.rublevskaya;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class HashTableDemoTest {

    @Test
    void testMainMethodExecution() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        try {
            HashTableDemo.main(new String[]{});
            String output = outputStream.toString();
            assertTrue(output.contains("Запись вставлена: Амеба -> Простейший одноклеточный организм."));
            assertTrue(output.contains("Коэффициент заполнения:"));
            assertTrue(output.contains("Найдено: Функциональная единица наследственности."));
            assertTrue(output.contains("Состояние хеш-таблицы:"));
            assertTrue(output.contains("[пусто]"));
        } finally {
            System.setOut(originalOut);
        }
    }
}