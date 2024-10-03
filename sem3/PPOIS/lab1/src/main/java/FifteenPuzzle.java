import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

    public class FifteenPuzzle {

        public static final int EMPTY_CELL = 0;
        private final int size;
        public int[][] board;

        public FifteenPuzzle(int size) {
            this.size = size;
            this.board = new int[size][size];
            initializeBoard();
        }

        protected void initializeBoard() {
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

        public boolean moveTile(int tileValue) {
            int[] tilePosition = findPosition(tileValue);
            int[] emptyPosition = findPosition(EMPTY_CELL);
            if (isMoveValid(tilePosition, emptyPosition)) {
                board[tilePosition[0]][tilePosition[1]] = EMPTY_CELL;
                board[emptyPosition[0]][emptyPosition[1]] = tileValue;
                return true;
            } else {
                return false;
            }
        }

        protected int[] findPosition(int tileValue) {
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    if (board[row][col] == tileValue) {
                        return new int[]{row, col};
                    }
                }
            }
            return null;
        }

        private boolean isMoveValid(int[] tilePosition, int[] emptyPosition) {
            if (tilePosition == null || emptyPosition == null) {
                return false;
            }

            int rowDiff = Math.abs(tilePosition[0] - emptyPosition[0]);
            int colDiff = Math.abs(tilePosition[1] - emptyPosition[1]);

            return (rowDiff == 1 && colDiff == 0) || (rowDiff == 0 && colDiff == 1);
        }

        public boolean isSolved() {
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

        public void printBoard() {
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    System.out.printf("%2d ", board[row][col]);
                }
                System.out.println();
            }
        }
    }
