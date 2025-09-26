package org.example;

public class ScytaleCipher {

    // Метод шифрования
    public static String encrypt(String plaintext, int rows, int columns) {
        plaintext = plaintext.replaceAll("\\s+", "").toUpperCase(); // Удаляем пробелы и приводим к верхнему регистру
        char[][] table = new char[rows][columns];
        int index = 0;

        // Заполнение таблицы по строкам
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (index < plaintext.length()) {
                    table[i][j] = plaintext.charAt(index++);
                } else {
                    table[i][j] = 'X'; // Заполнение пустых мест символом 'X'
                }
            }
        }

        // Считывание текста по столбцам для получения криптотекста
        StringBuilder ciphertext = new StringBuilder();
        for (int j = 0; j < columns; j++) {
            for (int i = 0; i < rows; i++) {
                ciphertext.append(table[i][j]);
            }
        }

        return ciphertext.toString();
    }

    // Метод расшифрования
    public static String decrypt(String ciphertext, int rows, int columns) {
        char[][] table = new char[rows][columns];
        int index = 0;

        // Заполнение таблицы по столбцам
        for (int j = 0; j < columns; j++) {
            for (int i = 0; i < rows; i++) {
                if (index < ciphertext.length()) {
                    table[i][j] = ciphertext.charAt(index++);
                }
            }
        }

        // Считывание текста по строкам для получения открытого текста
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                plaintext.append(table[i][j]);
            }
        }

        return plaintext.toString().replaceAll("X+$", ""); // Убираем символы-заполнители
    }

    public static void main(String[] args) {
        String plaintext = "Katyusha is the funniest girl";
        int rows = 4;
        int columns = 8;

        // Шифрование
        String ciphertext = encrypt(plaintext, rows, columns);
        System.out.println("Зашифрованный текст: " + ciphertext);

        // Расшифрование
        String decryptedText = decrypt(ciphertext, rows, columns);
        System.out.println("Расшифрованный текст: " + decryptedText);
    }
}
