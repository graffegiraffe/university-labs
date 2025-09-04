package by.rublevskaya.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    private HashTable hashTable;

    @BeforeEach
    void setUp() {
        hashTable = new HashTable();
    }

    @Test
    void testInsertAndSearch() {
        hashTable.insert("Амеба", "Простейший одноклеточный организм.");
        hashTable.insert("Бактерия", "Одноклеточный микроорганизм без ядра.");
        assertEquals("Найдено: Простейший одноклеточный организм.", hashTable.search("Амеба"));
        assertEquals("Найдено: Одноклеточный микроорганизм без ядра.", hashTable.search("Бактерия"));
        assertEquals("Запись с ID Некто не найдена.", hashTable.search("Некто"));
    }

    @Test
    void testInsertCollisionResolution() {
        hashTable.insert("Амеба", "Простой организм.");
        hashTable.insert("Бактерия", "Организм без ядра.");
        hashTable.insert("Вирус", "Паразит.");
        hashTable.insert("Митоз", "Процесс деления клетки.");
        assertEquals("Найдено: Простой организм.", hashTable.search("Амеба"));
        assertEquals("Найдено: Организм без ядра.", hashTable.search("Бактерия"));
        assertEquals("Найдено: Паразит.", hashTable.search("Вирус"));
        assertEquals("Найдено: Процесс деления клетки.", hashTable.search("Митоз"));
    }

    @Test
    void testDelete() {
        hashTable.insert("Амеба", "Простой организм.");
        hashTable.delete("Амеба");
        assertEquals("Запись с ID Амеба не найдена.", hashTable.search("Амеба"));
        hashTable.delete("Амеба");
        hashTable.insert("Амеба", "Новый организм.");
        assertEquals("Найдено: Новый организм.", hashTable.search("Амеба"));
    }

    @Test
    void testLoadFactor() {
        assertEquals(0.0, hashTable.loadFactor());
        hashTable.insert("Амеба", "Простой организм.");
        hashTable.insert("Бактерия", "Организм без ядра.");
        hashTable.insert("Вирус", "Паразит.");
        hashTable.insert("Зоология", "Наука о животных.");
        hashTable.insert("Клетка", "Единица жизни.");
        assertEquals(0.25, hashTable.loadFactor(), 0.01); // 5 из 20 ячеек заняты
    }

    @Test
    void testPrintTable() {
        hashTable.insert("Вирус", "Паразит.");
        assertDoesNotThrow(() -> hashTable.printTable());
    }

    @Test
    void testSearchInEmptyTable() {
        assertEquals("Запись с ID Некто не найдена.", hashTable.search("Некто"));
    }
}