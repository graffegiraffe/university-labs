package by.rublevskaya.grapheditor;

import by.rublevskaya.grapheditor.show.GraphPrinter;
import by.rublevskaya.grapheditor.graph.UndirectedGraph;
import by.rublevskaya.grapheditor.util.IteratorUtils;
import by.rublevskaya.grapheditor.util.Pair;

import java.util.Iterator;

public class Runner {
    public static void main(String[] args) {
        UndirectedGraph<String> graph = new UndirectedGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");

        System.out.println("Vertices: " + graph.vertices());
        System.out.println("Edges: " + graph.edges());
        System.out.println("Contains vertex A: " + graph.containsVertex("A"));
        System.out.println("Contains edge A-B: " + graph.containsEdge("A", "B"));
        GraphPrinter.printGraph(System.out, graph);

        System.out.println("\nIterating vertices:");
        IteratorUtils.forEach(graph.vertices(), vertex -> System.out.println("Vertex: " + vertex));

        System.out.println("\nIterating edges:");
        IteratorUtils.forEach(graph.edges(), edge -> System.out.println("Edge: " + edge));

        System.out.println("\nIterating vertices in reverse order:");
        Iterator<String> reverseVertexIterator = graph.reverseVertexIterator();
        while (reverseVertexIterator.hasNext()) {
            System.out.println("Vertex: " + reverseVertexIterator.next());
        }

        System.out.println("\nIterating edges in reverse order:");
        Iterator<Pair<String, String>> reverseEdgeIterator = graph.reverseEdgeIterator();
        while (reverseEdgeIterator.hasNext()) {
            Pair<String, String> edge = reverseEdgeIterator.next();
            System.out.println("Edge: " + edge);
        }

        System.out.println("\nIterating adjacent vertices of 'A':");
        Iterator<String> adjacentVerticesIterator = graph.adjacentVertexIterator("A");
        while (adjacentVerticesIterator.hasNext()) {
            System.out.println("Adjacent Vertex: " + adjacentVerticesIterator.next());
        }

        System.out.println("\nIterating adjacent vertices of 'A' in reverse order:");
        Iterator<String> reverseAdjacentVerticesIterator = graph.reverseAdjacentVertexIterator("A");
        while (reverseAdjacentVerticesIterator.hasNext()) {
            System.out.println("Adjacent Vertex: " + reverseAdjacentVerticesIterator.next());
        }

        System.out.println("\nRemoving vertex 'A':");
        graph.removeVertex("A");
        IteratorUtils.forEach(graph.vertices(), vertex -> System.out.println("Vertex: " + vertex));

        System.out.println("\nRemoving edge 'B-C':");
        graph.removeEdge("B", "C");
        if (!graph.edges().iterator().hasNext()) {
            System.out.println("Edge: []");
        } else {
            IteratorUtils.forEach(graph.edges(), edge -> System.out.println("Edge: " + edge));
        }
    }
}