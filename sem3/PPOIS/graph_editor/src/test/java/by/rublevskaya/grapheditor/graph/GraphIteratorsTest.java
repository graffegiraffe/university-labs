package by.rublevskaya.grapheditor.graph;

import by.rublevskaya.grapheditor.util.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class GraphIteratorsTest {

    private GraphBase<String> graph;
    private GraphIterators<String> iterators;

    @Before
    public void setUp() {
        graph = new GraphBase<>() {};
        iterators = new GraphIterators<>(graph);
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
    }

    @Test
    public void testVertexIterator() {
        Iterator<String> iterator = iterators.vertexIterator();
        assertTrue(iterator.hasNext());
        assertEquals("A", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("B", iterator.next());
    }

    @Test
    public void testReverseVertexIterator() {
        Iterator<String> iterator = iterators.reverseVertexIterator();
        assertTrue(iterator.hasNext());
        assertEquals("B", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("A", iterator.next());
    }

    @Test
    public void testEdgeIterator() {
        Iterator<Pair<String, String>> iterator = iterators.edgeIterator();
        assertTrue(iterator.hasNext());
        Pair<String, String> edge = iterator.next();
        assertEquals(new Pair<>("A", "B"), edge);
    }

    @Test
    public void testReverseEdgeIterator() {
        Iterator<Pair<String, String>> iterator = iterators.reverseEdgeIterator();
        assertTrue(iterator.hasNext());
        Pair<String, String> edge = iterator.next();
        assertEquals(new Pair<>("A", "B"), edge);
    }

    @Test
    public void testAdjacentVertexIterator() {
        Iterator<String> iterator = iterators.adjacentVertexIterator("A");
        assertTrue(iterator.hasNext());
        assertEquals("B", iterator.next());
    }

    @Test
    public void testReverseAdjacentVertexIterator() {
        Iterator<String> iterator = iterators.reverseAdjacentVertexIterator("A");
        assertTrue(iterator.hasNext());
        assertEquals("B", iterator.next());
    }

    @Test
    public void testEdges() {
        Iterator<Pair<String, String>> iterator = iterators.edges().iterator();
        assertTrue(iterator.hasNext());
        Pair<String, String> edge = iterator.next();
        assertEquals(new Pair<>("A", "B"), edge);
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testRemoveVertex() {
        Iterator<String> iterator = iterators.vertexIterator();
        iterators.removeVertex(iterator);
        assertFalse(graph.containsVertex("A"));
    }

    @Test
    public void testRemoveEdge() {
        Iterator<Pair<String, String>> iterator = iterators.edgeIterator();
        iterators.removeEdge(iterator);
        assertFalse(graph.containsEdge("A", "B"));
    }
}