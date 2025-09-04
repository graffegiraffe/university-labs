package by.rublevskaya.service;

public class HashFunction {
    private static final int TABLE_SIZE = 20;

    public static int computeKey(String word) {
        word = word.toUpperCase();
        if (word.length() >= 2) {
            char first = word.charAt(0);
            char second = word.charAt(1);
            return (first - 'А') * 33 + (second - 'А');
        } else {
            return (word.charAt(0) - 'А') * 33;
        }
    }

    public static int hashFunction(int key) {
        return key % TABLE_SIZE;
    }
}