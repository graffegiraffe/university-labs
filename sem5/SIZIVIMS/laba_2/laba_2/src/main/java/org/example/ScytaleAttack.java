package org.example;

public class ScytaleAttack {

    public static void bruteForceAttack(String ciphertext) {
        int length = ciphertext.length();

        System.out.println("Атака полным перебором размеров таблицы:");
        for (int rows = 1; rows <= length; rows++) {
            if (length % rows == 0) {
                int columns = length / rows;

                // Засекаем время выполнения расшифровки
                long startTime = System.nanoTime();

                // Проводим расшифровку
                String decryptedText = ScytaleCipher.decrypt(ciphertext, rows, columns);

                // Засекаем окончательное время выполнения
                long endTime = System.nanoTime();

                System.out.printf("Попытка с размером таблицы (%d x %d): %s\n", rows, columns, decryptedText);
                System.out.printf("Время выполнения: %.2f мс\n", (endTime - startTime) / 1_000_000.0);
            }
        }
    }

    public static void main(String[] args) {
        String ciphertext = "KINLASIXTTEXYHSXUETXSFGXHUIXANRX";

        // Запуск атаки
        bruteForceAttack(ciphertext);
    }
}