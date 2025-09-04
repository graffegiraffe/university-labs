package by.rublevskaya.matrix.logic;

public class LogicalFunctions {

    public static int[] applyOr(int[] word1, int[] word2) {
        int[] result = new int[word1.length];
        for (int i = 0; i < word1.length; i++) {
            result[i] = word1[i] | word2[i];
        }
        return result;
    }

    public static int[] applyNor(int[] word1, int[] word2) {
        int[] result = new int[word1.length];
        for (int i = 0; i < word1.length; i++) {
            result[i] = ~(word1[i] | word2[i]) & 1;
        }
        return result;
    }

    public static int[] applyNotA(int[] word1, int[] word2) {
        int[] result = new int[word1.length];
        for (int i = 0; i < word1.length; i++) {
            result[i] = ~word1[i] & word2[i] & 1;
        }
        return result;
    }

    public static int[] applyImply(int[] word1, int[] word2) {
        int[] result = new int[word1.length];
        for (int i = 0; i < word1.length; i++) {
            result[i] = (~word1[i] | word2[i]) & 1;
        }
        return result;
    }
}