package by.rublevskaya.matrix.logic;

import by.rublevskaya.matrix.core.Matrix;
import by.rublevskaya.matrix.service.MatrixService;

import java.util.List;

public class RangeSearch {

    private final MatrixService matrixService;

    public RangeSearch(MatrixService matrixService) {
        this.matrixService = matrixService;
    }

    public void findWordsInRange(Matrix matrix, int min, int max) {
        System.out.println("Слова в диапазоне [" + min + ", " + max + "]:");
        for (int i = 0; i < matrix.getSize(); i++) {
            List<Integer> word = matrixService.readWord(matrix, i);
            int wordValue = toDecimal(word);
            if (wordValue >= min && wordValue <= max) {
                System.out.println("Слово #" + i + ": " + word);
            }
        }
    }

    private int toDecimal(List<Integer> binaryList) {
        int decimal = 0;
        for (int value : binaryList) {
            decimal = (decimal << 1) | value;
        }
        return decimal;
    }
}