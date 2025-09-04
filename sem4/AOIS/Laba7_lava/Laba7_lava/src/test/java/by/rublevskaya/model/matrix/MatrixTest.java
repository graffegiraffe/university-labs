package by.rublevskaya.model.matrix;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    private Matrix matrix;

    @BeforeEach
    void setUp() {
        matrix = new Matrix(4, 4);
        int[][] initialState = {
                {1, 0, 0, 1},
                {0, 1, 1, 0},
                {1, 0, 1, 1},
                {0, 1, 0, 0}
        };
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrix.getMatrixState()[i][j] = initialState[i][j];
            }
        }
    }

    @Test
    void readWord() {
        List<Integer> expectedWord = Arrays.asList(0, 1, 1, 0);
        List<Integer> actualWord = matrix.readWord(1);
    }

    @Test
    void writeWord() {
        List<Integer> newWord = Arrays.asList(1, 1, 1, 1);
        matrix.writeWord(2, newWord);
        List<Integer> updatedWord = matrix.readWord(2);
        assertEquals(newWord, updatedWord);
    }

    @Test
    void readAddressColumn() {
        List<Integer> expectedColumn = Arrays.asList(0, 0, 1, 1);
        List<Integer> actualColumn = matrix.readAddressColumn(3);
        assertEquals(expectedColumn, actualColumn);
    }

    @Test
    void writeAddressColumn() {
        List<Integer> newAddress = Arrays.asList(1, 0, 1, 0);
        matrix.writeAddressColumn(1, newAddress);
        List<Integer> updatedColumn = matrix.readAddressColumn(1);
        assertEquals(newAddress, updatedColumn);
    }

    @Test
    void addFieldsByKey() {
        String keyV = "001";
        matrix.addFieldsByKey(keyV);
        int[][] expectedMatrix = {
                {1, 0, 0, 1},
                {0, 1, 1, 0},
                {1, 0, 1, 1},
                {0, 1, 0, 0}
        };
        assertArrayEquals(expectedMatrix, matrix.getMatrixState());
    }

    @Test
    void getMatrixState() {
        int[][] expectedMatrix = {
                {1, 0, 0, 1},
                {0, 1, 1, 0},
                {1, 0, 1, 1},
                {0, 1, 0, 0}
        };
        assertArrayEquals(expectedMatrix, matrix.getMatrixState());
    }

    @Test
    void addFieldsByKey_FromMainValues() {
        Matrix matrix = new Matrix(16, 16);
        List<Integer> word1 = List.of(1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1);
        matrix.writeWord(3, word1);
        List<Integer> word2 = List.of(0, 1, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0);
        matrix.writeWord(7, word2);
        List<Integer> word3 = List.of(1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0);
        matrix.writeWord(11, word3);
        List<Integer> word4 = List.of(0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0);
        matrix.writeWord(4, word4);
        String keyV = "011";
        try {
            matrix.addFieldsByKey(keyV);
        } catch (Exception e) {
            fail("Метод вызвал исключение: " + e.getMessage());
        }
        int[][] expectedState = matrix.getMatrixState();
        int[][] actualState = matrix.getMatrixState();
        assertArrayEquals(expectedState, actualState, "Матрица после сложения полей по ключу не соответствует ожидаемому значению");
    }
}