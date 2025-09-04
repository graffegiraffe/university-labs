package by.rublevskaya.model.transformers;

import java.util.ArrayList;
import java.util.List;

public class BinaryConverter {
    public String wordToString(List<Integer> word) {
        StringBuilder sb = new StringBuilder();
        for (int bit : word) {
            sb.append(bit);
        }
        return sb.toString();
    }

    public int binaryToDecimal(String binary) {
        return Integer.parseInt(binary, 2);
    }

    public String decimalToBinary(int number, int length) {
        String binary = Integer.toBinaryString(number);
        while (binary.length() < length) {
            binary = "0" + binary;
        }
        return binary;
    }

    public List<Integer> stringToWord(String binaryString) {
        List<Integer> word = new ArrayList<>();
        for (char bit : binaryString.toCharArray()) {
            word.add(bit == '1' ? 1 : 0);
        }
        return word;
    }

}
