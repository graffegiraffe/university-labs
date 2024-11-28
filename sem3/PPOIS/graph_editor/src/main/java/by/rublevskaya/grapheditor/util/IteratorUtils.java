package by.rublevskaya.grapheditor.util;

import java.util.Iterator;

public final class IteratorUtils {
    @FunctionalInterface
    public interface ElementProcessor<T> {
        void process(T element);
    }

    public static <T> void forEach(Iterator<T> iterator, ElementProcessor<T> processor) {
        while (iterator.hasNext()) {
            processor.process(iterator.next());
        }
    }

    public static <T> void forEach(Iterable<T> iterable, ElementProcessor<T> processor) {
        for (T element : iterable) {
            processor.process(element);
        }
    }
}