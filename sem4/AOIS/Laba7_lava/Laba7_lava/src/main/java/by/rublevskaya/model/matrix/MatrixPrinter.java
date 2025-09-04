package by.rublevskaya.model.matrix;

public class MatrixPrinter {
    public static String formatMatrix(int[][] matrix) {
        StringBuilder sb = new StringBuilder("\nМатрица:\n");
        for (int[] row : matrix) {
            for (int bit : row) {
                sb.append(bit).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}