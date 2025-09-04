package by.rublevskaya.matrix.core;

import java.util.Random;

public class MatrixInitializer {
    public static void randomInitialize(Matrix matrix) {
        Random random = new Random();
        int[][] internalMatrix = matrix.getMatrix();
        for (int i = 0; i < matrix.getSize(); i++) {
            for (int j = 0; j < matrix.getSize(); j++) {
                internalMatrix[i][j] = random.nextInt(2);
            }
        }
    }
}