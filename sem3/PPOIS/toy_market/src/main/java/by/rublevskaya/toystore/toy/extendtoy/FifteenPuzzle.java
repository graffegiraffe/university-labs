package by.rublevskaya.toystore.toy.extendtoy;

import by.rublevskaya.toystore.toy.Toy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FifteenPuzzle extends Toy {
    public static final int EMPTY_CELL = 0;
    protected final int size;
    protected int[][] board;

    public FifteenPuzzle(String name, int size) {
        super(name, 0);
        this.size = size;
        this.board = new int[size][size];
        initializeGameBoard();
    }

    @Override
    public void use() {
        System.out.println("Играем в " + name + " (Пятнашки)");
        printBoard();
    }

    @Override
    public String toString() {
        return "Пятнашки {" +
                " имя ='" + name + '\'' +
                ", размер =" + size +
                ", доска =" + Arrays.deepToString(board) +
                '}';
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("%2d ", board[i][j]);
            }
            System.out.println();
        }
    }

    private void initializeGameBoard() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i < size * size; i++) {
            numbers.add(i);
        }
        numbers.add(EMPTY_CELL);
        Collections.shuffle(numbers);
        int index = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                board[row][col] = numbers.get(index++);
            }
        }
    }

    public boolean moveSpecificTile(int tileValue) {
        int[] tilePosition = findSpecificPosition(tileValue);
        int[] emptyPosition = findSpecificPosition(EMPTY_CELL);
        if (canMoveValid(tilePosition, emptyPosition)) {
            board[tilePosition[0]][tilePosition[1]] = EMPTY_CELL;
            board[emptyPosition[0]][emptyPosition[1]] = tileValue;
            return true;
        } else {
            return false;
        }
    }

    public int[] findSpecificPosition(int tileValue) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board[row][col] == tileValue) {
                    return new int[]{row, col};
                }
            }
        }
        return null;
    }

    private boolean canMoveValid(int[] tilePosition, int[] emptyPosition) {
        if (tilePosition == null || emptyPosition == null) {
            return false;
        }
        int rowDiff = Math.abs(tilePosition[0] - emptyPosition[0]);
        int colDiff = Math.abs(tilePosition[1] - emptyPosition[1]);
        return (rowDiff == 1 && colDiff == 0) || (rowDiff == 0 && colDiff == 1);
    }

    public boolean decisionMade() {
        int expectedValue = 1;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board[row][col] != expectedValue) {
                    if (row == size - 1 && col == size - 1 && board[row][col] == EMPTY_CELL) {
                        continue;
                    }
                    return false;
                }
                expectedValue++;
            }
        }
        return true;
    }
}