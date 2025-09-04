package by.rublevskaya.service;


import by.rublevskaya.model.Entry;

import java.util.Arrays;

public class HashTable {
    private static final int TABLE_SIZE = 20;
    private final Entry[] table;

    public HashTable() {
        this.table = new Entry[TABLE_SIZE];
        Arrays.fill(table, null);
    }

    public void insert(String id, String data) {
        int key = HashFunction.computeKey(id);
        int hash = HashFunction.hashFunction(key);
        int i = 0;
        boolean inserted = false;
        while (i < TABLE_SIZE) {
            int newHash = (hash + i * i) % TABLE_SIZE;
            if (table[newHash] == null || table[newHash].isDeleted()) {
                table[newHash] = new Entry(id, data);
                System.out.println("Запись вставлена: " + id + " -> " + data);
                inserted = true;
                break;
            }
            i++;
        }
        if (!inserted) {
            System.out.println("Ошибка: Таблица переполнена. Запись не может быть добавлена.");
        }
    }

    public String search(String id) {
        int key = HashFunction.computeKey(id);
        int hash = HashFunction.hashFunction(key);
        int i = 0;
        while (i < TABLE_SIZE) {
            int newHash = (hash + i * i) % TABLE_SIZE;
            Entry entry = table[newHash];
            if (entry == null) {
                break;
            }
            if (entry.getId().equals(id) && !entry.isDeleted()) {
                return "Найдено: " + entry.getData();
            }
            i++;
        }
        return "Запись с ID " + id + " не найдена.";
    }

    public void delete(String id) {
        int key = HashFunction.computeKey(id);
        int hash = HashFunction.hashFunction(key);
        int i = 0;
        while (i < TABLE_SIZE) {
            int newHash = (hash + i * i) % TABLE_SIZE;
            Entry entry = table[newHash];
            if (entry == null) {
                System.out.println("Запись с ID " + id + " не найдена.");
                return;
            }
            if (entry.getId().equals(id) && !entry.isDeleted()) {
                entry.setDeleted(true);
                System.out.println("Запись с ID " + id + " удалена.");
                return;
            }
            i++;
        }
        System.out.println("Запись с ID " + id + " не найдена.");
    }

    public void printTable() {
        System.out.println("\nСостояние хеш-таблицы:");
        for (int i = 0; i < TABLE_SIZE; i++) {
            if (table[i] == null) {
                System.out.println(i + ": [пусто]");
            } else {
                System.out.println(i + ": " + table[i]);
            }
        }
    }

    public double loadFactor() {
        int count = 0;
        for (Entry entry : table) {
            if (entry != null && !entry.isDeleted()) {
                count++;
            }
        }
        return (double) count / TABLE_SIZE;
    }
}