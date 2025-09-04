package by.rublevskaya.matrix.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MatrixTest {

    @Test
    void testMatrixInitialization() {
        Matrix matrix = new Matrix();
        assertNotNull(matrix.getMatrixState());
        assertEquals(16, matrix.getSize());
        assertEquals(16, matrix.getMatrixState().length);
        assertEquals(16, matrix.getMatrixState()[0].length);
    }

    @Test
    void testMatrixSetAndGetValues() {
        Matrix matrix = new Matrix();
        matrix.getMatrixState()[0][0] = 1;
        assertEquals(1, matrix.getMatrixState()[0][0]);
        matrix.getMatrixState()[15][15] = 2;
        assertEquals(2, matrix.getMatrixState()[15][15]);
    }
}