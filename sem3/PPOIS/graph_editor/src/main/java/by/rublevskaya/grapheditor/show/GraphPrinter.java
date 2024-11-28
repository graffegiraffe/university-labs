package by.rublevskaya.grapheditor.show;

import by.rublevskaya.grapheditor.graph.UndirectedGraph;
import by.rublevskaya.grapheditor.util.IteratorUtils;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GraphPrinter {
    public static <T extends Comparable<T>> void printGraph(PrintStream out, UndirectedGraph<T> graph) {
        List<T> vertices = new ArrayList<>();
        IteratorUtils.forEach(graph.vertices(), vertices::add);
        Collections.sort(vertices);
        List<String> edges = new ArrayList<>();
        IteratorUtils.forEach(graph.edges(), edge -> edges.add(edge.toString()));
        Collections.sort(edges);

        for (T vertex : vertices) {
            out.println("Vertex: " + vertex);
        }
        for (String edge : edges) {
            out.println("Edge: " + edge);
        }
    }
}