package by.rublevskaya.matrix.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MatrixInitializerTest {

    private Matrix matrix;

    @BeforeEach
    void setUp() {
        matrix = new Matrix(8);
    }

    @Test
    void testRandomInitialize() {
        MatrixInitializer.randomInitialize(matrix);
        int[][] initializedMatrix = matrix.getMatrixState();
        for (int i = 0; i < matrix.getSize(); i++) {
            for (int j = 0; j < matrix.getSize(); j++) {
                int value = initializedMatrix[i][j];
                assertTrue(value == 0 || value == 1);
            }
        }
    }
}