package by.rublevskaya.grapheditor.iterator;

import java.util.Iterator;

public class UndirectedGraphIterator<U> implements Iterator<U> {
    private final Iterator<U> iterator;

    public UndirectedGraphIterator(Iterator<U> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public U next() {
        return iterator.next();
    }

    @Override
    public void remove() {
        iterator.remove();
    }
}