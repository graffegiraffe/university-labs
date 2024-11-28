package by.rublevskaya.grapheditor.graph;

import by.rublevskaya.grapheditor.util.Pair;
import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;

import static org.junit.Assert.*;

public class UndirectedGraphTest {

    private UndirectedGraph<String> graph;
    private UndirectedGraph<String> other;

    @Before
    public void setUp() {
        graph = new UndirectedGraph<>();
        other = new UndirectedGraph<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");

        other.addVertex("A");
        other.addVertex("B");
    }

    @Test
    public void testEquals() {
        other.addEdge("A", "B");
        assertTrue(graph.equals(other));
    }

    @Test
    public void testHashCode() {
        other.addEdge("A", "B");
        assertEquals(graph.hashCode(), other.hashCode());
    }

    @Test
    public void notEquals() {
        assertTrue(graph.notEquals(other));
    }

    @Test
    public void isGreaterThan() {
        other.addVertex("C");
        other.addEdge("B", "C");
        assertTrue(other.isGreaterThan(graph));
        assertFalse(graph.isGreaterThan(other));
    }

    @Test
    public void isLessThan() {
        other.addVertex("C");
        other.addEdge("B", "C");
        assertTrue(graph.isLessThan(other));
        assertFalse(other.isLessThan(graph));
    }

    @Test
    public void isGreaterOrEqual() {
        other.addEdge("A", "B");
        assertTrue(graph.isGreaterOrEqual(other));
        other.addVertex("C");
        assertFalse(graph.isGreaterOrEqual(other));
    }

    @Test
    public void isLessOrEqual() {
        other.addEdge("A", "B");
        assertTrue(graph.isLessOrEqual(other));
        graph.addVertex("C");
        graph.addEdge("B", "C");
        assertFalse(graph.isLessOrEqual(other));
    }

    @Test
    public void iterator() {
        Iterator<String> iterator = graph.iterator();
        assertTrue(iterator.hasNext());
        String vertex = iterator.next();
        assertTrue(vertex.equals("A") || vertex.equals("B"));
        assertTrue(iterator.hasNext());
        vertex = iterator.next();
        assertTrue(vertex.equals("A") || vertex.equals("B"));
    }

    @Test
    public void reverseVertexIterator() {
        Iterator<String> iterator = graph.reverseVertexIterator();
        assertTrue(iterator.hasNext());
        String vertex = iterator.next();
        assertTrue(vertex.equals("A") || vertex.equals("B"));
        assertTrue(iterator.hasNext());
        vertex = iterator.next();
        assertTrue(vertex.equals("A") || vertex.equals("B"));
    }

    @Test
    public void edgeIterator() {
        Iterator<Pair<String, String>> iterator = graph.edgeIterator();
        assertTrue(iterator.hasNext());
        Pair<String, String> edge = iterator.next();
        assertEquals(new Pair<>("A", "B"), edge);
    }

    @Test
    public void reverseEdgeIterator() {
        Iterator<Pair<String, String>> iterator = graph.reverseEdgeIterator();
        assertTrue(iterator.hasNext());
        Pair<String, String> edge = iterator.next();
        assertEquals(new Pair<>("A", "B"), edge);
    }

    @Test
    public void adjacentVertexIterator() {
        Iterator<String> iterator = graph.adjacentVertexIterator("A");
        assertTrue(iterator.hasNext());
        assertEquals("B", iterator.next());
    }

    @Test
    public void reverseAdjacentVertexIterator() {
        Iterator<String> iterator = graph.reverseAdjacentVertexIterator("A");
        assertTrue(iterator.hasNext());
        assertEquals("B", iterator.next());
    }

    @Test
    public void removeVertex() {
        Iterator<String> iterator = graph.iterator();
        if (iterator.hasNext()) {
            String vertex = iterator.next();
            iterator.remove();
            assertFalse(graph.containsVertex(vertex));
        }
        iterator = graph.iterator();
        if (iterator.hasNext()) {
            String vertex = iterator.next();
            iterator.remove();
            assertFalse(graph.containsVertex(vertex));
        }
        assertTrue(graph.empty());
    }

    @Test
    public void removeEdge() {
        Iterator<Pair<String, String>> iterator = graph.edgeIterator();
        graph.removeEdge(iterator);
        assertFalse(graph.containsEdge("A", "B"));
    }

    @Test
    public void edges() {
        Iterable<Pair<String, String>> edges = graph.edges();
        Iterator<Pair<String, String>> iterator = edges.iterator();
        assertTrue(iterator.hasNext());
        Pair<String, String> edge = iterator.next();
        assertEquals(new Pair<>("A", "B"), edge);
        assertFalse(iterator.hasNext());
    }
}