package by.rublevskaya.matrix;

import by.rublevskaya.matrix.core.Matrix;
import by.rublevskaya.matrix.core.MatrixInitializer;
import by.rublevskaya.matrix.service.MatrixService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private Matrix matrix;
    private MatrixService matrixService;

    @BeforeEach
    void setup() {
        matrix = new Matrix(16);
        matrixService = new MatrixService();
    }

    @Test
    void testRandomInitialization() {
        MatrixInitializer.randomInitialize(matrix);
        for (int i = 0; i < matrix.getSize(); i++) {
            for (int j = 0; j < matrix.getSize(); j++) {
                int value = matrix.getMatrixState()[i][j];
                assertTrue(value == 0 || value == 1);
            }
        }
    }

    @Test
    void testWriteWordAndReadWord() {
        List<Integer> word = List.of(1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0);
        matrixService.writeWord(matrix, 3, word);
        List<Integer> readWord = matrixService.readWord(matrix, 3);
        assertEquals(word, readWord);
    }

    @Test
    void testWriteAndReadAddressColumn() {
        List<Integer> column = List.of(1, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1);
        matrixService.writeAddressColumn(matrix, 2, column);
        List<Integer> readColumn = matrixService.readAddressColumn(matrix, 2);
        assertEquals(column, readColumn);
    }

    @Test
    void testLogicalOROperation() {
        int[] word1 = {1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0};
        int[] word2 = {0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1};
        int[] orResult = matrixService.applyOr(word1, word2);
        int[] expected = {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1};
        assertArrayEquals(expected, orResult);
    }

    @Test
    void testLogicalNOTOperation() {
        int[] word = {1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0};
        int[] notResult = matrixService.applyNotA(word);
        int[] expected = {0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1};
        assertArrayEquals(expected, notResult);
    }

    @Test
    void testAddFieldsByKey() {
        List<Integer> word = List.of(
                1, 0, 1,  // Ключ: 101
                1, 0, 1, 1,  // Поле A: 1011
                0, 1, 0, 0,  // Поле B: 0100
                0, 0, 0, 0, 0 //сумма
        );
        matrixService.writeWord(matrix, 0, word);
        matrixService.addFieldsByKey(matrix, "101");
        List<Integer> modifiedWord = matrixService.readWord(matrix, 0);
        List<Integer> expected = List.of(
                1, 0, 1,  // Ключ
                1, 0, 1, 1,  // Поле A
                0, 1, 0, 0,  // Поле B
                0, 0, 1, 1, 1 // Сумма A + B = 15 = 01111
        );
        assertEquals(expected, modifiedWord);
    }
}