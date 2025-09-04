package by.rublevskaya.model.matrix;

import by.rublevskaya.model.transformers.BinaryConverter;

import java.util.ArrayList;
import java.util.List;

public class Matrix {
    protected final int rows;
    protected final int cols;
    private final BinaryConverter converter;
    protected final int[][] matrix;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = new int[rows][cols];
        this.converter = new BinaryConverter();
    }

    public List<Integer> readWord(int wordIndex) {
        List<Integer> column = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            column.add(matrix[i][wordIndex]);
        }
        List<Integer> word = new ArrayList<>(column.subList(wordIndex, column.size()));
        word.addAll(column.subList(0, wordIndex));
        return word;
    }

    public void writeWord(int wordIndex, List<Integer> word) {
        if (word.size() != rows) {
            System.out.println("Некорректная длина слова");
            return;
        }
        List<Integer> writeWord = new ArrayList<>(word.subList(rows - wordIndex, word.size()));
        writeWord.addAll(word.subList(0, rows - wordIndex));
        for (int i = 0; i < rows; i++) {
            matrix[i][wordIndex] = writeWord.get(i);
        }
    }
    
    public List<Integer> readAddressColumn(int colIdx) {
        List<Integer> result = new ArrayList<>();
        for (int col = 0; col < cols; col++) {
            int row = (colIdx + col) % rows;
            result.add(matrix[row][col]);
        }
        return result;
    }

    public void writeAddressColumn(int colIdx, List<Integer> address) {
        if (address.size() != rows) {
            System.out.println("Некорректная длина адресного столбца");
            return;
        }
        for (int col = 0; col < cols; col++) {
            int row = (colIdx + col) % rows;
            matrix[row][col] = address.get(col);
        }
    }

    public void addFieldsByKey(String keyV) {
        for (int i = 0; i < cols; i++) {
            List<Integer> word = readWord(i);
            String binaryWord = converter.wordToString(word);
            String vBits = binaryWord.substring(0, 3);
            if (vBits.equals(keyV)) {
                String aBits = binaryWord.substring(3, 7);
                String bBits = binaryWord.substring(7, 11);
                String sBits = binaryWord.substring(11, 16);
                int aVal = converter.binaryToDecimal(aBits);
                int bVal = converter.binaryToDecimal(bBits);
                int sum = (aVal + bVal) % 32;
                String newSBits = converter.decimalToBinary(sum, 5);
                String newBinaryWord = vBits + aBits + bBits + newSBits;
                List<Integer> newWord = converter.stringToWord(newBinaryWord);
                writeWord(i, newWord);
                System.out.println("Обновлено слово #" + i + ": " + binaryWord + " → " + newBinaryWord);
            }
        }
    }

    public int[][] getMatrixState() {
        return this.matrix;
    }
}
