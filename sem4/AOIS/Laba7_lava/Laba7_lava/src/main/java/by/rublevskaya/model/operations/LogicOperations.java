package by.rublevskaya.model.operations;

import java.util.ArrayList;
import java.util.List;

public class LogicOperations {
    public List<Integer> disjunction(List<Integer> word1, List<Integer> word2) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < word1.size(); i++) {
            result.add(word1.get(i) | word2.get(i));
        }
        return result;
    }

    public List<Integer> negation(List<Integer> word) {
        List<Integer> result = new ArrayList<>();
        for (int val : word) {
            result.add(val == 0 ? 1 : 0);
        }
        return result;
    }

    public List<Integer> disjunctionNegation(List<Integer> word1, List<Integer> word2) {
        return negation(disjunction(word1, word2));
    }

    public List<Integer> firstArgBan(List<Integer> word1, List<Integer> word2) {
        List<Integer> negWord2 = negation(word2);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < word1.size(); i++) {
            result.add(word1.get(i) & negWord2.get(i));
        }
        return result;
    }

    public List<Integer> implication(List<Integer> word1, List<Integer> word2) {
        List<Integer> negWord1 = negation(word1);
        return disjunction(negWord1, word2);
    }

}
