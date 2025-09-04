package by.rublevskaya.matrix.logic;

import by.rublevskaya.matrix.core.Matrix;
import by.rublevskaya.matrix.service.MatrixService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FieldOperationsTest {

    private FieldOperations fieldOperations;
    private Matrix matrix;
    private MatrixService matrixService;

    @BeforeEach
    void setUp() {
        matrixService = new MatrixService();
        fieldOperations = new FieldOperations(matrixService);
        matrix = new Matrix();
    }

    @Test
    void testAddFieldsByKey() {
        List<Integer> word = List.of(
                1, 0, 1,  // Ключ: 101 (decimal 5)
                1, 0, 1, 1,  // Поле A: 1011 (decimal 11)
                0, 1, 0, 0,  // Поле B: 0100 (decimal 4)
                0, 0, 0, 0, 0 // Результирующее поле
        );

        matrixService.writeWord(matrix, 0, word);
        fieldOperations.addFieldsByKey(matrix, 5);

        List<Integer> updatedWord = matrixService.readWord(matrix, 0);

        List<Integer> expectedWord = List.of(
                1, 0, 1,  // Ключ: 101
                1, 0, 1, 1,  // Поле A: 1011
                0, 1, 0, 0,  // Поле B: 0100
                0, 0, 1, 1, 1 // Сумма A + B: 15 = 01111
        );

        assertEquals(expectedWord, updatedWord);
    }
}