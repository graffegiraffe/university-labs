package by.rublevskaya.matrix.utils;

import by.rublevskaya.matrix.core.Matrix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MatrixPrinterTest {

    private Matrix matrix;

    @BeforeEach
    void setUp() {
        matrix = new Matrix();
    }

    @Test
    void testPrint() {
        int[][] matrixState = matrix.getMatrix();
        matrixState[0][0] = 1;
        matrixState[0][1] = 0;
        matrixState[1][0] = 1;
        matrixState[1][1] = 1;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        MatrixPrinter.print(matrix);
        String expectedOutput = "Матрица:\n1 0 0 0 \n1 1 0 0 \n0 0 0 0 \n0 0 0 0 \n";
        assertEquals(expectedOutput, outputStream.toString());
        System.setOut(System.out);
    }
}