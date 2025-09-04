package by.rublevskaya.model.operations;

import java.util.List;

public class FieldSearcher {
    public void searchInRange(List<Integer> lowerBound, List<Integer> upperBound, List<List<Integer>> words) {
        System.out.println("\nСлова в диапазоне:");
        for (int i = 0; i < words.size(); i++) {
            List<Integer> word = words.get(i);
            if (compareWords(word, lowerBound) >= 0 && compareWords(word, upperBound) <= 0) {
                System.out.println("Слово #" + i + ": " + word);
            }
        }
    }

    protected int compareWords(List<Integer> word1, List<Integer> word2) {
        for (int i = 0; i < word1.size(); i++) {
            if (!word1.get(i).equals(word2.get(i))) {
                return word1.get(i) - word2.get(i);
            }
        }
        return 0;
    }
}