package by.rublevskaya.model.transformers;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinaryConverterTest {

    private final BinaryConverter binaryConverter = new BinaryConverter();

    @Test
    void wordToString() {
        List<Integer> word = Arrays.asList(1, 0, 1, 1, 0);

        String result = binaryConverter.wordToString(word);

        assertEquals("10110", result);
    }

    @Test
    void binaryToDecimal() {
        String binary = "10110";

        int result = binaryConverter.binaryToDecimal(binary);

        assertEquals(22, result);
    }

    @Test
    void decimalToBinary() {
        int number = 22;
        int length = 6;

        String result = binaryConverter.decimalToBinary(number, length);

        assertEquals("010110", result);
    }

    @Test
    void stringToWord() {
        String binaryString = "10110";

        List<Integer> result = binaryConverter.stringToWord(binaryString);

        assertEquals(Arrays.asList(1, 0, 1, 1, 0), result);
    }
}