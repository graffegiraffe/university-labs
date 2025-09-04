package by.rublevskaya.matrix.utils;

import by.rublevskaya.matrix.core.Matrix;

public class MatrixPrinter {

    public static void print(Matrix matrix) {
        System.out.println("Матрица:");
        for (int i = 0; i < matrix.getSize(); i++) {
            for (int j = 0; j < matrix.getSize(); j++) {
                System.out.print(matrix.getMatrix()[i][j] + " ");
            }
            System.out.println();
        }
    }
}