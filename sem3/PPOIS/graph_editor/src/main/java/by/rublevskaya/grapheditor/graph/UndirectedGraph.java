package by.rublevskaya.grapheditor.graph;

import by.rublevskaya.grapheditor.util.Pair;

import java.util.Iterator;
import java.util.Objects;

public class UndirectedGraph<T> extends GraphBase<T> {
    private final GraphIterators<T> iterators;

    public UndirectedGraph() {
        super();
        this.iterators = new GraphIterators<>(this);
    }

    public UndirectedGraph(UndirectedGraph<T> other) {
        super(other);
        this.iterators = new GraphIterators<>(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UndirectedGraph<?> that = (UndirectedGraph<?>) o;
        return edgeCount == that.edgeCount && Objects.equals(adjacencyList, that.adjacencyList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adjacencyList, edgeCount);
    }

    public boolean notEquals(UndirectedGraph<T> other) {
        return !this.equals(other);
    }

    public boolean isGreaterThan(UndirectedGraph<T> other) {
        return this.edgeCount > other.edgeCount ||
                (this.edgeCount == other.edgeCount && this.vertexCount() > other.vertexCount());
    }

    public boolean isLessThan(UndirectedGraph<T> other) {
        return this.edgeCount < other.edgeCount ||
                (this.edgeCount == other.edgeCount && this.vertexCount() < other.vertexCount());
    }

    public boolean isGreaterOrEqual(UndirectedGraph<T> other) {
        return this.isGreaterThan(other) || this.equals(other);
    }

    public boolean isLessOrEqual(UndirectedGraph<T> other) {
        return this.isLessThan(other) || this.equals(other);
    }

    public Iterator<T> iterator() {
        return iterators.vertexIterator();
    }

    public Iterator<T> reverseVertexIterator() {
        return iterators.reverseVertexIterator();
    }

    public Iterator<Pair<T, T>> edgeIterator() {
        return iterators.edgeIterator();
    }

    public Iterator<Pair<T, T>> reverseEdgeIterator() {
        return iterators.reverseEdgeIterator();
    }

    public Iterator<T> adjacentVertexIterator(T vertex) {
        return iterators.adjacentVertexIterator(vertex);
    }

    public Iterator<T> reverseAdjacentVertexIterator(T vertex) {
        return iterators.reverseAdjacentVertexIterator(vertex);
    }

    public void removeVertex(Iterator<T> vertexIterator) {
        iterators.removeVertex(vertexIterator);
    }

    public void removeEdge(Iterator<Pair<T, T>> edgeIterator) {
        iterators.removeEdge(edgeIterator);
    }

    public Iterable<Pair<T, T>> edges() {
        return iterators.edges();
    }
}