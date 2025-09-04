package by.rublevskaya.model.matrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixPrinterTest {

    @Test
    void formatMatrix() {
        int[][] matrix = {
                {1, 0, 1},
                {0, 1, 0},
                {1, 1, 1}
        };
        String expectedOutput = "\nМатрица:\n" +
                "1 0 1 \n" +
                "0 1 0 \n" +
                "1 1 1 \n";
        String actualOutput = MatrixPrinter.formatMatrix(matrix);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void formatMatrix_EmptyMatrix() {
        int[][] emptyMatrix = {};
        String expectedOutput = "\nМатрица:\n";
        String actualOutput = MatrixPrinter.formatMatrix(emptyMatrix);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void formatMatrix_SingleElementMatrix() {
        int[][] singleElementMatrix = {
                {1}
        };
        String expectedOutput = "\nМатрица:\n1 \n";
        String actualOutput = MatrixPrinter.formatMatrix(singleElementMatrix);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void formatMatrix_EmptyRows() {
        int[][] emptyRowsMatrix = {
                {},
                {},
                {}
        };
        String expectedOutput = "\nМатрица:\n\n\n\n";
        String actualOutput = MatrixPrinter.formatMatrix(emptyRowsMatrix);
        assertEquals(expectedOutput, actualOutput);
    }
}