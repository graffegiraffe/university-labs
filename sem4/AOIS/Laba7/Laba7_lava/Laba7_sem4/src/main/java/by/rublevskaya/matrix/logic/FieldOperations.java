package by.rublevskaya.matrix.logic;

import by.rublevskaya.matrix.core.Matrix;
import by.rublevskaya.matrix.service.MatrixService;

import java.util.List;

public class FieldOperations {

    private final MatrixService matrixService;

    public FieldOperations(MatrixService matrixService) {
        this.matrixService = matrixService;
    }

    public void addFieldsByKey(Matrix matrix, int key) {
        System.out.println("Сложение полей для ключа V = " + Integer.toBinaryString(key) + ":");

        for (int i = 0; i < matrix.getSize(); i++) {
            List<Integer> word = matrixService.readWord(matrix, i);
            int v = (word.get(0) << 2) | (word.get(1) << 1) | word.get(2);
            if (v == key) {
                int a = (word.get(3) << 3) | (word.get(4) << 2) | (word.get(5) << 1) | word.get(6);
                int b = (word.get(7) << 3) | (word.get(8) << 2) | (word.get(9) << 1) | word.get(10);
                int sum = a + b;
                for (int j = 0; j < 5; j++) {
                    word.set(11 + j, (sum >> (4 - j)) & 1);
                }
                matrixService.writeWord(matrix, i, word);
                System.out.println("Обновлено слово #" + i);
            }
        }
    }
}