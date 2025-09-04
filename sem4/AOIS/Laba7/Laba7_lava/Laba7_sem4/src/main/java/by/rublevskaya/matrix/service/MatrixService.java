package by.rublevskaya.matrix.service;

import by.rublevskaya.matrix.core.Matrix;

import java.util.ArrayList;
import java.util.List;

public class MatrixService {

    public void writeWord(Matrix matrix, int wordIndex, List<Integer> word) {
        for (int i = 0; i < word.size(); i++) {
            int row = (i + wordIndex) % matrix.getSize();
            int col = i;
            matrix.getMatrixState()[row][col] = word.get(i);
        }
    }

    public List<Integer> readWord(Matrix matrix, int wordIndex) {
        List<Integer> word = new ArrayList<>();
        for (int i = 0; i < matrix.getSize(); i++) {
            int row = (i + wordIndex) % matrix.getSize();
            word.add(matrix.getMatrixState()[row][i]);
        }
        return word;
    }

    public void writeAddressColumn(Matrix matrix, int columnIndex, List<Integer> values) {
        for (int i = 0; i < values.size(); i++) {
            matrix.getMatrixState()[i][columnIndex] = values.get(i);
        }
    }

    public List<Integer> readAddressColumn(Matrix matrix, int columnIndex) {
        List<Integer> column = new ArrayList<>();
        for (int[] row : matrix.getMatrixState()) {
            column.add(row[columnIndex]);
        }
        return column;
    }

    public void addFieldsByKey(Matrix matrix, String key) {
        for (int i = 0; i < matrix.getSize(); i++) {
            List<Integer> word = readWord(matrix, i);
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < 3; j++) {
                stringBuilder.append(word.get(j));
            }
            String calculatedV = stringBuilder.toString();
            if (calculatedV.equals(key)) {
                int a = toDecimal(word.subList(3, 7));
                int b = toDecimal(word.subList(7, 11));
                int sum = a + b;
                List<Integer> resultBits = toBinary(sum, 5);
                for (int j = 0; j < resultBits.size(); j++) {
                    int row = (11 + j + i) % matrix.getSize();
                    matrix.getMatrixState()[row][11 + j] = resultBits.get(j);
                }
            }
        }
    }

    private int toDecimal(List<Integer> bits) {
        int value = 0;
        for (int bit : bits) {
            value = (value << 1) | bit;
        }
        return value;
    }


    private List<Integer> toBinary(int number, int length) {
        List<Integer> bits = new ArrayList<>();
        for (int i = length - 1; i >= 0; i--) {
            bits.add((number >> i) & 1);
        }
        return bits;
    }
}