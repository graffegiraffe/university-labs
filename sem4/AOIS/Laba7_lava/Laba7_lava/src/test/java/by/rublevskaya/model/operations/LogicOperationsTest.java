package by.rublevskaya.model.operations;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LogicOperationsTest {

    private final LogicOperations logicOperations = new LogicOperations();

    @Test
    void disjunction() {
        List<Integer> word1 = Arrays.asList(1, 0, 1, 0);
        List<Integer> word2 = Arrays.asList(0, 1, 1, 0);

        List<Integer> result = logicOperations.disjunction(word1, word2);

        assertEquals(Arrays.asList(1, 1, 1, 0), result);
    }

    @Test
    void negation() {
        List<Integer> word = Arrays.asList(1, 0, 1, 0);

        List<Integer> result = logicOperations.negation(word);

        assertEquals(Arrays.asList(0, 1, 0, 1), result);
    }

    @Test
    void disjunctionNegation() {
        List<Integer> word1 = Arrays.asList(1, 0, 1, 0);
        List<Integer> word2 = Arrays.asList(0, 1, 1, 0);

        List<Integer> result = logicOperations.disjunctionNegation(word1, word2);

        assertEquals(Arrays.asList(0, 0, 0, 1), result);
    }

    @Test
    void firstArgBan() {
        List<Integer> word1 = Arrays.asList(1, 0, 1, 1);
        List<Integer> word2 = Arrays.asList(0, 1, 1, 0);

        List<Integer> result = logicOperations.firstArgBan(word1, word2);

        assertEquals(Arrays.asList(1, 0, 0, 1), result);
    }

    @Test
    void implication() {
        List<Integer> word1 = Arrays.asList(1, 0, 1, 0);
        List<Integer> word2 = Arrays.asList(0, 1, 1, 0);

        List<Integer> result = logicOperations.implication(word1, word2);

        assertEquals(Arrays.asList(0, 1, 1, 1), result);
    }
}