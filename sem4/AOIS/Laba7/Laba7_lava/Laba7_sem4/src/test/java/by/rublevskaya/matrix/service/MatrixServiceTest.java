package by.rublevskaya.matrix.service;

import by.rublevskaya.matrix.core.Matrix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MatrixServiceTest {

    private MatrixService matrixService;
    private Matrix matrix;

    @BeforeEach
    void setUp() {
        matrixService = new MatrixService();
        matrix = new Matrix();
    }

    @Test
    void testWriteWord() {
        List<Integer> word = List.of(1, 0, 1, 1);
        matrixService.writeWord(matrix, 0, word);
        List<Integer> matrixWord = matrixService.readWord(matrix, 0);
        assertEquals(word, matrixWord.subList(0, word.size()));
    }

    @Test
    void testReadWord() {
        int[][] state = matrix.getMatrixState();
        state[0][0] = 1;
        state[1][1] = 1;
        state[2][2] = 1;
        state[3][3] = 1;
        List<Integer> word = matrixService.readWord(matrix, 0);
        assertEquals(List.of(1, 1, 1, 1), word.subList(0, 4));
    }

    @Test
    void testWriteAddressColumn() {
        List<Integer> column = List.of(1, 0, 1, 1);
        matrixService.writeAddressColumn(matrix, 0, column);
        List<Integer> matrixColumn = matrixService.readAddressColumn(matrix, 0);
        assertEquals(column, matrixColumn.subList(0, column.size()));
    }

    @Test
    void testReadAddressColumn() {
        int[][] state = matrix.getMatrixState();
        state[0][0] = 1;
        state[1][0] = 0;
        state[2][0] = 1;
        state[3][0] = 1;
        List<Integer> column = matrixService.readAddressColumn(matrix, 0);
        assertEquals(List.of(1, 0, 1, 1), column.subList(0, 4));
    }

    @Test
    void testAddFieldsByKey() {
        List<Integer> word = List.of(
                1, 0, 1,  // Ключ: 101
                1, 0, 1, 1,  // Поле A: 1011 (decimal 11)
                0, 1, 0, 0,  // Поле B: 0100 (decimal 4)
                0, 0, 0, 0, 0 // Результирующее поле
        );
        matrixService.writeWord(matrix, 0, word);
        matrixService.addFieldsByKey(matrix, "101");
        List<Integer> updatedWord = matrixService.readWord(matrix, 0);
        assertEquals(List.of(
                1, 0, 1,
                1, 0, 1, 1,
                0, 1, 0, 0,
                0, 0, 1, 1, 1
        ), updatedWord.subList(0, 17));
    }
}