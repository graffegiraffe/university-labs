package org.example;

import java.util.*;
import java.text.DecimalFormat;

public class Main {

    // Функция генерации случайной строки
    public static String generateRandomString(int length, String alphabet) {
        StringBuilder result = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            char randomChar = alphabet.charAt(random.nextInt(alphabet.length()));
            result.append(randomChar);
        }
        return result.toString();
    }

    // Функция для вычисления частотного распределения символов
    public static Map<Character, Integer> calculateFrequency(String str) {
        Map<Character, Integer> frequency = new HashMap<>();
        for (char ch : str.toCharArray()) {
            frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
        }
        return frequency;
    }

    // Функция для визуализации частотного распределения
    public static void visualizeFrequency(Map<Character, Integer> frequency) {
        System.out.println("\nЧастотное распределение символов:");
        for (Map.Entry<Character, Integer> entry : frequency.entrySet()) {
            System.out.print(entry.getKey() + ": ");
            for (int i = 0; i < entry.getValue(); i++) {
                System.out.print("*");
            }
            System.out.println(" (" + entry.getValue() + ")");
        }
    }

    public static void generateCombinations(String alphabet, String password) {
        int length = password.length();
        int alphabetSize = alphabet.length();

        // Массив индексов для комбинаций
        int[] indices = new int[length];
        long totalCombinations = (long) Math.pow(alphabetSize, length);

        long startTime = System.nanoTime();

        for (long i = 0; i < totalCombinations; i++) {
            StringBuilder combination = new StringBuilder(length);

            // Создаем строку из текущих индексов
            for (int j = 0; j < length; j++) {
                combination.append(alphabet.charAt(indices[j]));
            }

            // Проверяем, соответствует ли текущая строка паролю
            if (combination.toString().equals(password)) {
                long endTime = System.nanoTime();

                // Вычисляем время в секундах
                double timeTaken = (endTime - startTime) / 1e9;

                // Форматируем время
                DecimalFormat df = new DecimalFormat("#.######");
                System.out.println("Пароль подобран: " + combination);
                System.out.println("Время, затраченное на подбор: " + df.format(timeTaken) + " секунд");
                return;
            }

            // Обновляем индексы (аналог системы счисления с основанием alphabetSize)
            for (int j = length - 1; j >= 0; j--) {
                indices[j]++;
                if (indices[j] < alphabetSize) {
                    break;
                }
                indices[j] = 0; // Перенос "разряда"
            }
        }

        System.out.println("Пароль не удалось подобрать.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

        // Ввод длины строки
        System.out.print("Введите длину строки: ");
        int stringLength = scanner.nextInt();

        // Генерация случайной строки
        String randomString = generateRandomString(stringLength, alphabet);
        System.out.println("\nСгенерированная строка: " + randomString);

        // Подсчет частот символов и визуализация
        Map<Character, Integer> frequency = calculateFrequency(randomString);
        visualizeFrequency(frequency);

        // Ввод пароля для проверки времени подбора
        System.out.print("\nВведите пароль для вычисления среднего времени подбора: ");
        scanner.nextLine(); // для очистки буфера
        String password = scanner.nextLine();

        // Подбор пароля и вывод времени
        generateCombinations(alphabet, password);
    }
}
