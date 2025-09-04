package by.rublevskaya.matrix.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Matrix {

    private static final int SIZE = 16;
    private final int[][] matrix;

    public Matrix() {
        this.matrix = new int[SIZE][SIZE];
    }

    public int[][] getMatrixState() {
        return matrix;
    }

    public int getSize() {
        return SIZE;
    }

}