package by.rublevskaya.grapheditor.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class GraphBase<T> {
    protected final Map<T, Set<T>> adjacencyList;
    protected int edgeCount;

    protected GraphBase() {
        this.adjacencyList = new HashMap<>();
        this.edgeCount = 0;
    }

    protected GraphBase(GraphBase<T> other) {
        this.adjacencyList = new HashMap<>(other.adjacencyList);
        this.edgeCount = other.edgeCount;
    }

    public boolean containsVertex(T vertex) {
        return adjacencyList.containsKey(vertex);
    }

    public boolean containsEdge(T vertex1, T vertex2) {
        return adjacencyList.containsKey(vertex1) && adjacencyList.get(vertex1).contains(vertex2);
    }

    public int vertexCount() {
        return adjacencyList.size();
    }

    public int edgeCount() {
        return edgeCount;
    }

    public int degree(T vertex) {
        if (containsVertex(vertex)) {
            return adjacencyList.get(vertex).size();
        }
        return 0;
    }

    public Iterable<T> vertices() {
        return adjacencyList.keySet();
    }

    public Iterable<T> adjacentVertices(T vertex) {
        if (containsVertex(vertex)) {
            return adjacencyList.get(vertex);
        }
        return Set.of();
    }

    public boolean empty() {
        return adjacencyList.isEmpty();
    }

    public void clear() {
        adjacencyList.clear();
        edgeCount = 0;
    }

    public void addVertex(T vertex) {
        adjacencyList.putIfAbsent(vertex, new HashSet<>());
    }

    public void addEdge(T vertex1, T vertex2) {
        addVertex(vertex1);
        addVertex(vertex2);
        if (!adjacencyList.get(vertex1).contains(vertex2)) {
            adjacencyList.get(vertex1).add(vertex2);
            adjacencyList.get(vertex2).add(vertex1);
            edgeCount++;
        }
    }

    public void removeVertex(T vertex) {
        if (containsVertex(vertex)) {
            Set<T> adjacentVertices = adjacencyList.get(vertex);
            for (T adjVertex : adjacentVertices) {
                adjacencyList.get(adjVertex).remove(vertex);
                edgeCount--;
            }
            adjacencyList.remove(vertex);
        }
    }

    public void removeEdge(T vertex1, T vertex2) {
        if (containsEdge(vertex1, vertex2)) {
            adjacencyList.get(vertex1).remove(vertex2);
            adjacencyList.get(vertex2).remove(vertex1);
            edgeCount--;
        }
    }
}