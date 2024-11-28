package by.rublevskaya.grapheditor.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class IteratorUtilsTest {

    @Test
    public void forEach_withIterator() {
        List<String> elements = Arrays.asList("A", "B", "C");
        Iterator<String> iterator = elements.iterator();
        StringBuilder result = new StringBuilder();
        IteratorUtils.forEach(iterator, result::append);
        assertEquals("ABC", result.toString());
    }

    @Test
    public void forEach_withIterable() {
        List<String> elements = Arrays.asList("A", "B", "C");
        StringBuilder result = new StringBuilder();
        IteratorUtils.forEach(elements, result::append);
        assertEquals("ABC", result.toString());
    }

    @Test
    public void forEach_withEmptyIterator() {
        List<String> elements = new ArrayList<>();
        Iterator<String> iterator = elements.iterator();
        StringBuilder result = new StringBuilder();
        IteratorUtils.forEach(iterator, result::append);
        assertTrue(result.toString().isEmpty());
    }

    @Test
    public void forEach_withEmptyIterable() {
        List<String> elements = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        IteratorUtils.forEach(elements, result::append);
        assertTrue(result.toString().isEmpty());
    }
}