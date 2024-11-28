package by.rublevskaya.grapheditor.graph;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class GraphBaseTest {

    private GraphBase<String> graph;

    @Before
    public void setUp() {
        graph = new GraphBase<>() {};
    }

    @Test
    public void testContainsVertex() {
        graph.addVertex("A");
        assertTrue(graph.containsVertex("A"));
        assertFalse(graph.containsVertex("B"));
    }

    @Test
    public void testContainsEdge() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        assertTrue(graph.containsEdge("A", "B"));
        assertFalse(graph.containsEdge("A", "C"));
    }

    @Test
    public void testVertexCount() {
        graph.addVertex("A");
        graph.addVertex("B");
        assertEquals(2, graph.vertexCount());
        graph.addVertex("C");
        assertEquals(3, graph.vertexCount());
    }

    @Test
    public void testEdgeCount() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        assertEquals(1, graph.edgeCount());
        graph.addEdge("B", "C");
        assertEquals(2, graph.edgeCount());
    }

    @Test
    public void testDegree() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        assertEquals(1, graph.degree("A"));
        assertEquals(1, graph.degree("B"));
        assertEquals(0, graph.degree("C"));
    }

    @Test
    public void testVertices() {
        graph.addVertex("A");
        graph.addVertex("B");
        Set<String> vertices = (Set<String>) graph.vertices();
        assertTrue(vertices.contains("A"));
        assertTrue(vertices.contains("B"));
    }

    @Test
    public void testAdjacentVertices() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        Set<String> adjVertices = (Set<String>) graph.adjacentVertices("A");
        assertTrue(adjVertices.contains("B"));
        assertFalse(adjVertices.contains("C"));
    }

    @Test
    public void testEmpty() {
        assertTrue(graph.empty());
        graph.addVertex("A");
        assertFalse(graph.empty());
    }

    @Test
    public void testClear() {
        graph.addVertex("A");
        graph.addEdge("A", "B");
        assertFalse(graph.empty());
        graph.clear();
        assertTrue(graph.empty());
    }

    @Test
    public void testAddVertex() {
        graph.addVertex("A");
        assertTrue(graph.containsVertex("A"));
    }

    @Test
    public void testAddEdge() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        assertTrue(graph.containsEdge("A", "B"));
    }

    @Test
    public void testRemoveVertex() {
        graph.addVertex("A");
        graph.addEdge("A", "B");
        assertTrue(graph.containsVertex("A"));
        graph.removeVertex("A");
        assertFalse(graph.containsVertex("A"));
    }

    @Test
    public void testRemoveEdge() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        assertTrue(graph.containsEdge("A", "B"));
        graph.removeEdge("A", "B");
        assertFalse(graph.containsEdge("A", "B"));
    }
}