package by.rublevskaya.model.operations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FieldSearcherTest {

    private FieldSearcher fieldSearcher;

    @BeforeEach
    void setUp() {
        fieldSearcher = new FieldSearcher();
    }

    @Test
    void compareWords_EqualWords() {
        List<Integer> word1 = Arrays.asList(1, 0, 1, 1, 0);
        List<Integer> word2 = Arrays.asList(1, 0, 1, 1, 0);
        int result = fieldSearcher.compareWords(word1, word2);
        assertEquals(0, result);
    }

    @Test
    void compareWords_FirstWordGreater() {
        List<Integer> word1 = Arrays.asList(1, 1, 0, 0, 1);
        List<Integer> word2 = Arrays.asList(1, 0, 1, 1, 0);
        int result = fieldSearcher.compareWords(word1, word2);
        assertTrue(result > 0);
    }

    @Test
    void compareWords_SecondWordGreater() {
        List<Integer> word1 = Arrays.asList(1, 0, 0, 1, 0);
        List<Integer> word2 = Arrays.asList(1, 0, 1, 0, 1);
        int result = fieldSearcher.compareWords(word1, word2);
        assertTrue(result < 0);
    }

    @Test
    void searchInRange_WordsInRange() {
        List<Integer> lowerBound = Arrays.asList(1, 0, 0, 0, 0);
        List<Integer> upperBound = Arrays.asList(1, 1, 1, 1, 1);
        List<List<Integer>> words = new ArrayList<>();
        words.add(Arrays.asList(1, 0, 0, 0, 0)); // В пределах диапазона
        words.add(Arrays.asList(1, 0, 0, 1, 0)); // В пределах диапазона
        words.add(Arrays.asList(1, 1, 1, 1, 1)); // В пределах диапазона
        words.add(Arrays.asList(0, 1, 0, 0, 1)); // Не в диапазоне
        words.add(Arrays.asList(1, 1, 1, 1, 2)); // Не в диапазоне
        fieldSearcher.searchInRange(lowerBound, upperBound, words);
        List<List<Integer>> expectedInRange = Arrays.asList(
                Arrays.asList(1, 0, 0, 0, 0),
                Arrays.asList(1, 0, 0, 1, 0),
                Arrays.asList(1, 1, 1, 1, 1)
        );
        List<List<Integer>> actualInRange = new ArrayList<>();
        for (int i = 0; i < words.size(); i++) {
            List<Integer> word = words.get(i);
            if (fieldSearcher.compareWords(word, lowerBound) >= 0 &&
                    fieldSearcher.compareWords(word, upperBound) <= 0) {
                actualInRange.add(word);
            }
        }
        assertEquals(expectedInRange, actualInRange);
    }

    @Test
    void searchInRange_NoWordsInRange() {
        List<Integer> lowerBound = Arrays.asList(1, 0, 0, 0, 0);
        List<Integer> upperBound = Arrays.asList(1, 0, 0, 0, 1);
        List<List<Integer>> words = new ArrayList<>();
        words.add(Arrays.asList(0, 0, 0, 0, 0)); // Не в диапазоне
        words.add(Arrays.asList(1, 0, 0, 0, 2)); // Не в диапазоне
        words.add(Arrays.asList(1, 1, 1, 1, 0)); // Не в диапазоне
        fieldSearcher.searchInRange(lowerBound, upperBound, words);
        List<List<Integer>> expectedInRange = new ArrayList<>();
        List<List<Integer>> actualInRange = new ArrayList<>();
        for (int i = 0; i < words.size(); i++) {
            List<Integer> word = words.get(i);
            if (fieldSearcher.compareWords(word, lowerBound) >= 0 &&
                    fieldSearcher.compareWords(word, upperBound) <= 0) {
                actualInRange.add(word);
            }
        }
        assertEquals(expectedInRange, actualInRange);
    }
}