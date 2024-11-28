package by.rublevskaya.grapheditor.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class PairTest {

    @Test
    public void getFirst() {
        Pair<String, Integer> pair = new Pair<>("A", 1);
        assertEquals("A", pair.getFirst());
    }

    @Test
    public void getSecond() {
        Pair<String, Integer> pair = new Pair<>("A", 1);
        assertEquals(Integer.valueOf(1), pair.getSecond());
    }

    @Test
    public void testToString() {
        Pair<String, Integer> pair = new Pair<>("A", 1);
        assertEquals("(A, 1)", pair.toString());
    }

    @Test
    public void testEquals() {
        Pair<String, Integer> pair1 = new Pair<>("A", 1);
        Pair<String, Integer> pair2 = new Pair<>("A", 1);
        Pair<String, Integer> pair3 = new Pair<>("B", 2);
        assertTrue(pair1.equals(pair2));
        assertFalse(pair1.equals(pair3));
        assertFalse(pair1.equals(null));
        assertFalse(pair1.equals("A"));
    }

    @Test
    public void testHashCode() {
        Pair<String, Integer> pair1 = new Pair<>("A", 1);
        Pair<String, Integer> pair2 = new Pair<>("A", 1);
        assertEquals(pair1.hashCode(), pair2.hashCode());
        Pair<String, Integer> pair3 = new Pair<>("B", 2);
        assertNotEquals(pair1.hashCode(), pair3.hashCode());
    }
}