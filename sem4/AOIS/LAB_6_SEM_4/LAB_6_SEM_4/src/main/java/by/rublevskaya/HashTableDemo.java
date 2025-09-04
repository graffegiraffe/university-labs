package by.rublevskaya;

import by.rublevskaya.service.HashTable;

public class HashTableDemo {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable();

        hashTable.insert("Амеба", "Простейший одноклеточный организм.");
        hashTable.insert("Бактерия", "Одноклеточный микроорганизм без ядра.");
        hashTable.insert("Вирус", "Немолекулярный паразит, размножающийся только в клетках.");
        hashTable.insert("Ген", "Функциональная единица наследственности.");
        hashTable.insert("ДНК", "Двухцепочечная молекула, хранящая генетическую информацию.");
        hashTable.insert("Эукариот", "Организм с ядром.");
        hashTable.insert("Зоология", "Наука, изучающая животных.");
        hashTable.insert("Клетка", "Основная структурная и функциональная единица жизни.");
        hashTable.insert("Лишайник", "Симбиотическая связь грибов и водорослей.");
        hashTable.insert("Митоз", "Процесс деления клетки.");
        hashTable.insert("Хромосома", "Структура, несущая генетический материал.");
        hashTable.insert("Фермент", "Белок, ускоряющий химические реакции.");
        hashTable.insert("Рибосома", "Органелла, синтезирующая белки.");
        hashTable.insert("Хлоропласт", "Органелла, ответственная за фотосинтез.");
        hashTable.insert("Цитоплазма", "Внутренняя среда клетки, заполненная жидкостью.");
        hashTable.insert("Мембрана", "Защитный слой клетки.");
        hashTable.insert("Цитология", "Раздел биологии, изучающий клетки.");
        hashTable.insert("Эндоплазма", "Часть цитоплазмы клетки.");

        hashTable.printTable();
        System.out.println("\nКоэффициент заполнения: " + hashTable.loadFactor());

        System.out.println(hashTable.search("Ген"));
        System.out.println(hashTable.search("Некто"));

        hashTable.delete("Ген");
        hashTable.printTable();
    }
}