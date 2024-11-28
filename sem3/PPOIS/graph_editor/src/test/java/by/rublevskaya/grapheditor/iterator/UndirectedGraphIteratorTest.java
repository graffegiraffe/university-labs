package by.rublevskaya.grapheditor.iterator;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class UndirectedGraphIteratorTest {

    private List<String> list;
    private UndirectedGraphIterator<String> iterator;

    @Before
    public void setUp() {
        list = new ArrayList<>(Arrays.asList("A", "B", "C"));
        iterator = new UndirectedGraphIterator<>(list.iterator());
    }

    @Test
    public void hasNext() {
        assertTrue(iterator.hasNext());
        iterator.next();
        iterator.next();
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void next() {
        assertEquals("A", iterator.next());
        assertEquals("B", iterator.next());
        assertEquals("C", iterator.next());
    }

    @Test
    public void remove() {
        assertTrue(iterator.hasNext());
        iterator.next();
        iterator.remove();
        assertFalse(list.contains("A"));
        assertTrue(iterator.hasNext());
        String nextElement = iterator.next();
        assertEquals("B", nextElement);
        iterator.remove();
        assertFalse(list.contains("B"));
        assertTrue(iterator.hasNext());
        nextElement = iterator.next();
        assertEquals("C", nextElement);
        iterator.remove();
        assertFalse(list.contains("C"));
        assertFalse(iterator.hasNext());
        assertTrue(list.isEmpty());
    }
}