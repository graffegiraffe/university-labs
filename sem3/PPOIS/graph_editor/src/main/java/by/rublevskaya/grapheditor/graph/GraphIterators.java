package by.rublevskaya.grapheditor.graph;

import by.rublevskaya.grapheditor.iterator.UndirectedGraphIterator;
import by.rublevskaya.grapheditor.util.Pair;

import java.util.*;

public class GraphIterators<T> {
    private final GraphBase<T> graphBase;

    public GraphIterators(GraphBase<T> graphBase) {
        this.graphBase = graphBase;
    }

    public Iterator<T> vertexIterator() {
        return new UndirectedGraphIterator<>(graphBase.adjacencyList.keySet().iterator());
    }

    public Iterator<T> reverseVertexIterator() {
        List<T> vertices = new ArrayList<>(graphBase.adjacencyList.keySet());
        Collections.reverse(vertices);
        return new UndirectedGraphIterator<>(vertices.iterator());
    }

    public Iterator<Pair<T, T>> edgeIterator() {
        return new UndirectedGraphIterator<>(edges().iterator());
    }

    public Iterator<Pair<T, T>> reverseEdgeIterator() {
        List<Pair<T, T>> edges = new ArrayList<>((Collection<? extends Pair<T, T>>) edges());
        Collections.reverse(edges);
        return new UndirectedGraphIterator<>(edges.iterator());
    }

    public Iterator<T> adjacentVertexIterator(T vertex) {
        if (graphBase.containsVertex(vertex)) {
            return new UndirectedGraphIterator<>(graphBase.adjacencyList.get(vertex).iterator());
        }
        return Collections.emptyIterator();
    }

    public Iterator<T> reverseAdjacentVertexIterator(T vertex) {
        if (graphBase.containsVertex(vertex)) {
            List<T> adjacents = new ArrayList<>(graphBase.adjacencyList.get(vertex));
            Collections.reverse(adjacents);
            return new UndirectedGraphIterator<>(adjacents.iterator());
        }
        return Collections.emptyIterator();
    }

    public Iterable<Pair<T, T>> edges() {
        Set<Pair<T, T>> edges = new HashSet<>();
        for (T vertex : graphBase.adjacencyList.keySet()) {
            for (T adj : graphBase.adjacencyList.get(vertex)) {
                if (vertex.hashCode() <= adj.hashCode()) {
                    edges.add(new Pair<>(vertex, adj));
                }
            }
        }
        return edges;
    }

    public void removeVertex(Iterator<T> vertexIterator) {
        if (vertexIterator.hasNext()) {
            T vertex = vertexIterator.next();
            vertexIterator.remove();
            if (graphBase.containsVertex(vertex)) {
                graphBase.removeVertex(vertex);
            }
        }
    }

    public void removeEdge(Iterator<Pair<T, T>> edgeIterator) {
        if (edgeIterator.hasNext()) {
            Pair<T, T> edge = edgeIterator.next();
            graphBase.removeEdge(edge.getFirst(), edge.getSecond());
        }
    }
}