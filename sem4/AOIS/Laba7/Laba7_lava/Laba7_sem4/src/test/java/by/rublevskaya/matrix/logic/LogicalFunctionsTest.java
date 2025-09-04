package by.rublevskaya.matrix.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class LogicalFunctionsTest {

    @Test
    void testApplyOr() {
        int[] word1 = {0, 1, 1, 0};
        int[] word2 = {1, 0, 1, 1};
        int[] expected = {1, 1, 1, 1};
        assertArrayEquals(expected, LogicalFunctions.applyOr(word1, word2));
    }

    @Test
    void testApplyNor() {
        int[] word1 = {0, 1, 1, 0};
        int[] word2 = {1, 0, 1, 1};
        int[] expected = {0, 0, 0, 0};
        assertArrayEquals(expected, LogicalFunctions.applyNor(word1, word2));
    }

    @Test
    void testApplyNotA() {
        int[] word1 = {0, 1, 1, 0};
        int[] word2 = {1, 1, 0, 0};
        int[] expected = {1, 0, 0, 0};
        assertArrayEquals(expected, LogicalFunctions.applyNotA(word1, word2));
    }

    @Test
    void testApplyImply() {
        int[] word1 = {0, 1, 1, 0};
        int[] word2 = {1, 0, 1, 1};
        int[] expected = {1, 0, 1, 1};
        assertArrayEquals(expected, LogicalFunctions.applyImply(word1, word2));
    }
}