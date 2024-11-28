package by.rublevskaya.toystore.toy.extendtoy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.Assert.*;

public class FifteenPuzzleTest {

    private FifteenPuzzle puzzle;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        puzzle = new FifteenPuzzle("Test Puzzle", 4);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testInitializeGameBoard() {
        assertEquals(4, puzzle.board.length);
        assertEquals(4, puzzle.board[0].length);
        boolean foundEmptyCell = false;
        for (int row = 0; row < puzzle.board.length; row++) {
            for (int col = 0; col < puzzle.board[row].length; col++) {
                if (puzzle.board[row][col] == FifteenPuzzle.EMPTY_CELL) {
                    foundEmptyCell = true;
                    break;
                }
            }
        }
        assertTrue(foundEmptyCell);
    }

    @Test
    public void testMoveSpecificTileValid() {
        int emptyRow = 0;
        int emptyCol = 0;
        for (int row = 0; row < puzzle.board.length; row++) {
            for (int col = 0; col < puzzle.board[row].length; col++) {
                if (puzzle.board[row][col] == FifteenPuzzle.EMPTY_CELL) {
                    emptyRow = row;
                    emptyCol = col;
                }
            }
        }
        boolean moved = false;
        if (emptyRow > 0) {
            moved = puzzle.moveSpecificTile(puzzle.board[emptyRow - 1][emptyCol]);
        } else if (emptyCol > 0) {
            moved = puzzle.moveSpecificTile(puzzle.board[emptyRow][emptyCol - 1]);
        }
        assertTrue(moved);
    }

    @Test
    public void testMoveSpecificTileInvalid() {
        assertFalse(puzzle.moveSpecificTile(-1));
        assertFalse(puzzle.moveSpecificTile(999));
    }

    @Test
    public void testDecisionMadeFalse() {
        assertFalse(puzzle.decisionMade());
    }

    @Test
    public void testDecisionMadeTrue() {
        for (int i = 0; i < puzzle.board.length; i++) {
            for (int j = 0; j < puzzle.board[i].length; j++) {
                if (i == puzzle.board.length - 1 && j == puzzle.board[i].length - 1) {
                    puzzle.board[i][j] = FifteenPuzzle.EMPTY_CELL;
                } else {
                    puzzle.board[i][j] = i * puzzle.board.length + j + 1;
                }
            }
        }
        assertTrue(puzzle.decisionMade());
    }

    @Test
    public void testFindSpecificPosition() {
        int[] position = puzzle.findSpecificPosition(FifteenPuzzle.EMPTY_CELL);
        assertNotNull(position);
        assertEquals(2, position.length);
    }

    @Test
    public void testUse() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        puzzle.use();
        String expectedOutput = "Играем в Test Puzzle (Пятнашки)\n";
        for (int i = 0; i < puzzle.size; i++) {
            for (int j = 0; j < puzzle.size; j++) {
                expectedOutput += String.format("%2d ", puzzle.board[i][j]);
            }
            expectedOutput += "\n";
        }
        assertEquals(expectedOutput, outContent.toString());
        System.setOut(null);
    }

    @Test
    public void testToString() {
        String expectedOutput = "Пятнашки {" +
                " имя ='" + puzzle.name + '\'' +
                ", размер =" + puzzle.size +
                ", доска =" + Arrays.deepToString(puzzle.board) +
                '}';
        assertEquals(expectedOutput, puzzle.toString());
    }

    @Test
    public void testPrintBoard() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        puzzle.printBoard();
        String expectedOutput = "";
        for (int i = 0; i < puzzle.size; i++) {
            for (int j = 0; j < puzzle.size; j++) {
                expectedOutput += String.format("%2d ", puzzle.board[i][j]);
            }
            expectedOutput += "\n";
        }
        assertEquals(expectedOutput, outContent.toString());
        System.setOut(null);
    }
}